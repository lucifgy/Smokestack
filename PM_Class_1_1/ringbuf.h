#pragma once

struct ringbuf
{
  char* data;
  int size;
  int start;
  int finish;
};

void init(ringbuf& buf, int size);}
void destruct(ringbuf& buf);
bool isempty(ringbuf& buf);
char& get(ringbuf& buf, int index);
void push(ringbuf& buf, char ch);
int strdiff(ringbuf& x, const char y, int length);
