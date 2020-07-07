.data
s1: .asciiz "Hola mundo jeje"
s2: .space 100
.text

la $a0, s1
la $a1, s2
jal funcion
li $v0,10
syscall


funcion:
lb $t0, 0($a0)

bucle:
	beq $t0, $zero, fin
	sb $t0, 0($a1)
	addi $a0, $a0, 1
	addi $a1, $a1, 1
	lb $t0, 0($a0)
	j bucle

fin: 
jr $ra