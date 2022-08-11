/*
 *
 * First we convert given decimal to fraction
 * Then we calculate the continued fraction of the fraction
 * 
 * To convert decimal to fraction:
 * We take the int and the fraction part of a given number
 * Then we calculate the GCD of fraction part with 10^9 percision
 * We calculate the numerator by diving int equivalent of fraction by gcd
 * And calculate the denominator by dividing the precision value by gcd
 * Drom mixed fraction we conver it to improper fraction.
 *
 *
 */
//TODO:
//Insert condition for rational input
//
#include <stdio.h>
#include <math.h>

int gcd(int a, int b);

int main()
{
	double n;

	printf("Enter an irrational number: ");
	scanf("%lf",&n);

	int precision = pow(10,9);
	int i = floor(n);
	double f = n - i;
	int g = gcd(f * precision, precision);
	int numerator = (f * precision) / g;
	int denominator = precision / g;
	numerator = ((i * denominator) + numerator);

	int r, output;
	int period = 0;
	while(denominator != 0)
	{
		period += 1;
		r = numerator % denominator;
		output = (numerator - r) / denominator;
		printf("%d ",output);
		numerator = denominator;
		denominator = r;
	}
	printf("\nPeriod: %d",period);
	return 0;
}

int gcd(int a, int b)
{
	if (b==0) return a;
	return gcd(b, a%b);
}
