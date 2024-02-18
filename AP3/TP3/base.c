#include<stdlib.h>
#include<stdio.h>
#include<string.h>
#include "base.h"

int compare(s_base_t t1, s_base_t t2){
  int cmpNom=strcmp(t1.nom,t2.nom);
  int cmpPrenom=strcmp(t1.prenom,t2.prenom);
  if(cmpNom==0){
    return cmpPrenom;
  }else{
    return cmpNom;
  }
}

void print(s_base_t t){
  printf("%s %s (%d)  ",t.nom, t.prenom,t.dateNaissance);
}
