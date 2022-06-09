/*
 * We have a string returning function
 * Takes an unsinged long long and a bit specifier int(for string size)
 *
 *  We Convert the given decimal into binary using the formula:
 * x[i] = n%2; n /= 2;
 * Result is stored backwards into an int array.
 * Then result is stored into array using integers' ASCII values
 * 0 = 48; 9 = 56; '\0' is added to indicate it's a string.
 *
 * Product of the strings given strings sizes is being used to display in binary:
 * -Its negative; Float precision; Negative double precision;
 *
 * *(type *) &var: 
 * Point to location of var type 
 * Cast it to another type
 * Retrive the contents
 */
//TODO:
//-
//
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char *bar(unsigned long long num, int bits);

int main()
{
	int product = strlen("Yunus") * strlen("Emre") * strlen("Cakici");

	float f_product = (float)product;
	double d_product = (double)-product;

	printf("Product: %d\n", product);
	printf("Negative int: %s\n", bar(-product, 32));
	printf("Float: %s\n",bar(*(long long*)&f_product, 32));
	printf("Negative double: %s\n",bar(*((long long*)&d_product), 64));

	return 0;
}

char *bar(unsigned long long num, int bits)
{
	int *ar = (int*)malloc(sizeof(int*) * bits);
	int i;

	for (i = 0; i < bits; i++)
	{
		ar[bits - i - 1] = num % 2;
		num /= 2;
	}
	char x[bits];
	for (i = 0; i < bits; i++) x[i] = ar[i] + '0';
	x[bits] = '\0';
	char *result = x;
	free(ar);
	return result;
}
