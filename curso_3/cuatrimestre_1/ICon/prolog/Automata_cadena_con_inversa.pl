mueve(q0,a,[z],q0,[a|z]).
mueve(q0,b,[z],q0,[b|z]).
mueve(q0,a,X,q0,[a|X]).
mueve(q0,b,X,q0,[b|X]).
mueve(q0,e,[X|Y],q1,[X|Y]).
mueve(q1,X,[X|Y],q1,Y).
mueve(q1,[],[z],qf,[z]).

transita(q1,[],z,qf,[z]):-!.
transita(Qi,[X|Y],R,Qf,T):- X\=[],mueve(Qi,X,R,P,S), transita(P,Y,S,Qf,T).

acepta(X,Resultado) :- transita(q0,X,[z],Q,_), Q=qf,Resultado is 1, !.
