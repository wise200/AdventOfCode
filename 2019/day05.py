
def parse(num):
    op = nums % 100
    num /= 100
    modes = []
    for x in range(3):
        modes.append(num % 10)
        num /= 10
    return op, modes

string = ''

with open('day05.dat', 'r') as file:
    string = file.readline()

ops = [int(s) for s in string.split(',')]

#print(ops)

i = 0
while ops[i] != 99:
    op, modes = parse(ops[i])
    if op == 1:
        ops[ops[i+3]] = ops[ops[i+1]] + ops[ops[i+2]]
        i += 4
    elif op == 2:
        ops[ops[i+3]] = ops[ops[i+1]] * ops[ops[i+2]]
        i += 4
    elif op == 3:
        //lol
    else:
        print('Illegal operation at', i)
        exit(1)
    #print(ops)

#print('n:', len(ops))
#print('i:', i)
if (ops[0] == 19690720):
    print(a, ', ', b)
#print(ops)
