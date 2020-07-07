sumar([],X):- X is 0.
sumar([H|T],X):- sumar(T, X1), X is X1 + H.
