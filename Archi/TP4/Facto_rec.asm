.text
main:	li $a0, 5
	jal fact
	j fin
	
fact:	sw $ra, 0($sp)
	sub $sp, $sp, 4
	beq $a0, 0, cas0
	move $t0, $a0
	subi $a0, $a0, 1
	jal fact
	mul $v0, $v0, $t0
	j ff

cas0:	li $v0, 1
	addi $sp, $sp, 4
	lw $ra, 0($sp)
	
ff:	jr $ra
fin: