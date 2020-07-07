
combinatorio(M,N,A):-	M>0,N>0,M>=N,
			fac(M,X),fac(N,Y),M2 is M-N,fac(M2,Z),A is X/(Y*Z).

comEf(M,N,A):-N2 is N+1,fac2(M,N2,X),M2 is M-N, fac(M2,Y), A is X/Y.

fac(1,1).
fac(X,Y):-X2 is X-1,fac(X2,Z), Y is X*Z.

fac2(Y,Y,Y).
fac2(X,Y,A):-X2 is X-1, fac2(X2,Y,A2), A is A2*X. 
