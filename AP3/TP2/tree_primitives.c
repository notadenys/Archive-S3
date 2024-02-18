#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<math.h>
#include <ctype.h>
#include "tree_primitives.h"

#define PI 3.14159265

tree_t cons_empty()
{
  /* Pas besoin d'allouer de la mémoire inutilement et de créer un nœud.
   * Retourner NULL suffit. */
  return NULL; 
}

tree_t cons(s_base_t v, tree_t fg, tree_t fd)
{
  /* On alloue uniquement la mémoire pour le nœud que l'on crée. v, fg et fd
   * ont déjà été créés, la mémoire a donc déjà été allouée.*/
  tree_t a = (tree_t) malloc(sizeof(s_node_t));
  /* affectations */
  a->val = v ;
  a->fg = fg;
  a->fd = fd ;
  return a ; 
}

int is_empty(tree_t a)
{
  /* Dans le cas où la restitution de l'arbre vide serait implémentée
   * différemment, le test de vacuité serait différent : il faudrait tester les
   * pointeurs de a */
  return a == NULL;
}

s_base_t value(tree_t a)
{
  return a->val;
}

tree_t left(tree_t a)
{
  return a->fg ; 
}

tree_t right(tree_t a)
{
  return a->fd; 
}

void change_value(tree_t pa, s_base_t new_value)
{
  pa->val = new_value; 
}

void change_left(tree_t pa, tree_t new_left)
{
  pa->fg = new_left; 
}

void change_right(tree_t pa, tree_t new_right)
{
  pa->fd = new_right; 
}

void free_tree(tree_t a)
{
  /* test indispensable pour traiter le cas de l'arbre vide à libérer */
  if(!is_empty(a)) {
  /* On libére d'abord récursivement la mémoire sur les fils gauche et
   * droit, puis sur le nœud lui-même. */
    free_tree(left(a));
    free_tree(right(a));
    //free(value(a).oper);
    free(a);
  }
}

/* Parcours préfixe : donnée préfixe(fils_gauche) préfixe(fils_droit) */
void prefix(tree_t a)
{
  /* Test pour traiter le cas de l'arbre vide */
  if (!is_empty(a)) {
    /* Affichage de la donnée de l'arbre */
    print(value(a));
    // Appels récursifs sur les fils gauche et droit
    prefix(left(a));
    prefix(right(a));
  }
}

/* On passe la profondeur en paramètre pour obtenir une indentation cohérente */
void graphical_print(tree_t a, int depth)
{
  if (!is_empty(a)) {
    // Appels récursifs sur le fils gauche, en augmentant le décalage
    graphical_print(left(a),depth+3);
    // affichage de la racine 
    for(int i=0;i<depth;i++) {
      printf(" ");
    }
    printf("@--"); 
    print(value(a));
    printf("\n");
    // Appels récursifs sur le fils droit, en augmentant le décalage
    graphical_print(right(a),depth+3);
  }
}


int size(tree_t a)
{
  int size_a=0;
  if (!is_empty(a)) {
    /* La taille d'un arbre correspond à la somme des tailles de ses fils + 1
     * (le nœud courrant. If faut donc faire deux appels récursifs. */
    size_a = 1 + size(left(a)) + size(right(a)); 
  }
  //Dans le cas de l'arbre vide, la taille vaut 0 (initialisation de size_a en
  //début de fonction)
  return size_a;
}

int height(tree_t a)
{
  int height_a=0;
  if (!is_empty(a)) {
    /* La hauteur d'un arbre correspond à 1 + le max des hauteurs de ses fils.
     * Il faut donc deux appels récursifs.
     * Comme il faut accéder deux fois à la hauteur d'un fils, on stocke les
     * hauteurs dans des variables pour plus d'efficacité.
     */
    int hfg=height(left(a));
    int hfd=height(right(a));
    height_a = 1+((hfg) > (hfd) ? hfg : hfd);
  }
  return height_a;
}

/* On peut aussi implémenter cette fonction en utilisant un _Bool. Mais on
 * tâche d'être cohérent si on a commencé à implémenter compare() sans _Bool
 * mais avec des int. */
int exists(tree_t a, s_base_t v)
{
  if (is_empty(a)) {
    return 0;
  } else {
    if (compare(value(a),v)==0) {
      return 1;
    } else {
      /* Appels récursifs : si on n'a pas trouvé v, il faut tester sa présence
       * dans les fils gauche et droit */
      return (exists(left(a),v) || exists(right(a),v));
    }
  }
}

tree_t copy_tree(tree_t tree)
{
	if (!is_empty(tree))
	{
		return cons(value(tree), copy_tree(left(tree)), copy_tree(right(tree)));
	}
	return cons_empty();
}




/****************************************************
 * Partie specifique aux expressions mathematiques
 *****************************************************/

void tree_print(tree_t a)
{
	if(!is_empty(a))
	{
		s_base_t rac = value(a);
		switch (rac.nature)
		{
			case Variable:
				print(rac);
				break;

			case Constante:
				printf("%d", rac.val);
				break;

			case Binaire:
				printf("(");
				tree_print(left(a));
				printf("%s", rac.oper);
				tree_print(right(a));
				printf(")");
				break;
			
			case Fonction:
				printf("%s", rac.oper);
				printf("(");
				tree_print(left(a));
				printf(")");
				break;

			default:
				break;
		}
	}
}

double evaluate(tree_t a, int val){
  double res=0;
  if (!is_empty(a))
  {
	s_base_t rac = value(a);
	double vg = evaluate(left(a), val);
	double vd = evaluate(right(a), val);
	char* op = rac.oper;
	switch (rac.nature)
	{
		case Variable:
			res = val;
			break;

		case Constante:
			res = rac.val;
			break;

		case Binaire:
			switch (op[0])
			{
				case '+':
					res = vg + vd;
					break;

				case '*':
					res = vg * vd;
					break;

				default:
					break;
			}
			break;

		case Fonction:
			if (!strcmp(op, "sin"))
			{
				res = sin(vg * 3.14159265358979 / 180);
			}
			else if (!strcmp(op, "cos"))
			{
				res = cos(vg * 3.14159265358979 / 180);
			}
			break;

		default:
			break;
		}
  }
  return res;
}

tree_t derivate(tree_t a){
  s_base_t cnst={Constante,0,""};
  tree_t res=cons(cnst,cons_empty(),cons_empty());
  if (!is_empty(a))
  {
	s_base_t rac = value(a);
	tree_t dg = derivate(left(a));
	tree_t dd = derivate(right(a));
	tree_t cg = copy_tree(left(a));
	tree_t cd = copy_tree(right(a));
	char* op = rac.oper;
	s_base_t plus = {Binaire, 0, "+"};
	s_base_t etoile = {Binaire, 0, "*"};
	s_base_t sin = {Fonction, 0, "sin"};
	s_base_t cos = {Fonction, 0, "cos"};
	switch (rac.nature)
	{
		case Variable:
			cnst.val = 1;
			res = cons(cnst, cons_empty(), cons_empty());
			break;

		case Constante:
			break;

		case Binaire:
			switch (op[0])
			{
				case '+':
					res = cons(plus, dg, dd);
					break;

				case '*':
					res = cons(plus, cons(etoile, dg, cd), cons(etoile, cg, dd));
					break;

				default:
					break;
			}
			break;

		case Fonction:
			if (!strcmp(op, "sin"))
			{
				res = cons(etoile, dg, cons(cos, cg, cons_empty()));
			}
			else if (!strcmp(op, "cos"))
			{
				res = cons(etoile, dg, cons(sin, cg, cons_empty()));
			}
			break;

		default:
			break;
		}
  }
  return res;
}

tree_t build_tree(const char* exp, int debut, int* fin){
	s_base_t cnst={Constante,0,""};
	tree_t res=cons(cnst,cons_empty(),cons_empty());
	char* oper = malloc(10*sizeof(char));
	char premier = exp[debut];
	if (isdigit(premier))
	{
		char ct[10];
        int i = 0;
        while (isdigit(exp[debut]))
        {
          ct[i++] = exp[debut++];
        }
        int nb = atoi(ct);
        cnst.val = nb;
        *fin = debut + i-1;
        res = cons(cnst, cons_empty(), cons_empty());
	}
	else if (isalpha(premier) && !isalpha(exp[debut+1]))
	{
		*fin = debut + 1;
		oper[0] = premier;
		oper[1] = '\0';
		s_base_t var={Variable,0,oper};
		res = cons(var, cons_empty(), cons_empty());
	}
	else if (premier == '(')
	{
		int fing, find;
		tree_t afg = build_tree(exp, debut+1, &fing);
		oper[0] = exp[fing];
		oper[1] = '\0';
		tree_t afd = build_tree(exp, fing + 1, &find);
		*fin = find + 1;
		s_base_t op = {Binaire, 0, oper};
		res = cons(op, afg, afd);
	}
	else
	{
		char *func = malloc(10 * sizeof(char));
        int i = 0;
        while (isalpha(exp[debut]))
        {
          func[i++] = exp[debut++];
        }
        func[i] = '\0';
		int fing;
		tree_t afg = build_tree(exp, debut+1, &fing);
		*fin = fing + 1;
		s_base_t oper = {Fonction, 0, func};
		res = cons(oper, afg, cons_empty());
	}
	return res;
}
