def part_one(string):
    min_layer, minimum = 0, string[:150].count('0')
    for x in range(0,len(string),150):
        layer = string[x:x+150]
        count = layer.count('0')
        if count < minimum:
            min_layer, minimum = x, count

    layer = string[min_layer:min_layer+150]
    print('ans:', layer.count('1') * layer.count('2'))

def part_two(string):
    image = [[3 for x in range(25)] for x in range(6)]
    print_image([[string[25*r+c] for c in range(25)] for r in range(6)])
    print('----------')
    for r in range(6):
        for c in range(25):
            pixel = 25 * r + c
            while string[pixel] == '2':
                pixel += 150
            image[r][c] = string[pixel]

    print_image(image)

def print_image(image):
    for r in range(6):
        for c in range(25):
            char = ' ' if image[r][c] != '1' else image[r][c]
            print(char, end='')
        print()

if __name__ == '__main__':
    part_two(input())
