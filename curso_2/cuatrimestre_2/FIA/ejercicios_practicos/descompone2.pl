descompone([X|L1], [X], L1).
descompone([X|L1], [X|L2],L3 ) :- descompone(L1,L2,L3).
descompone([X|L1], L2, [X|L3]) :- descompone(L1,L2,L3).
