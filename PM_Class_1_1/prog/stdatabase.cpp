#include "stdatabase.h"
#include <fstream>
void printDB(const DataBase& DB)
{
  for (int i = 0; i < DB.count; ++i)
  {
    std::cout << DB.data[i] << std::endl;
  }
}

int addRecord(DataBase& DB)
{
  if (DB.count >= DB.maxSize)
  {
    DB.maxSize = (DB.maxSize > 0) ? 2 * DB.maxSize : 1;
    student *tmp = new student[DB.maxSize];
    for(int i = 0; i < DB.count; ++i)
    {
      tmp[i] = DB.data[i];
    }
    delete[] DB.data;
    DB.data = tmp;
  }
  std::cin >> DB.data[DB.count];
  ++DB.count;
  return DB.count;
}

void saveDB(const DataBase& DB, const char filename[])
{
  std::ofstream outFile(filename, std::ios_base::binary);
  if(!outFile.is_open())
  {
    std::cout << "Cannot open file " << filename << std::endl;
    return;
  }
  outFile.write((const char*)(&DB.count), sizeof(DB.count));
  outFile.write((const char*)(&DB.data), DB.count * sizeof(DB.data[0]));
  outFile.close();
}

int openDB(DataBase& DB, const char filename[])
{
  std::ifstream inFile(filename, std::ios_base::binary);
  if(!inFile.is_open())
  {
    std::cout << "Cannot open file " << filename << std::endl;
    return 0;
  }
  int count;
  inFile.read((char*)(&count), sizeof(count));
  if(DB.data)
    delete[] DB.data;
  DB.data = new student[count];
  DB.count = count;
  DB.maxSize = count;
  inFile.read((char*)(DB.data), count * sizeof(DB.data[0]));
  return count;
}
void exportDB(const DataBase& DB, const char filename[])
{
  std::ofstream outFile(filename);
  if(!outFile.is_open())
  {
    std::cout << "Cannot open file " << filename << std::endl;
    return;
  }
  for (int i = 0; i < DB.count; i++)
  {
    outFile << DB.data[i] << std::endl;
  }

  outFile.close();
}
