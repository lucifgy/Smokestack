#include "ringbuf.h"

void init(ringbuf& buf, int size)
{
  buf.data = new char[size];
  buf.size = size;
  buf.start = -1;
  buf.finish = -1;
}

void destruct(ringbuf& buf)
{
  delete[] buf.data;
  buf.data = nullptr;
  buf.size = 0;
  buf.start = -1;
  buf.finish = -1;
}

bool isempty(ringbuf& buf)
{
  return buf.start == -1;
}

char& get(ringbuf& buf, int index)
{
  return buf.data[(buf.start + index) % buf.size];
}

void push(ringbuf& buf, char ch)
{
  if(isempty(buf))
  {
    buf.start = buf.finish = 0;
    buf.data[buf.start] = ch;
    return;
  }
  ++buf.finish;
  buf.finish = buf.finish % buf.size;
  buf.data[buf.finish] = ch;
  if(buf.finish == buf.start)
  {
    ++buf.start;
    buf.start = buf.start % buf.size;
  }
}

int strdiff(ringbuf& x, const char y, int length)
{
  int pos = length -1;
  while(y[pos] == get(x,pos))
  {
    --pos;
    if(pos < 0)
    {
      return -1;
    }
  }
  return pos;
}
