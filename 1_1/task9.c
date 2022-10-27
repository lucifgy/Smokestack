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
 */

#include <stdio.h>
#include <stdlib.h>
#include <stddef.h>
#include <memory.h>

#define MAX_MEMORY_SIZE (160)

#pragma pack(1)

typedef struct Meta
{
	size_t size;
	unsigned char allocated;
	struct Block *next;
	struct Block *prev;
}Meta;

typedef struct Block
{
	struct Meta meta;
	char data[];
}Block;

struct Block *mem_block;

void fillMeta(Meta *meta, size_t size, Block *next, Block *prev, unsigned char allocated);

void merge(Block *block);

void init()
{
	mem_block = (Block *) malloc(MAX_MEMORY_SIZE);
	mem_block->meta.size = MAX_MEMORY_SIZE - sizeof(Meta);
	mem_block->meta.next = NULL;
	mem_block->meta.prev = NULL;
	mem_block->meta.allocated = 0;
}

void *myMalloc(size_t size) 
{
	if (size == 0)
		return NULL;
	Block *block = mem_block;
	while (block) {
		if (block->meta.allocated) 
		{   
			block = block->meta.next;
			continue;
		}   
		size_t needed_space = size;
		if (block->meta.size < needed_space) 
		{   
			block = block->meta.next;
			continue;
		}   
		size_t remaining_space = block->meta.size - needed_space;
		fillMeta(&block->meta, size, block->meta.next, block->meta.prev, 1); 
		if (remaining_space > sizeof(Meta))
		{   
			Block *remainedBlock = (Block *) block->data + size;
			fillMeta(&remainedBlock->meta, remaining_space - sizeof(Meta), block->meta.next,    block, 0);
			block->meta.next = remainedBlock;
			if(remainedBlock->meta.next)
			{   
				Block *next = remainedBlock->meta.next;
				next->meta.prev = remainedBlock;
			}   
		}
		return block->data;
	}
	printf("No space avaible to alloc\n");
	return NULL;
}

void myFree(void *ptr)
{
	if (!ptr)
		return;
	Block *block = mem_block;
	while (block)
	{
		if (block->data == ptr)
		{
			block->meta.allocated = 0;
			merge(block);
			return;
		}
		block = block->meta.next;
	}
}

void *myRealloc(void *ptr, size_t size)
{
	void *newPtr = myMalloc(size);

	Block *block = mem_block;
	//printf("%zu\n",block->meta.size);
	//shrink
	if(block->meta.size > size)
	{
		//fillMeta(&block->meta, size, block->meta.next, block->meta.prev, 1);

		//Block *freedBlock = (Block*) block->data - (block->meta.size - size);

		//size_t freed_space = block->meta.size - size;
		//fillMeta(&freedBlock->meta, freed_space, block, block->meta.prev, 0);


		Block *freedBlock = (Block*) block->data - size;
		fillMeta(&freedBlock->meta, block->meta.size - size, block->meta.next, block, 0);

		fillMeta(&block->meta, size, freedBlock, block->meta.prev, 1);

		//char *str = myMalloc(size*sizeof(char));
		//strcpy(str, "ad");
		//memcpy(str, block->data, size);

		//printf("Freed: %s\n",freedBlock->data);
		//printf("Block: %s\n",block->data);

		//printf("%zu\n",freedBlock->meta.size);

		return block->data;
	}
	//expand or malloc
	if(block->meta.size < size)
	{
		size_t needed_space = size - block->meta.size;

		Block* next = block->meta.next;

		if(next->meta.size < needed_space)
		{
			fillMeta(&block->meta, block->meta.size + needed_space, next->meta.next, block->meta.prev, 1);
		}

		else{
			memcpy(newPtr, ptr, size);
			myFree(ptr);
			printf("%zu\n",block->meta.size);
			return newPtr;
		}
	}
	else return NULL;;
	return NULL;

}


void fillMeta(Meta *meta, size_t size, Block *next, Block *prev, unsigned char allocated) {
	meta->allocated = allocated;
	meta->size = size;
	meta->next = next;
	meta->prev = prev;
}

void merge(Block *block) {
	if (block->meta.next) {
		Block *next = block->meta.next;
		if (!next->meta.allocated) {
			block->meta.next = next->meta.next;
			block->meta.size = next->meta.size + sizeof(Meta);
			if (next->meta.next) {
				next = next->meta.next;
				next->meta.prev = block;
			}

		}
	}
	if (block->meta.prev) {
		Block *prev = block->meta.prev;
		if (!prev->meta.allocated) {
			prev->meta.next = block->meta.next;
			prev->meta.size = block->meta.size + sizeof(Meta);
			if (block->meta.next) {
				block = block->meta.next;
				block->meta.prev = prev;
			}
		}
	}
}



int main () 
{

	init();
	char *c1 = myMalloc(5*sizeof(char));
	strcpy(c1, "222222222");
	//printf("%s\n",c1);
	myRealloc(c1, 3*sizeof(char));
	//printf("%s",c1);
	/*
	   char *c1 = myMalloc(5 * sizeof(char));
	   char *c2 = myMalloc(10 * sizeof(char));
	   char *c3 = myMalloc(5 * sizeof(char));
	   char *c4 = myMalloc(10 * sizeof(char));
	   char *c5 = myMalloc(5 * sizeof(char));
	   strcpy(c1, "1111");
	   strcpy(c2, "222222222");
	   strcpy(c3, "3333");
	   strcpy(c4, "444444444");
	   strcpy(c5, "5555");
	   myFree(c2);
	   myFree(c4);
	   char *c6 = myMalloc(15 * sizeof(char)); //cant because no space availabe (
	   myFree(c3);
	   c6 = myMalloc(15 * sizeof(char)); // can because free blocks merging together when c3       released
	   */
	return 0;
}
