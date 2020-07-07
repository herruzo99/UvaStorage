.data

A:.word 1, 2, 3, 5, 8, 13, 21, 34
B:.word  0x01, 0x02, 0x02, 0x04, 0x08, 0x20, 0x100, 0x2000

.text

main:
lw $s0, A
lb $s1, A

li $v0, 1
add $a0, $s0, $0
syscall

li $v0, 1
add $a0, $s1, $0
syscall


la $s0, A
la $s1, B
addi $s2, $s0, 32
cambio:
lw $t0, 0($s0)
lw $t1, 0($s1)

sw $t1, 0($s0)
sw $t0, 0($s1)

addi $s0, $s0, 4
addi $s1, $s1, 4

bne $s0, $s2, cambio

la $s0, B
addi $s2, $0, 32
addi $s1, $s0, 32
escribir:
li $v0, 1
lw $t0, 0($s0)
add $a0, $t0, $0
syscall

addi $s0, $s0, 4

li $v0, 11
add $a0, $s2, $0
syscall

bne $s0, $s1, escribir
li $v0, 10
syscall