
factorial(1,1):-!.
factorial(N,X):-N2 is N-1, factorial(N2,X2), X is X2*N.
