.data
f: .word -1000000
g: .word 10
h: .word 5
.text
main: lw $t0, g
lw $s0, h
add $t0, $t0, $s0
addi $t0, $t0, -2
lw $s0, f
sub $s0, $s0, $t0
sw $s0, f
li $v0, 10
syscall

