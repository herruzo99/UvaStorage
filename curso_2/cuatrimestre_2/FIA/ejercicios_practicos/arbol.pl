es_un(gorriolin1,gorrion).
es_un(gorriolin2,gorrion).
es_un(gorrion,ave).
es_un(piolin, canario).
es_un(canario,ave).
es_un(ave,animal).
es_un(catalina, oveja).
es_un(oveja,mamifero).
es_un(rosenda,vaca).
es_un(vaca, mamifero).
es_un(mamifero, animal).

es_un(X,Y):- es_un(X,Z), es_un(Z,Y).

atributo(animal,puede,respirar).
atributo(ave,vuela,bien).
atributo(ave,tiene,plumas).
atributo(ave,pone,huevos).
atributo(gorrion,patas,cortas).
atributo(gorriolin1,color,marron).
atributo(canario,canta,bien).
atributo(piolin,color,amarillo).
atributo(mamifero,da,leche).
atributo(oveja,da,lana).
atributo(vaca,come,hierba).
atributo(rosenda,color,amarillo).

atributo(X,Y,Z):- es_un(X,X2), atributo(X2,Y,Z).
