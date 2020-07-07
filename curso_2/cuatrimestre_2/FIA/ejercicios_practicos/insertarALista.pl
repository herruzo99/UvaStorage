insertar(X,L,Y):- append([X],L,Y).
insertar(X,[A|L],Y):- insertar(X,L,Y1),append([A],Y1,Y).

