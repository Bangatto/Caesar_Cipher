#include<stdio.h>
#include<stdlib.h>
/*
program that takes in plain texts and produces a cipher of tets using Caesar Ciphers
************************************************************************************
* Author        Dept.           Date            Notes
* *********************************************************************************
* Gattuoch Kuon Comp. Science   Feb 18 2020     Initial version
* Gattuoch Kuon Comp. Science	Feb 20 2020  	Made Changes to error
*/
int main(int argc, char*argv){
	if(argc <= 1){
		printf("Error: Usage is caesacipher <offset>\n");
		return 1;
	}
	int key = (argv[1]);
	char plainText[100];
	char letters;

	//reading multiple lines
	while(fgets(plainText,100, stdin) !=NULL){
		int i;
		for(i=0; plainText[i] != '\0';i++){
			letters=plainText[i];
			//check whether the character is between a and z.
			if (letters >='a' && letters <= 'z' ){	
				if (key > 0){;
					letters=(((letters-'a')+key)%26) + 'a';
				}
				else{
					letters=(((letters-'z')+key)%26) + 'z';
				}
				plainText[i]=letters;
			}
			else{
				continue;
			}
			plainText[i]=letters;
		}
		printf("%s", plainText);
	}
return 0;
}
