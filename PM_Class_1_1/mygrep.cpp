#include <iostream>
#include <fstream>
#include "ringbuf.h"

int strdiff(const char* x, const char y, int length)
{
  int pos = length -1;
  while(y[pos] == x[pos])
  {
    --pos;
    if(pos < 0)
    {
      return -1;
    }
  }
  return pos;
}

int main(int argc, char* argv[])
{
  if(argc < 3)//TODO
  {
  }
  std::ifstream inFile(argv[1]);
  if(!inFile)//TODO
  {
  }

  char* what = argv[2];

  int positions[256];
  for (int i = 0; i < 256; ++i)
  {//TODO
  }
  int length = 0;
  for (int i = 0; what[i] != '\n'; ++i)
  {//TODO
  }

  ringbuf buf;
  init(buf, length);

  int count = 0;
  int linenumver = 1;
  int charnumber = 0;
  int shift = length;

  while(true)
  {
    while(shift > 0)
    {
      int ch = inFile.get();
      if(ch == EOF)
        break;
      ++charnumber;
      if(ch == '\n')
      {
        //TODO
      }
    }
  }

  destruct(buf);
  inFile.close();
  return 0;
}
