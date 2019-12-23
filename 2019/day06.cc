#include <iostream>
#include <string>
#include <vector>

std::unordered_map<std::string,node*> nodes;

struct node {
	std::string name;
	std::vector<node> children;

	node(std::string n) : name(n) {
		nodes.insert({{n,this}});
	}

	void add(node child) {
		children.push_back(child);
	}
};

//namespace std {
	//template<> struct hash<Student> {
		//size_t operator()(const node& obj) const noexcept {
			//return hash<std::string>()(obj.name);
		//}
	//};
//}

int main() {
	std::string line;
	while (std::cin >> line) {
		int i = line.find(')');
		assert(i >= 0);
		
		node parent = node(line.substr(0,i));
		if (parent.
		node child = node(line.substr(i+1));
		parent.add(child);

		

	}

	node bound = node('magic');
	std::queue<node> q;
	q.push_back(
}
