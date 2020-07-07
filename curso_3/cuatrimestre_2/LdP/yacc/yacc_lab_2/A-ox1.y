%{
#include<stdio.h>
void yyerror (char *s)  /* Called by yyparse on error */
{
  fprintf (stderr, "%s\n", s);
}
int yylex();
%}
%token CART PLOW AND HORSE GOAT OX 
%% 
phrase        : cart_animal AND CART {printf ("REGLA 1\n");}
              | work_animal AND PLOW {printf ("REGLA 2\n");}
              ;    
cart_animal   : HORSE  {printf ("REGLA 3\n");}
              | GOAT   {printf ("REGLA 4\n");}
              ;    
work_animal   : HORSE  {printf ("REGLA 5\n");}
              | OX     {printf ("REGLA 6\n");}
              ;    
%%
int main(){
 yyparse();
}
