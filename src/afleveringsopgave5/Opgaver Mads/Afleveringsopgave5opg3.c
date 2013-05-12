/*
 ============================================================================
 Name        : Afleveringsopgave5opg3.c
 Author      : Mads Bornebusch
 Version     :
 Copyright   :
 Description : Opgave 3, The C University Data Base
 ============================================================================
 */

// cudb.c

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define NAME_SIZE 5
#define DB_SIZE 10000

typedef struct {
  char name[NAME_SIZE];
  int data;
} student_t;

int dataconvert(int d, int startVal, int bits){
	int i;
	int bitval=1;
	int result=0;
	for(i=0; i<bits; i++){
		result += ((d/startVal) % 2)*bitval;
		startVal*=2;
		bitval*=2;
	}
	return result;
}


int listAll(student_t *database_p[], int currentSize){
	int i, year, gpa,d;
	char semester[7];

	for(i=0; i<currentSize; i++){
		d=(*database_p[i]).data;
		year = dataconvert(d, 1, 5);

		if ((dataconvert(d, 32, 1))==0){
			strncpy(semester, "Autumn",6);
		}else
			strncpy(semester, "Spring",6);
		semester[6]='\0';

		gpa = dataconvert(d, 64 , 8);

		printf("s%04d %s %d %s %03d\n", i, (*database_p[i]).name, 2009+year, semester, gpa);
	}
	return 0;
}

int newStudent(student_t *database_p[], int currentSize){
	char name[NAME_SIZE];

	int year=2041;
	int semester=2;
	int gpa=256;

	printf("Enter name (4 characters only):\n");
	fflush(stdout);
	scanf("%s4", name);

	while(year<2009||year>2040){
		printf("Enter start year (2009-2040):\n");
		fflush(stdout);
		scanf("%d", &year);
	}

	while(semester!=0 && semester!=1){
		printf("Enter start semester (0=Autumn/1=Spring):\n");
		fflush(stdout);
		scanf("%d", &semester);
	}

	while(gpa<0 || gpa>255){
		printf("Enter GPA (0-255):\n");
		fflush(stdout);
		scanf("%d", &gpa);
	}

	strncpy((*database_p[currentSize]).name, name, NAME_SIZE);
	(*database_p[currentSize]).data = (year-2009)+(32*semester) + (64*gpa);
	//2^5*startsemester og 2^6*karakter fordi disse data starter på 5.og 6. bit

	return 0;
}

int main() {
	int cmd;
	student_t *database_p;
	database_p = calloc(DB_SIZE, sizeof (student_t));
	int currentSize=1;

	puts("Welcome to CUDB - The C University Data Base");
	printf("\n0: Halt \n1: List all students\n2: Add a new student");

	while (cmd!=0){
		printf("\nEnter action:\n");
		fflush(stdout);
		scanf("%d", &cmd);

		if(cmd==1){
			listAll(&database_p, currentSize);
			cmd=3;
		}else if(cmd==2){
			printf("currsz=%d\n", currentSize);
			newStudent(&database_p, currentSize);
			currentSize++;
			printf("currsz=%d\n", currentSize);
			cmd=3;
		}

	}

	free(database_p);
	printf("Bye!");


  return 0;
}

