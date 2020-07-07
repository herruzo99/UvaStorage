libro_de_familia(
esposo(nombre(antonio,garcia,fernandez), profesion(arquitecto), salario(30000)),
esposa(nombre(ana,ruiz,lopez), profesion(docente), salario(12000)),
domicilio(sevilla)).

libro_de_familia(
esposo(nombre(luis,alvarez,garcia), profesion(arquitecto), salario(40000)),
esposa(nombre(ana,romero,soler), profesion(sus_labores), salario(0)),
domicilio(sevilla)).

libro_de_familia(
esposo(nombre(bernardo,bueno,martinez), profesion(docente), salario(12000)),
esposa(nombre(laura,rodriguez,millan), profesion(medico), salario(25000)),
domicilio(cuenca)).

libro_de_familia(
esposo(nombre(miguel,gonzalez,ruiz), profesion(empresario), salario(40000)),
esposa(nombre(belen,salguero,cuevas), profesion(sus_labores), salario(0)),
domicilio(dos_hermanas)).

profesional_de(Nombre,X):- libro_de_familia(esposo(Nombre,profesion(X),_),_,_).
profesional_de(Nombre,X):- libro_de_familia(_,esposa(Nombre,profesion(X),_),_).

salario_familia(N,P1,P2,Nf,P1f,P2f,S):-
	libro_de_familia(esposo(nombre(N,P1,P2),_,salario(X)),esposa(nombre(Nf,P1f,P2f),_,salario(Y)),
	g is X + Y.
