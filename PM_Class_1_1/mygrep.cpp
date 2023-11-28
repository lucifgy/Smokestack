#include <iostream>
#include <fstream>
#include <cstring>

void mygrep(char* filename, char* str_to_search)
{

}

int main(int argc, char* argv[])
{
  char inputFileName[256];
  char str_to_search[256];
  if (argc > 2)
  {
    strcpy(inputFileName, argv[1]);
    strcpy(str_to_search, argv[2]);
  }
  std::ifstream inFile(inputFileName);
  if (!inFile)
  {
    std::cerr << "Cannot open file " << inputFileName << std::endl;
    return 1;
  }

  std::cout << count << std::endl;

  inFile.close();

  return 0;
}
