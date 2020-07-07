valor(w1, 1).
conectado(w2, w1).
conectado(w3, w2).
valor(W,X):-conectado(W,V), valor(V,X).
casa(X):- (a(Y),b()), mesa().
a(Z):- c(),d().
builtin(A is B). 	builtin(A > B). 	builtin(A < B).
builtin(A = B). 	builtin(A =:= B). 	builtin(A =< B).
builtin(A >= B). 	builtin(functor(T, F, N)).
builtin(read(X)). 	builtin(write(X)).

solve(true,true) :- !.
solve((A, B), (ProofA, ProofB)) :-!, solve(A, ProofA), solve(B, ProofB).
solve(A, (A:-builtin)):- builtin(A), !, A.
solve(A, (A:-Proof)) :- clause(A, B), solve(B, Proof).
