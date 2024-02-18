#include <stdlib.h>
#include <stdio.h>
#include "addition.h"


int main(void)
{
    int a, b;
    scanf("%d", &a);
    scanf("%d", &b);
    printf("%d\n", somme(a, b));
    return 0;
}
