%%%%%%%%%%%%%%%%%%%%%%%%%%%
%   Programa restaurante  %
%%%%%%%%%%%%%%%%%%%%%%%%%%%	

% menu

entrada(paella).
entrada(gazpacho).
entrada(consome).

carne(filete_de_cerdo).
carne(pollo_asado).

pescado(trucha).
pescado(bacalao).

postre(flan).
postre(nueces_con_miel).
postre(naranja).

bebida(vino).
bebida(cerveza).
bebida(agua).

% Valor calorico de una ración

calorias(paella, 200).
calorias(gazpacho, 150).
calorias(consome, 300).
calorias(filete_de_cerdo, 400).
calorias(pollo_asado, 280).
calorias(trucha, 160).
calorias(bacalao, 300).
calorias(flan, 200).
calorias(nueces_con_miel, 500).
calorias(naranja, 50).
calorias(agua, 0).
calorias(vino, 83).
calorias(cerveza, 43).

% plato_principal(P) P es un plato principal si es carne o pescado

plato_principal(P):- carne(P).
plato_principal(P):- pescado(P).

% comida(Entrada, Principal, Postre, Bebida)

comida(Entrada, Principal, Postre, Bebida):-
        entrada(Entrada),
        plato_principal(Principal),
        postre(Postre),
	bebida(Bebida).
    
% Valor calorico de una comida

valor(Entrada, Principal, Postre, Bebida, Valor):-
        calorias(Entrada, X),
        calorias(Principal, Y),
        calorias(Postre, Z),
	calorias(Bebida, W),
        sumar(W, X, Y, Z, Valor).

% comida_equilibrada(Entrada, Principal, Postre, Bebida)

comida_equilibrada(Entrada, Principal, Postre, Bebida):-
        comida(Entrada, Principal, Postre, Bebida),
        valor(Entrada, Principal, Postre, Bebida, Valor),
        menor(Valor, 600).

comida_de(Entrada, Principal, Postre, Bebida, Calorias):-
        comida(Entrada, Principal, Postre, Bebida),
        valor(Entrada, Principal, Postre, Bebida, Valor),
        menor(Valor, Calorias).


% Conceptos auxiliares

sumar(W, X, Y, Z, Res):-
        Res is W + X + Y + Z.             % El predicado "is" se satisface si Res se puede unificar
                                      % con el resultado de evaluar la expresión X + Y + Z 
menor(X, Y):- 
        X < Y.                        % "menor" numérico

dif(X, Y):-
        X =\= Y.                      % desigualdad numérica 


    