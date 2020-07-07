
max_lista([X,Y|[]],Z):- X > Y, !, Z is X.
max_lista([X,Y|[]],Z):- Z is Y.

max_lista([X|Y],Z):- max_lista(Y,Z2), X > Z2, Z is X, !. 
max_lista([X|Y],Z):- max_lista(Y,Z2), Z is Z2, !. 
