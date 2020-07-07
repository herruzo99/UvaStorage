.data
vector: .space 200
finicio: .asciiz "PROGRAMA RECURSIVO PARA EL CÁLCULO DEL MÁXIMO COMÚN DIVISOR \n"
pnum: .asciiz "Introduce el primero entero: "
onum: .asciiz "Introduce otro entero: "
ffin: .asciiz "El máximo común divisor de "
coma: .asciiz ", "
y: .asciiz  " y "
es: .asciiz " es "
nega: .asciiz "Numéro negativo no valido \n"
.text
la $a0, finicio
li $v0, 4
syscall

la $a0, pnum
li $v0, 4
syscall

la $s0, vector
add $t0, $s0,$zero
li $v0, 5
syscall

slt $t1, $v0,$zero

beq $t1,$zero,negativoI
la $a0, nega
li $v0, 4
syscall
j otronum

negativoI:
sw $v0, 0($t0)
addi $t0, $t0, 4
beq $v0, $zero, finnum


otronum:
	la $a0, onum
	li $v0, 4
	syscall
	
	li $v0, 5
	syscall
	
	slt $t1, $v0,$zero
	beq $t1,$zero,negativo
	la $a0, nega
	li $v0, 4
	syscall
	j otronum
	negativo:
	sw $v0,0($t0)
	addi $t0, $t0, 4
	bne $v0, $zero,otronum
finnum:

add $t0, $s0,$zero
lw $a0, 0($t0)
addi $t0, $t0, 4

bucle:
	lw $a1, 0($t0)
	addi $t0, $t0, 4
	
	beq $a1, $zero, finBucle
	jal mcd
	add $a0, $v0, $zero
	j bucle
finBucle:
add $s1, $v0,$zero


la $a0, ffin
li $v0, 4
syscall

add $t0, $s0,$zero
escribeDiv:
	lw $a0, 0($t0)
	lw $t1, 8($t0)
	addi $t0, $t0, 4
	
	li $v0, 1
	syscall	
	
	beq $t1, $zero, finEscribe
	
	la $a0, coma
	li $v0, 4
	syscall
	j escribeDiv
finEscribe:
	
	la $a0, y
	li $v0, 4
	syscall
	
	lw $a0, 0($t0)
	li $v0, 1
	syscall	
	
	la $a0, es
	li $v0, 4
	syscall
	
	add $a0, $s1,$zero
	li $v0, 1
	syscall	

li $v0, 10
syscall

mcd:	
	addi $sp, $sp, -4
	sw $ra, 0($sp)
	bne $a0, $zero, compB
	add $v0, $a1,$zero
	j finMcd
	
	compB:
	bne $a1, $zero, resta
	add $v0, $a0,$zero
	j finMcd
	
	resta:
	slt $t1,$a0,$a1
	beq $t1,$zero, mayorA
	sub $a1, $a1, $a0
	jal mcd
	j finMcd
	
	mayorA:
	sub $a0, $a0, $a1
	jal mcd

	finMcd:
	lw $ra 0($sp)
	addi $sp, $sp, 4
	jr $ra
	