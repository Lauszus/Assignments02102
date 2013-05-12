// stack.c

#include <stdlib.h>
#include <stdio.h>
#include "stack.h"

/*

Tilføj funktionerne newStack, pop, push, top og empty.

*/

stack_t * newStack(void){
	stack_t* new = malloc(sizeof(stack_t));
	new->size = 0;
	new->capacity=1;
	new->array = malloc(sizeof(int));
	return new;
}

int pop(stack_t * stack_p){
	stack_p->size--;
	return stack_p->array[stack_p->size];
}

void push(stack_t * stack_p, int value){
	stack_p->size++;
	if(stack_p->size > stack_p->capacity){
		stack_p->capacity *=2;
		stack_p->array = realloc(stack_p->array, sizeof(int)*stack_p->capacity);
	}
	stack_p->array[stack_p->size-1] = value;
}

int top(stack_t * stack_p){
	return stack_p->array[stack_p->size-1];
}


int empty(stack_t * stack_p){
	return (stack_p->size == 0);
}

