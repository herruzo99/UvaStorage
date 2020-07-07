:-op(40, xfy, &).
:-op(50, xfy, --->).

true ---> live(outside).
true ---> live(p1).
true ---> live(p2).
true ---> light(l1).
true ---> light(l2).
true ---> down(s1).
true ---> up(s2).
true ---> up(s3).

light(L)& ok(L) & live(L)---> lit(L).
connected_to(W,W1) & live(W1)---> live(W).

up(s2) & ok(s2) ---> connected_to(w0,w1).
down(s2) & ok(s2) ---> connected_to(w0,w2).
up(s1) & ok(s1) ---> connected_to(w1,w3).
down(s1) & ok(s1) ---> connected_to(w2,w3).
up(s3) & ok(s3) ---> connected_to(w4,w3).


ok(cb1) ---> connected_to(w3,w5).
ok(cb2) ---> connected_to(w6,w5).
true ---> connected_to(p2,w6).
true ---> connected_to(l1,w0).
true ---> connected_to(l2,w4).
true ---> connected_to(p1,w3).
true ---> connected_to(w5,outside).
true ---> ok(_).


solve(true):-!.
solve((A & B)) :-!, solve(A), solve(B).
solve(A) :- (B ---> A), solve(B).

%Traza
solve_traza(true, Nivel):-!.
solve_traza((A & B), Nivel) :-!, solve_traza(A, Nivel), solve_traza(B, Nivel).
solve_traza(A, Nivel):-tab(Nivel*5),write(Nivel),write(": "), write(A),nl,
			(B ---> A), Nivel2 is Nivel + 1, solve_traza(B, Nivel2),
			tab(Nivel*5),write(Nivel),write(": "), write(A),nl.

%Meta diferida	
delay(ok(X)).

dsolve(true,D,D):-!.
dsolve((A & B), D1,D2) :-!, dsolve(A,D1,D3), dsolve(B,D3,D2).
dsolve(A,D,[A|D]) :- delay(A).
dsolve(A,D1,D2) :- (B ---> A), dsolve(B,D1,D2).

