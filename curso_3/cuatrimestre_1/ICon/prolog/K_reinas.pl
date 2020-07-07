diagReina(_,[]).
diagReina(H,[T|T2]):-diagUp(H,[T|T2]), diagDown(H,[T|T2]),diagReina(T,T2).

diagUp(_,[]).
diagUp(H,[T|T2]):- H1 is H+1, T \= H1, diagUp(H1,T2).

diagDown(_,[]).
diagDown(H,[T|T2]):- H1 is H-1, T \= H1, diagDown(H1,T2).
reinas(N,[H|T]):-numlist(1,N,X),
		 permutation([H|T],X),
		 diagReina(H,T).







diagReinas(_,[],_):- true.
diagReinas(Reina, [C|L], Col):- (Reina + Col) =\= C,(Reina - Col) =\= C,diagReinas(Reina, L, Col+1).
diagsOK([]):- !.
diagsOK([P|F]):- diagReinas(P, F, 1),diagsOK(F).
reinasClase(N, Res):-numlist(1, N, Base),permutation(Res, Base),diagsOK(Res).
