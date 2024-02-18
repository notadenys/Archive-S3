CC = gcc
CFLAGS = -W -Wall -ansi -std=c99
LDFLAGS =
EXEC = main
SRC = addition.c main.c
OBJ = $(SRC:.c=.o)
all: $(EXEC)
main: $(OBJ)
	$(CC) -o $@ $^ $(LDFLAGS)
addition.o: addition.c
	$(CC) -o $@ -c $< $(CFLAGS)
main.o: main.c addition.h
	$(CC) -o $@ -c $< $(CFLAGS)
clean:
	rm -rf *.o *~
mrproper: clean
	rm -rf $(EXEC)