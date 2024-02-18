.data

mem:	.word 0 : 42
V:	.word 6
S:	.word 7

.text

main:	la $s0,mem
	la $s1,V
	lw $s1,0($s1)
	la $s2,S
	lw $s2,0($s2)

	addi $t0,$zero,0
boucle1:mul $t1,$t0,4
	add $t1,$t1,$s0
	sw $t0,0($t1)
	addi $t0,$t0,1
	bgt $s2,$t0,boucle1

	addi $a0,$zero,0
	
boucle2:sw $s0,0($sp)
	sw $s1,-4($sp)
	sw $s2,-8($sp)
	sw $a0,-12($sp)
	addi $sp,$sp,-16
	jal yin
	lw $a0,4($sp)
	lw $s2,8($sp)
	lw $s1,12($sp)
	lw $s0,16($sp)
	addi $sp,$sp,16
	
	addi $a0,$a0,1
	bgt $s2,$a0,boucle2
	
	addi $v0,$zero,10
	syscall


yin:	li $s0, 1  # i
	li $s2, 1  # val
	move $s1, $a0  # j
	lw $s5, V  # load V into $s5
loop:	beq $s0, $s5, f_loop  #for loop

	add $a0, $zero, $s0  # send i, j, val to $a0, $a1, $a2 resp. to be sent to yang as parameters
	add $a1, $zero, $s1
	add $a2, $zero, $s2
	jal yang
fin_yang:	
	addi $s2, $s2, 1  # val = val + 1
	
	# r = i%2
	addi $s3, $zero, 2
	div $s0, $s3
	mfhi $s4
	
	add $s2, $s2, $s4  # val = val + r
	
	addi $s0, $s0, 1  # i++
f_loop:	jr $ra  #links to fin_yang and causes infinite loop (error)

yang:   la $t0, mem  # load the address of mem into $t0
	lw $t3, S  # load S into $t3
   	mul $t2, $a0, $a2  # $t2 = i * val
   	mul $t2, $t2, $t3  # $t2 = S * i * val
   	mul $t2, $t2, 4  # $t2 = 4*(S*i+j)
   	add $t2, $t2, $t0  # $t2 = mem + 4*(S*i+j)

   	lw $t1, 0($t2)  # load mem[i][j] into $t1

   	sub $t5, $a1, $a2  # $t5 = j - val
  	# store mem[i-1][j-val] in $t4
     	la $t6, mem
     	subi $t7, $a0, 1
     	mul $t7, $t7, $a2
     	mul $t7, $t7, $t3
     	mul $t7, $t7, 4
     	add $t7, $t7, $t6
     	lw $t4, 0($t7)

   	add $t1, $t1, $a0  # mem[i][j] += i - j
   	slt $t4, $t3, $t1  # if mem[i][j] < cmp then mem[i][j] = cmp
   	bne $t4, $zero, mem_update
   	j fin_yang

mem_update:
   sw $t1,0($t2)  # store mem[i][j]

   jr $ra

