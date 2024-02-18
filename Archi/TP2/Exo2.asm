.data
	start: .word 17:20
	end: .word 0
.text
	la $a0,start
	la $a1,end

main: 	jal moy
	j fin_prog
	
sum:
loop:	beq $a0, $a1, l_end
	lw $s2, 0($a0)
	add $v0, $v0, $s2
	addi $a0, $a0, 4
	j loop
l_end:	jr $ra
	

moy: 	
	jal sum
	move $t0, $v0
	sub $t1, $a1, $a0
	li $t3, 4
	div $t1, $t3
	mflo $t1
	div $t0, $t1
	mflo $t2
	jr $ra


fin_prog: