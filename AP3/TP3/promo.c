#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include "tree_primitives.h"

int main()
{
  s_base_t jb = {"James","Bond",1942};
  s_base_t bo = {"Jason","Bourne",1982};
  s_base_t ap = {"Austin","Powers",1977};
  s_base_t je = {"Johnny","English",1983};
  s_base_t eh = {"Ethan","Hunt",1976};
  s_base_t jt = {"Joseph","Turner",1955};
  s_base_t dp = {"Dummy","Person",1922};

  tree_t Ajb = cons_empty();

  printf("INSERT --\n");
  insert(&Ajb,jb);
  insert(&Ajb,bo);
  insert(&Ajb,ap);
  insert(&Ajb,je);
  insert(&Ajb,eh);
  insert(&Ajb,jt);
  infix(Ajb); 
  printf("--\n");
  /* graphical_print(Ajb,0); */
  int h = height(Ajb);
  int trace_Ajb[h];
  graphical_print_link(Ajb,0,trace_Ajb);
  printf("\n--------------------------------------\n");


  printf("SEARCH OK = ");
  print(search(Ajb,je));
  printf("\n");
  printf("SEARCH KO = ");
  print(search(Ajb,dp));
  printf("\n");

  printf("REMOVE --\n");
  remove_value(&Ajb,ap);
  remove_value(&Ajb,bo);
  remove_value(&Ajb,je);
  remove_value(&Ajb,jb);
  remove_value(&Ajb,eh);
  remove_value(&Ajb,jt);
  infix(Ajb);
  printf("\n");
  graphical_print(Ajb,0);

  printf("INSERT AVL --\n");
  insert_AVL(&Ajb,jb);
  insert_AVL(&Ajb,bo);
  insert_AVL(&Ajb,ap);
  insert_AVL(&Ajb,je);
  insert_AVL(&Ajb,eh);
  insert_AVL(&Ajb,jt);
  infix(Ajb);
  printf("\n");
  /* graphical_print(Ajb,0); */
  h = height(Ajb);
  graphical_print_link(Ajb,0,trace_Ajb);

  remove_value(&Ajb,ap);
  remove_value(&Ajb,bo);
  remove_value(&Ajb,je);
  remove_value(&Ajb,jb);
  remove_value(&Ajb,eh);
  remove_value(&Ajb,jt);
  infix(Ajb);
  printf("\n");
  /* graphical_print(Ajb,0); */
  h = height(Ajb);
  graphical_print_link(Ajb,0,trace_Ajb);

  printf("INSERT ROOT --\n");
  insert_2_root(&Ajb,bo);
  insert_2_root(&Ajb,ap);
  insert_2_root(&Ajb,je);
  insert_2_root(&Ajb,eh);
  insert_2_root(&Ajb,jt);
  insert_2_root(&Ajb,jb);
  infix(Ajb);
  printf("\n");
  /* graphical_print(Ajb,0); */
  h = height(Ajb);
  graphical_print_link(Ajb,0,trace_Ajb);

  remove_value(&Ajb,ap);
  remove_value(&Ajb,bo);
  remove_value(&Ajb,je);
  remove_value(&Ajb,jb);
  remove_value(&Ajb,eh);
  remove_value(&Ajb,jt);
  infix(Ajb);
  printf("\n");
  /* graphical_print(Ajb,0); */
  h = height(Ajb);
  graphical_print_link(Ajb,0,trace_Ajb);

  printf("INSERT ROTATE --\n");
  insert_by_rotate(&Ajb,bo);
  insert_by_rotate(&Ajb,ap);
  insert_by_rotate(&Ajb,je);
  insert_by_rotate(&Ajb,eh);
  insert_by_rotate(&Ajb,jt);
  insert_by_rotate(&Ajb,jb);
  infix(Ajb);
  printf("\n");
  /* graphical_print(Ajb,0); */
  h = height(Ajb);
  graphical_print_link(Ajb,0,trace_Ajb);

  free_tree(Ajb);
}
