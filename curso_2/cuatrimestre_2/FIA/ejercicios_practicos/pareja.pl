chico(juan).
chico(pedro).
chico(roberto).
chica(maria).
chica(ana).
chica(rosa).
chica(marta).

posible_pareja(X, Y):- !, chico(X), chica(Y).
posible_pareja2(X, Y):- chico(X), !, chica(Y).
posible_pareja3(X, Y):- chico(X), chica(Y), !.
