#include "fonctions_SDL.h"


SDL_Texture* charger_image (const char* nomfichier, SDL_Renderer* renderer)
{
    SDL_Surface* surface = SDL_LoadBMP(nomfichier);
    if (surface == NULL) {return NULL;}
    SDL_Texture* texture = SDL_CreateTextureFromSurface(renderer, surface);
    SDL_FreeSurface(surface);
    return texture;
}

SDL_Texture* charger_image_transparente(const char* nomfichier, SDL_Renderer* renderer, Uint8 r, Uint8 g, Uint8 b) {
  // Chargement de l'image
  SDL_Surface* surface = SDL_LoadBMP(nomfichier);
  if (surface == NULL) {return NULL;}

  // Définition de la couleur transparente
  SDL_PixelFormat* format = surface->format;
  Uint32 colorkey = SDL_MapRGB(format, r, g, b);
  SDL_SetColorKey(surface, SDL_TRUE, colorkey);

  // Création de la texture
  SDL_Texture* texture = SDL_CreateTextureFromSurface(renderer, surface);
  SDL_FreeSurface(surface);

  return texture;
}

SDL_Texture* charger_texte(const char* message, SDL_Renderer* renderer, TTF_Font *font, SDL_Color color)
{
    // Écrire le texte sur une surface SDL
    SDL_Surface* surface = TTF_RenderText_Solid(font, message, color) ;
    if (surface == NULL) {return NULL;}
    SDL_Texture* texture = SDL_CreateTextureFromSurface(renderer, surface);
    SDL_FreeSurface(surface);
    return texture;
}