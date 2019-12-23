verts = ['U','D']
hors = ['L','R']

class Wire:
    def __init__(self):
        self.current = (0,0)
        self.points = {self.current: 0}

    def move(self, string):
        direction = string[0]
        dist = int(string[1:])
        
        dy = 1 if direction in verts else 0
        dx = 1 if direction in hors else 0

        if direction == 'U' or direction == 'L':
            dy = -dy
            dx = -dx

        for x in range(1,dist+1):
            length = self.points[self.current] + 1
            self.current = (self.current[0] + dx, self.current[1] + dy)
            self.points[self.current] = length

    def intersect(self, other):
        points = set(self.points.keys()).intersection(set(other.points.keys()))
        n = 10**10
        for p in points:
            dist = self.points[p] + other.points[p]
            if dist < n and dist != 0:
                n = dist
        return n

a = Wire()
b = Wire()

with open('day03.dat', 'r') as file:
    for string in file.readline().split(','):
        #print(string)
        a.move(string)
    for string in file.readline().split(','):
        #print(string)
        b.move(string)

    print(a.intersect(b))

