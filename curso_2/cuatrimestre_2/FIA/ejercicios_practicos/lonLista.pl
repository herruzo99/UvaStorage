lonLista([],X):- X is 0.
lonLista([_|L],X):- lonLista(L,X1), X is X1 + 1.
