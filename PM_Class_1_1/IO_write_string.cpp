#include <iostream>
#include <fstream>

int main()
{

  const char* fname = "output.txt";
  std::ofstream outFile(fname, std::ios_base::binary);

  if(!outFile)
  {
    std::cout << "Can't open file" << fname << std::endl;
    return 0;
  }
  int n = 100;
  int* M = new int[n];
  M[0] = 1;
  M[1] = 1;
  for (int i = 2; i < n; ++i)
  {
    M[i] = M[i-1] + M[i-2];
  }
  /*
  outFile << n << std::endl;
  for(int i = 0; i < n; ++i)
  {
    outFile << M[i] << " ";
  }
  */
  outFile.write(reinterpret_cast<const char*>(&n), sizeof(n));
  //outFile.write((const char*)(&n), sizeof(n));
  outFile.write(reinterpret_cast<const char*>(M), n * sizeof(M[0]));

  const char str [] = "Hello, world(char *)";
  ////outFile.write(reinterpret_cast<const char*>(), sizeof());
  outFile.write(str, sizeof(str));


  /*
  std::string s("Hello, world (string)");
  //outFile.write(reinterpret_cast<const char*>(&s), sizeof(s));
  outFile.write(s.c_str(), s.size());
  */
  outFile.close();

  std::ifstream inFile(fname, std::ios_base::binary);

  if(!inFile)
  {
    std::cout << "Cannot read file " << fname << std::endl;
    return 0;
  }
  int m;
  //inFile.read((char*)(&m), sizeof(m));
  inFile.read(reinterpret_cast<char*>(&m), sizeof(m));
  int* A = new int[m]; 
  inFile.read(reinterpret_cast<char*>(A), m * sizeof(A[0]));
  for(int i = 0; i < m; ++i)
  {
    std::cout << A[i] << " ";
  }
  std::cout << std::endl;

  char str2[1024];
  int ch;
  int ind = 0;

  while((ind<1023) && ((ch = inFile.get()) != EOF))
  {
    str2[ind++] = ch;
    if (ch == '\0') break;
  }
  std::cout << str2 << std::endl;

  inFile.close();
  delete[] A;
  delete[] M;
  return 0;
}
