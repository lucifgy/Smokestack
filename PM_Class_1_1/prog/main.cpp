#include <iostream>
#include "stdatabase.h"


int menu()
{
  for (int i = 0; i < 40; ++i)
    std::cout.put('*');
  std::cout << std::endl;
  std::cout << "1. Open database\n";
  std::cout << "2. Save database\n";
  std::cout << "3. Export database\n";
  std::cout << "4. Print\n";
  std::cout << "5. Add student\n";
  std::cout << "6. Set marks\n";
  std::cout << "7. Sort studets\n";
  std::cout << "8. Delete students\n";
  std::cout << "0. Exit\n";
  int tmp;
  std::cin >> tmp;
  if (!std::cin.good())
  {
    std::cin.clear();
    std::cin.ignore();
    tmp = -1;
  }
  return tmp;
}

int main()
{
  DataBase DB = {0, 0, nullptr};
  const char dbfilename[] = "student.db";
  int action;
  do
  {
    action = menu();
    switch(action) 
    {
      case 1: openDB(DB, dbfilename); break;
      case 2: saveDB(DB, dbfilename); break;
      case 3: exportDB(DB, "studentdb.txt"); break;
      case 4: printDB(DB); break;
      case 5: addRecord(DB); break;
    }
  }while (action != 0);

  if(DB.data)
  {
    delete[] DB.data;
  }

  return 0;
}
