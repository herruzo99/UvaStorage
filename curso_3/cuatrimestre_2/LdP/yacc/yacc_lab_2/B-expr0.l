%{
#include <stdio.h>
#include "y.tab.h"
%}
entero	[1-9][0-9]*|0
%%
[ \t]+	;
{entero}	{
			printf ("      %s\n", yytext);
			return NUM;
		}
.|\n	{ 
		return yytext[0];
	}

