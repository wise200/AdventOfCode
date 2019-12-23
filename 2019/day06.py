from queue import Queue


def part_one():
    children = {}

    line = input()
    while line:
        i = line.find(')')
        parent = line[:i]
        child = line[i+1:]
        if parent in children.keys():
            children[parent].append(child)
        else:
            children[parent] = [child]
        try:
            line = input()
        except EOFError as e:
            line = ''

    #print('children:',children)

    q = Queue()
    q.put('COM')
    q.put('')
    depth = 0
    count = 0

    while not q.empty():
        current = q.get()
        if not current:
            if q.empty():
                break
            depth += 1
            q.put('')
        else:
            count += depth
            if current in children.keys():
                for child in children[current]:
                    q.put(child)

    print('count:', count)

def part_two():
    
    parents = {}
    line = input()
    while line:
        i = line.find(')')
        parent = line[:i]
        child = line[i+1:]
        parents[child] = parent
        try:
            line = input()
        except EOFError as e:
            line = ''

    you_path = []
    san_path = []
    you_pos = 'YOU'
    san_pos = 'SAN'

    while you_pos != 'COM':
        you_pos = parents[you_pos]
        you_path.append(you_pos)
    while san_pos != 'COM':
        san_pos = parents[san_pos]
        san_path.append(san_pos)

    while you_path[-1] == san_path[-1]:
        you_path.pop()
        san_path.pop()

    print('ans:', len(you_path) + len(san_path))


part_two()
