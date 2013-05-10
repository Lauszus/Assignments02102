typedef struct {
	int capacity;
	int * array;
	int size;
} my_stack;

int pop(my_stack * stack_p);
void push(my_stack * stack_p, int value);
int top(my_stack * stack_p);
my_stack * newStack(void);
int empty(my_stack * stack_p);