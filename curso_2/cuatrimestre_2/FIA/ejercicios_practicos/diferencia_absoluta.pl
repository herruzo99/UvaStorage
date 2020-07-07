dif_abs(X,Y,Z) :- X>=Y, Z is X-Y.
dif_abs(X,Y,Z) :- X<Y, Z is Y-X.

dif_abs2(X,Y,Z) :- X>=Y, !, Z is X-Y.
dif_abs2(X,Y,Z) :- X<Y, Z is Y-X.

dif_abs3(X,Y,Z) :- X>=Y, !, Z is X-Y.
dif_abs3(X,Y,Z) :- Z is Y-X.
