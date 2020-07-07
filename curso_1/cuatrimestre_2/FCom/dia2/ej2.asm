.text
	addi $s3, $zero, 5
	addi $t1, $zero, 1
	addi $t2,$s3,1
bucle:
	beq $t2, $t1, fin
	add $s5, $s5, $t1
	addi $t1, $t1, 1
	j bucle	
fin:
	li $v0, 10
	syscall