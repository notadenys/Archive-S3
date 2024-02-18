.text
main: 	li $a0, 5
	jal fact
	j fin
	
fact:	move $t0, $a0
	li $t1, 1
loop:	beq $t0, 0, finloop
	mul $t1, $t1, $t0
	subi $t0, $t0, 1
	j loop
finloop:move $v0, $t1

fin: 