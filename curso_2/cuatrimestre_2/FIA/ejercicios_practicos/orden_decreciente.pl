
orden_creciente([X|[]]):- !.
orden_creciente([X,Y|Z]):- X > Y, orden_creciente([Y|Z]).
