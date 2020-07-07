

del_cuarto(X,Z):-dele(X,Z,1).

dele([X|Y],Y,4):- !.
dele([X|Y],[X|Z],Num):- Num2 is Num+1,dele(Y,Z,Num2).
