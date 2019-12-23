from itertools import permutations
from queue import Queue

class operation:
    def __init__(self, val):
        self.opcode = val % 100
        val //= 100
        self.immediate = []
        for x in range(3):
            self.immediate.append(val % 10 == 1)
            val //= 10

class Program:
    def __init__(self, program, phase):
        self.program = program.copy()
        self.pos = 0
        self.input = Queue()
        self.input.put(phase)

    def add_input(self, n):
        self.input.put(n)

    def finished(self):
        return self.program[self.pos] == 99

    def __next__(self):
        read = 0

        while not self.finished():
            op = operation(self.program[self.pos])
            
            if op.opcode == 1:
                # addition
                a = self.program[self.pos+1] if op.immediate[0] else self.program[self.program[self.pos+1]]
                b = self.program[self.pos+2] if op.immediate[1] else self.program[self.program[self.pos+2]]
                target = self.pos+3 if op.immediate[2] else self.program[self.pos+3]

                self.program[target] = a+b;
                self.pos+= 4
            elif op.opcode == 2:
                # multiplication
                a = self.program[self.pos+1] if op.immediate[0] else self.program[self.program[self.pos+1]]
                b = self.program[self.pos+2] if op.immediate[1] else self.program[self.program[self.pos+2]]
                target = self.pos+3 if op.immediate[2] else self.program[self.pos+3]

                self.program[target] = a*b;
                self.pos+= 4
            elif op.opcode == 3:
                # input
                target = self.pos+1 if op.immediate[0] else self.program[self.pos+1]
                self.program[target] = self.input.get()
                self.pos += 2
            elif op.opcode == 4:
                # output
                target = self.pos+1 if op.immediate[0] else self.program[self.pos+1]
                self.pos += 2
                return self.program[target]
            elif op.opcode == 5:
                # jump if true
                a = self.program[self.pos+1] if op.immediate[0] else self.program[self.program[self.pos+1]]
                if a == 0:
                    self.pos += 3
                else:
                    self.pos = self.program[self.pos+2] if op.immediate[1] else self.program[self.program[self.pos+2]]
            elif op.opcode == 6:
                # jump if false
                a = self.program[self.pos+1] if op.immediate[0] else self.program[self.program[self.pos+1]]
                if a != 0:
                    self.pos += 3
                else:
                    self.pos = self.program[self.pos+2] if op.immediate[1] else self.program[self.program[self.pos+2]]
            elif op.opcode == 7:
                # less than
                a = self.program[self.pos+1] if op.immediate[0] else self.program[self.program[self.pos+1]]
                b = self.program[self.pos+2] if op.immediate[1] else self.program[self.program[self.pos+2]]
                target = self.pos+3 if op.immediate[2] else self.program[self.pos+3]

                self.program[target] = 1 if a < b else 0
                self.pos+= 4
            elif op.opcode == 8:
                # equals
                a = self.program[self.pos+1] if op.immediate[0] else self.program[self.program[self.pos+1]]
                b = self.program[self.pos+2] if op.immediate[1] else self.program[self.program[self.pos+2]]
                target = self.pos+3 if op.immediate[2] else self.program[self.pos+3]

                self.program[target] = 1 if a == b else 0
                self.pos+= 4
            else:
                # error
                print('Invalid opcode!')
                exit(1)
        raise StopIteration()

def test():
    # day 5 test
    program = [int(n) for n in input().split(',')]
    data = compute(None, 5, program)
    print('ans:', data)

def part_one():
    program = [int(n) for n in input().split(',')]
    phase_base = [0,1,2,3,4]
    maximum = -10**30
    for phases in permutations(phase_base):
        data = 0
        for i in range(5):
            data = compute(data, phases[i], program)
        maximum = max(maximum, data)
    print('ans:', maximum)

def part_two():
    program = [int(n) for n in input().split(',')]
    phase_base = [5,6,7,8,9]
    maximum = -10**30
    for phases in permutations(phase_base):
        amps = [Program(program, phases[x]) for x in range(5)]
        amps[0].add_input(0)
        i = 0
        signal = 0
        while True:
            try:
                output = next(amps[i])
            except StopIteration:
                break
            if i == 4:
                signal = output
                amps[0].add_input(output)
                i = 0
            else:
                i += 1
                amps[i].add_input(output)
        maximum = max(maximum, signal)
    print('answer:', maximum)

        
if __name__ == '__main__':
    part_two()
