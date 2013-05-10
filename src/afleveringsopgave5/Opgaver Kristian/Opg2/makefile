TARGET = Opg2
CC = gcc
CFLAGS = -O -Wall

OBJS = stack.o $(TARGET).o

$(TARGET): ${OBJS}
	${CC} -o $(TARGET) ${CFLAGS} ${OBJS}

stack.o: stack.c stack.h
	${CC} ${CFLAGS} -c stack.c

$(TARGET).o: $(TARGET).c
	${CC} ${CFLAGS} -c $(TARGET).c

clean:
	rm -f $(TARGET) ${OBJS}

run: $(TARGET)
	./$(TARGET)