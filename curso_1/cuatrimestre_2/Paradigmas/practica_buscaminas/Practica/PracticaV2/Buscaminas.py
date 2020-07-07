# -*-coding=utf-8-*-
from random import randint

# listas con los valores que hay que sumar a la fila y columna correspondiente a la celda de la cual se quiere mirar las
# 6 que la rodean
lis_pares = [[-1, 0], [-1, 1], [0, -1], [0, 1], [1, 0], [1, 1]]
lis_impares = [[-1, -1], [-1, 0], [0, -1], [0, 1], [1, -1], [1, 0]]


# Clase para cada celda individual
class Celda:
    # Cada celda tiene sus coordenadas y si esta abierta, marcada, con mina o no y el numero de minas a su alrededor
    def __init__(self, fila, columna, abierta=False, mina=False, marcada=False):
        self.fila = fila
        self.columna = columna
        self.abierta = abierta
        self.mina = mina
        self.marcada = marcada
        self.minas_vecinas = 0

    # Al abrir una celda pueden pasar tres cosas:
    # Que explotes si hay mina y no esta marcada (-1)
    # Que ya este abierta o marcada (0)
    # Que se pueda abrir sin problema (1)
    def abrir_celda(self):
        if self.mina and not self.marcada:
            return -1
        elif self.abierta or self.marcada:
            return 0
        else:
            return 1


# Clase que contiene al tablero y sus funciones
class Tablero:

    # Cuenta el numero de minas totales y restantes, el numero de celdas marcadas y el tamaño del tablero
    def __init__(self, minas=0, filas=0, columnas=0):
        self.minas = minas
        self.filas = filas
        self.columnas = columnas
        self.minas_restantes = minas
        self.fin = False
        self.matriz = []

    # Se le un archivo que tenga el formato pedido en la practica
    def leer_matriz(self, matriz):
        fila = []
        self.filas = len(matriz)
        self.columnas = len(matriz[0])
        for i in range(self.filas):
            for j in range(self.columnas):
                if matriz[i][j] == "*":
                    # Si hay una mina suma 1 al contador de minas totales
                    fila.append(Celda(j, i, mina=True))
                    self.minas += 1
                else:
                    fila.append(Celda(j, i))

            self.matriz.append(fila + [])
            fila = []
            self.minas_restantes = self.minas

    # Se crea una matriz con el numero de minas especificado al azar
    def crear_matriz(self):
        fila = []
        for i in range(self.filas):
            for j in range(self.columnas):
                fila.append(Celda(j, i))

            self.matriz.append(fila + [])
            fila = []

        cont = 0
        # bulce que coloca las minas en posiciones aleatorias
        # escoge fila y columna de forma aleatoria y si en esa casilla no hay mina la coloca
        while cont < self.minas:
            f = randint(0, self.filas - 1)
            c = randint(0, self.columnas - 1)
            if not self.matriz[f][c].mina:
                self.matriz[f][c].mina = True
                cont += 1

    # Función que valida una instruccion dada
    # Devuelve la frase que hay que mostrar por pantalla en cada caso de error
    def validar_jugada(self, fil, col, tipo):
        if tipo == 0 and not self.matriz[fil][col].abierta and not self.matriz[fil][
                col].marcada and self.minas_restantes == 0:
            return "NO SE PUEDEN MARCAR MAS CELDAS QUE MINAS"
        elif tipo == 1 and self.matriz[fil][col].marcada:
            return "NO SE PUEDE ABRIR UNA CELDA MARCADA"
        elif tipo == 0 and self.matriz[fil][col].abierta:
            return "NO SE PUEDE MARCAR UNA CELDA ABIERTA"
        elif tipo == 1 and self.matriz[fil][col].abierta and self.matriz[fil][col].minas_vecinas > 0:
            return "NO SE PUEDEN ABRIR LAS CELDAS CON NÚMERO"
        return True

    # Dada una celda devuelve el numero de minas - numero de marcas en sus celdas vecinas
    def minas_alrededor(self, fila, columna):
        # Depende de si la fila es par o impar tiene unas celdas vecinas u otras
        if fila % 2 == 0:  # estamos en fila par
            contador = 0
            for par in lis_pares:
                # Se comprueba que no se intenta mirar una celda fuera de la matriz
                if self.filas > fila + par[0] >= 0 and self.columnas > columna + par[1] >= 0:
                    #if self.matriz[fila + par[0]][columna + par[1]].marcada:
                     #   contador -= 1
                    if self.matriz[fila + par[0]][columna + par[1]].mina:
                        contador += 1

        else:  # estamos en fila impar
            contador = 0
            for impar in lis_impares:
                if self.filas > fila + impar[0] >= 0 and self.columnas > columna + impar[1] >= 0:

                    #if self.matriz[fila + impar[0]][columna + impar[1]].marcada:
                     #   contador -= 1
                    if self.matriz[fila + impar[0]][columna + impar[1]].mina:
                        contador += 1

        self.matriz[fila][columna].minas_vecinas = contador

    # Función que devuelve true si no quedan minas en la matriz sin marcar y tampoco hay celdas cerradas.
    def comprobar_fin(self):
        self.fin = True
        for i in range(self.filas):
            for j in range(self.columnas):
                if self.matriz[i][j].mina and not self.matriz[i][j].marcada:
                    self.fin = False
                if not self.matriz[i][j].abierta and not self.matriz[i][j].mina:
                    self.fin = False

    # Función que abre una celda dada
    def abrir_celda(self, fil, col, humano=False):
        # Se comprueba que la instrucción esté comprendida en los límites de la matriz.
        if self.filas > fil >= 0 and self.columnas > col >= 0:
            caso = self.matriz[fil][col].abrir_celda()

            # Si se puede abrir o si el usuario abre una celda ya abierta
            if caso == 1 or (caso == 0 and humano):
                self.minas_alrededor(fil, col)
                self.matriz[fil][col].abierta = True

                # Si minas alrededor - marcas alrededor es cero se abren recursivamente las casillas de alrededor.
                # Siempre y cuando no se haya explotado (minas_restantes = -1)
                if self.matriz[fil][col].minas_vecinas <= 0 and self.minas_restantes != -1:
                    if fil % 2 == 0:
                        for par in lis_pares:
                            self.abrir_celda(fil + par[0], col + par[1])
                    else:
                        for impar in lis_impares:
                            self.abrir_celda(fil + impar[0], col + impar[1])

            # Si se abre una mina a no ser de que sea el primer turno (humano = -1) se
            # pone en minas_restantes -1 que dice al programa que se ha activado una mina
            elif caso == -1:
                if humano == -1:
                    self.matriz[fil][col].mina = False
                    nueva_mina = True

                    # Se usa el mismo metodo para poner una nueva mina que el que se usa para poner las iniciales
                    while nueva_mina:
                        f = randint(0, self.filas - 1)
                        c = randint(0, self.columnas - 1)
                        if not self.matriz[f][c].mina:
                            self.matriz[f][c].mina = True
                            nueva_mina = False
                    self.abrir_celda(fil, col, humano)
                else:
                    if self.minas_restantes != -1:
                        self.matriz[fil][col].abierta = True
                    self.minas_restantes = -1

            # Despues de cada intento de apertura se comprueba si se ha ganado
            self.comprobar_fin()

    # Marca o desmarca una celda dada
    def marcar_celda(self, fil, col):
        # Si ya estaba marcada la desmarca
        if self.matriz[fil][col].marcada:
            self.minas_restantes += 1  # Se añade una mina de nuevo al contador de minas
            self.matriz[fil][col].marcada = False
        else:
            self.minas_restantes -= 1  # Se resta una mina al contador
            self.matriz[fil][col].marcada = True
        self.actualizar_matriz()
        self.comprobar_fin()

    def actualizar_matriz(self):
        for i in range(self.filas):
            for j in range(self.columnas):
                self.minas_alrededor(i, j)
