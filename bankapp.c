#include<stdio.h>
#include<stdlib.h>
#include<string.h>
/*
program that implement a simple banking applications. The bankapp allows users to  add their account numbers, make  deposit and to withdraw.
All the baning information are stored in a CSV file bankdata.csv
************************************************************************************
* Author        Dept.           Date            Notes
* *********************************************************************************
* Gattuoch Kuon Comp. Science   March	8    2020     Initial version
* Gattuoch Kuon Comp. Science   March   11   2020     Made Changes to errors
* Gattuoch Kuon Comp. Science 	March	12   2020     Made chnages to withdrawal account
* Gattuoch Kuon Comp. Science 	March	13   2020     debugged to make sure the code work
*/
FILE* readFile(){
	FILE *fp;
	fp=fopen("bankdata.csv", "r");
        if(fp == NULL)
        {
               fprintf(stderr,"Error, unable to locate data file bankdata.csv\n");
               return NULL;
        }else{

		return fp;
	}
}
//a function to print errors and use it over and over
void printError() {
	fprintf(stderr, "Error, incorrect usage!\n");
	fprintf(stderr, "-a ACCTNUM NAME\n");
	fprintf(stderr, "-d ACCTNUM DATE AMOUNT\n");
	fprintf(stderr,"-w ACCTNUM DATE AMOUNT\n");
}
int main(int argc, char* argv[]){
	 //initialize the variables
        float amount=0;
	char lines[40];
	FILE *fp;
	int accountFound = 0; //keep track whether account exists or not
	float  temp;
	float requestedAmount;

        if(argc <= 1)
	{
		printError();
       		return 1;
	}     
	//check the number of arguments provided to add an account are all given
	if(strcmp(argv[1], "-a")==0)
	{
		if (argc != 4)
		{
			fprintf(stderr, "Error, incorrect usage!\n");
			fprintf(stderr, "-a ACCTNUM NAME\n");
			return 1;
		}else{
			fp=readFile();
		        if(fp==NULL){
       			return 100;
       			}
       		 //read until the end of the line
       			while(!feof(fp)){
				fgets(lines,39,fp);
               			if(strncmp(lines+3,argv[2],4)==0){
               			fprintf(stderr,"Error, account number %s already exists\n", argv[2]);
               			return 50;
         			}
        		}
        		fclose(fp);
        		//append the new account into the csv file
       			fp=fopen("bankdata.csv","a+");
         		fprintf(fp, "AC,%s,%s\n",argv[2],argv[3]);
        		fclose(fp);

		}
	}
	//check if the number of arguments passed to make a deposit are suffient
	else if(strcmp(argv[1], "-d")==0)
        {
                if (argc != 5)
                {
                       fprintf(stderr, "Error, incorrect usage!\n");
                       fprintf(stderr, "-d ACCTNUM NAME\n");
                        return 1;
                }else{
			fp=readFile();
			if(fp==NULL){
				return 100;
			}
			while(!feof(fp)){
				fgets(lines,39,fp);
				if(strncmp(lines+3,argv[2],4)==0){
					accountFound = 1;
				}
			}
			//chech whether the account provided exists
			if (accountFound == 0){
				fprintf(stderr,"Error, account number %s does not exists\n", argv[2]);
				return 50;
			}
			//append the amount to the existing account
			fclose(fp);
			fp=fopen("bankdata.csv", "at");
			fprintf(fp, "TX,%s,%s,%s\n",argv[2],argv[3],argv[4]);
			fclose(fp);
		}			
        }
	//check if the number of arguments to make a withdrwal are passed
	else if(strcmp(argv[1], "-w")==0)
        {
                if (argc != 5)
		{
                        fprintf(stderr, "Error, incorrect usage!\n");
                        fprintf(stderr, "-w ACCTNUM DATE AMOUNT\n");
                        return 1;
                }else{
			fp=readFile();
			if(fp==NULL){
				return 100;
			}
			while(!feof(fp)){
				fgets(lines, 39,fp);
				if(strncmp(lines+3,argv[2],4)==0){
					accountFound = 1;
					if(strncmp(lines,"TX",2)==0){
						temp = strtof(lines+19,NULL);
						amount +=temp;
					}
				}
			}
			 //return an error if the account does not exists
                        if (accountFound == 0){
                                fprintf(stderr,"Error, account number %s does not exists\n", argv[2]);
                                return 50;
                        }
                        fclose(fp);

			//check whether the account has enough money to be withdrawn, return an error if its insufficient
			requestedAmount = strtof(argv[4], NULL);
			if (requestedAmount>amount){
				fprintf(stderr, "Error, account number %s has only %.2f\n", argv[2], amount);
        			return 60;
			}
			//append the new data into the csv file
			fp=fopen("bankdata.csv", "at");
			fprintf(fp, "TX,%s,%s,%.2f\n",argv[2],argv[3],requestedAmount * -1);
			fclose(fp);
			return 0;
		}

        } else {
		printError();
		return 1;
	}
}
