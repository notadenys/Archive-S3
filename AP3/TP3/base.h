/**
 * Structure de données que l'arbre stockera.
 * Il suffit de remplacer base.{h,c} pour changer l'utilisation de l'arbre sans
 * changer ses primitives
 * le qualificateur const signifie que l'on a des constantes (plus exactement :
 * on dit au compilateur que la variable ne doit pas changer)
 */
typedef struct s_base{
  const char* prenom; 
  const char* nom; 
  int dateNaissance; 
} s_base_t;

/**
 * retourne
 * un entier <0 si t1<t2, 0 si si t1==t2, un entier >0 si t1>t2 
 */
int compare(s_base_t t1, s_base_t t2);

/**
 * affiche une donnee
 */
void print(s_base_t t);
