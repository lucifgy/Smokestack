/*
 * Row sorting with memory mapping
 *
 * We create a txt file filled with random ints: rand.txt and mmap the file
 * Convert mmaped "string" to an int array, sort it and convert it back to string
 * Then we write the sorted numbers into out.txt
 *
 * PS:
 * N's value at the current state cannot be increased
 * to increase the value further, variables must be adjusted
 */
//TODO:
//(optional) increase the maximum input of N
//~
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <sys/mman.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <string.h>
#include <unistd.h>

#define N 20//32768

int cmp(const void *a1, const void *a2);
void rand_txt(int size);

int main() 
{
	rand_txt(N);
	int i;

	int rfile = open("rand.txt", O_RDWR);
	struct stat fileStat;
	fstat(rfile, &fileStat);
	int size = fileStat.st_size;

	char *addr = mmap(NULL, size, PROT_READ|PROT_WRITE, MAP_SHARED, rfile, 0);
	
	close(rfile);

	int len = strlen(addr);
	int numbers[N];


	int j, k;

	char** linePtr = (char**)malloc(N * sizeof(char*));
    char* str = (char*)malloc(N * sizeof(char));

    k = 0;

    for (i = 0; i < N; ) {
        j = 0;

        while (k < len) {
            if (addr[k] == '\n') {
                str[j] = '\n';
                j++;
                k++;
				break;
            }

            str[j] = addr[k];
			j++;
			k++;
        }

        linePtr[i++] = strdup(str);

        if (k == len) {
            break;
        }
    }


	qsort(linePtr, N, sizeof(char*), cmp);


	char* floutMapped = (char*)malloc(len * sizeof(char*));
    k = 0;

	for (i = 0; i < N; i++) {
        char* tmp = linePtr[i];

        while (*tmp != '\n') {
            floutMapped[k] = *tmp;
            k++;
            tmp++;
        }

        if (*tmp == '\n') {
            floutMapped[k] = '\n';
            k++;
        }

        free(linePtr[i]);
    }



	int outfile = open("out.txt", O_CREAT | O_RDWR, S_IRUSR | S_IWUSR);
	write(outfile,floutMapped,len);

	return 0;
}

int cmp(const void *a1, const void *a2)
{
	const char *x = *(char **)a1;
	const char *y = *(char **)a2;
	return strcmp(x,y);
}
void rand_txt(int size)
{
	FILE *fp = fopen("rand.txt", "w");
	srand(time(0));
	for (int i = 0; i < size; i++)
		fprintf(fp, "%d\n", (rand() % size) + 1);
	fclose(fp);
}
