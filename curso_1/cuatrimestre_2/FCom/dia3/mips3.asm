.text
li $v0,5
syscall
or $t0,$v0,$zero
and $t1,$t1,$zero
and $s0,$s0,$zero
addi $t1,$t1,2

bucle:
beq $t0,$zero,fin
	add $s0,$s0,$t1
	addi $t1,$t1,2
	addi $t0,$t0,-1
	j bucle
fin:
or $a0,$s0,$zero
li $v0,1
syscall
li $v0,10
syscall