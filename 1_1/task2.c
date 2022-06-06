/*
 * A primitive Pythagorean triple is a right triangle(x,y,z)
 *  where x^2 + y^2 = z^2 where GCD(x,y,z) = 1
 * 
 * We take 3 int inputs into an array, sort it.
 * (Greatest number is the last for arbitary input)
 * First we check if the input is Pythagorean triple
 * Then we check if it is primitive with our gcd function
 *
 * gcd is a recursive function which return gdc of 2 numbers
 * To find 3 ints' gcd we take 1st number's gcd with 2nd and 3rd numbers' gcd
 */
//TODO:
//~
//
#include <stdio.h>
#include <math.h>

int gcd(int a, int b);

int main()
{
	int sides[4];
	scanf("%d %d %d", &sides[0], &sides[1], &sides[2]);
	int gcd_res = gcd(sides[0],gcd(sides[1],sides[2]));
	
	int i;
	sides[3] = 0;
  for (i = 1; i < 4; i++)
	{
		if (sides[i - 1] > sides[i])
		{
			sides[3] = sides[i - 1];
			sides[i - 1] = sides[i];
			sides[i] = sides[3];
		}
	}

	if(pow(sides[0], 2) + pow(sides[1], 2)
		 	== pow(sides[3], 2))
	{
		printf("Pythagorean Triple\n");	
		if(gcd(sides[0], gcd(sides[1], sides[2])) == 1)
		{
			printf("Primitive\n");
		}
	}
  return 0;
}

int gcd(int a, int b)
{
	if (b == 0) return a;
	return gcd(b, a % b);
}
