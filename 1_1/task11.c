/*Digital root:
* The sum of maximum digital roots of a number's decomposition till 999999
*
* We decompose the number given to the function and find the maximum digital root:
* "Greates digital root": for 10: dicisors are 1,2,5,10
* So, the greatest DR of 10 would be 5, so we add it to the sum from 2 to 999999. 
*/
//TODO:
//~
//
#include <stdio.h>
#include <math.h>
#include <stdlib.h>

int digitalRoot(int n);
int max(int a, int b);
int mdr(int n);

int main()
{
	printf("\nMDSR from 2 to 999999: %d\n", mdr(999999));
	return 0;
}

int digitalRoot(int n)
{
	return ((n - 1) % 9) + 1;
}
int max(int a, int b)
{
	if (a > b) return a;
	else return b;
}

int mdr(int n)
{
	int* mdrs = (int*)malloc(sizeof(int) * n);
	int max_dr, sup;
	int sum = 0;

	for(int i = 2; i <= n; i++)
	{
		max_dr = digitalRoot(i);
		sup = (int)sqrt(i*1.0);

		for (int j = 2; j <= sup; j++)
			if(i % j == 0)
				max_dr = max(max_dr, mdrs[j] + mdrs[i/j]);
		mdrs[i] = max_dr;
		sum += max_dr;
	}
	return sum;
}
