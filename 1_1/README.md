1 - Representation of numbers
Calculate the product of the lengths of your first name, last name and middle name. Display a binary        representation of the 
following values in the specified data formats:

A) a negative 32-bit integer whose module is equal to the product found;

B) a positive floating-point number of unit precision according to the IEEE 754 standard, the module of     which is equal to the product found;

C) a negative double-precision floating-point number according to the IEEE 754 standard, the module of      which is equal to the product found.
 
2 - Pythagorean triples

A Pythagorean triple is a triple of natural numbers (x, y, z) satisfying the condition x^2 + y^2 = z^2. 
The Pythagorean triple is called primitive if the numbers that make it up are mutually simple. 
For the three numbers entered by the user, determine whether they are a Pythagorean triple, and if so, 
whether they are also a simple Pythagorean triple. The order in which numbers are entered is arbitrary.

3 - Triangle angles

Determine whether it is possible, based on the three numbers entered by the user, to construct a non-       degenerate 
triangle with the corresponding sides. If possible, determine its angles in degrees, minutes and seconds to the nearest second.
Provide for user input of numbers with a fractional part.

4 - Mersenne numbers

Mersenne numbers are numbers of the form 2 ^ N -1, where N is a natural number. 
Display all Mersenne primes on the interval [1; 2 ^ 31 - 1]. 

5 - Chain fractions

Continued fraction - a finite or infinite expression of the form

[a0; a1, a2, a3, ...] = a0 + 1 / (a1 + 1 / (a2 + 1 / (a3 + ...)))

where a0 is an integer, the remaining ai are natural numbers. Any real number can be represented as a       continued fraction, 
while rational numbers are represented by a finite continued fraction, and irrational numbers by an         infinite one. 
The square root of an integer that is not the square of another integer is an irrational number. 
The continued fraction of the square root has the property that starting from the following
ai aj = aj-i: ai + 1 = a1, ai + 2 = a2, etc. i is called the period of this continued fraction.
For a user-entered number that is not a square of an integer, print period i and the sequence [a0; a1 ...   ai].

6 - Row sorting
https://github.com/witwall/mman-win32

Write a program for sorting lines in a text file using the mechanism of files mapped to memory (memory      mapped files).
Lines in the file end according to the rules of the operating system (\ n or \ r \ n). As a comparator, you can use any adequate task.
For an example of sufficiently large text files,
you can take files with genomes from https://www.ncbi.nlm.nih.gov/genome.

7 - Hash table

Create a data structure for the hash table and define the following operations for it:
- Insert a new key and value. At the same time, when a certain condition is reached (for example, a too     long list for one of the baskets relative to the total number of elements in the hash table), the hash      table must be rebalanced.
- Search for values ÎõÎõby key. In case the key is not found, some predetermined value is returned.
- Delete key and value. If the key is not found, nothing happens. Rebalancing is not required.

8 - Filters

It is required to write a program that receives the following data from the command line:
- The name of the input file.
- Type of filter.
- The name of the output file.
The input file is a 24-bit or 32-bit BMP file without compression, but possibly with a palette. It is       required to read image data from it and apply one of the following filters to it according to the passed    parameter:
- Averaging filter 3x3.
- Gaussian averaging filter 3x3 or 5x5. It is permissible to use other filter sizes if the Gaussian         parameters are correctly selected.
- Sobel filter by X. It is permissible to use the Shcharr filter instead.
- Sobel filter by Y. It is permissible to use the Shcharr filter instead.
- Translation of images from color to shades of gray.
The resulting image must be saved at the specified output file path in BMP format. The resulting file       should be opened with standard image viewers.
A possible command line example for using the program is: MyInstagram C: \ Temp \ 1.bmp SobelX C: \ Temp \  2.bmp

9 - Memory manager

Implement a set of the following functions and show their performance:
- void * myMalloc (size_t size) - an analogue of the malloc function;
- void myFree (void * ptr) - an analogue of the free function;
- void * myRealloc (void * ptr, size_t size) - an analogue of the realloc function;
- void init () - an auxiliary function that initializes the necessary data structures;
In the init () function, a large area of dynamic memory is allocated using regular means.
Memory allocation in implemented functions should occur in this area. Outside the init () function,
you cannot use the malloc, realloc, and free functions.

10 - English coins

In England, coins of the following denominations are in circulation:
1 penny, 2 pence, 5 pence, 10 pence, 20 pence, 50 pence, 1 pound (100 pence) and 2 pound (200 pence).
The user enters a natural number denoting a certain amount of money in pensions.
Display the number of ways in which this amount can be dialed using any number of any English coins.

11 - Digital root

The digital root is the number in the decimal number system, obtained from the digits of the original       number by
adding them and repeating this process over the resulting amount until a number less than 10 is obtained.
For example, the digital root 467 is 8.
It is known that a compound number can be factorized in various ways, for example
24 = 2x2x2x3 = 2x3x4 = 2x2x6 = 4x6 = 3x8 = 2x12 = 24.
In this case, unit multiplication is not considered.
We call the sum of the digital roots the sum of the digital roots of the individual factors in the          expansion of the composite number.
For example, for 24:

Decomposition - Sum of Digital Roots
2x2x2x3 - 9
2x3x4 - 9
2x2x6 - 10
4x6 - 10
3x8 - 11
2x12 - 5
24 - 6

In this case, the maximum sum of digital roots is 11.
Denote the maximum sum of digital roots among all factorizations of n by MDRS (n).
Calculate the sum of all MDRS (n) for n = [2; 999999]

12 - Long arithmetic

Using long arithmetic algorithms, calculate the value of 3 ^ 5000 and represent it in hexadecimal notation.
