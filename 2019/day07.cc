#include <iostream>
#include <vector>
#include <string>
#include <cassert>


struct operation {
	int opcode;
	bool immediate[3];

	operation(int val) {
		opcode = val % 100;
		val /= 100;
		for (int i = 0; i != 3; i++) {
			immediate[i] = val % 10 == 1;
			val /= 10;
		}
	}
};

void compute(int input, int phase) {
	size_t sz = 0;
	int program[700];

	for (int i; std::cin >> i; ) {
		program[sz] = i;
		sz++;
		if (std::cin.peek() == ',') {
			std::cin.ignore();
		}
	}

	//for (int i = 0; i != sz; ++i) {
		//std::cout << program[i] << ",";
	//}
	
	int input;
	std::cin >> input;
	std::cout << "Input: " << input << "\n";
	program[sz] = 0;
	bool read = false;
	int n = 0;

	int pos = 0;
	while(program[pos] != 99) {
		operation op = *(new operation(program[pos]));
		
		if (op.opcode == 1) {
			int a = op.immediate[0] ? program[pos+1] : program[program[pos+1]];
			int b = op.immediate[1] ? program[pos+2] : program[program[pos+2]];
			int* target = op.immediate[2] ? &program[pos+3] : &program[program[pos+3]];

			target[0] = a + b;
			pos += 4;
		} else if (op.opcode == 2) {
			 //multiplication
			int a = op.immediate[0] ? program[pos+1] : program[program[pos+1]];
			int b = op.immediate[1] ? program[pos+2] : program[program[pos+2]];
			int* target = op.immediate[2] ? &program[pos+3] : &program[program[pos+3]];

			target[0] = a * b;
			pos += 4;
		} else if (op.opcode == 3) {
			 //input
			 assert(!read);
			 int* target = op.immediate[0] ? &program[pos+1] : &program[program[pos+1]];
			 target[0] = input;
			 read = true;
			 pos += 2;
		} else if (op.opcode == 4) {
			 //output
			int* target = op.immediate[0] ? &program[pos+1] : &program[program[pos+1]];
			std::cout << target[0] << '\n';
			pos += 2;
		} else if (op.opcode == 5) {
			// jump if true
			int a = op.immediate[0] ? program[pos+1] : program[program[pos+1]];
			if (a != 0) {
				pos = op.immediate[1] ? program[pos+2] : program[program[pos+2]];
			} else {
				pos += 3;
			}
		} else if (op.opcode == 6) {
			// jump if false
			int a = op.immediate[0] ? program[pos+1] : program[program[pos+1]];
			if (a == 0) {
				pos = op.immediate[1] ? program[pos+2] : program[program[pos+2]];
			} else {
				pos += 3;
			}
		} else if (op.opcode == 7) {
			// less than
			int a = op.immediate[0] ? program[pos+1] : program[program[pos+1]];
			int b = op.immediate[1] ? program[pos+2] : program[program[pos+2]];
			int* target = op.immediate[2] ? &program[pos+3] : &program[program[pos+3]];

			target[0] = a < b ? 1 : 0;
			pos += 4;
		} else if (op.opcode == 8) {
			// equals
			int a = op.immediate[0] ? program[pos+1] : program[program[pos+1]];
			int b = op.immediate[1] ? program[pos+2] : program[program[pos+2]];
			int* target = op.immediate[2] ? &program[pos+3] : &program[program[pos+3]];

			target[0] = a == b ? 1 : 0;
			pos += 4;
		} else {
			std::cout << "invalid opcode at pos " << pos << '\n';
			std::cout << "opcode: " << op.opcode << " Immediates: " << op.immediate[0] << ", " << op.immediate[1] << ", " << op.immediate[2] << '\n';
			exit(1);
		}
		++n;
		//std::cout << pos << '\n';
	}
}

int main() {
	part2();
}
