
burbuja(N,X):-bur(N,X),ordenado(X),!.
burbuja(N,X2):- bur(N,X), burbuja(X,X2),!.

bur([N,N2|[]],[N2,N]):- N2 < N, !.
bur([N,N2|[]],[N,N2]):-!.
bur([N,N2|N3],[N2|X]):-N2 < N,!, bur([N|N3], X).
bur([N,N2|N3],[N|X]):- bur([N2|N3], X).


ordenado([N,N2|[]]):- N =< N2.
ordenado([N,N2|N3]):- N =< N2, ordenado([N2|N3]).



%burbuja(N,[Z|X]):- bur(N,X,Z).

%bur([N,N2|[]],[N], N2):- N2 < N, !.
%bur([N,N2|[]],[N2],N):-!.
%bur([N|N3],[X2|X],Z):- bur(N3, X,Z2), Z2 < N, !, Z is Z2, X2 is N.
%bur([N|N3],[X2|X],Z):- bur(N3, X,Z2), !, Z is N, X2 is Z2.
