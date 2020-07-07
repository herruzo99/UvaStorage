.data
f: .word 1
g: .word 10
h: .word 5
.text
main: lw $t0, g
lw $t1, h
lw $t2, f
add $t2,$t2,$t1
sll $t2, $t2, 2
add $t0,$t0,$t1
sll $t0, $t0, 3
add $t2,$t2,$t0
sw $t2, f
li $v0,10
syscall