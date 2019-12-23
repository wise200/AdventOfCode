
string = ''

with open('day02.dat', 'r') as file:
    string = file.readline()

orig = [int(s) for s in string.split(',')]

#print(ops)

for a in range(100):
    for b in range(100):
        ops = [x for x in orig]
        ops[1] = a
        ops[2] = b
        i = 0
        while ops[i] != 99:
            if ops[i] == 1:
                ops[ops[i+3]] = ops[ops[i+1]] + ops[ops[i+2]]
            elif ops[i] == 2:
                ops[ops[i+3]] = ops[ops[i+1]] * ops[ops[i+2]]
            else:
                i = -4
                ops[0] = 99
            i += 4
            #print(ops)

        #print('n:', len(ops))
        #print('i:', i)
        if (ops[0] == 19690720):
            print(a, ', ', b)
        #print(ops)
