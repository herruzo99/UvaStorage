mueve(q0,b,q1).
mueve(q0,a,q2).
mueve(q1,a,q0).
mueve(q1,b,q3).
mueve(q2,a,q2).
mueve(q2,b,q3).
mueve(q3,a,q0).
mueve(q3,b,q3).

final(q1).

acepta(X,[]):- final(X).
acepta(X,[Y|Y2]):-mueve(X,Y,Z), acepta(Z,Y2).


transita(X,[],X):- !.
transita(X,[A|B],Y):-mueve(X,A,P),transita(P,B,Y),!.
aceptaClase(C):- transita(q0,C,Q), Q = q1.
