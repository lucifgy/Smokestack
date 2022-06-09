/*
 * A non-degenerate triangle is a triangle that has a positive area:
 * a + b > c
 * a + c > b
 * b + c > a 
 *
 *    /\
 *   a  c
 *  /x_b_\
 *
 * acos((a^2 + b^2 - c^2) / (2ab)) = rad
 * rad * 180 / pi = degrees
 *
 * x = 66.66
 * degree = int(x) = 66
 * decimal = x - degree 
 * minutes = int((decimal * 60) = 39
 * seconds = (decimal - minutes/60) * 3600 = 36
 * 66 degrees 39 minutes 36 seconds
 */
#include <stdio.h>
#include <math.h>

#define PI 3.142857

double find_angle(double x, double y, double z);
void calc_dms_print(double a);

int main ()
{
	double x[3];
	double a, b, c;
	printf("Sides of the tri: ");
	scanf("%lf %lf %lf", &x[0], &x[1], &x[2]);

	if (x[0] + x[1] > x[2] &&
			x[0] + x[2] > x[1] &&
			x[1] + x[2] > x[0])
	{
		a = find_angle(x[0], x[1], x[2]);
		b = find_angle(x[1], x[2], x[0]);
		c = find_angle(x[2], x[0], x[1]);

		calc_dms_print(a);
		calc_dms_print(b);
		calc_dms_print(c);
	}
	return 0;
}

double find_angle(double x, double y, double z)
{
	double deg;
	deg = acos((pow(x,2) + pow(y,2) - pow(z,2)) / (2 * x * y));
	deg = deg * (180/PI);
	return deg;
}

void calc_dms_print(double a)
{
	int degree = (int)a;
	double decimal = a - degree;
	double minutes = decimal * 60;
	double seconds = (minutes - (int)minutes) * 60;
	printf("%d\t %.1f' \t %.2f\" \n", degree, minutes, seconds);
}
