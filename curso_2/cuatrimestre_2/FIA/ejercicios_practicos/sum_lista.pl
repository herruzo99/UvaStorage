

sum_lista([X|[]],X):-!.
sum_lista([X|Z],Y):-sum_lista(Z,Y2), Y is Y2+X.
