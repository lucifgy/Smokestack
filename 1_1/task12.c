/*
 * Calculating 3^5000 in hexadecimal
 * To convert a number to hexadecimal we divide it by 16, as 16 is base for hexadecimal
 * We simply calculate it in hexadecimal and print it with the following algorithm.
 *
 * https://e-maxx.ru/algo/big_integer
 */
//TODO:
//Faster algorithm
//
#include <stdio.h>
#include <math.h>
#include <stdlib.h>

int* foo(int num, int pow, int base)
{
	int i, j, array_size;
	int *digit;

	array_size = (int)(pow * log10(num) / log10(base)) + 1;
	digit = (int*)calloc(array_size, sizeof(int));
	digit[0] = 1;

	for (i = 0; i < pow; i++)
	{
		for(j = 0; j < array_size; j++)
		{
			digit[j] = digit[j] * 3;
		}
		for (j = 0; j < array_size - 1; j++)
		{
			digit[j + 1] = digit[j + 1] + digit[j] / base;
			digit[j] = digit[j] % base;
		}
	}

	if(base == 16)
	{
		printf("\nHexadecimal form:\n");
		for(i = array_size - 1; i >= 0; i--)
			printf("%X",digit[i]);
	}
	else
	{
		printf("\nDecimal form:\n");
		for(i = array_size - 1; i >= 0; i--)
			printf("%d", digit[i]);
	}

	return digit;
}
/*
 *Incomplete
 *
 void bar(int num, int pow)
 {
 int base = 10;
 int array_size = (int)pow * log10(num) + 1;
 int* digit = (int*)calloc(array_size, sizeof(int));
 int carry = 0;
 int i;
 for(i = 0; i < array_size || carry; i++)
 {
 if (i == array_size)
 digit[i+1] = 0;
 long long cur = carry + digit[i] * 111 * num;
 digit[i] = (int)(cur % base);
 carry = (int)(cur / base);
 }

 i = 0;
 while (array_size > 1 && digit[array_size] == 0)
 {
 digit[array_size - i] = NULL;\\C doesn't support this, digit[] = NULL is for symbolism
 i++;
 }
 for (i = 0; i < array_size; i++)
 printf("%d", digit[i]);
 }
 */
int main ()
{
	foo(3,5000,10);
	printf("\n");
	foo(3,5000,16);
	//bar(3,5000);
	return 0;
}
