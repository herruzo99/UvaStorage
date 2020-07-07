.data
Vector: .word 1,4,7,10,13,15,18, 21, 24,27
.text
main: addi $s0, $zero, 10
add $s2, $zero, $zero
add $s3,$zero, $zero
la $s1, Vector
Bucle: sll $t1, $s2, 2
add $t1, $t1, $s1
lw $t0, 0($t1)
add $s3, $s3, $t0
addi $s2, $s2, 1
addi $s0, $s0, -1
bne $s0, $zero, Bucle
li $v0, 10              #exit
syscall