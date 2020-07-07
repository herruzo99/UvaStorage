.text

li $v0, 5
syscall
or $a0, $v0,$zero
jal contar
or $a0, $v0,$zero
li $v0, 1
syscall
li $v0, 10
syscall

contar:
	li $t0, 32
	li $t1, 1
	and $v0, $v0, $zero
	bucle:
		beq $t0, $zero, fin
		and $t2, $a0, $t1
		beq $t2, $zero, salto
		addi $v0, $v0, 1
		salto:
		sll $t1, $t1, 1
		addi $t0, $t0, -1
		j bucle
	
fin:
	jr $ra
	