/*
  We have a string returning function which takes an usinged long long(data type  that can take 64 bits of unsigned int), and a bit specifier int(used for size   of the string).

We convert the given decimal into binary using the formula x[i] = n % 2; n /=   2; and store the result backwards into an int array. Then we store the array as string using integers' ASCII values: char = int + 48 as ASCII 48 equals to 0    and 9 to 56. '\0' is added at the end of char array to indicate it's a string.

In main() we get the product of 3 string's sizes. Using this number, we display its negative, float precision, negative double precision in binary.

*(type *) &var; (right to left) point to location of var type cast the pointer  to another type retrieve the contents of the converted pointer.

PS. Not sure why we get the address of a variable then getting contents of the  pointer gives a different number. The same trick is used in fast inverse square root algorithm.
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
