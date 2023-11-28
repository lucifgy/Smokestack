#include <iostream>
#include <cstring>
#include <fstream>
#include <ctime>

inline char changeCase(char &ch)
{
  if(ch >= 'a' && ch <= 'z')
  {   
    return ch = ('A' + (ch - 'a'));
  }   
  if(ch >= 'A' && ch <= 'Z')
  {   
    return ch = ('a' + (ch - 'A'));
  }   
  return ch;
}

inline void changeCase(char *buf, int count)
{
  for(int i = 0; i < count; ++i)
  {
    //buf[i] = changeCase(buf[i]);
    changeCase(buf[i]);
  }
}

int main(int argc, char* argv[])
{
  char inputFileName[256];
  if (argc > 1)
  {
    strcpy(inputFileName, argv[1]);
  }
  else
  {
    std::cout << "Input file name: ";
    std::cin.getline(inputFileName, 256);
  }
  std::ifstream inFile(inputFileName);
  if (!inFile)
  {
    std::cerr << "Cannot open file " << inputFileName << std::endl;
    return 1;
  }
  char outputFileName[256];
  if(argc > 2)
  {
    strcpy(outputFileName, argv[2]);
  }
  else
  {
    const char defaultFileName[] = "output.txt";
    std::cout << "Output file name [" << defaultFileName << "]: ";
    std::cin.getline(outputFileName, 256);
    if(strlen(outputFileName) == 0)
    {
      strcpy(outputFileName, defaultFileName);
    } 
  }
  std::ofstream outFile(outputFileName);
  if(!outFile)
  {
    std::cerr << "Cannot open file" << std::endl;
    inFile.close();
    return 2;
  }

  const int blocksize = 1024;
  char buf[blocksize];

  clock_t ticktok = clock();
  /*
  int ch;
  while((ch = inFile.get()) != EOF)
  {
    outFile.put(changeCase(ch));
  }
  */

  int cnt;
  while (!inFile.eof())
  {
    inFile.read(buf, blocksize);
    cnt = inFile.gcount();
    changeCase(buf, cnt);
    outFile.write(buf, cnt);
  }

  ticktok = clock() - ticktok;
  std::cout << "Total " << ticktok << "ticks" << std::endl;
  inFile.close();
  outFile.close();
  return 0;
}
