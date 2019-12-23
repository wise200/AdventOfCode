
def fuel(n):
    return max(0, n // 3 - 2)

sum = 0
with open('day01.dat', 'r') as file:
    for line in file:
        num = int(line)
        while fuel(num) > 0:
            sum += fuel(num)
            num = fuel(num)

print(sum)
