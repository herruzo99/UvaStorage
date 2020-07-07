.data
c: .space 40
.text
	add $t0,$zero,$zero
	la $t1, c
bucle:
	beq $t0, 10, fin
	sll $t2, $t0, 1
	sll $t3, $t0, 2
	add $t3, $t3, $t1
	sw $t2, 0($t3)
	addi $t0,$t0, 1
	j bucle
 fin:
 	li $v0, 10
 	syscall 