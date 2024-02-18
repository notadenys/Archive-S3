CC = gcc

CFLAGS = -Wall -std=c99 -g #Common flags

#LIBS = -L./SDL2_ttf/.libs #Location of the library

LFLAGS = `sdl2-config --cflags --libs` 

#INCLUDES = -I./SDL2_ttf #Location of the header files

SRCS = main.c fonctions_fichiers.c

OBJS = $(SRCS:.c=.o)

BIN = main

$(BIN) : $(OBJS)
	$(CC) $(CFLAGS) $(INCLUDES) -o $(BIN) $^ $(LIBS) $(LFLAGS)

.c.o:
	$(CC) $(CFLAGS) $(INCLUDES) -c $< -o $@ $(LIBS) $(LFLAGS)

clean:
	$(RM) *.o *~ $(BIN)	