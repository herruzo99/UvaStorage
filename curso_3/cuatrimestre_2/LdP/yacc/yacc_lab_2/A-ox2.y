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
phrase        : cart_animal CART {printf ("REGLA 1\n");}
              | work_animal PLOW {printf ("REGLA 2\n");}
              ;    
cart_animal   : HORSE AND {printf ("REGLA 3\n");} 
              | GOAT AND  {printf ("REGLA 4\n");} 
              ;    
work_animal   : HORSE AND {printf ("REGLA 5\n");} 
              | OX AND    {printf ("REGLA 6\n");} 
              ;    
%%
int main(){
 yyparse();
}
