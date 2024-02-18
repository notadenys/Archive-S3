#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<math.h>
#include "tree_primitives.h"

#define MIN(a,b) (((a)<(b))?(a):(b))
#define MAX(a,b) (((a)>(b))?(a):(b))


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
  a->height = 0 ;
  return a ; 
}

int is_empty(tree_t a)
{
  /* Dans le cas où la restitution de l'arbre vide serait implémentée
   * différemment, le test de vacuité serait différent : il faudrait tester les
   * pointeurs de a*/
  return a == NULL;
}

s_base_t value(tree_t a)
{
	if(is_empty(a)) 
	{
		s_base_t s = {" ", " ", -1};
		return s;
	}
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
    free(a);
  }
}

/* Parcours préfixe : donnée préfixe(fils_gauche) préfixe(fils_droit) */
void prefix(tree_t a)
{
  if (!is_empty(a)) {
    print(value(a));
    prefix(left(a));
    prefix(right(a));
  }
}

/* Parcours infixe :  préfixe(fils_gauche) donnée préfixe(fils_droit) */
void infix(tree_t a)
{
  if (!is_empty(a)) {
    infix(left(a));
    print(value(a));
    infix(right(a));
  }
}

/* On passe la profondeur en paramètre pour obtenir une indentation cohérente */
void graphical_print(tree_t a, int depth)
{
if (!is_empty(a)) {
    for(int i=0;i<depth;i++) {
      printf(" ");
    }
    printf("+--");
    print(value(a));
    printf("\n");
    graphical_print(left(a),depth+3);
    graphical_print(right(a),depth+3);
  }
}

/* Affichage avec mise en forme "graphique" (indentation ET LIEN en fonction de la profondeur) 
   - la trace enregistre la direction: 0 Left, 1 Right
*/
void graphical_print_link(tree_t a, int depth, int trace[])
{
  if(!is_empty(a)){
    // Appels récursifs sur le fils gauche, en augmentant le décalage, en précisant qu'on va à gauche
    trace[depth]=0;
    graphical_print_link(left(a), depth+1, trace);
    // affichage de la racine 
    for(int i=0; i<depth; ++i){
      // si profondeur > 0 et changement de direction (dans la filiation) il faut afficher le lien
      if(i>0 && trace[i] != trace[i-1]){
        printf("|");
      }else{
        printf(" ");
      }
      // completer avec des espaces
      printf("  ");
    }
    printf("@----");
    print(value(a));
    printf("\n");
    // Appels récursifs sur le fils gauche, en augmentant le décalage, en précisant qu'on va à droite
    trace[depth]=1;
    graphical_print_link(right(a), depth+1, trace);
  }else{
    for(int i=0; i<depth; ++i){
      // si profondeur > 0 et changement de direction (dans la filiation) il faut afficher le lien
      if(i>0 && trace[i] != trace[i-1]){
        printf("|");
      }else{
        printf(" ");
      }
      // completer avec des espaces
      printf("  ");
    }
    printf("  \n");
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

/****************************************************
 * Partie specifique aux arbres ordones
 *****************************************************/

// q1
/* Inserer val a la bonne position dans l'arbre */
void insert(tree_t* a, s_base_t val){
  if(is_empty(*a))
  {
    *a = cons(val, cons_empty(), cons_empty());
  } else {
    if(compare(val, value(*a)) > 0)
    {
      tree_t a_temp = right(*a);
      insert(&a_temp, val);
      change_right(*a, a_temp);
    } else {
      tree_t a_temp = left(*a);
      insert(&a_temp, val);
      change_left(*a, a_temp);
    }
  }
  return;
}

// q2
/* Chercher un agent (le nom dans pers) */
s_base_t search(tree_t a, s_base_t pers){
  s_base_t res={" "," ",-1};
  if (is_empty(a))
  {
    return res;
  }
  // L'arbre n'est pas vide.
  if (compare(pers, value(a)) == 0)
  {
    res = value(a);
  }
  else if (compare(pers, value(a)) > 0)
  {
    res = search(right(a), pers);
  }
  else
  {
    res = search(left(a), pers);
  }
  return res;
}

// q3
s_base_t remove_max(tree_t* a)
{
	s_base_t max = {" ", " ", -1};
	if (is_empty(right(*a)))
  	{
		max = value(*a);
		*a = left(*a);
  	}
	else
	{
		tree_t a_temp = right(*a);
		max = remove_max(&a_temp);
		change_right(*a, a_temp);
	}
	return max;
}

/* Supprimer un agent (le nom dans val) */
void remove_value(tree_t *a, s_base_t val){
	if (!is_empty(*a))
	{
		if (compare(value(*a), val) > 0)
		{
			tree_t a_temp = left(*a);
			remove_value(&a_temp, val);
			change_left(*a, a_temp);
		} 
		else if (compare(value(*a), val) < 0)
		{
			tree_t a_temp = right(*a);
			remove_value(&a_temp, val);
			change_right(*a, a_temp);
		} 
		else if (compare(value(*a), val) == 0)
		{
			if(is_empty(left(*a)) && is_empty(right(*a)))
			{
				*a = cons_empty();
				free_tree(*a);
			} 
			else if (is_empty(left(*a)))
			{
				*a = right(*a);
			}
			else if (is_empty(right(*a)))
			{
				*a = left(*a);
			} else {
				tree_t fg = left(*a);
				s_base_t max = remove_max(&fg);
				change_value(*a, max);
				change_left(*a, fg);
			}
			
		}
	}
}

// q4
void turn_right(tree_t* a)
{
	if (!is_empty(*a))
	{
		tree_t fg = left(*a);
		change_left(*a, right(fg));
		change_right(fg, *a);
		*a = fg;
	}
}

void turn_left(tree_t* a)
{
	if (!is_empty(*a))
	{
		tree_t fd = right(*a);
		change_right(*a, left(fd));
		change_left(fd, *a);
		*a = fd;
	}
}

/* Inserer val pour recherche efficace */
void insert_AVL(tree_t* a, s_base_t val)
{
	if (is_empty(*a))
	{
		*a = cons(val, cons_empty(), cons_empty());
	}
	else if (compare(val, value(*a)) > 0) 
	{
		tree_t a_temp = right(*a);
      	insert_AVL(&a_temp, val);
    	change_right(*a, a_temp);
	}
	else
	{
		tree_t a_temp = left(*a);
      	insert_AVL(&a_temp, val);
    	change_left(*a, a_temp);
	}
	int h_diff = height(left(*a)) - height(right(*a));
	if (h_diff > 1)
	{
		if (compare(val, value(left(*a))) >= 0)
		{
			turn_right(a);
		}
		else
		{
			tree_t fg = left(*a);
			turn_left(&fg);
			change_left(*a, fg);
			turn_right(a);
		} 
	}
	else  if (h_diff < -1)
	{
		graphical_print(*a, height(*a));
		if (compare(val, value(left(*a))) >= 0)
		{
			turn_left(a);
		}
		else
		{
			tree_t fd = right(*a);
			turn_right(&fd);
			change_right(*a, fd);
			turn_left(a);
		}
	}
}

// q5
// SOLUTION
void cut(tree_t* a, s_base_t val, tree_t* inf, tree_t* sup)
{
	if(is_empty(*a))
	{
		*inf = cons_empty();
		*sup = cons_empty(); 
	}
	else if (compare(val, value(*a)) < 0)
	{
		tree_t ginf = cons_empty();
		tree_t gsup = cons_empty();
		tree_t fg = left(*a);
		cut(&fg, val, &ginf, &gsup);
		change_left(*a, fg);
		*inf = ginf;
		sup = a;
		change_left(*sup, gsup);
	}
	else
	{
		tree_t dinf = cons_empty();
		tree_t dsup = cons_empty();
		tree_t fd = right(*a);
		cut(&fd, val, &dinf, &dsup);
		change_right(*a, fd);
		*sup = dsup;
		inf = a;
		change_right(*inf, dinf);
	}
}

/* Optionnels */
void insert_2_root(tree_t* a, s_base_t val)
{
	if (is_empty(*a))
	{
		*a = cons(val, cons_empty(), cons_empty());
	}
	else
	{
		tree_t inf = cons_empty();
		tree_t sup = cons_empty();
		cut(a, val, &inf, &sup);
		*a = cons(val, inf, sup);
	}
}
void insert_by_rotate(tree_t* a, s_base_t val){}
  
 


