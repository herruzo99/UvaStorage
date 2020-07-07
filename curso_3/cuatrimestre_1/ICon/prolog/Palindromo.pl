palindromo(P):-name(P,X), invertir(X,X2), igual(X,X2).

invertir([],[]).
invertir([X|X2],Y):- invertir(X2,Y2), append(Y2,[X],Y).

igual([],[]).
igual([X|X2],[X|Y2]):- igual(X2,Y2).
