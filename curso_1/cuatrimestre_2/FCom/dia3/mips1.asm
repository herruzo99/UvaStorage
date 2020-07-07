.text
li $v0,5
syscall
or $s0,$v0,$zero
sll $s0,$s0,2
addi $a0,$s0,1
li $v0,1
syscall
li $v0,10
syscall