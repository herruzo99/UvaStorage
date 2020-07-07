.data
A: .space 100
B: .space 100 
etiqueta: .asciiz "Introduce un número en "

.text
main:
la $a0, A
li $a1, 5
jal pedir
la $a0, A
la $a1, B
li $a2, 5
jal resta
la $a0, B
li $a1, 5
jal imprimir
li $v0, 10
syscall

resta:
add $t0, $a0, $0 #Cargar dir A
add $t1, $a1, $0 #Cargar dir B
add $t2, $a2, $0 #Cargar num filas (m)
add $t3, $0, $0 #Inciar contador fila (i)

buclefila:
add $t4, $0, $0  #Iniciar contador columna (j)
buclecolumna:
#Todas las cargas son del tipo A[i][j] = A+4*m*i+4*j
#Cargamos A[i][j]
sll $t5, $t4, 2
mul $t6, $t2, $t3
sll $t6, $t6, 2
add $t5, $t5, $t6
add $t5, $t0, $t5

lw $t5, 0($t5)

#Cargamos A[j][i]
sll $t7, $t3, 2
mul $t6, $t2, $t4
sll $t6, $t6, 2
add $t7, $t7, $t6
add $t7, $t0, $t7

lw $t7, 0($t7)

#Cargamos B[i][j]
sll $t8, $t4, 2
mul $t6, $t2, $t3
sll $t6, $t6, 2
add $t8, $t8, $t6
add $t8, $t1, $t8

#B[i][j] = A[i][j] - A[j][i]
sub $t5, $t5, $t7

sw $t5, 0($t8)

#aumentamos i
addi $t4, $t4, 1
bne $t4, $t2, buclecolumna

#aumentqamos j
addi $t3, $t3, 1
bne $t3, $t2, buclefila


jr $ra

imprimir:
add $t0, $a0, $0
add $t1, $a1, $0
add $t3, $0, $0 #Inciar contador fila (i)

#imprime el salto de linea
li $a0, 10
li $v0, 11
syscall

printfila:
add $t4, $0, $0  #Iniciar contador columna (j)
printcolumna:

sll $t2, $t4, 2
mul $t6, $t1, $t3
sll $t6, $t6, 2
add $t2, $t2, $t6
add $t2, $t0, $t2

lw $a0, 0($t2)

#imprime el nnumero de la matriz
li $v0, 1
syscall 

#imprime el tabulador
li $a0, 9
li $v0, 11
syscall

#aumentamos i
addi $t4, $t4, 1
bne $t4, $t1, printcolumna

#imprime el salto de linea
li $a0, 10
li $v0, 11
syscall

#aumentqamos j
addi $t3, $t3, 1
bne $t3, $t1, printfila
jr $ra
pedir:
add $t0, $a0, $0
add $t1, $a1, $0
add $t3, $0, $0 #Inciar contador fila (i)

pedirfila:
add $t4, $0, $0  #Iniciar contador columna (j)
pedircolumna:

#imprime la frase
la $a0, etiqueta
li $v0, 4
syscall

#imprime la fila i
add $a0, $t3, $0
li $v0, 1
syscall

#imrpime un espacio
li $a0, 32
li $v0, 11
syscall

#imrpime la columna j
add $a0, $t4, $0
li $v0, 1
syscall

#imprime el salto de linea
li $a0, 10
li $v0, 11
syscall

#pide el numero
li $v0, 5
syscall

#Guarda en B[i][j]
sll $t2, $t4, 2
mul $t6, $t1, $t3
sll $t6, $t6, 2
add $t2, $t2, $t6
add $t2, $t0, $t2

sw $v0, 0($t2)

#aumentamos i
addi $t4, $t4, 1
bne $t4, $t1, pedircolumna

#aumentqamos j
addi $t3, $t3, 1
bne $t3, $t1, pedirfila


jr $ra
