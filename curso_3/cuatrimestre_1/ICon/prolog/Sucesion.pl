sucesion(2):-!.
sucesion(X):- X>2, X2 is X - 3, sucesion(X2).
