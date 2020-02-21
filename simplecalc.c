#include<stdio.h>
#include<stdlib.h>

/*
program to implement a simple calculator
***************************************************************
* Author 	Dept.		Date		Notes
* *************************************************************
* Gattuoch Kuon	Comp. Science 	Feb 17 2020 	Initial version
*/
int main(int argc, char*argv[]){
	// check if the user input are exactly 3 arguments
	if (argc != 4){
		printf("Error :Usage is simplecalc <x> <op> <y>\n");
		return  1;
	}
	int  x = atoi(argv[1]);
	char  op = argv[2][0];
	int y = atoi(argv[3]);

//Arithmetics on the operations
switch(op){
	case '+':
		printf("%d\n",x+y);
		return 0;
		break;
	case '-':
                printf("%d\n",x-y);
                break;
	case '*':
                printf("%d\n",x*y);
                break;

	case '/':
                printf("%d\n",x/y);
                break;
	
	default:
		printf("Not a valid operator\n");
		return  2;
}
	
return 0;
}


	
