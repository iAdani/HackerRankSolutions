#!/bin/python3

import math
import os
import random
import re
import sys

#
# Complete the 'anagram' function below.
#
# The function is expected to return an INTEGER.
# The function accepts STRING s as parameter.
#

def anagram(s):
    n = len(s)
    if n % 2:
        return -1
    
    s1 = s[:n // 2]
    s2 = s[n // 2:]
    
    s = set(s)
    same = 0
    for letter in s:
        same += min(s1.count(letter), s2.count(letter))
        
    return (n - (2 * same)) // 2
    

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    q = int(input().strip())

    for q_itr in range(q):
        s = input()

        result = anagram(s)

        fptr.write(str(result) + '\n')

    fptr.close()
