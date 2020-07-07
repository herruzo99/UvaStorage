
prefijo(X,Y,[Y|X]) .

sufijo([X|[]],Y,[X|[Y]]).
sufijo([X|X2],Y,[X|Z]):- sufijo(X2,Y,Z).


