.data
#reservamos las 4 palabras de memorias que se van a leer por teclado
vector: .word 0 0 0 0
.text
#inicializacion del contador del 1º bucle
li $t0,3
la $t1, vector
#bucle para leer las 4 palabras opr teclado
bucle:
	#lee por pantalla y lo carga en un registro auxiliar
	li $v0, 5
	syscall
	sw $v0, 0($t1)
	beq $t0, $zero, fin
	#suma 4 a la posicion de memoria del registro y resta 1 al contador
	addi $t1, $t1, 4
	addi $t0, $t0, -1
	j bucle
fin:
	#inicializa $a0 para a continuación llmar a la función empaquetador
	or $a0, $t1, $zero
	jal empaquetador
	#inicializa $a0 para imprimir por pantalla
	or $a0, $v0, $zero
	li $v0, 1
	syscall
	li $v0, 10
	syscall

empaquetador:
	li $t0,3
	#limpiamos de posible basura $v0
	and $v0, $v0, $zero

	bucle_1:
		#carga la última palabra leida en un registro axuliar y lo guardo en $v0
		lw $t1, 0($a0)
		or $v0, $t1, $v0
		beq $t0, $zero, fin_1
		#desplaza $v0 4 posiciones de memoria a la izquierda
		sll $v0, $v0, 4
		#resta 1 al contador y 4 a la posición de memoria del registro auxiliar
		addi $t0, $t0, -1
		addi $a0, $a0, -4
		j bucle_1
	fin_1:
		jr $ra