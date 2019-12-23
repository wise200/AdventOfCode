from itertools import permutations
from queue import Queue

class Operation:
    def __init__(self, val):
        self.opcode = val % 100
        val //= 100
        self.immediate = []
        for x in range(3):
            self.immediate.append(val % 10 == 1)
            val //= 10

class Program:
    def __init__(self, program, phase):
        self.program = {i:program[i] for i in range(len(program))}
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
            op = Operation(self.program[self.pos])
            
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
    pass

def part_two():
    pass
        
if __name__ == '__main__':
    part_two()
