borrar(X,[X|L],Y):- append([],L,Y).
borrar(X,[A|L],Y):- borrar(X,L,Y1),append([A],Y1,Y).

