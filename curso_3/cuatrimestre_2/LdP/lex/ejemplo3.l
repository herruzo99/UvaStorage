%{
#include <ctype.h>
%}
%%
[a-zA-Z]+ {
		for (int i=0; i <yyleng; i++)
			printf("%c", toupper(yytext[i]));
		printf(" %s \n", yytext);
	}
.|\n	;
