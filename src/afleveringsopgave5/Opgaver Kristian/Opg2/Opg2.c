#include <stdio.h>
#include <stdlib.h>
#include "stack.h"
/*
Udskrift:
Stack is empty
popped: -1
The numbers 123 and 99 have been pushed
top: 99

Three values have been pushed
popped: 4444
popped: 99
popped: 123
top: -1
*/
int main() {
	my_stack * myStack = newStack();
	if (empty(myStack))
		printf("Stack is empty\n");
	else
		printf("Stack is not empty\n");

	printf("popped: %d\n", pop(myStack));
	push(myStack, 123);
	push(myStack, 99);
	printf("The numbers 123 and 99 have been pushed\ntop: %d\n\n", top(myStack));
	push(myStack, 4444);
	printf("Three values have been pushed\n");
	while (!empty(myStack)) {
		int value;
		value = pop(myStack);
		printf("popped: %d\n", value);
	}
	printf("top: %d", top(myStack));
	free(myStack);
	return 0;
}
