/*
 * A hashtable is a data structure that implements an associative array.
 * it's an abstract data type that maps keys to values.
 * Basically a hashtable is a linked list with a hash function.
 * A hash function computes a hash code with which it finds the needed element.
 *
 * A Hastable has: Insert-Search-Delete functions.
 * Ideally it should also handle collisions and is able to rebalance.
 *
 * Collision happens when different elements has the same hash code.
 *
 * Rebalancing is needed when the size of the hashtable becomes too little for the amount of data
 *
 */

//TODO:
//~
//
#include <stdio.h>
#include <stdlib.h>
typedef struct Entry
{
	int key;
	int value;
	struct Entry *next;
}Entry;

typedef struct HashTable
{
	int size;
	int defaultValue;
	struct Entry **baskets;
} HashTable;

HashTable *createHashTable(int size, int defaultValue)
{
	HashTable *hashTable = (HashTable *)malloc(sizeof(HashTable));
	hashTable->size = size;
	hashTable->defaultValue = defaultValue;
	hashTable->baskets = (Entry **)malloc(sizeof(Entry) * size);
	for (int i = 0; i < size; i++)
		hashTable->baskets[i] = NULL;
	return hashTable;
}

int hashCode(int key, int size)
{
	return key % size;
}

void insert(HashTable *hashTable, int key, int value)
{
	int hash = hashCode(key, hashTable->size);
	int basketSize = 0;
	Entry *basket = hashTable->baskets[hash];
	Entry *entry = basket;
	while (entry)
	{
		if (entry->key == key)
		{
			entry->value = value;
			return;
		}
		entry = entry->next;
		basketSize++;
	}
	Entry *newEntry = (Entry *)malloc(sizeof(Entry));
	newEntry->key = key;
	newEntry->value = value;
	newEntry->next = basket;
	hashTable->baskets[hash] = newEntry;
	basketSize++;
	//rebalance
	if(basketSize > (hashTable->size) / 2)
	{
		int newSize = hashTable->size * 2;
		HashTable *newHashTable = createHashTable(newSize, hashTable->defaultValue);
		for (int i = 0; i < hashTable->size; i++)
		{
			Entry *r_newEntry = hashTable->baskets[i];
			while (r_newEntry)
			{
				insert(newHashTable, r_newEntry->key, r_newEntry->value);
				r_newEntry = r_newEntry->next;
			}
		}
		//free the table
		for (int i = 0; i < hashTable->size; i++)
		{
			Entry *d_entry = hashTable->baskets[i];
			Entry *d_nextEntry = NULL;
			while(d_entry)
			{
				d_nextEntry = d_entry->next;
				free(d_entry);
				d_entry = d_nextEntry;
			}
		}
		free(hashTable->baskets);
		hashTable->size = newSize;
		hashTable->baskets = newHashTable->baskets;
	}
}

int search(HashTable *hashTable, int key)
{
	int hash = hashCode(key, hashTable->size);
	Entry *entry = hashTable->baskets[hash];
	while(entry)
	{
		if (entry->key == key)
			return entry->value;
		entry = entry->next;
	}
	return hashTable->defaultValue;
}

void delete(HashTable *hashTable, int key)
{
	int hash = hashCode(key, hashTable->size);
	Entry *entry = hashTable->baskets[hash];
	Entry *prev = NULL;
	while(entry)
	{
		if(entry->key == key)
		{
			if(!prev)
			{
				hashTable->baskets[hash] = entry->next;
				free(entry);
				return;
			}
			prev->next = entry->next;
			free(entry);
			return;
		}
		prev = entry;
		entry = entry->next;
	}
}

void print_ht(HashTable *hashTable)
{
	for(int i = 0; i < hashTable->size; i++)
	{
		printf("Basket%d: ", i);
		Entry *entry = hashTable->baskets[i];
		while(entry)
		{
			printf("(%d, %d) ", entry->key, entry->value);
			entry = entry->next;
		}
		printf("\n");
	}
	printf("\n");
}

int main()
{
	int initialSize = 3;
	int dataSize = 20;
	int max = 20;
	HashTable *table = createHashTable(initialSize, 0);
	for(int i = 0; i < dataSize; i++)
	{
		insert(table, i, i*2);
	}
	print_ht(table);

	int search_key = 4;
	int delete_key = 0;

	printf("Searching key %d's value: %d\n",search_key,  search(table, 4));

	printf("\nDeleting key %d\n", delete_key);
	delete(table, delete_key);

	print_ht(table);
	return 0;
}
