#include<stdio.h>
#include<stdlib.h>
#include <math.h>
#include "tree_primitives.h"

tree_t cons_empty()
{
  	return NULL;
}

tree_t cons(s_base_t v, tree_t fg, tree_t fd)
{
	tree_t tree = malloc(sizeof(s_node_t));
	tree->val = v;
	tree->fd = fd;
	tree->fg = fg;
	return tree; 
}

int is_empty(tree_t a)
{
  	return a == NULL;
}

s_base_t value(tree_t a)
{
  	return a->val;
}

tree_t left(tree_t a)
{
  	return a->fg;
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
	if(!is_empty(a))
	{
		free_tree(right(a));
		free_tree(left(a));
		free(a);
	}
}

void prefix(tree_t a)
{
	if(!is_empty(a)){
		print(value(a));
		prefix(right(a));
		prefix(left(a));
	}
}

void graphical_print(tree_t a, int depth)
{
	if(!is_empty(a))
	{
		for(int i = 0; i < depth; i++)
		{
			printf(" ");
		}
		printf("+--");
		print(value(a));
		printf("\n");
		graphical_print(left(a), depth+3);
		graphical_print(right(a), depth+3);
	}
}

void graphical_print_link(tree_t a, int depth, int trace[])
{
	if (!is_empty(a))
	{
		trace[depth] = 0;
		graphical_print_link(left(a), depth + 1, trace);
		for (int i = 0; i < depth; i++)
		{
			if (i > 0 && trace[i] != trace[i - 1])
			{
				printf("|");
			}
			else
			{
				printf(" ");
			}
			printf("  ");
		}
		printf("+--");
		print(value(a));
		printf("\n");
		trace[depth] = 1;
		graphical_print_link(right(a), depth + 1, trace);
	}
}

int size(tree_t a)
{
	if(is_empty(a))
	{
		return 0;
	}
  	return 1 + size(right(a)) + size(left(a));
}

int height(tree_t a)
{
	if(is_empty(a))
	{
		return 0;
	}
	int hd = height(right(a));
	int hg = height(left(a));
  	return 1 + ((hd > hg) ? hd : hg);
}

int exists(tree_t a, s_base_t v)
{
	if (is_empty(a))
	{
		return 0;
	}
	if (!compare(value(a), v))
	{
		return 1;
	}
	if (exists(left(a), v))
	{
		return 1;
	}
	if (exists(right(a), v))
	{
		return 1;
	}
	return 0;
}
