.data 
c: .word 1,2,4,8,16,32,64,128,256,512
.text
main: la $t0, c
lw $t1, 16($t0)
lw $t2, 32($t0)
add $t1,$t1,$t2
sw $t1, 36($t0)
li $v0,10
syscall
