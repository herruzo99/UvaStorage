
jefe_de(director,calidad).
jefe_de(calidad,teso).
jefe_de(calidad,ventas).
jefe_de(calidad,comercial).
jefe_de(teso,asesor).
jefe_de(asesor,contable).
jefe_de(ventas,op1).
jefe_de(ventas,op2).
jefe_de(op1,r1).
jefe_de(op2,r2).
jefe_de(comercial,v1).
jefe_de(comercial,v2).


jefe(X,Y, P):- jefe_de(X,Y), P is 1.

jefe(X,Y, P):- jefe_de(X,Z),jefe(Z,Y, P1), P is P1 + 1.
