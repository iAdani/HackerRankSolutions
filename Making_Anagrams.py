#!/bin/python3

import math
import os
import random
import re
import sys

#
# Complete the 'makingAnagrams' function below.
#
# The function is expected to return an INTEGER.
# The function accepts following parameters:
#  1. STRING s1
#  2. STRING s2
#

def makingAnagrams(s1, s2):
    d1, d2 = {}, {}
    for letter in s1:
        count = d1.get(letter, 0)
        d1[letter] = count + 1
        
    for letter in s2:
        count = d2.get(letter, 0)
        d2[letter] = count + 1
     
    same = 0   
    for key in d1:
        if key in d2:
            same += min(d1[key], d2[key])
            
    return len(s1) + len(s2) - (2 * same)
    
    
if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    s1 = input()

    s2 = input()

    result = makingAnagrams(s1, s2)

    fptr.write(str(result) + '\n')

    fptr.close()
