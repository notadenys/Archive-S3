li $s1, 0
li $s0, 1
loop: 	add $s1, $s1, $s0
	addi $s0, $s0, 1
	bne $s0, 42, loop