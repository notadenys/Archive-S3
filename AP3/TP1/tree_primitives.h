#include "base.h"

/*
 * Structure d'arbre = une structure de nœud comprenant :
 * - un fils gauche (fg) et un fils droit (fd), qui sont des pointeurs vers
 *   d'autres nœuds
 * - une donnée val de type s_base_t, défini dans base.h.
 *
 *   L'intérêt d'une telle implémentation est de complètement décorréler la 
 *   structure d'arbre des  données contenues (et donc leurs implémentations 
 *   respectives). L'implémentation des primitives est indépendante des données.
 *   Il suffit de remplacer base.{c,h} pour changer l'utilisation que l'on peut
 *   faire de cet arbre.
 *   On en profite pour définir s_node_t et tree_t (pointeur vers un s_node)
 *   pour pouvoir manipuler les arbres dans la suite sans chaque fois avoir à 
 *   rajouter le mot-clef struct.
 */

typedef struct s_node { 
  s_base_t val; 
  struct s_node *fg,*fd ;
} s_node_t, *tree_t ;

/* Traduction de la spécification en langage naturel (sujet de TP) en
 * spécification formelle (langage C, fichier .h) respectant les conventions de
 * nommage les plus courantes. */

/* Restitue un arbre vide */
tree_t cons_empty();

/* Restitue un arbre de racine v et dont les fils sont fg et fd */
tree_t cons(s_base_t v, tree_t fg, tree_t fd);

/* Vrai si et seulement si l'arbre est vide */
int is_empty(tree_t a);

/* Restitue la valeur du nœud racine, fonction aussi appelée Racine/root dans 
 * le sujet. 
 * L'arbre a n'est pas vide
*/
s_base_t value(tree_t a);

/* Restitue le fils gauche 
 * L'arbre a n'est pas vide
 */
tree_t left(tree_t a);

/* Restitue le fils droit 
 * L'arbre a n'est pas vide
*/
tree_t right(tree_t a);

/* Remplace la valeur de la racine pa par new_value.
 * L'arbre a n'est pas vide
*/
void change_value(tree_t pa, s_base_t new_value);

/* Remplace le fils gauche de pa par new_left.
 * L'arbre a n'est pas vide
*/
void change_left(tree_t pa, tree_t new_left);

/* Remplace le fils droit de pa par new_right.
 * L'arbre a n'est pas vide
*/
void change_right(tree_t pa, tree_t new_right);

/* Libère la mémoire allouée pour l'arbre a */
void free_tree(tree_t a);

/* Affichage préfixe simple de l'arbre */
void prefix(tree_t a);

/* Affichage avec mise en forme "graphique" (indentation en fonction de la profondeur) 
 * Par exemeple, pour un arbre complet avec 7 noueds on devrait obtenir un affichage de la forme
   +--racine
       +--fg
          +--fgfg
          +--fgfd
       +--fd
          +--fdfg
          +--fdfd
*/
void graphical_print(tree_t a, int depth);

/* Affichage avec mise en forme "graphique" (indentation ET LIEN en fonction de la profondeur) 
 * Par exemeple, pour un arbre complet avec 7 noueds on devrait obtenir un affichage de la forme
 *
         +--fgfg
      +--fg
      |  +--fgfd
   +--racine
      |  +--fdfg
      +--fd
         +--fdfd
 *
 * depth est utilisé pour indiquer la profondeur du (sous-) arbre qui sera affiché
 * trace est utilisé pour tracer les changements de direction dans la filiation 
 * (passer de fg à fd ou de fd à fg) et afficher les liens (avec "|")
*/
void graphical_print_link(tree_t a, int depth, int trace[]);

/* Retourne la taille de l'arbre a */
int size(tree_t a);

/* Retourne la hauteur de l'arbre a */
int height(tree_t a);

/* Teste l'existence de la donnée v dans l'arbre a */
int exists(tree_t a, s_base_t v);
