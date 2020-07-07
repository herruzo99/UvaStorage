divide(N1, N2, Resultado):-
	es_entero(Resultado),
	Producto1 is Resultado*N2,
	Producto2 is (Resultado+1)*N2,
	Producto1 =< N1,
	Producto2>N1,!	.

es_entero(0).
es_entero(N) :- es_entero(Y), N is Y+1.
