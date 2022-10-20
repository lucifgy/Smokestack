/* Task:
 * Analogues of malloc relloc and free:
 *
 * void *myMalloc(size_t size)
 * void myFree(void *ptr)
 * void *myRealloc(void *ptr, size_t size
 * void init()
 * 
 * An auxiliary function to init data structures:
 *  -in init() a large area of dynamic mem is alloced using regular funcs
 *  -mem alloc in "my" functions should occur here.
 *  -Outside of init() cannot use malloc, realloc, free functions.
 *
 *
 */
//TODO:
//Freed mem merge
//~
#include <stdio.h>
#include <stdlib.h>

#define MAX_SIZE 500

#pragma pack(1)

unsigned char* malloc_data;
size_t mallocUsed;

void init() 
{
    malloc_data = (unsigned char*)malloc(MAX_SIZE * sizeof(unsigned char));
    mallocUsed = 0;
}

void *myMalloc(size_t size) 
{
    void* p = &malloc_data[mallocUsed];
    mallocUsed += size;
    return p;
}

void myFree(void *ptr) 
{
    ptr = NULL;
}

void *myRealloc(void *ptr, size_t size) 
{
    ptr = &malloc_data[mallocUsed];
    mallocUsed = size;
	return 0; //not needed..its purpose is to avoid warning. Or:
			  //void newP = maMalloc(size);
			  //memcpy(newP, ptr, size);
			  //myFree(ptr);
			  //return newP;
}

int main() {
    init();

    printf("Creating new dynamic memory of size 10.\n");
    char *p = (char*)myMalloc(sizeof(char) * 10);

	for (int i = 0; i < 10; i++)
		p[i] = i + '0';

	for(int i = 0; i < 10; i++)
		printf("%c\n", p[i]);

	printf("\nFreeing the memory.\n");
    myFree(&p);
    if (!p)
		printf("The memory is not free.\n");
       
    else 
        printf("The memory is free.\n");
       
    return 0;
}
