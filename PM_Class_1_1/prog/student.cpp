#include "student.h"

std::ostream& operator<<(std::ostream& out, const student& st)
{
  out << "st" << st.number << ". "
      << st.surname << " " << st.name << ":";

  const char marks[] = "-FEDCBA";
  for(int i = 0; i < marksCount; ++i)
  {
    out << ' ' << marks[st.marks[i] % 7 ];
  }
  return out;
}


std::istream& operator>>(std::istream& in, student& st)
{
  std::cout << "Student number: ";
  in >> st.number;
  in.ignore();
  std::cout << "Surname: ";
  in.getline(st.surname, 32);
  std::cout << "Name: ";
  in.getline(st.name, 316);
  for (int i = 0; i < marksCount; ++i) st.marks[i] = 0;
  return in;
}
