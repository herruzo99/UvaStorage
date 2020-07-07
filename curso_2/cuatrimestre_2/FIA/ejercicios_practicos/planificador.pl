transporte(roma, 200).
transporte(londres, 250).
transporte(tunez, 150).

noche(roma,hotel,250).
noche(londres,holel,150).
noche(tunez,hotel,100).
noche(roma,hostal,150).
noche(londres,hostal,100).
noche(tunez,hostal,80).
noche(roma,camping,100).
noche(londres,camping,50).
noche(tunez,camping,50).


viaje(Ciudad,Estancia,Dias,Precio):-
	transporte(Ciudad,X),
	noche(Ciudad,Estancia,Y),
	X1 is X * 2,
	Y1 is Y * Dias,
	Precio is X1 + Y1.
