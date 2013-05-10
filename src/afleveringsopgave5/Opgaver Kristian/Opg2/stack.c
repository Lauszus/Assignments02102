#include <stdlib.h>
#include <stdio.h>
#include "stack.h"

int pop(my_stack * stack_p) {
	if (stack_p->size > 0) { // Check if there is actually something in the array
		stack_p->size--;
		return stack_p->array[stack_p->size];
	}
	return -1;
}
void push(my_stack * stack_p, int value) {
	if (stack_p->size >= stack_p->capacity) { // It's already full
		stack_p->capacity = stack_p->capacity*2;
		stack_p->array = (int*)realloc(stack_p->array, stack_p->capacity*sizeof(int));
	}	
	stack_p->array[stack_p->size] = value;
	stack_p->size++;
}
int top(my_stack * stack_p) {
	if (stack_p->size > 0) // Check if there is actually something in the array
		return stack_p->array[stack_p->size-1];
	return -1;
}
my_stack * newStack(void) {
	my_stack * stack_p = (my_stack*)malloc(sizeof(my_stack));

	stack_p->capacity = 1;
	stack_p->array = (int*)malloc(sizeof(int));
	stack_p->size = 0;

	return stack_p;
}
int empty(my_stack * stack_p) {
	if (stack_p->size > 0)
		return 0;
	return 1;
}