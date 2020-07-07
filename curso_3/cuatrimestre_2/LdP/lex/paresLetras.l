%{  
	int digraf[26][26]; 
%} 

%%
[a-z][a-z] {
		digraf[ yytext[0]-'a' ][ yytext[1]-'a' ]++;
		REJECT;
		} 
%%
int main(){ 
	yylex();  
	for (int i=0; i<26; i++){ 
		for (int j=0; j<26;j++)
			printf ("%d ", digraf[i][j]);printf ("\n");
		}
	}
