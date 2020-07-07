enlinea([1, 2, 3]).
enlinea([4, 5, 6]).
enlinea([7, 8, 9]).
enlinea([1, 4, 7]).
enlinea([2, 5, 8]).
enlinea([3, 6, 9]).
enlinea([1, 5, 9]).
enlinea([3, 5, 7]).
amenaza([X, Y, Z], B, X):- vacio(X, B), cruz(Y, B), cruz(Z, B).
amenaza([X, Y, Z], B, Y):- cruz(X, B), vacio(Y, B), cruz(Z, B).
amenaza([X, Y, Z], B, Z):- cruz(X, B), cruz(Y, B), vacio(Z, B).

vacio(Casilla, Tablero):- argumento(Casilla, Tablero, Valor), Valor=[].
cruz(Casilla, Tablero):- argumento(Casilla, Tablero, Valor), Valor=x.
cara(Casilla, Tablero):- argumento(Casilla, Tablero, Valor), Valor=o.

argumento(Posicion, Lista, X):- Posicion=1, arg(1, Lista, X).
argumento(Posicion, Lista, X):- Posicion>1, arg(2, Lista, Y), Pos is Posicion-1, argumento(Pos, Y, X).

movimiento_forzoso(Tablero, Casilla) :- enlinea(Linea), amenaza(Linea, Tablero, Casilla), !.