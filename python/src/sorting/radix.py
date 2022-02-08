def counting_sort(a, k, digito):
    c = [0]*k

    # freq
    for v in l:
        d = v % (pow(10, digito)) / (pow(10, digito - 1))
        c[d-1] += 1

    # cumulativa
    for i in range(1, len(c)):
        c[i] = c[i-1] + c[i]

    b = [0]*len(a)
    for i in range(len(l) -1, -1, -1):
        d = a[i] % (pow(10, digito)) / (pow(10, digito - 1))
        b[c[d - 1] - 1] = a[i]
        c[d - 1] -= 1

    return b

def radix(l, num_dig):
    for i in range(num_dig):
        l = counting_sort(l, 9, i + 1)
    return l
