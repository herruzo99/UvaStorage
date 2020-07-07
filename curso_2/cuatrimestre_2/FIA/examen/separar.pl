


separar([N|[]],[],[Y]):- N < 0, !,Y is N. 
separar([N|[]],[X],[]):-!,X is N. 

separar([N|N2],X,[Y|Y2]):- N < 0, !,Y is N, separar(N2,X,Y2). 
separar([N|N2],[X|X2],Y):-!,X is N, separar(N2,X2,Y). 

