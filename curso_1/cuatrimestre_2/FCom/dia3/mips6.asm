.data
s1: .asciiz "1 2"
s2: .space 500
.text

la $a0, s1
la $a1, s2
li $a2, 3
jal copia
li $v0,10
syscall

mayusculas:
	or $t0,$a0,$zero

	bucle:
	lb $t1, 0($t0)
	addi $t0, $t0,1
	
	beq $t1,$zero,fin
	
		slti $t2,$t1,123
		beq $t2,$zero,bucle
		
		slti $t2,$t1,97
		bne $t2,$zero,bucle
		
		addi $t1, $t1, -32
		sb $t1, -1($t0) 
		j bucle
	fin:
	jr $ra


copia:
	or $t0,$a0,$zero #dir
	or $t1,$a1,$zero #dir
	or $t2,$a2,$zero #num
	
	buclecopia:	
	beq $t2,$zero,fincopia
		buclecopia2:
		lb $t3, 0($t0)
		beq $t3, $zero, fincopia2
		sb $t3, 0($t1)
		addi $t0,$t0,1
		addi $t1,$t1,1
		j buclecopia2
		
		fincopia2:
	or $t0,$a0,$zero
	addi $t2,$t2,-1
	j buclecopia
	fincopia:
	jr $ra