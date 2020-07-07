.data
numero: .space 1000
frase0x: .asciiz "0x"
fraseInicio: .asciiz "Dime un numero decimal: \n"
.text
main:
	li $v0,4
	la $a0, fraseInicio
	syscall
	la $a0, numero
	li $a1, 1000
	li $v0, 8
	syscall
	
	jal comprobar_decimal
	jal aDecimal
	
	la $a0, numero
	add $a1, $v0, $0
	jal aHexadecimal
	
	li $v0,4
	la $a0, frase0x
	syscall
	
	la $a0, numero
	li $v0, 4
	syscall
	
	li $v0, 10
	syscall
	
comprobar_decimal:

	jr $ra
aDecimal:
	add $v0, $0, $0
	add $t1, $0, $0
	lb $t0, 0($a0)
	bne $t0, 45, bucle_dec
		addi $t1, $0, 1
		addi $a0, $a0, 1
	bucle_dec:
		lb $t0, 0($a0)
		beq $t0, 10, fin_bucle
		
		slti $t2, $t0, 48
		beq $t2, 1, no_numero
		slti $t2, $t0, 58
		beq $t2, 0, no_numero
		
		mul $v0, $v0, 10
		addi $t0, $t0, -48
		beq $t1, 1, negativo
			add $v0, $v0, $t0
			j positivo
		negativo:
			sub $v0, $v0, $t0
		positivo:
		addi $a0, $a0, 1
		mfhi $t2
		bne $t2, 0, overflow
		j bucle_dec
		
	overflow:
	li $v0,2
	jr $ra
	
	no_numero:
	li $v1, 1	
	fin_bucle:
	jr $ra


aHexadecimal:
	#Al ser little-ednian y como vamos a leer el numero de digito menos significativo a mas significativo,
	# hay que escribir del mayor al menor byte, al ser dos palabras se empieza en el octavo byte.
	addi $a0, $a0, 7
	add $t5, $0,$0
	
	#Máscara para leer los últimos 4 bytes
	addi $t0, $0,0x0000000f
	bucle:
		#Se guarndan los últimos 4 bits del numero y se desplaza este para dejar a los 4 buts siguientes 
		# en posicion con la mascara
		and $t1, $a1, $t0
		srl $a1,$a1,4
	
		slti $t2, $t1, 10
		add $t1, $t1, '0'
	
		#Si el numero hexadecimal en mayor que 9 hay que sumarle 39 para estar en escala con a-f en ASCII
		beq $t2, 1,num
			addi $t1,$t1,39
		num:
			#Se guarda y se apunta el byte anterior
			sb $t1, 0($a0)
			addi $a0, $a0, -1
			
			#Se itera 8 veces
			addi $t5, $t5, 1
			bne $t5, 8, bucle
	
	jr $ra
