.data 
c: .space 10
.text
main: la $s0, c
addi $t1,$zero,1
addi $t2,$zero,10 #Se crea un contador para el bucle
add $t0,$s0,$zero #Se copia la dirrecion de c para ser usada en el bucle
bucle: sw $t1, 0($t0)
sll $t1,$t1,1 #Se va multiplicando t1 por 2 en cada iteracion del bucle
addi $t0,$t0,4 #Se suman 4 a la direccion de c para desplazar la posicion de memoria a la siguiente 
addi $t2,$t2,-1
bne $t2,$zero,bucle #Cuando t2 sea 0 acabamos el bucle
lw $t1, 16($s0)
lw $t2, 32($s0)
add $t1,$t1,$t2
sw $t1, 36($s0) #Se hace la suma c[8]+c[4] y se guarda en c[9]
li $v0,10
syscall
