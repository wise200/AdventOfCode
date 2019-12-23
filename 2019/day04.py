
def password(digits):
    for i in range(len(digits)-1):
        if digits[i] > digits[i+1]:
            return False
    i = 0
    while i < len(digits):
        val = digits[i]
        digits[i] = 1
        while i+1 < len(digits) and digits[i+1] == val:
            digits[i] += 1
            digits.pop(i+1)
        i += 1
    return 2 in digits

count = 0
for x in range(273025,767254):
    digits = [int(d) for d in str(x)]
    if password(digits):
        count += 1

print(count)
