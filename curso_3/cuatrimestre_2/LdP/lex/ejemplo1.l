%{
#define MINUMERO 5
//comentario1
%}
 //Espacio en blanco comentaio2
 int p4();
%%
 //comentario 3
[AP_Z] p4();
%%
// comentario 2
int p4(){
	printf("%d ",MINUMERO);
}
int main(){
	yylex();
}

