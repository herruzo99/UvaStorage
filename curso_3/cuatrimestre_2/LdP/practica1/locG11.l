%{
#include<stdio.h>
#include<stdbool.h>

int locf = 0; // Lineas Fisicas
int locp = 0; // Lineas de codigo
int locc = 0; // Lineas de comentario
int locw = 0; // Lineas en blanco
int complexity = 1; // Complejidad ciclomatica
bool code = false;
bool comment = false;

void countComplexity();
void countLine(bool comm);
%}
MCCABE "if"|"for"|"while"|"case"|"catch"|"&&"|"||"|"?"
%x BC
%x STRING
%%
[ \t]			; // Tabuladores, y espacios en blanco, no cuentan
'\\?\"'			{code = true;} // Caso excepcional de tener una comilla como char
\"			{code = true; BEGIN STRING;} // Strings
<STRING>[^\\]\"		{BEGIN INITIAL;}
<STRING>.		; // mientras estemos en un String, tragamos lo que sea
[^a-zA-Z0-9_]{MCCABE}[^a-zA-Z0-9_\n] {countComplexity();} // Calcula complejidad ciclomatica
[^a-zA-Z0-9_]{MCCABE}\n  {countComplexity(); countLine(comment);} // Calcula complejidad
"/*"			{comment = true; BEGIN BC;} // Abre comentario grande
<BC>.			; // Mientras este en comentario, llegue lo que llegue que no sea salto, traga
<BC>"*/"[ \t]*\n	{countLine(true); comment = false; BEGIN INITIAL;}
<BC>"*/"		{comment = false; BEGIN INITIAL;} // Cierra comentario grande
"//".*\n		{countLine(true);} // Linea de comentario simple
<BC>\n			{countLine(comment);} // Salto de linea en comentario
.			{code = true;} // Considera caracter normal como codigo
\n			{countLine(comment);} // Cualquier otro salto de linea

%%

/* Cuenta complejidad ciclomatica de Mc Cabe si no esta en un comentario */
void countComplexity(){
	if(!comment){
		complexity++;
		code = true;
	} 
}

void countLine(bool comm){
	if(code){
		locp++;
		code = false;
	}else if(comm){
		locc++;
	}else{
		locw++;
	}
	locf++;
}

int main(int argc, char** argv){
	
	// Entrada Lex
	if ( argc > 1 ){
		yyin = fopen( argv[1], "r" );
	}else{
		yyin = stdin;
	}
 	yylex();

	// Salida a Std output
	printf("Resultados del analisis del codigo JAVA:\n");
	printf("LOCF: %d\n", locf);
	printf("LOCP: %d\n", locp);
	printf("LOCC: %d\n", locc);
	printf("LOCW: %d\n", locw);
	printf("Complejidad ciclomatica: %d\n", complexity);

	// Escritura a fichero
	FILE* file;
	file = fopen("SalidaG11.txt", "w");
	fprintf(file, "%d\n%d\n%d\n%d\n%d\n", locf, locp, locc, locw, complexity);
	fclose(file);

	return 0;

}




