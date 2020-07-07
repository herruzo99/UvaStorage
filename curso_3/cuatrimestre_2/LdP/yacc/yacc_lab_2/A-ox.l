%{
#include<stdio.h>
#include"y.tab.h"
%}
%%
[ \t]+	;
c	return CART;
p	return PLOW;
a	return AND;
h	return HORSE;
g	return GOAT;
o	return OX;
.|\n	return 0;
