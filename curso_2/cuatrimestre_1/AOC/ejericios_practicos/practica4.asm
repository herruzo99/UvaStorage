.data
cadena: .space 100
numIncorrecto: .asciiz "Cadena de caracteres mal introducida, introduce 8 exactos.\n"
charIncorrecto: .asciiz "Caracter incorrecto, por favor use solo los caracteres 0-9 A-F a-f\n"
.text
main:
#la g la toma
repetir:
	jal pedirNumero
bne $v0, 0, repetir

jal aBinario
add $s0, $v0, $0
li $a0 10
li $v0 11
syscall


add $a0, $s0, $0
li $v0 1
syscall

li $v0, 10
syscall


aBinario:
	add $t0, $0, 0
	add $v0, $0, $0
	bucle:
		sll $v0, $v0, 4
		lb $t1, 0($a0)
			addi $t1, $t1, -48
			slti $t2,$t1, 10
			beq $t2, 1, num
			addi $t1, $t1, -7
			slti $t2,$t1, 16
			beq $t2, 1, num
			addi $t1, $t1, -32
		num:
		add $v0, $v0, $t1
		addi $a0, $a0, 1
		addi $t0, $t0, 1
	bne $t0, 8, bucle
	
	jr $ra


pedirNumero:

la $a0, cadena
li $a1, 100
li $v0, 8
syscall
	add $t0, $0, $0 
	contarCaracteres:
		addi $t0, $t0, 1
		lb $t1, 0($a0)
		addi $a0, $a0, 1
	bne $t1, 10, contarCaracteres
	beq $t0, 9,numeroCorrecto 
	la $a0, numIncorrecto
	li $v0, 4
	syscall
	li $v0, 1
	
	jr $ra
	
	numeroCorrecto:
		la $t0, cadena
		comprobarCaracteres:
			lb $t1, 0($t0)
			addi $t0, $t0, 1
			bne $t1, 10, fin
			
			slti $t2, $t1, 48
			beq $t2, 1, caracterIncorrecto
			slti $t2, $t1,58
			beq $t2, 1, comprobarCaracteres
			slti $t2, $t1, 65
			beq $t2, 1, caracterIncorrecto
			slti $t2, $t1,71
			beq $t2, 1, comprobarCaracteres
			slti $t2, $t1, 97
			beq $t2, 1, caracterIncorrecto
			slti $t2, $t1,103
			beq $t2, 1, comprobarCaracteres
			
			caracterIncorrecto:
			la $a0, charIncorrecto
			li $v0, 4
			syscall
			li $v0, 1
			jr $ra
	fin:
	li $v0, 0
	jr $ra

