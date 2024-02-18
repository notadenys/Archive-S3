#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include "sort.h"

void print_tab(s_data_t tab[], int tab_size)
{
  for(int i=0; i<tab_size; i++){
    print(tab[i]);
  }
  printf("\n");
}

void swap(s_data_t tab[], int i, int j)
{
    s_data_t temp = tab[i];
    tab[i] = tab[j];
    tab[j] = temp;
}


// BUBBLE SORT
void bubble_sort(s_data_t tab[], int tab_size)
{
    for (int i = 0; i < tab_size-1; i++)
    {
        for (int j = i; j < tab_size; j++)
        {
            if (compare(tab[i], tab[j]) > 0)
            {
                swap(tab, i, j);
            }
        }
    }
}


// INSERTION SORT
void insertion_sort(s_data_t tab[], int tab_size)
{
    for (int i = 1; i < tab_size; i++)
    {
        s_data_t key = tab[i];
        int j = i - 1;

        while(j >= 0 && tab[j] > key)
        {
            tab[j+1] = tab[j];
            j--;
        }

        tab[j+1] = key;
    }
}


// SELECTION SORT
void selection_sort(s_data_t tab[], int tab_size)
{
    for(int i = 0; i < tab_size-1; i++)
    {
        s_data_t min = i;
        for(int j = i+1; j < tab_size; j++)
        {
            if(tab[j] < tab[min])
            {
                min = j;
            }
        }
        if(i != min)
        {
            swap(tab, i, min);
        }
    }
}


void swap_down(s_data_t tab[], int i, int tab_size)
{
    if (i < tab_size / 2)
    {
        int fg = 2 * i + 1;
        int fd = fg + 1;
        int fmin;
        if (fd < tab_size)
        {
            fmin = (tab[fg] < tab[fd]) ? fg : fd;
        }
        else
        {
            fmin = fg;
        }
        if (compare(tab[fmin], tab[i]) < 0)
        {
            int temp = tab[i];
            tab[i] = tab[fmin];
            tab[fmin] = temp;
            swap_down(tab, fmin, tab_size);
        }
    }
}

void make_heap(s_data_t tab[], int tab_size)
{
    for (int i = tab_size / 2 - 1; i >= 0; i = i - 1)
    {
        swap_down(tab, i, tab_size);
    }
}

s_data_t delete_min_from_heap(s_data_t tab[], int *tab_size)
{
    s_data_t min = tab[0];
    (*tab_size) = (*tab_size) - 1;
    tab[0] = tab[*tab_size];
    swap_down(tab, 0, *tab_size);
    return min;
}

// SOLUTION
void heap_sort(s_data_t tab[], int tab_size)
{
    make_heap(tab, tab_size);
    int last = tab_size;
    for (int i = 0; i < tab_size; i++)
    {
        s_data_t min = delete_min_from_heap(tab, &last);
        tab[last] = min;
    }
}

void quick_sort(s_data_t tab[], int lower, int upper)
{
    if (lower < upper)
    {
        int pivot = lower;
        int l = lower;
        int u = upper;
        while (l < u)
        {
            while (tab[l] <= tab[pivot] && l < upper)
                ++l;
            while (tab[u] > tab[pivot])
                --u;
            if (l < u)
            {
                // Swap
                int temp = tab[l];
                tab[l] = tab[u];
                tab[u] = temp;
            }
        }
        int temp = tab[pivot];
        tab[pivot] = tab[u];
        tab[u] = temp;
        quick_sort(tab, lower, u - 1);
        quick_sort(tab, u + 1, upper);
    }
}