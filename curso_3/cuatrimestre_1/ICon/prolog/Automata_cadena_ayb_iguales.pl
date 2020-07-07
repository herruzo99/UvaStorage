mueveC(q0, a, [z], q0, [a|z]).
mueveC(q0, a, [a|H], q0, [a|[a|H]]).
mueveC(q0, b, [a|H], q1, H).
mueveC(q1, b, [a|H], q1, H).
mueveC(q1,[],[z],qf,[z]).
transita(q1,[],z,qf,[z]):-!.
transita(Qi, [X|Y], R, Qf, T):- X \=[],mueveC(Qi, X, R, P, S), transita(P, Y, S, Qf, T).
aceptaC(X,Resultado) :- transita(q0,X,[z],Q,_), Q=qf,Resultado is 1, !.

