.data 
espacio: .asciiz " "
.text
li $t0, 20
li $t1, 1
bucle:
	beq $t0,$zero,fin
	or $a0,$t1,$zero
	li $v0, 1
	syscall
	
	la $a0, espacio
	li $v0, 4
	syscall
	
	sll $t1,$t1,1

	
	addi $t0,$t0, -1
	j bucle
fin:
li $v0,10
syscall