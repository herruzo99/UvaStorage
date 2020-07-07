.text

li $a0, 5
jal fact
or $a0, $v0, $zero
li $v0, 1
syscall
li $v0, 10
syscall
fact:
	addi $sp, $sp, -8 # reserva 2 lugares en la pila
	sw $ra, 4($sp) # guarda dirección de retorno
	sw $a0, 0($sp) # guarda parámetro
	slti $t0, $a0, 1 # n < 1?
	beq $t0, $zero, L1 # Si n >= 1 va a L1
	addi $v0, $zero, 1 # si n < 1 devuelve 1,
	addi $sp, $sp, 8 # libera los 2 lugares de la pila
	jr $ra # y retorna
	L1: 
		addi $a0, $a0, -1 # si no, decrementa n
		jal fact # llamada recursiva
	lw $a0, 0($sp) # recupera valor original de n
	lw $ra, 4($sp) # y dirección de retorno,
	addi $sp, $sp, 8 # libera los 2 lugares de la pila,
	mul $v0, $a0, $v0 # multiplica, devuelve resultado
	jr $ra # y retorna