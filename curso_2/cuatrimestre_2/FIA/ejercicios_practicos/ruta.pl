enlace(a,b,1).
enlace(a,c,10).
enlace(c,d,100).
enlace(c,e,1000).
enlace(b,d,10000).
enlace(e,k,100000).
enlace(a,k,1000000).

ruta(X,Y,Dist):- enlace(X,Y,D), sum(D,0,Dist).
ruta(X,Y,Dist):- enlace(X,Z,D), ruta(Z,Y,Res), sum(D,Res, Dist).




sum(X,Y,Z):-
	Z is X + Y.

%Al intentar quitar los arcos el programa no encuentra rutas optimas
%y por ejemplo para ir de a a e hace: a-b-a