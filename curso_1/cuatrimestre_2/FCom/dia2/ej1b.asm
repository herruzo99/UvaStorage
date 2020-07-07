.text

addi $s0, $zero, 1
addi $s1, $zero, 100000
bucle:
	beq $s0,$s1, fin
	addi $s0, $s0,1
	j bucle
fin:
	li $v0,10
	syscall