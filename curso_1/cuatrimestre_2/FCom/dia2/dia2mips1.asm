.text
addi $s0, $zero, 10
addi $s1, $zero, 11

	bne $s0, $s1 , iguales
	addi $s0, $s1, 4
	j fin
iguales:
	sub $s0, $zero, $s1
	addi $s0, $s0, 4
fin:
	li $v0, 10
	syscall