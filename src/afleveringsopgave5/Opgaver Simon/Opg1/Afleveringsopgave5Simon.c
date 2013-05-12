// exam.c

#include <stdio.h>

typedef struct {
  int one;
  int two;
} combo_t;

void helper(int one, int two, int sum) {
  printf("%d+%d=%d\n", one, two, sum);
}

void calculate(combo_t combo){
	int sum = combo.one + combo.two;
	helper(combo.one, combo.two, sum);
}

int main() {
  combo_t combo;
  combo.one = 2;
  combo.two = 2;
  calculate(combo);
  combo.one = 4;
  calculate(combo);
  return 0;
}
