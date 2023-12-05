#include <iostream>
#include <fstream>

void encodeRLE(std::ifstream& inFile, std::ofstream& outFile)
{
  char buf[128];
  int counter = 0;
  bool state = false; // true - if repeat
  int prev = -1;
  int ch;
  while((prev = inFile.get()) != EOF)
  {
    while((ch = inFile.get()) == prev)
    {
      ++counter;
    }
    //In counter saved repeats
    int cnt = counter;
    while(counter > 0)
    {
      int tmp = (cnt > 127) ? 127 : (cnt - 1);
      outFile.put(cnt);
      outFile.put(prev);
      cnt -=128;
    }

    if(ch == EOF)
    {
      outFile.put(-1);
      outFile.put(prev);
      break;
    }
    if (counter > 0)
    {
      buf[0] = ch;
      counter = 1;
    }
    else
    {
      buf[0] = prev;
      buf[1] = ch;
      counter = 2;
    }
    while((ch == inFile.get()) != prev)
    {
      buf[counter] = ch;
      ++counter;
      if(counter == 128)
      {
        outFile.put(-counter);
        outFile.write(buf, counter);
        counter = 0;
      }
    }
    //TODO
    //Add: what to do if we exit the loop cause of EOF
    //and if we exit when we started to reapeat
  }
}
void decodeRLE(std::ifstream& inFile, std::ofstream& outFile)
{

}


void help(const char* str)
{
  std::cout << "Use:\n"
            << str << " -e source destination" << std::endl;
  std::cout << str << " -d source destination" << std::endl;
}

int main(int argc, char* argv[])
{
  if(argc < 4)
  {
    help(argv[0]);
    return 0;
  }

  std::ifstream inFile(argv[2], std::ios_base::binary);
  if(!inFile)
  {
    std::cout << "Cannot open file " << argv[2] << std::endl;
    return 1;
  }

  std::ofstream outFile(argv[3], std::ios_base::binary);
  if(!outFile)
  {
    std::cout << "Cannot read file " << argv[3] << std::endl;
    inFile.close();
    return 2;
  }
  if(strcmp(argv[1], "-e") == 0)
  {
    encodeRLE(inFile, outFile);
  }
  else if(strcmp(argv[1], "-d") == 0)
  {
    decodeRLE(inFile, outFile);
  }
  else
  {
    help(argv[0]);
  }
  return 0;
}

