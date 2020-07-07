
a_binario(X,[Z]):-	X =< 1,
			Z is X, !.

a_binario(X,Y):-	X2 is X//2,
			Y2 is X - (X2*2),
			a_binario(X2,Z),
			append(Z,[Y2],Y).
