miembro(X, [Y|_]):- X=Y.
miembro(X,[_|Y]) :- miembro(X,Y).
