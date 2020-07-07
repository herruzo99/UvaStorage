
maximo([X,X2|[]],Y):- Y is max(X,X2).

maximo([X|X2],Y):-maximo(X2,Y2), Y is max(X,Y2).
