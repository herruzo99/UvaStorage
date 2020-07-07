.data
numero: .space 1000
pedirNum: .asciiz "Dime un numero hexadecimal de 8 caracteres:\n"
numIncorrecto: .asciiz "Cadena de caracteres mal introducida, introduce 8 exactos:\n"
charIncorrecto: .asciiz "Caracter incorrecto, por favor use solo los caracteres 0-9, A-F, a-f:\n"

numASCII: .space 11
aux: .space 12

.text

main:
	
	la $a0, numero
	li $a1, 100
	li $v0, 8
	syscall
	
	jal aBinario
	#Código error '1': Cadena de caracteres distinta de 8.
	bne $v0, 1, numCorrecto
		la $a0, numIncorrecto
		li $v0, 4
		syscall
		li $v0, 10
		syscall
	numCorrecto:
	#Código error '2': Caracter incorrecto en la cadena.
	bne $v0, 2, charCorrecto
		la $a0, charIncorrecto
		li $v0, 4
		syscall
		li $v0, 10
		syscall
		
	charCorrecto:
	
	add $a1, $v1, $0
	la $a0, numASCII
	jal aASCII
	
	la $a0, numASCII
	li $v0, 4
	syscall
	li $v0, 10
	syscall

#TODO en la pila estabas
aBinario:
	addi $sp, $sp, -8
	sw $ra, 8($sp)
	sw $a0, 4($sp)
	jal comprobarNumero
	sw $ra, 8($sp)
	sw $a0, 4($sp)
	addi $sp, $sp, 8
	beq $v0, 0, valido
		jr $ra
	valido:
	#Para pasar a binaro de hexadecimal escrito en ascii, va caracter a caracter empezando por el de mas valor.
	add $t0, $0, 0
	add $v1, $0, $0
	bucleBin:
		#Se desplaza 4 espacios el resultado final para una vez iterado todo el hezadecimal, tener el número bineario bien escrito.
		sll $v1, $v1, 4
		lb $t1, 0($a0)
			
			#Si una vez restado es menor de 10 significa que ya tenemos el numero hexadecimal de 0 al 9 en binario.
			addi $t1, $t1, -48
			slti $t2,$t1, 10
			beq $t2, 1, numBin
			#Si no al restarle 7 si es menor de 16 tenemos el numero hexadecimal de 'A' a 'F' en binario.
			addi $t1, $t1, -7
			slti $t2,$t1, 16
			beq $t2, 1, numBin
			#Si tampoco significa que esta entre la 'a' y la 'f', se le resta otros 32 para pasarlo a binario.
			addi $t1, $t1, -32
		numBin:
		#Una vez se tiene el caracter hexadecimal en binario se guarda.
		add $v1, $v1, $t1
		addi $a0, $a0, 1
		addi $t0, $t0, 1
	bne $t0, 8, bucleBin
	
	jr $ra
	
comprobarNumero:
	#Comprueba que la lista de caracteres contiene 9 contando al \0, es decir, 8 caracteres.
	add $t0, $0, $0 
	contarCaracteres:
		addi $t0, $t0, 1
		lb $t1, 0($a0)
		addi $a0, $a0, 1
	bne $t1, 10, contarCaracteres
	slti $t1, $t0, 10
	beq $t1, 1,numeroCorrecto 
	
	#Si no tiene 8 devuelve un codigo de error.
	li $v0, 1
	jr $ra
	
	numeroCorrecto:
		#Comprueba que los caracteres pertenencen a los intervalos [0-9], [A-F], [a-f].
		la $t0, numero
		comprobarCaracteres:
			lb $t1, 0($t0)
			addi $t0, $t0, 1
			beq $t1, 10, fin
			#Comprueba si es menor que '0' en ascii.
			slti $t2, $t1, 48
			beq $t2, 1, caracterIncorrecto
			#Comprueba si es menor que ':'(Caracter ascii a continuacion del '9') en ascii.
			slti $t2, $t1,58
			beq $t2, 1, comprobarCaracteres
			#Comprueba si es menor que 'A' en ascii.
			slti $t2, $t1, 65
			beq $t2, 1, caracterIncorrecto
			#Comprueba si es menor que 'G' en ascii.
			slti $t2, $t1,71
			beq $t2, 1, comprobarCaracteres
			#Comprueba si es menor que 'a' en ascii.
			slti $t2, $t1, 97
			beq $t2, 1, caracterIncorrecto
			#Comprueba si es menor que 'g' en ascii.
			slti $t2, $t1,103
			beq $t2, 1, comprobarCaracteres
			
			caracterIncorrecto:
			#Si contiene algun caracter incorrecto devuelve un código de error.
			li $v0, 2
			jr $ra
	fin:
	li $v0, 0
	jr $ra


aASCII:
	li $t0, 10
	la $t3, aux
	sb $0, 11($t3)
	
	addi $t3, $t3, 10
	
	bucleASCII:
	div $a1, $t0
	mfhi $t2
	add $t2, $t2, 48
	sb $t2, 0($t3)
	mflo $t1
	beq $t1,0,finASCII
	addi $t3, $t3, -1
	add $a1, $t1, $0
	j bucleASCII
	
	finASCII:
	
	lb $t2, 0($t3)
	sb $t2, 0($a0)
	addi $t3, $t3, 1
	addi $a0, $a0, 1
	bne $t2, 0, finASCII
	jr $ra