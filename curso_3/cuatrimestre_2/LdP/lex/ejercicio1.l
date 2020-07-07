%%
[\ |\t]+"//".*\n			;
-?[0-9]+			printf("INT");
[0-9]+\.[0-9]+ 			|
[0-9]+[eE][+-]?[0-9]+		|
[0-9]+\.[0-9]+[eE][+-]?[0-9]+  printf("FLOAT");	
if				printf("IF");
else				printf("ELSE");
[a-zA-Z][a-zA-Z0-9]*		printf("ID(%s %d)", yytext,yyleng);
"/*"(.|\n)*"*/"			;

