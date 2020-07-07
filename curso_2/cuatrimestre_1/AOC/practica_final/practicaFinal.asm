.data
fechaMem: .word 0,0,0,0,0,0,0
fechaMemSegunda: .word 0,0,0,0,0,0,0
fechaString: .space 200

.text

#En este programa utilizamos recurrentemente este vector el cual llamaremos vector base:
#a1[0] = dias, a1[1] = meses, a1[2] = años, a1[3] = eras, a1[4] = segundos. 
main:
la $a0, fechaString
li $a1, 200
li $v0, 8
syscall

la $a1, fechaMem
jal parsearFecha

la $a0, fechaMem
#comprobar errores
jal aSegundos

la $a0, fechaMem
jal fechaCorrecta

li $v0, 10
syscall

#----------------------------------PARSEO----------------------------------#
#Guarda en $a1 la fecha pasado por $a0.
#Estructura de $a0: dd/mm/aaa hh:mm:ss
#Se guarda en $a1 del siguiente modo:
#a1[0] = dias, a1[1] = meses, a1[2] = años, a1[3] = eras, a1[4] = horas, a1[5] = minutos, a1[6] = segundos. 
parsearFecha:
	addi $sp, $sp, -12
	sw $ra, 4($sp)
	sw $s0, 8($sp)
	sw $s1, 12($sp)
	sw $s2, 16($sp)
	
	add $s0, $0, $0
	add $s1, $a1, $0
	add $s2, $a0, $0
	bucle_parseo:
		beq $s0,0, parseo_barra
		beq $s0,1, parseo_barra
		beq $s0,2, parseo_espacio
		beq $s0,3, parseo_puntos
		beq $s0,4, parseo_puntos
		beq $s0,5, parseo_fin
		#TODO comprobar errores
		parseo_barra:
			li $a1, 47
			add $a0, $s2, $0
			jal aDecimal
			sw $v0, 0($s1)
			addi $s1, $s1, 4
			addi $s2, $s2, 3
			j parseo_iteracion
			
		parseo_espacio:
			li $a1, 32
			add $a0, $s2, $0
			jal aDecimal
			li $t0, 400
			div $v0, $t0
			
			mfhi $t0
			sw $t0, 0($s1)
			addi $s1, $s1, 4
			
			mflo $t0
			sw $t0, 0($s1)
			addi $s1, $s1, 4
			
			addi $s2, $s2, 5
			j parseo_iteracion
			
		parseo_puntos:
			li $a1, 58
			add $a0, $s2, $0
			jal aDecimal
			sw $v0, 0($s1)
			addi $s1, $s1, 4
			addi $s2, $s2, 3
			j parseo_iteracion
			
		parseo_fin:
			li $a1, 10
			add $a0, $s2, $0
			jal aDecimal
			sw $v0, 0($s1)
			
			jal aDecimal
	parseo_iteracion:
	addi $s0, $s0,1
	bne $s0, 6, bucle_parseo
	
	lw $ra, 4($sp)
	lw $s0, 8($sp)
	lw $s1, 12($sp)
	sw $s2, 16($sp)
	addi $sp, $sp, 8
jr $ra

#Devuelve por $v0 el numero en string pasado por $a0 hasta '$a1'
aDecimal:
#Guarda en pila la direccion del link y la de donde esta guardado el número en hexadecimal.
	addi $sp, $sp, -8
	sw $ra, 4($sp)
	sw $a0, 8($sp)
	jal comprobarNumerico
	lw $ra, 4($sp)
	lw $a0, 8($sp)
	addi $sp, $sp, 8
	beq $v0, 0, valido
		jr $ra
	
	valido:
		add $t0, $0, 0
		add $v1, $0, $0
		bucle_dec:
			#Carga caracter a caracter hasta que encunetra un fin de línea.
			lb $t0, 0($a0)
			beq $t0, $a1, fin_bucle_dec
			beq $t0, 10, fin_inesperado
			#Se multiplica para colorcar el numero en su posición una vez acabado el bucle.
			mul $v0, $v0, 10
			mflo $t2
			#Se pasa el caracter de ASCII a INT.
			addi $t0, $t0, -48
			
			add $v0, $v0, $t0
			slt $t2, $t2, $0
			beq $t2, 1, overflow
			#Se incrementa la posicion del apuntador para coger el siguienta caracter en la próxima iteración
			addi $a0, $a0, 1
			j bucle_dec
		
	#Ruta de salida si hay overflow.
	overflow:
	li $v1,2
	jr $ra
	
	#Ruta de salida si hay demasiados numeros.
	fin_inesperado:
	li $v1,3
	jr $ra
	
	#Ruta normal.
	fin_bucle_dec:
	li $v1,0
	jr $ra


#Comprueba que el string $a0 sean solo numeros hasta el caracter en $a1.
comprobarNumerico:	
		lb $t1, 0($a0)

		beq $t1, $a1, fin
		beq $t1, 10, fin
		#Comprueba si es menor que '0' en ascii.
		slti $t2, $t1, 48
		beq $t2, 1, caracterIncorrecto
		#Comprueba si es menor que ':'(Caracter ascii a continuacion del '9') en ascii.
		slti $t2, $t1,58
		addi $a0, $a0, 1
		beq $t2, 1, comprobarNumerico
		
	caracterIncorrecto:
	#Si contiene algun caracter incorrecto devuelve un código de error.
	li $v0, 1
	jr $ra
	
	fin:
	li $v0, 0
	jr $ra

#----------------------------------HORARIO A SEGUNDOS----------------------------------#
#Pasa las horas, minutos y segundos a segundos.
#El vector es pasado por $a0.
#Usa el vector utilizado en el parseo y lo cambia al vector base.
aSegundos:
	add $t2, $0, $0
	addi $a0, $a0, 16
	lw $t0, 0($a0)
	slti $t1, $t0, 24
	beq $t1, 0, horario_erroneo
	li $t3, 3600
	mul $t0, $t0, $t3
	add $t2, $t2, $t0
	
	lw $t0, 4($a0)
	slti $t1, $t0, 60
	beq $t1, 0, horario_erroneo
	li $t3, 60
	mul $t0, $t0, $t3
	add $t2, $t2, $t0
	
	lw $t0, 8($a0)
	slti $t1, $t0, 60
	beq $t1, 0, horario_erroneo
	add $t2, $t2, $t0
	sw $t2, 0($a0)
	
	li $v0, 0
	jr $ra
	
	#Si la fecha es incorrecta devuelve un 1
	horario_erroneo:
	li $v0, 1
	jr $ra

#----------------------------------COMPROBAR FECHA----------------------------------#
#Pasando el vector base por $a0, devuelve si esta es correcto o no.
fechaCorrecta:
	addi $sp, $sp, -8
	sw $ra, 4($sp)
	sw $s1, 8($sp)
	
	lw $t0, 12($a0)
	slti $t1, $t0, 0
	beq $t1, 1, fecha_incorrecta
	
	beq $t0, 0, era_cero
	lw $t0, 8($a0)
	slti $t1, $t0, 0
	beq $t1, 1, fecha_incorrecta
	j fin_era
	
	era_cero:
	lw $t0, 8($a0)
	slti $t1, $t0, 1
	beq $t1, 1, fecha_incorrecta

	fin_era:
	lw $t2, 4($a0)
	slti $t1, $t2, 1
	beq $t1, 1, fecha_incorrecta
	li $t5, 12
	slt $t1, $t5, $t2
	beq $t1, 1, fecha_incorrecta
	
	lw $s1, 0($a0)
	slti $t1, $s1, 1
	beq $t1, 1, fecha_incorrecta
	
	#Dependiendo del mes tenemos un max de dias.
	beq $t2, 4, treinta_dias
	beq $t2, 6, treinta_dias
	beq $t2, 9, treinta_dias
	beq $t2, 11, treinta_dias
	beq $t2, 2, febrero
	
	li $t5, 31
	slt $t1, $t5, $s1
	beq $t1, 1, fecha_incorrecta
	j fin_comp_dias
	treinta_dias:
	
	li $t5, 30
	slt $t1, $t5, $s1
	beq $t1, 1, fecha_incorrecta
	j fin_comp_dias	
	febrero:
	
	#a0 sigue siendo a0
	jal esBisiesto
	beq $v0, 1, bisiesto_comp
	
	li $t5, 28
	slt $t1, $t5, $s1
	beq $t1, 1, fecha_incorrecta
	j fin_comp_dias
	bisiesto_comp:
	li $t5, 29
	slt $t1, $t5, $s1
	beq $t1, 1, fecha_incorrecta
	
	fin_comp_dias:
	lw $ra, 4($sp)
	lw $s1, 8($sp)
	addi $sp, $sp, 8
	li $v0, 0
	jr $ra
	
	fecha_incorrecta:
	lw $ra, 4($sp)
	lw $s1, 8($sp)
	addi $sp, $sp, 8
	li $v0, 1
	jr $ra

#----------------------------------CALCULO DE BISIESTOS----------------------------------#

#Pasando el vector base por $a0, devuelve si el año es bisiesto ($v0 = 1) o no ($v0 = 0).
esBisiesto:
	lw $t0, 8($a0)
	li $t1, 4
	div $t0, $t1
	mfhi $t2
	bne $t2, 0, noBisiesto
	
	li $t1, 100
	div $t0, $t1
	mfhi $t2
	bne $t2, 0, esBisiesto_check
	
	li $t1, 400
	div $t0, $t1
	mfhi $t2
	bne $t2, 0, noBisiesto
	
	esBisiesto_check:
	li $v0, 1
	jr $ra
	
	noBisiesto:
	li $v0, 0
	jr $ra

bisiestosAnteriores:

jr $ra
