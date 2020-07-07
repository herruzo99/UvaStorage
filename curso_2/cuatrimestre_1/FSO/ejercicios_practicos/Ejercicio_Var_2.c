#include <stdio.h>
int main ( void ){

      short c = 0xff;
      short c2 = 0x00;
      int d = 0x7fffffff;
      int d2 = 0x80000000;
      long l = 0x7fffffffffffffff;
      long l2 = 0x8000000000000000;
      long long ll =0x7fffffffffffffff;
      long long ll2 =0x8000000000000000;
      printf("short: %d  %d int: %d  %d long: %ld %ld long long: %Ld  %Ld   \n",
      c,c2,d,d2,l,l2,ll,ll2);
  return 0;
}
