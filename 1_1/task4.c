/*
 * Mersenne numbers are numbers: 2^i -1
 * 32bit int's limit is 2,147,482,647
 * Same as 31rt Marsenne number that's why we use unsigned int
 * We have a fucntion checking if it's a prime number  
 */
//TODO:
//A way to check primality of a number without a function
#include <stdio.h>
#include <math.h>

int is_prime(unsigned int x);

int main()
{
	unsigned int x;
	int i;
	for (i = 0; i < 32; i++)
	{							//Alternate
		x = pow(2, i) - 1;		//x = x << 1; Shifting bit to get square of 2;
		if(is_prime(x))			//C doesn't like moving bits then subtracting(?)
			printf("%d\n", x);	// x - 1 here
	}
	return 0;
}

int is_prime(unsigned int x)
{
	int i;
	if (x < 2) 
		return 0;

	for (i = 2; i <= sqrt(x); i++)
		if((x % i) == 0) 
			return 0;

	return 1;
}
