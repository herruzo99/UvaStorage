
%adyacentes(X,Y,[X,Y|_]).
%adyacentes(X,Y,[Y,X|_]).
%adyacentes(X,Y, [Z|Z2]):- adyacentes(X,Y,Z2).

adyacentes(X,Y,Z):- append(_,[X,Y|_],Z).
adyacentes(X,Y,Z):- append(_,[Y,X|_],Z).
