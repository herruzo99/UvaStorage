poblacion(eeuu, 203).
poblacion(india, 548).
poblacion(china, 800).
poblacion(brasil, 108). superficie(eeuu, 3).
superficie(india, 1).
superficie(china, 4).
superficie(brasil, 3).
densidad(X, Y) :-
poblacion(X, P),
superficie(X, S),
Y is P/S.
