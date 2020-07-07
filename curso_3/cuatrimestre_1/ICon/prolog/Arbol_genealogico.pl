pareja_de(juan,ana).
padre_de(juan,julio).
padre_de(juan,elena).
padre_de(juan,fernando).

pareja_de(julio,maria).
padre_de(julio,isabel).
padre_de(julio,gonzalo).
padre_de(julio,alvaro).

pareja_de(elena,javier).
padre_de(javier,beatriz).
padre_de(javier,eva).
padre_de(javier,guillermo).

pareja(X,Y):-	pareja_de(Y,X),!;
		pareja_de(X,Y).
		
hijo(X,Y):- 	padre_de(Y,X); 
	  	(padre_de(Z,X),pareja(Z,Y)).

abuelo(X,Y):-	hijo(Y,Z),hijo(Z,X).

padre(X,Y):- 	hijo(Y,X).

hermano(X,Y):-	hijo(X,Z),!,hijo(Y,Z),X \= Y.

tio(X,Y):- 	hijo(Y,Z),hermano(Z,X).

primo(X,Y):- 	tio(Z,X),padre(Z,Y).

sobrino(X,Y):- 	tio(Y,X).


