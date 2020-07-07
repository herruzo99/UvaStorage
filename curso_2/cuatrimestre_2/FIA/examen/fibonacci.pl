
fibonacci(0,0):-!.

fibonacci(1,1):-!.

fibonacci(N,X):-N2 is N-1, fibonacci(N2,X2),
		N3 is N-2, fibonacci(N3,X3),
		X is X2 + X3.
