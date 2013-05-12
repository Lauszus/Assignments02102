typedef struct {
	int capacity;
	int * array;
	int size;
} stack_t2;

int pop(stack_t2 * stack_p);
void push(stack_t2 * stack_p, int value);
int top(stack_t2 * stack_p);
stack_t2 * newStack(void);
int empty(stack_t2 * stack_p);