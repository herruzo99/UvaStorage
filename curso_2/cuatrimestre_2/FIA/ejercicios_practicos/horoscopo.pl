dias(enero,31).
dias(febrero,28).
dias(marzo,31).
dias(abril,30).
dias(mayo,31).
dias(junio,30).
dias(julio,31).
dias(agosto,31).
dias(septiembre,30).
dias(octubre,31).
dias(noviembre,30).
dias(diciembre,31).

mes(enero,1).
mes(febrero,2).
mes(marzo,3).
mes(abril,4).
mes(mayo,5).
mes(junio,6).
mes(julio,7).
mes(agosto,8).
mes(septiembre,9).
mes(octubre,10).
mes(noviembre,11).
mes(diciembre,12).

signo(aries,20,3,19,4).
signo(tauro,20,4,20,5).
signo(geminis,21,5,20,6).
signo(cancer,21,6,22,7).
signo(leo,23,7,22,8).
signo(virgo,23,8,22,9).
signo(libra,23,9,22,10).
signo(escorpio,23,10,21,11).
signo(sagitario,22,11,21,12).
signo(capricornio,22,12,19,1).
signo(acuario,20,1,17,2).
signo(piscis,18,2,19,3).

mi_signo(Mes,Dia,S):-
	dias(Mes,X),
	Dia =< X,
	signo(S,DiaI,MesI,DiaF,MesF),
	mes(Mes,NumMes),
	dentro(MesI,NumMes,MesF,DiaI,Dia,DiaF).

dentro(MI,M,MF,_,_,_):-
	MI < M,
	MF > M.

dentro(MI,M,_,DI,D,_):-
	MI = M,
	DI =< D.
dentro(_,M,MF,_,D,DF):-
	MF = M,
	DF >= D.
