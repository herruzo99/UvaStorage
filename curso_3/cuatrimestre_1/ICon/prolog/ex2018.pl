mueve(q0,a,[z],q0,[a|[z]]).
mueve(q0,a,H,q0,[a|H]).
mueve(q0,b,[a|H],q1,H).
mueve(q0,b,[z],q2,[b|[z]]).

mueve(q1,b,[a|H],q1,H).
mueve(q1,b,[z],q2,[b|[z]]).

mueve(q2,b,H,q2,[b|H]).
mueve(q2,c,[b|H],q3,H).

mueve(q3,c,[b|H],q3,H).
mueve(q3,[],[z],qf,[z]).

transita(_,[],[z],qf,[z]).
transita(Q,[H|T],P,Qf,Pf):- mueve(Q,H,P,Q2,P2), transita(Q2,T,P2,Qf,Pf).



acepta(Lista,R):- transita(q0,Lista,[z],Qf,P), Qf = qf, R is 1.


solve_trace(true,_):-!.
solve_trace((A,B),Nivel):- solve_trace(A,Nivel), solve_trace(B,Nivel).
solve_trace(A,_):- builtin(A) , !, A.
solve_trace(A,Nivel):- clause(A,B),tab(Nivel*3), write(Nivel), write(" "), write(A), nl,Nivel2 is Nivel + 1, solve_trace(B,Nivel2).

builtin(A\=B).
builtin(!).
builtin(A=B).
builtin(A is B).
builtin(read(A)).
builtin(true).

solve_traza(true,_,0):-!.
solve_traza( (A,B), N, Prf) :-!,solve_traza(B,N,Prf), solve_traza(A,N,Prf).
solve_traza(A,_,_):- builtin(A) , !, A.
solve_traza(A,N,Prf):-
    Prf>=0, P is (Prf-1),
    N3 is (N*3), tab(N3), write(N), write(' Call: '), write(A), nl,
    clause(A,B), N1 is N+1, solve_traza(B,N1,P),
    tab(N*3),write(N),
    write(' Exit: '), write(A), nl.
solve_traza(A, Prf):-solve_traza(A, 0, Prf).
