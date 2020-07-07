#
# Test de técnicas de inlining
# 	Ejemplo obtenido en Wikipedia
#

OBJS=original todoJunto inline_l1 inline_l2

FLAGS=-Wall -O3 

todo: $(OBJS)

.c:
	gcc $(FLAGS) $(OPT) $^ -o $@

todoJunto: todoJunto.c

original: func1.c func2.c main.c
	gcc $(FLAGS) $(OPT) $^ -o $@

inline_l1: inline_level1.c
	gcc $(FLAGS) $(OPT) $^ -o $@

inline_l2: inline_level2.c
	gcc $(FLAGS) $(OPT) $^ -o $@


clean:
		rm -f $(OBJS)

