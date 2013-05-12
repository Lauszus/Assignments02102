// cudb.c

#include <stdio.h>

#define NAME_SIZE 5
#define DB_SIZE 10000

typedef struct {
	char name[NAME_SIZE];
	int data;
} student_t;

int bitconvert(int i, int a){
	int k, bit, binary, n;
	if(i == 1){
		if(a>=2009){
			int bin1;
			for(k=0; k <= 14; k++){
				bin1 += ((a)/bit)%2;
				bit *= 2;
			}
			binary = bin1;
		}
		else if(a<2009){
			int bin2;
			for(k=0; k <= 7; k++){
				bin2 += ((a)/bit)%2;
				bit *= 2;
			}
			binary =  bin2;
		}
	}

	return binary;
}

void ListStudents(student_t * database_p, databaseSize){
	if(databaseSize<10){
		printf()
	}
}
void newStudent(student_t * database_p, int databaseSize){
	int year, semester, gpa;
	char name[NAME_SIZE];

	printf("Enter name (4 characters only):\n");
	fflush(stdout);
	scanf("%s4", name);

	printf("Enter start year (2009-2040):\n");
	fflush(stdout);
	scanf("%d", &year);

	printf("Enter start semester (0=Autumn/1=Spring):\n");
	fflush(stdout);
	scanf("%d", &semester);

	printf("Enter GPA (0-255):\n");
	fflush(stdout);
	scanf("%d", &gpa);
	database_p[databaseSize]->name=name;
	database_p[databaseSize]->data = (year-2009) +semester*32 + gpa*64;

}

int main() {
	int a;
	int databaseSize=0;
	student_t *database_p = (student_t*)malloc(sizeof (student_t));
	database_p = (student_t*)realloc(database_p, sizeof(student_t)*DB_SIZE);
	puts("Welcome to CUDB - The C University Data Base\n\n");

	puts("0: Halt \n1: List all students \n2: Add new student \n \n Enter action: \n");
	fflush(stdout);
	scanf("%d", &a);

	while (a != 0){

		if(a == 1)
		{if(databaseSize <1){
			puts("No students in the database");
		} else
		{ListStudents(database_p, databaseSize);
		a = 3;
			}
		}
		else if(a==2)
		{newStudent(database_p, databaseSize);
		databaseSize++;
		a = 3;
		}
	}
	if(a == 0)
		printf("\n bye");

	return 0;
}
