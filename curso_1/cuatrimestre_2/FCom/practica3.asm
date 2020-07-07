.data

cadena: .space 12
frase0x: .asciiz "0x"
fraseInicio: .asciiz "Dime un numero: \n"
.text

main:
	#Se pide el numero por pantalla.
	li $v0,4
	la $a0, fraseInicio
	syscall
	li $v0,5
	syscall
	
	#Se convierte la cadena de decimal a hexadecimal
	add $a1, $0, $v0
	la $a0, cadena
	jal convertir

	#Se imprime el numero en hexadecimal por pantalla
	li $v0,4
	la $a0, frase0x
	syscall
	li $v0,4
	la $a0, cadena
	syscall
	
	#Fin del programa
	li $v0, 10
	syscall

convertir:
	#Apartado 2.c multiplicación por 4
	sll $a1,$a1,2
	
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
