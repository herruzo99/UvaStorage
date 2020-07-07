es_entero(0).
es_entero(X) :- es_entero(Y), X is Y+1.
