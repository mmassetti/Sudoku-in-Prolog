%casilla(?Valor,?F,?C)
%Representa una posición del tablero con su respectivo valor.
:-dynamic casilla/3.

%num(N)
%numeros válidos del tablero
num(1).
num(2).
num(3).
num(4).
num(5).
num(6).
num(7).
num(8).
num(9).

%sudoku(-L).
%Retorna una lista L que contiene 9 listas, cada una correspondiente a un bloque.
sudoku(L):- sud_aux(1,1,[],L).

%sud_aux(+F,+C,+Aux,-L).
% Retorna una lista L que contiene 9 listas, cada una correspondiente a un fila.
% Aux es una lista auxiliar donde se van guardando las filas.
sud_aux(10,1,[],[]). %Termino de leer el tablero
sud_aux(F,10,Aux,[Aux|L]):- F2 is F+1, sud_aux(F2,1,[],L). %Termino una fila.
sud_aux(F,C,Aux,L):- casilla(N,F,C), C2 is C+1, append(Aux,[N],Aux2), sud_aux(F,C2,Aux2,L). % Guardamos el punto
sud_aux(F,C,Aux,L):- not(casilla(_,F,C)), C2 is C+1, append(Aux,[0],Aux2), sud_aux(F,C2,Aux2,L). % No existe el punto

%resolver/0
%Resuelve el sudoku
resolver():- res(1,1).

%res(+F,+C)
%Resuelve el sudoku desde la casilla (F,C) hasta la casilla (9,9)
res(9,10).
res(F,10):- F2 is F+1, res(F2,1).
res(F,C):- not(casilla(_,F,C)), buscar_numero(N,F,C), insertar(N,F,C), C2 is C+1, res(F,C2).
res(F,C):- casilla(_,F,C), C2 is C+1, res(F,C2).

%buscar_numero(?N,+F,+C)
%Busca un numero válido para la casilla (F,C).
%F y C son numeros entre 1 y 9.
buscar_numero(N,F,C):- num(N), not(casilla(N,_,C)), not(casilla(N,F,_)), validar_bloque(N,F,C).

%validar_fila(+N,+F)
%Retorna true si es válido insertar el número N en la Fila F. 
%F pertenece al intervalo [1,9].
validar_fila(N,F):- not(casilla(N,F,_)).

%validar_columna(+N,+C)
%Retorna true si es válido insertar el número N en la columna C.
%C pertenece al intervalo [1,9].
validar_columna(N,C):- not(casilla(N,_,C)).

%validar_bloque(+N,+F,+C)
%Retorna true si es válido insertar el número N en la region de la casilla (F,C).
%F y C pertenecen al intervalo [1,9].
validar_bloque(N,F,C):- FMax is ((((F-1)//3)+1)*3), CMax is ((((C-1)//3)+1)*3),  
    					not(casilla(N,FMax,CMax)), C1 is CMax-1, not(casilla(N,FMax,C1)), C2 is CMax-2, not(casilla(N,FMax,C2)),
    					F1 is FMax-1, not(casilla(N,F1,CMax)), not(casilla(N,F1,C1)), not(casilla(N,F1,C2)), 
    					F2 is FMax-2, not(casilla(N,F2,CMax)), not(casilla(N,F2,C1)), not(casilla(N,F2,C2)).
    					

%insertar(+V,+F,+C)
%Agrega el número V en la casilla (F,C).
%Primero elimina todos los valores que tiene asignado la posición (F,C), para luego insertar.
insertar(V,F,C):- retractall(casilla(_,F,C)), asserta_2(casilla(V,F,C)).

%asserta_2(+casilla(V,F,C))
%Agrega el hecho casilla(V,F,C).
asserta_2(casilla(V,F,C)):- asserta(casilla(V,F,C)).
asserta_2(casilla(V,F,C)):- retract(casilla(V,F,C)), fail.

%eliminar(+F,+C)
%retorna true si elimina todos los valores que corresponden a la casilla (F,C)
%F es la fila y C la columna. F y C pertenecen al intervalo [1,9].
eliminar(F,C):- retractall(casilla(_,F,C)).

%eliminarTodo()
%retorna true si elimina todas las casillas del juego.
eliminarTodo():- retractall(casilla(_,_,_)).