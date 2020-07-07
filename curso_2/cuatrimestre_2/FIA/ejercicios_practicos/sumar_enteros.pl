sumara(1, 1) :- !. 
sumara(N, X) :- N1 is N-1, sumara(N1, Res), X is Res+N.

sumara2(N, N) :- N=<1, !.
sumara2(N, X) :- N1 is N-1, sumara2(N1, Res), X is Res+N.
