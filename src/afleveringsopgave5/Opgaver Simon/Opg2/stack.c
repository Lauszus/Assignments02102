#include <stdlib.h>
#include <stdio.h>
#include "stack.h"

int pop(stack_t *stack_p){
	int i;
	while(stack_p->array==0){
		stack_p->array++;
	}
	i = *stack_p->array;
	*stack_p->array = 0;
	stack_p->size--;
	return i;
}

void push(stack_t * stack_p, int value){
	int size;
	if(stack_p->capacity >= stack_p->size){
		size = stack_p->size;
		stack_p->capacity = 2*size;
		stack_p->array = (int*)realloc(stack_p->array, stack_p->capacity*sizeof(int));
		stack_p->size ++;
	}
	else{
		stack_p->array[stack_p->size]=value;
		stack_p->size++;
	}
}

int top(stack_t * stack_p){
	while(stack_p->array==0){
		stack_p->array++;
	}
	return *stack_p->array;
}

stack_t *newStack(void){
	stack_t * stack_p = (stack_t*)malloc(sizeof(stack_t));
	stack_p->array = (int*)malloc(sizeof(int));
	stack_p->size = 0;
	stack_p->capacity = 1;
	return stack_p;
}

int empty(stack_t * stack_p){
	int empty;
	if(stack_p->size==0)
		empty= 1;
	else
		empty = 0;
	return empty;
}

