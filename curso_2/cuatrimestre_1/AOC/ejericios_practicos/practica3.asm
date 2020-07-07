.data

cadena: .space 12
frase0x: .asciiz "0x"
fraseInicio: .asciiz "Dime un numero \n"
.text

main:
	li $v0,4
	la $a0, fraseInicio
	syscall
	
	li $v0,5
	syscall
	
	add $a1, $0, $v0
	srl $a1,$a1,4
	la $a0, cadena
	jal convertir

	li $v0,4
	la $a0, frase0x
	syscall
	
	li $v0,4
	la $a0, cadena
	syscall
	
	li $v0, 10
	syscall

convertir:
	addi $a0, $a0, 7
	add $t5, $0,$0

	addi $t0, $0,0x0000000f
bucle:
	and $t1, $a1, $t0
	

	slti $t2, $t1, 10

	add $t1, $t1, '0'

	beq $t2, 1,num

	addi $t1,$t1,39
	num:
		sb $t1, 0($a0)
		addi $a0, $a0, -1

		bne $t5, 3, mitad

		addi $a0, $a0,0
		add $t1,$0 , $0
	mitad:
		addi $t5, $t5, 1
	bne $t5, 8, bucle
	
	addi $a0, $a0, 9
	li $t1, 0
	sb $t1, 0($a0)
	jr $ra
