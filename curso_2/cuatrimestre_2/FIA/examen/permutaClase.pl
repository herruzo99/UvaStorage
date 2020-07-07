burbuja(L,O):-permuta(L,L1),!,burbuja(L1,O).
burbuja(O,O).
permuta([X,Y|R],[Y,X|R]):-X>Y.
permuta([Z|R],[Z|R1]):- permuta(R,R1).
