.data
a: .word 1, 2, 3, 4, 5, 6, 7, 8, 9, 100
b: .word 5, 5, 5, 5, 5, 5, 5, 5, 5, 6
c: .space 40
.text
	la $t0, a
	la $t1, b
	la $t2, c
	add $s0,$zero,$zero

bucle:

	beq $s0, 10,fin	
	lw $t3,0($t0)	
	lw $t4,0($t1)
	add $t5, $t3, $t4
	sw $t5, 0($t2)
	
	addi $t0,$t0,4
	addi $t1,$t1,4
	addi $t2,$t2,4
	
	addi $s0,$s0,1
	j bucle
fin:	
	li, $v0, 10
	syscall