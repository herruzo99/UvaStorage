borrar(X,[X|Resto],Resto).
borrar(X,[Y|Resto],[Y|Resto1]):- borrar(X,Resto,Resto1).

insertar(X,L,Lgrande):-borrar(X,Lgrande,L).

descomponer([X|Resto],L):-borrar(X,[X|Resto],L).
descomponer([X|Resto],[X|L]):-descomponer(Resto,L).
descomponer([X|Resto],L):-descomponer(Resto,L).


borrarLis(X,Lgrande,L):-comprobarIguales(X,Lgrande,L).
borrarLis(X,[Y|Lgrande],[Y|L]):-borrarLis(X,Lgrande,L).

comprobarIguales([],L,L).
comprobarIguales([X|Resto],[X|Lgrande],L):-comprobarIguales(Resto,Lgrande,L).

%tres ultimos: append(L,[_,_,_],[a,b,c,d]).
miembro(X,Lista):-append([X],_,Lista).
miembro(X,[_|Y]):-miembro(X,Y).

ultimo([Lista|[]],Lista).
ultimo([_|Lista],X):- ultimo(Lista,X).

%append(_,[X|[]],[a,b,c]).

