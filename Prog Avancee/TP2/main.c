#include "fonctions_fichiers.h"
#include <SDL2/SDL.h>
#include <stdbool.h>


SDL_Texture* charger_image (const char* nomfichier, SDL_Renderer* renderer)
{
    SDL_Surface* surface = SDL_LoadBMP(nomfichier);
    if (surface == NULL) {return NULL;}
    SDL_Texture* texture = SDL_CreateTextureFromSurface(renderer, surface);
    SDL_FreeSurface(surface);
    return texture;
}

void calculate_map(int* gY, int* t1X, int* t2X, int* tY, int* oX, int* oY)
{
    int* nbLig = malloc(sizeof(int));
    int* nbCol = malloc(sizeof(int));

    *nbLig = 0;
    *nbCol = 0;

    taille_fichier("resources/terrain.txt", nbLig, nbCol);
    char** tab = lire_fichier("resources/terrain.txt");

    for(int i = 0; i < *nbLig; i++)
    {
        for(int j = 0; j < *nbCol; j++)
        {
            switch (tab[i][j])
            {
            case '1':
                *gY = (*gY < 0) ? (1000 / *nbLig) * i : *gY;
                break;
            case '2':
                *oX = (*oX < 0) ? (1920 / *nbCol) * j : *oX;
                *oY = (*oY < 0) ? (1000 / *nbLig) * i : *oY;
                break;
            case '3':
                if(*t1X < 0)
                {
                    *t1X = (*t1X < 0) ? (1920 / *nbCol) * j : *t1X;
                } else {
                    *t2X = (*t2X < 0) ? (1920 / *nbCol) * j : *t2X;
                }
            default:
                break;
            }
        }
    }

    free(nbLig);
    free(nbCol);
}

int main(void)
{
    SDL_Window* fenetre; // Déclaration de la fenêtre
    SDL_Event evenements; // Événements liés à la fenêtre
    bool terminer = false;
    // Créer la fenêtre
    fenetre = SDL_CreateWindow("Fenetre SDL", SDL_WINDOWPOS_CENTERED, SDL_WINDOWPOS_CENTERED, 1920, 1000, SDL_WINDOW_RESIZABLE);
    // Mettre en place un contexte de rendu de l’écran
    SDL_Renderer* ecran;
    ecran = SDL_CreateRenderer(fenetre, -1, SDL_RENDERER_ACCELERATED);
    // Charger l’image
    SDL_Texture* background = charger_image( "resources/background.bmp", ecran );
    SDL_Texture* grass = charger_image("resources/grass.bmp", ecran);
    SDL_Texture* owl = charger_image("resources/owl.bmp", ecran);
    SDL_Texture* tree = charger_image("resources/tree.bmp", ecran);
    SDL_Texture* leaves = charger_image("resources/leaves.bmp", ecran);


    int grassX = 0, grassY = -1;
    int tree1X = -1, treeY = -1;
    int tree2X = -1000;
    int owlX = -1, owlY = -1;

    calculate_map(&grassY, &tree1X, &tree2X, &treeY, &owlX, &owlY);

    SDL_Rect grassR;
    grassR.x = grassX;
    grassR.y = grassY;
    grassR.w = 1920;
    grassR.h = 1000;

    SDL_Rect owlR;
    owlR.x = owlX;
    owlR.y = owlY;
    owlR.w = 100;
    owlR.h = 100;


    SDL_Rect tree1;
    tree1.x = tree1X;
    tree1.y = 50;
    tree1.w = 100;
    tree1.h = grassY-50;

    SDL_Rect tree2;
    tree2.x = tree2X;
    tree2.y = 50;
    tree2.w = 100;
    tree2.h = grassY-50;

    SDL_Rect leaves1;
    leaves1.x = tree1.x-100;
    leaves1.y = 0;
    leaves1.w = 300;
    leaves1.h = 200;

    SDL_Rect leaves2;
    leaves2.x = tree2.x-100;
    leaves2.y = 0;
    leaves2.w = 300;
    leaves2.h = 200;

    // Boucle principale
    while(!terminer){
        SDL_RenderClear(ecran);
        SDL_RenderCopy(ecran, background, NULL, NULL);
        SDL_RenderCopy(ecran, grass, NULL, &grassR);
        SDL_RenderCopy(ecran, owl, NULL, &owlR);
        SDL_RenderCopy(ecran, tree, NULL, &tree1);
        SDL_RenderCopy(ecran, tree, NULL, &tree2);
        SDL_RenderCopy(ecran, leaves, NULL, &leaves1);
        SDL_RenderCopy(ecran, leaves, NULL, &leaves2);
        SDL_PollEvent( &evenements );
        switch(evenements.type)
        {
            case SDL_QUIT:
            terminer = true; break;
            case SDL_KEYDOWN:
            switch(evenements.key.keysym.sym)
            {
                case SDLK_ESCAPE:
                case SDLK_q:
                terminer = true; break;
            }
        }
        SDL_RenderPresent(ecran);
    }
    // Quitter SDL
    SDL_DestroyTexture(background);
    SDL_DestroyRenderer(ecran);
    SDL_DestroyWindow(fenetre);
    SDL_Quit();
    return 0;
}
