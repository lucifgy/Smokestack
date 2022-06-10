/*
 * Continues fractions a0 + (1 / (a1 + 1 / (a2 + (1 / ( ai)))))
 * i is a non negative integers and ai is a positive integer
 * 
 * r = 2.2
 * x = (int)r
 * y = x - r
 * if y != 0
 * y = 1 / y
 *
 * a = n % 1
 * if a == 0 //rational and integer
 * if a % 1 = a //repeating rational
 * else //irrational
 *
 * x = sqrt(y);
 * z = sqrt(x);
 * if z * z != sqrt(x): irrational;
 */
//TODO:
//Fix last digits of continued fractions being incorrect
#include <stdio.h>
#include <math.h>

double foo(double r);
int is_rational(double n);
int gcd(int a, int b);

int main() 

{
	int i;
	double x;
	printf("Enter an irrational number: ");
	scanf("%lf", &x);
	foo(x);

	if (is_rational(x)) printf("Enter irrational number\n");
	return 0;
}

double foo(double r)
{
	int x = (int)r;
	double y = r - x;
	if (is_rational(y))
	{
		printf("%d", x);
		return 0.0;
	}
	if (fabs(y) <= 0.00001)
	{
		return 0.0;
	}
	else
		printf("%d ", x);

	return foo(1 / y);
}

int is_rational(double n)
{
	if (fmod(n,1) == 0) return 1;
	else return 0;
}
int gcd(int a, int b)
{
	if (b == 0) return a;
	return gcd(b, a%b);
}
