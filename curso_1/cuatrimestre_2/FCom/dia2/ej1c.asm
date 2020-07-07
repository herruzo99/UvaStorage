.data
c: .word 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
.text
	add $s0, $zero, $zero
	addi $s1, $zero, 10
	la $s2, c
	sll $s3,$s0,2
	add $s3,$s2,$s3
	lw $s4, 0($s3)
bucle:
	beq $s4, $s1, fin
	addi $s3, $s3, 4
	lw $s4,0($s3)
	j bucle
fin:
	li, $v0, 10
	syscall
