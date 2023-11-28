#ifndef _ST_DATABASE_2023
#define _ST_DATABASE_2023
#include "student.h"

struct DataBase {
  int count;
  int maxSize;
  student* data;
};

void printDB(const DataBase& DB);
int addRecord(DataBase& DB);
void saveDB(const DataBase& DB, const char filename[]);
int openDB(DataBase& DB, const char filename[]);
void exportDB(const DataBase& DB, const char filename[]);

#endif 
