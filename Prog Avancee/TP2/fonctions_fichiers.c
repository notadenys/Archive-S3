#include "fonctions_fichiers.h"

/*
 allouer un tableau de caractères de taille n × m, 
 où n et m sont les nombres de lignes et de colonnes 
 et initialiser ce tableau avec le caractère espace (’ ’).
*/
char** allocate_tab_2D(int n, int m)
{
    char** tab = (char**)malloc(n * sizeof(char*));
    for (int i = 0; i < n; i++)
        tab[i] = (char*)malloc(m * sizeof(char));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            tab[i][j] = ' ';
        }
    }
    return tab;
}

/*
 libérer un tableau à deux dimensions, de n lignes.
*/
void desallouer_tab_2D(char** tab, int n) {
    for (int i = 0; i < n; ++i) {
        free(tab[i]);
    }
}

/*
 afficher le contenu d’un tableau de caractères de taille n × m.
*/
void afficher_tab_2D(char** tab, int n, int m) {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            printf("%c", tab[i][j]);
        }
        printf("\n");
    }
}

/*
 compter le nombre max de lignes (nbLig) et de colonnes (nbCol) dans le fichier dont le nom est nomFichier
*/
void taille_fichier(const char* nomFichier, int* nbLig, int* nbCol) {

    FILE* fichier = NULL;
    fichier = fopen(nomFichier, "r");
    int c;
    int max = 0;

    if (fichier != NULL) {
        do {
            c = fgetc(fichier);
            if (c == '\n' || c == '\r' || c == EOF) {
                if (*nbCol > max) {
                    max = *nbCol;
                }
                (*nbLig)++;
                (*nbCol) = 0;
                if (c == '\r') {
                    c = fgetc(fichier);
                }
            } else {
                (*nbCol)++;
            }

        } while (c != EOF);

        *nbCol = max;
        fclose(fichier);
    } else {
        printf("fichier introuvable");
    }
}


/*
 lire un fichier dont le nom est nomFichier
 et retourner le tableau qui contient les caractères du fichier
 tel qu’une ligne du tableau correspond à une ligne du fichier.
*/
char** lire_fichier(const char* nomFichier) {
    int ligne = 0;
    int colonne = 0;

    int lig = 0;
    int col = 0;

    taille_fichier(nomFichier, &ligne, &colonne);

    char** tab = allocate_tab_2D(ligne, colonne);

    FILE* fichier = NULL;
    fichier = fopen(nomFichier, "r");
    int c;
    if (fichier != NULL) {
        do {
            c = fgetc(fichier);
            if (c == '\n' || c == '\r' || c == EOF) {
                lig++;
                col = 0;
                if (c == '\r') {
                    c = fgetc(fichier);
                }
            } else {
                //printf("%d,%d,%c \n",lig,col,c);
                tab[lig][col] = c;
                col++;
            }
        } while (c != EOF);
        fclose(fichier);
    } else {
        printf("fichier introuvable ??????");
    }
    return tab;
}
