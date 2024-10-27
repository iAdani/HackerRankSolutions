#!/bin/python3

import math
import os
import random
import re
import sys
import itertools

#
# Complete the 'alternate' function below.
#
# The function is expected to return an INTEGER.
# The function accepts STRING s as parameter.
#
def alternate(s):
    char_set = set(s)
    perms = itertools.permutations(char_set, 2)
    max = 0
    
    for tupl in perms:
        lst = list()
        flag = True
        for c in s:
            if c in tupl:
                if len(lst) == 0 or lst[-1] != c:
                    lst += c
                else:
                    flag = False
        if flag and len(lst) > max:
            max = len(lst)
    return max
                    
    

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    l = int(input().strip())

    s = input()

    result = alternate(s)

    fptr.write(str(result) + '\n')

    fptr.close()
