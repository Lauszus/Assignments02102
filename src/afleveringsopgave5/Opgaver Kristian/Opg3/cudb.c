#include <stdlib.h>
#include <stdio.h>
#include <string.h>

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

	int i, temp, studentsSize = 0;
	student_t * students = (student_t*)malloc(sizeof(student_t));

	FILE * file = fopen("database.txt","r"); // Read from a file
	if (file != NULL) {
		puts("Reading students from database");
		while (fscanf(file,"%s %d\n", (students+studentsSize)->name, &(students+studentsSize)->data) != -1) {
			studentsSize++;
			students = (student_t*)realloc(students, sizeof(student_t)*(studentsSize+1));
		}
		fclose(file);
		
		if (studentsSize > 0) {
			puts("\nExisting students from database:");
			for (i = 0; i < studentsSize; i++)
				printStudent(i,students+i);
		}
	}

	while(1) {
		puts("\nEnter action:");
		scanf("%d", &temp);

		if (temp == 0)
			break;
		else if (temp == 1) {
			if (studentsSize > 0) {
				printf("\n");
				for (i = 0; i < studentsSize; i++)
					printStudent(i,students+i);
			}
		} else {
			puts("\nEnter name (4 characters only):");
			scanf("%4s", (students+studentsSize)->name);
			while(getchar() != '\n');

			do {
				puts("\nEnter start year (2009-2040):");
				scanf("%d", &temp);
			} while(temp < 2009 || temp > 2040);

			(students+studentsSize)->data = (temp-2009) & 0x1F;

			do {
				puts("\nEnter start semester (0=Autumn/1=Spring):");
				scanf("%d", &temp);
			} while(temp < 0 || temp > 1);

			(students+studentsSize)->data |= (temp & 0x1) << 5;

			do {
				puts("\nEnter GPA (0-255):");
				scanf("%d", &temp);
			} while(temp < 0 || temp > 255);

			(students+studentsSize)->data |= (temp & 0xFF) << 6;

			studentsSize++;
			students = (student_t*)realloc(students, sizeof(student_t)*(studentsSize+1));
		}
	}	

	if (studentsSize > 0) {
		file = fopen("database.txt","w");
		if (file == NULL)
			puts("Error opening file!");
		else {
			puts("Writing students to database");
			for (i = 0; i < studentsSize; i++)
				fprintf(file,"%s %d\n", (students+i)->name, (students+i)->data);
			fclose(file);
		}
	}

	puts("\nBye");
	free(students);

	return 0;
}