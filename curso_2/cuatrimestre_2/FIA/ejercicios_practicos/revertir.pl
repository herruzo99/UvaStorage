
revertir([X|[]],[X]).
revertir([X|X2],Y):- revertir(X2,Y2),append(Y2,[X],Y).
