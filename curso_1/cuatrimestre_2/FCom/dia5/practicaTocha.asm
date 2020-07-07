.data
monton: .space 200
comprobar: .space 10
gp: .word 196
finicio: .asciiz "PROGRAMA RECURSIVO PARA EL CÁLCULO DEL MÁXIMO COMÚN DIVISOR \n"
pnum: .asciiz "Introduce el primero entero: "
onum: .asciiz "Introduce otro entero: "
ffin: .asciiz "El máximo común divisor de "
coma: .asciiz ", "
y: .asciiz  " y "
es: .asciiz " es "
nega: .asciiz "Numéro negativo no valido \n"
repe: .asciiz "Número repetido \n"
carac: .asciiz "Entrada erronea \n"
.text
#Escribimos las primeras frases que s emuestran por pantalla
la $a0, finicio
li $v0, 4
syscall

la $a0, pnum
li $v0, 4
syscall

#simulamos el montón y pedimos que el usuario introduce el primer número
la $s0, monton
la $t0, gp
add $s0, $s1, $s0
add $t0, $s0, $zero
la $a0, comprobar
li $a1, 10
li $v0, 8
syscall

#comprobamos que solo introducen números
bucle_strI:
	lb $t1, 0($a0)
	addi $a0, $a0, 1
	beq $t1, 10, fin_bucle_strI
	slti $t5, $t1, 58
	beq $t5, $zero, caracterI
	slti $t5,$t1, 48
	bne $t5, $zero, caracterI
	j bucle_strI
	caracterI:
		la $a0, carac
		li $v0, 4
		syscall
		j otronum
fin_bucle_strI:
	la $a0, comprobar
	add $t5, $zero, $zero
	lb $t1, 0($a0)
	
	crear_numI:
		addi $t1, $t1, -48
		mul $t5, $t5, 10
		addi $a0, $a0, 1
		add $t5, $t1, $t5
		lb $t1, 0($a0)
		bne $t1, 10, crear_numI
		 
#se comprueba que no es negativo si lo es no se guarda en el vector
slt $t1, $v0,$zero
beq $t1,$zero,positivoI
#escribe el mensaje de error en patalla
la $a0, nega
li $v0, 4
syscall
j otronum


#si el 1º número introducido es positivo o cero se guarda y se avanza una posición en el vector
positivoI:
	sw $v0, 0($t0)
	addi $t0, $t0, -4
	#si el número es cero se deja de pedir valores
	beq $v0, $zero, finnum

#contador para comprobar que no se repitan valores
addi $t3, $zero,1
#se pide iterativamente el resto de números usando el mismo esquema anteriormente descrito
otronum:
	la $a0, onum
	li $v0, 4
	syscall
	
	la $a0, comprobar
li $a1, 10
li $v0, 8
syscall
add $t5, $zero, $zero
#comprobamos que solo introducen números
bucle_str:
	lb $t1, 0($a0)
	addi $a0, $a0, 1
	beq $t1, 10, fin_bucle_str
	slti $t5, $t1, 58
	beq $t5, $zero, caracter
	slti $t5,$t1,48
	bne $t5, $zero, caracter
	j bucle_str
	caracter:
		la $a0, carac
		li $v0, 4
		syscall
		j otronum
fin_bucle_str:
	add $t5, $zero, $zero
	lb $t1, 0($a0)
	crear_num:
		addi $t1, $t1, -48
		mul $t5, $t5, 10
		addi $a0, $a0, 1
		add $t5, $t1, $t5
		lb $t1, 0($a0)
		bne $t1, 10, crear_num
		 
	
	slt $t1, $v0,$zero
	beq $t1,$zero,positivo
	la $a0, nega
	li $v0, 4
	syscall
	j otronum
	positivo:
		#comprobamos que el valor no está repetido y si lo está no lo guardamos
		#volvemos a cargar el vector 
		add $t2, $s0, $zero
		add $t4, $t3, $zero
		add $t5, $zero, $zero
		bucle_repe:
			lw $t5, 0($t2)
			addi $t2, $t2, -4
			bne $t5, $v0, igual 
			la $a0, repe
			li $v0, 4
			syscall
			j otronum
			
			igual:
				addi $t4, $t4, -1
				bne $t4, $zero, bucle_repe
				
		#guardamos el valor en el vector y sumamos 1 al contador porque ha pasado todos los filtros
		sw $v0,0($t0)
		addi $t0, $t0, -4
		addi $t3, $t3, 1
		bne $v0, $zero,otronum

finnum:
#se carga la dirección del vector y se guarda el primer valor en $a0
	add $t0, $s0,$zero
	lw $a0, 0($t0)
	addi $t0, $t0, -4

#bucle que se encarga de llamar iterativamente a la función de mcd
bucle:
	#se carga el siguiente valor del vector
	lw $a1, 0($t0)
	addi $t0, $t0, -4
	
	#si $a1 no es 0 se hace el mcd de él y $a0
	beq $a1, $zero, finBucle
	jal mcd 
	#se guarda el resultado en $a0 y se itera el bucle
	add $a0, $v0, $zero
	j bucle
	
finBucle:
#se guarda el mcd de todos los números en $s1
add $s1, $v0,$zero


la $a0, ffin
li $v0, 4
syscall

#se carga la dirección del vector
add $t0, $s0,$zero

escribeDiv:
	#se carga el siguiente valor del vector en $a0 y dos más adelante en $t1
	lw $a0, 0($t0)
	lw $t1, -8($t0)
	addi $t0, $t0, -4
	
	#se imprime $a0
	li $v0, 1
	syscall	
	
	#si $t1 es 0 se termina este bucle
	beq $t1, $zero, finEscribe
	
	la $a0, coma
	li $v0, 4
	syscall
	j escribeDiv

#escribe la parte final de la frase final
finEscribe:
	
	la $a0, y
	li $v0, 4
	syscall
	
	lw $a0, 0($t0)
	li $v0, 1
	syscall	
	
	la $a0, es
	li $v0, 4
	syscall
	
	#escribe el resultado de la funcion mcd
	add $a0, $s1,$zero
	li $v0, 1
	syscall	

#termina el programa
li $v0, 10
syscall

#función mcd
#nos referimos a $a0 como 'a' y a $a1 como 'b'
mcd:	
	#guarda en el pila el contador de progrma al que saltar cuando se acabe esta función
	addi $sp, $sp, -4
	sw $ra, 0($sp)
	#comprue si a es 0 si es así devuelve a
	bne $a0, $zero, compB
	add $v0, $a1,$zero
	j finMcd
	
	#comprue si b es 0 si es así devuelve b
	compB:
		bne $a1, $zero, resta
		add $v0, $a0,$zero
		j finMcd
	
	#resta al mayor de a y b el menor y se llama a mcd con el mayor valor de los dos y la resta de ellos
	resta:
		slt $t1,$a0,$a1
		beq $t1,$zero, mayorA
		sub $a1, $a1, $a0
		jal mcd
		j finMcd
	mayorA:
		sub $a0, $a0, $a1
		jal mcd
	
	#recarga de la pila el contador y salta a él
	finMcd:
		lw $ra 0($sp)
		addi $sp, $sp, 4
		jr $ra
	
