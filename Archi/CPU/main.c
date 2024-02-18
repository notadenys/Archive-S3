/*
 * -------------------------- TP du module Archi -------------------------
 *
 * Un mot/registre de NBITS bits (par défaut NBITS=16) est représenté par un tableau d'entiers égaux à 0 ou 1.
 * Une ALU est représentée par une structure ALU, avec registre accumulateur et registre d'état.
 * Un CPU (très très simplifié) est représenté par une ALU et quelques registres nécessaires pour stocker les résultats intermédiaires.
 *
 * Certaines fonctions vous sont fournies, d'autres sont à implanter ou à compléter, de préférence dans l'ordre où eles sont indiquées.
 * Il vous est fortement conseillé de lire attentivement l'ensemble des commentaires.
 *
 * Parmi les opérations arithmétiques et logiques, seules 4 opérations de base sont directement fournies par l'ALU, les autres doivent être décrites comme des algorithmes
 * travaillant à l'aide des opérateurs de base de l'ALU simplifiée et pouvant utiliser les registres du CPU.
 *
 * La fonction main() vous permet de tester au fur et à mesure les fonctions que vous implantez.
 *
 * ----------------------------------------------------------------------------------------------
 *
 * author: B. Girau
 * version: 2021-22
 */
#include <stdio.h>
#include <stdlib.h>

#define NBITS 16  // attention, votre programme doit pouvoir être adapté à d'autres tailles juste en modifiant la valeur de cette constante
// en ayant toujours NBITS < 32

/////////////////////////////////////////////////////////
// définition de types
/////////////////////////////////////////////////////////

typedef struct {
  int* accu;
  int* flags; // indicateurs ZF CF OF NF
} ALU;

typedef struct {
  ALU alu;
  int* R0;
  int* R1;
  int* R2;
} CPU;

/////////////////////////////////////////////////////////
// fonctions d'initialisation
/////////////////////////////////////////////////////////

/*
 * allocation d'un mot entier de NBITS bits initialisé à 0
 */
int* word() {
  int* tab;
  int i;
  tab=(int*)malloc(NBITS*sizeof(int));
  for(i=0;i<NBITS;i++) tab[i]=0;
  // poids faible : tab[0]
  // poids fort : tab[NBITS-1]
  return tab;
}

/*
 * Initialisation du mot (mot de NBITS bits, codant un entier en Cà2) avec une valeur entière.
 */
void setValue(int* word,int n) {
  int n_low = n & 0x7FFFFFFF;
  // revient à mettre à 0 le bit de poids fort en 32 bits
  // on peut alors travailler sur la partie positive du codage de n
  // remarque : si n est bien codable en Ca2 sur NBITS, et si n est négatif, on récupère quand même le codage de n sur NBITS en Ca2 en prenant les NBITS de poids faible de n_low
  
  // Initialisation du mot
  for (int i = 0; i < NBITS; i++) {
    word[i] = n & 1;
    n >>= 1;
}
}

/*
 * instanciation d'un mot de NBITS bits initialisé avec la valeur n
 */
int* initWord(int n) {
  int* tab=word();
  setValue(tab,n) ;
  return tab;
}
    
/*
 * Initialisation du mot (mot de NBITS bits) par recopie des bits du mot en paramètre.
 */
void copyValue(int* word,int* src) {
    for (int i = 0; i < NBITS; i++) word[i] = src[i];
}

/*
 * instanciation d'un mot de NBITS bits initialisé par recopie d'un mot
 */
int* copyWord(int* src) {
  int* tab=word();
  copyValue(tab,src) ;
  return tab;
}

/*
 * initialise l'ALU
 */
ALU initALU() {
  ALU res;
  res.accu=word();
  res.flags=(int*)malloc(4*sizeof(int));
  return res;
}

/*
 * initialise le CPU
 */
CPU initCPU() {
  CPU res;
  res.alu=initALU();
  res.R0=(int*)malloc(NBITS*sizeof(int));
  res.R1=(int*)malloc(NBITS*sizeof(int));
  res.R2=(int*)malloc(NBITS*sizeof(int));
  return res;
}

/////////////////////////////////////////////////////////
// fonctions de lecture
/////////////////////////////////////////////////////////

/*
 * Retourne la valeur entière signée représentée par le mot (complément à 2).
 */    
int intValue(int* word) {
  // Vérification du bit de poids fort
  int is_negative = word[NBITS - 1] == 1;

  // Conversion du mot en valeur entière non signée
  int value = 0;
  for (int i = 0; i < NBITS; i++) {
    value += word[i] << i;
  }

  // Ajout de 1 si le nombre est négatif
  if (is_negative) {
    value = -value - 1;
  }

  return value;
}


/*
 * Retourne une chaîne de caractères décrivant les NBITS bits
 */
char* toString(int* word) {
  int i,j=0;
  char* s=(char*)malloc((2+NBITS)*sizeof(char));
  for (i=NBITS-1;i>=0;i--) {
    if (word[i]==1) s[j]='1';
    else s[j]='0';
    j++;
  }
  s[j]=0;
  return s;
}

/*
 * Retourne l'écriture des indicateurs associés à l'ALU.
 */
char* flagsToString(ALU alu) {
  char *string=(char*)malloc(10*sizeof(char));
  sprintf(string,"z%dc%do%dn%d",alu.flags[0],alu.flags[1],alu.flags[2],alu.flags[3]);
  return string;
}

/*
 * affiche à l'écran le contenu d'une ALU
 */
void printing(ALU alu) {
    printf("%s\n%s", toString(alu.accu), flagsToString(alu));
}

/////////////////////////////////////////////////////////
// fonctions de manipulations élémentaires
/////////////////////////////////////////////////////////

/*
 * Mise à la valeur b du bit spécifié dans le mot
 */
void set(int* word,int bitIndex,int b) {
  if ((bitIndex > NBITS-1) || (bitIndex < 0)) 
    printf("valeur d'index incorrecte\n");
  word[bitIndex] = b ;
}

/*
 * Retourne la valeur du bit spécifié dans le mot
 */
int get(int* word,int bitIndex) {
  if ((bitIndex > NBITS-1) || (bitIndex < 0)) 
    printf("valeur d'index incorrecte\n");
  return word[bitIndex] ;
}

/*
 * Positionne l'indicateur ZF en fonction de l'état de l'accumulateur
 */
void setZ(ALU alu) {
  int zf = 0;
  for (int i = 0; i < NBITS; i++)
  {
    if (get(alu.accu, i))
      {
        zf = 1;
        break;
      }
  }
  alu.flags[0] = zf;
}

/////////////////////////////////////////////////////////
// opérateurs de base de l'ALU
// IMPORTANT : les indicateurs doivent être mis à jour
/////////////////////////////////////////////////////////

/*
 * Stocke le paramètre dans le registre accumulateur
 */
void pass(ALU alu,int* B) {
  for (int i = 0; i < NBITS; i++)
  {
    set(alu.accu, i, B[i]);
  }
  setZ(alu);
  alu.flags[3] = B[NBITS-1];
}

/*
 * Effectue un NAND (NON-ET) entre le contenu de l'accumulateur et le paramètre.
 */
void nand(ALU alu,int* B) {
  for (int i = 0; i < NBITS; i++)
  {
    set(alu.accu, i, ((get(alu.accu, i) && B[i]) ? 0 : 1));
  }
  setZ(alu);
  alu.flags[3] = get(alu.accu, NBITS-1);
}


/*
 * Décale le contenu de l'accumulateur de 1 bit vers la droite
 */
void shift(ALU alu) {
  int carry = get(alu.accu, NBITS - 1);

  for (int i = NBITS - 1; i >= 0; i--) {
    int bit = get(alu.accu, i);
    set(alu.accu, i, carry);
    carry = bit;
  }

  alu.flags[1] = carry;

  setZ(alu);
}


/*
 * module Full Adder : a+b+c_in = s + 2 c_out
 * retourne un tableau contenant s et c_out
 */
int* fullAdder(int a, int b, int c_in) {
  int* res = (int*)malloc(2 * sizeof(int));

  res[0] = (a ^ b) ^ c_in;
  res[1] = (a & b) | ((a ^ b) & c_in);

  return res;
}

/*
 * Full Subtractor: a - b - borrow = difference + borrow_out
 * Returns an array [difference, borrow_out]
 */
int* fullSubtractor(int a, int b, int borrow) {
  int* result = (int*)malloc(2 * sizeof(int));

  result[0] = (a ^ b) ^ borrow;
  result[1] = (!a && b) || ((a ^ b) && borrow);

  return result;
}


/*
 * Additionne le paramètre au contenu de l'accumulateur (addition entière Cà2).
 * Les indicateurs sont positionnés conformément au résultat de l'opération.
 */
void add(ALU alu, int *B) {
  int carry = 0; // Initialize carry to 0

  for (int i = 0; i < NBITS; i++) {
    // Perform full addition of accumulator bit, parameter bit, and carry
    int *result = fullAdder(get(alu.accu, i), get(B, i), carry);
    set(alu.accu, i, result[0]);
    carry = result[1];

    free(result);
  }

  setZ(alu); 
  alu.flags[1] = carry;

  // Définir le drapeau de débordement (OF) si le MSB des deux opérandes est 1, mais que le MSB du résultat est 0 (indiquant un débordement)
  alu.flags[2] = (get(alu.accu, NBITS - 1) & get(B, NBITS - 1) & !get(alu.accu, NBITS - 1)) || (!get(alu.accu, NBITS - 1) & !get(B, NBITS - 1) & get(alu.accu, NBITS - 1));

  alu.flags[3] = get(alu.accu, NBITS - 1);
}



////////////////////////////////////////////////////////////////////
// Opérations logiques :
////////////////////////////////////////////////////////////////////

/*
 * Négation.
 */
void not(CPU cpu){
  for (int i = 0; i < NBITS; i++)
  {
    set(cpu.alu.accu, i, ((get(cpu.alu.accu, i)) ? 0 : 1));
  }
  setZ(cpu.alu);
}

/*
 * Et.
 */
void and(CPU cpu,int* B) {
  for (int i = 0; i < NBITS; i++)
  {
    set(cpu.alu.accu, i, (get(cpu.alu.accu, i) && B[i]));
  }
  setZ(cpu.alu);
}


/*
 * Ou.
 */
void or(CPU cpu,int* B) {
  for (int i = 0; i < NBITS; i++)
  {
    set(cpu.alu.accu, i, (get(cpu.alu.accu, i) || B[i]));
  }
  setZ(cpu.alu); 
  cpu.alu.flags[3] = get(cpu.alu.accu, NBITS - 1);
  // Le drapeau de retenue (CF) n'est généralement pas utilisé dans les opérations logiques, donc nous ne le mettons pas à jour ici.
}


/*
 * Xor.
 */
void xor(CPU cpu,int* B) {
  for (int i = 0; i < NBITS; i++)
  {
    set(cpu.alu.accu, i, (get(cpu.alu.accu, i) ^ B[i]));
  }
  setZ(cpu.alu);
  cpu.alu.flags[3] = get(cpu.alu.accu, NBITS - 1);
}

/*
 * Décale le receveur de |n| positions.
 * Le décalage s'effectue vers la gauche si n>0 vers la droite dans le cas contraire.
 * C'est un décalage logique (pas de report du bit de signe dans les positions 
 * libérées en cas de décalage à droite).
 * L'indicateur CF est positionné avec le dernier bit "perdu".
 */
void logicalShift(CPU cpu,int n) {
  if (n < 0) {
    n = -n; // On prends la valeur absolue de n au but de simplification

    for (int i = 0; i < n; i++) {
      int lostBit = get(cpu.alu.accu, NBITS - 1);

      for (int j = NBITS - 1; j > 0; j--) {
        set(cpu.alu.accu, j, get(cpu.alu.accu, j - 1));
      }
      set(cpu.alu.accu, 0, 0); 

      cpu.alu.flags[1] = lostBit;
    }
  }
  else if (n > 0) {
    for (int i = 0; i < n; i++) {

      int lostBit = get(cpu.alu.accu, 0);

      for (int j = 0; j < NBITS - 1; j++) {
        set(cpu.alu.accu, j, get(cpu.alu.accu, j + 1));
      }
      set(cpu.alu.accu, NBITS - 1, 0);

      cpu.alu.flags[1] = lostBit;
    }
  }
  setZ(cpu.alu); 
}

/////////////////////////////////////////////////////////
// Opérations arithmétiques entières
/////////////////////////////////////////////////////////

/*
 * Opposé.
 */
void opp(CPU cpu) {
  for (int i = 0; i < NBITS; i++) {
    set(cpu.alu.accu, i, !get(cpu.alu.accu, i));
  }

  setZ(cpu.alu);

  // Effacer le drapeau de retenue (CF) car il n'y a pas de retenue dans la négation en complément à deux
  cpu.alu.flags[1] = 0;

  cpu.alu.flags[3] = get(cpu.alu.accu, NBITS - 1);
}


/*
 * Soustraction.
 */
void sub(CPU cpu, int* B) {
  int borrow = 0;

  for (int i = 0; i < NBITS; i++) {

    int *result = fullSubtractor(get(cpu.alu.accu, i), B[i], borrow);

    set(cpu.alu.accu, i, result[0]);
    borrow = result[1];

    free(result);
  }

  setZ(cpu.alu); 
  cpu.alu.flags[1] = borrow;

  // Mettre à jour le drapeau de débordement (OF) s'il y a eu emprunt dans le bit de signe mais pas à l'extérieur
  cpu.alu.flags[2] = (get(cpu.alu.accu, NBITS - 1) & !B[NBITS - 1] & !get(cpu.alu.accu, NBITS - 1)) || (!get(cpu.alu.accu, NBITS - 1) & B[NBITS - 1] & get(cpu.alu.accu, NBITS - 1));

  cpu.alu.flags[3] = get(cpu.alu.accu, NBITS - 1);
}


/*
 * Multiplication.
 */
void mul(CPU cpu, int* B) {
    int result[NBITS];
    for (int i = 0; i < NBITS; i++) {
        result[i] = 0;
    }


    int overflow = 0;

    // Effectuer une multiplication à l'aide de l'algorithme Shift-and-Add
    for (int i = 0; i < NBITS; i++) {
        if (B[i]) {
            int carry = 0;
            for (int j = 0; j < NBITS; j++) {
                int sum = result[j] + cpu.alu.accu[j] + carry;
                result[j] = sum % 2;
                carry = sum / 2;
            }

            if (carry != 0) {
                overflow = 1;
            }
        }

        for (int j = NBITS - 1; j > 0; j--) {
            cpu.alu.accu[j] = cpu.alu.accu[j - 1];
        }
        cpu.alu.accu[0] = 0;
    }

    for (int i = 0; i < NBITS; i++) {
        cpu.alu.accu[i] = result[i];
    }
    setZ(cpu.alu);
    cpu.alu.flags[1] = 0;
    cpu.alu.flags[2] = overflow;
    cpu.alu.flags[3] = get(cpu.alu.accu, NBITS - 1);
}




/////////////////////////////////////////////////////////
// Programme de test
/////////////////////////////////////////////////////////

int main(int argc,char *argv[]) {
  
  /*
    Ce programme est fourni à titre d'exemple pour permettre de tester simplement vos fonctions.
    Il vous est bien entendu possible de le modifier/compléter, ou encore d'écrire vos propres fonctions de test.
   */
  
  int* operand;
  ALU alu;
  CPU cpu;
  
  int chosenInt,integer ;
  int go_on = 1 ;
  
  char* menu =     
    "              Programme de test\n\n0  Quitter\n1  setValue(operande,int)\n2  pass(alu,operande)\n3  printing(alu)\n4  afficher toString(operande)\n5  afficher intValue(operande)\n6  afficher intValue(accu)\n7  accu=nand(accu,operande)\n8  accu=add(accu,operande)\n9  accu=sub(accu,operande)\n10  accu=and(accu,operande)\n11 accu=or(accu,operande)\n12 accu=xor(accu,operande)\n13 accu=not(accu)\n14 accu=opp(accu)\n15 accu=shift(accu)\n16 accu=logicalShift(accu,int)\n17 accu=mul(accu,operande)\n\n" ;
  
  char* invite = "--> Quel est votre choix  ? " ;
  
  printf("%s",menu) ; 

  operand=word();
  cpu=initCPU();
  alu=cpu.alu;
  
  while (go_on==1) {
    printf("%s",invite);
    scanf("%d",&chosenInt);
    switch (chosenInt) {
    case 0 : 
      go_on = 0;
      break ;
    case 1 :
      printf("Entrez un nombre :"); 
      scanf("%d",&integer);
      setValue(operand,integer);
      break ;
    case 2 : 
      pass(alu,operand);
      break ;
    case 3 : 
      printing(alu);
      break ;
    case 4 : 
      printf("%s\n",toString(operand));
      break ;
    case 5 : 
      printf("intValue(operand)=%d\n",intValue(operand));
      break ;
    case 6 : 
      printf("intValue(accu)=%d\n",intValue(alu.accu));
      break ;
    case 7 : 
      nand(alu,operand);
      printf("apres nand(), accu = ");
      printing(alu);
      break ;
    case 8 : 
      add(alu,operand);
      printf("apres add(), accu = ");
      printing(alu);
      break ;
    case 9 : 
      sub(cpu,operand);
      printf("apres sub(), A = ");
      printing(alu);
      break ;
    case 10 : 
      and(cpu,operand);
      printf("apres and(), A = ");
      printing(alu);
      break ;
    case 11 : 
      or(cpu,operand);
      printf("apres or(), A = ");
      printing(alu);
      break ;
    case 12 : 
      xor(cpu,operand);
      printf("apres xor(), A = ");
      printing(alu);
      break ;
    case 13 : 
      not(cpu);
      printf("apres not(), A = ");
      printing(alu);
      break ;
    case 14 : 
      opp(cpu);
      printf("apres opp(), A = ");
      printing(alu);
      break ;
    case 15 : 
      shift(alu);
      printf("apres shift(), A = ");
      printing(alu);
      break ;
    case 16 : 
      printf("Entrez un entier :") ;
      scanf("%d",&integer);
      logicalShift(cpu,integer);
      printf("apres logicalshift(%d), A = ",integer);
      printing(alu);
      break ;
    case 17 : 
      mul(cpu,operand);
      printf("apres mul(), A = ");
      printing(alu);
      break ;
    default : 
      printf("Choix inexistant !!!!\n");
      printf("%s",menu);
    }
  }
  printf("Au revoir et a bientot\n");
  return 0;
}
