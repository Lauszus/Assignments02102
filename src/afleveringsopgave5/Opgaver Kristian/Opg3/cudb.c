#include <stdlib.h>
#include <stdio.h>

#define NAME_SIZE 5

typedef struct {
	char name[NAME_SIZE];
	int data;
} student_t;

/*
Bit 0–4: Startår (med udgangspunkt i år 2009, altså for eksempel 1 betyder 2010).
Bit 5: Startsemester (0 for efterår og 1 for forår).
Bit 6–13: Karaktersnit (255 er naturligvis den bedste karakter).
*/

void printStudent(int number, student_t * student) {
	printf("s%04d: %s %d ", number, student->name, (student->data & 0x1F) + 2009);
	if (student->data & 0x20)
		printf("Spring");
	else
		printf("Autumn");
	printf(" %d\n", (student->data >> 6) & 0xFF);
}

int main() {
	puts("Welcome to CUDB - The C University Data Base\n");
	puts("0: Halt\n1: List all students\n2: Add a new student");

	student_t * students = NULL;
	int i, temp, action = -1, studentsSize = 0;

	while(1) {
		students = (student_t*)realloc(students,sizeof(student_t)*(studentsSize+1));

		puts("\nEnter action:");
		fflush(stdout);
		scanf("%d", &action);
		fflush(stdin);

		if (action == 0)
			break;
		else if (action == 1) {
			if (studentsSize > 0) {
				printf("\n");
				for (i = 0; i < studentsSize; i++)
					printStudent(i,students+i);
			}
		} else {
			puts("\nEnter name (4 characters only):");
			fflush(stdout);
			scanf("%s", (students+studentsSize)->name);

			puts("\nEnter start year (2009-2040):");			
			fflush(stdout);
			scanf("%d", &temp);

			(students+studentsSize)->data = (temp-2009) & 0x1F;

			puts("\nEnter start semester (0=Autumn/1=Spring):");			
			fflush(stdout);
			scanf("%d", &temp);

			(students+studentsSize)->data |= (temp & 0x1) << 5;

			puts("\nEnter GPA (0-255):");
			fflush(stdout);
			scanf("%d", &temp);

			(students+studentsSize)->data |= (temp & 0xFF) << 6;

			studentsSize++;
		}
	}

	puts("\nBye");
	free(students);

	return 0;
}