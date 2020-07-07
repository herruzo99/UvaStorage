.data 
a: .word 1
b: .word 2
c: .space 10
.text
main: lw $t0, a
lw $t1, b
add $t0, $t0, $t1
addi $t0, $t0, 1
la $t1, c
addi $t1, $t1, 16
sw $t0, 0($t1)
li $v0,10
syscall
