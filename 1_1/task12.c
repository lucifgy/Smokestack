/*
 * Calculating 3^5000 in hexadecimal
 * To convert a number to hexadecimal we divide it by 16, as 16 is base for hexadecimal
 * We simply calculate it in hexadecimal and print it with the following algorithm.
 */
//TODO:
//Explanation of the formula.
//~
#include <stdio.h>
#include <math.h>
#include <stdlib.h>
int main() 
{
	int i, j, array_size;
	int *digit;
	int power = 5000;
	int base = 16;

	array_size = (int)(power * log10(3.0) / log10(16.0)) + 1;
	digit = (int*)calloc(array_size, sizeof(int));
	digit[0] = 1;

	for (i = 0; i < power; i++)
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
	for (i = array_size - 1; i >= 0; i--)
		printf("%X", digit[i]);
	return 0;
}
