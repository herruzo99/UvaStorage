%{
#include <stdio.h>
#include "y.tab.h"
%}
entero	[1-9][0-9]*|0
%%
[ \t]+	;
{entero}	{ return NUM; }
.|\n		{ return yytext[0]; }

