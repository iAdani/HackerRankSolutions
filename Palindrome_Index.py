#!/bin/python3

import math
import os
import random
import re
import sys

#
# Complete the 'palindromeIndex' function below.
#
# The function is expected to return an INTEGER.
# The function accepts STRING s as parameter.
#

def palindromeIndex(s):
    def check_pali(s):
        for i in range(len(s) // 2):
            if s[i] != s[-i - 1]:
                return False
        return True
        
    
    diff = []
    n = len(s)
    for i in range(n // 2):
        if s[i] != s[n - i - 1]:
            diff.extend([i, n - i - 1])
    
    for i in diff:
        if check_pali(s[:i] + s[i + 1:]):
            return i
    return -1
        
if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    q = int(input().strip())

    for q_itr in range(q):
        s = input()

        result = palindromeIndex(s)

        fptr.write(str(result) + '\n')

    fptr.close()
