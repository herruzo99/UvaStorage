pieza_basica(cadena).
pieza_basica(pedales).
pieza_basica(plato).
pieza_basica(eje).
pieza_basica(sillín).
pieza_basica(manillar).
pieza_basica(radios).
pieza_basica(llanta).
pieza_basica(piñones).

ensamblaje(bicicleta, [rueda_delantera, cuadro, rueda_trasera]).
ensamblaje(rueda_delantera, [eje, radios, llanta]).
ensamblaje(cuadro, [manillar, sillín, tracción]).
ensamblaje(tracción, [eje, plato, pedales, cadena]).
ensamblaje(rueda_trasera, [llanta, radios, eje, piñones]).

piezas_de(X,Y):- ensamblaje(X,Z), piezas_lis(Z,Y).

piezas_lis([],[]).
piezas_lis([X|X2],[X|Y2]):- pieza_basica(X), piezas_lis(X2,Y2).
piezas_lis([X|X2], Y):- ensamblaje(X,Z), append(Z,X2,N),piezas_lis(N,Y).

%%De clase
listapiezas([],[]).
listapiezas([H|T],X):- piezasde(H,PH),listapiezas(T,PT), append(PH,PT,X).

piezasde([],[]).
piezasde(X,[X]):- pieza_basica(X).
piezasde(X,Y):- ensamblaje(X,Sub),listapiezas(Sub,Y).
