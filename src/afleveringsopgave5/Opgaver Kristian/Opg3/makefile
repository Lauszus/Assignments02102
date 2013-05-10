TARGET = cudb
CC = gcc
CFLAGS = -O -Wall

OBJS = $(TARGET).o

$(TARGET): ${OBJS}
	${CC} -o $(TARGET) ${CFLAGS} ${OBJS}

$(TARGET).o: $(TARGET).c
	${CC} ${CFLAGS} -c $(TARGET).c

clean:
	rm -f $(TARGET) ${OBJS}

run: $(TARGET)
	./$(TARGET)