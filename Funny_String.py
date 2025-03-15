#!/bin/python3

import math
import os
import random
import re
import sys
import requests

#
# Complete the 'funnyString' function below.
#
# The function is expected to return a STRING.
# The function accepts STRING s as parameter.
#

def funnyString(s):
    n = len(s)
    pos = 1
    while pos < n:
        print(pos, s[pos], pos - 1, s[pos - 1])
        print(-pos, s[-pos], -pos - 1, s[-pos - 1])
        if abs(ord(s[pos]) - ord(s[pos - 1])) != abs(ord(s[-pos]) - ord(s[-pos - 1])):
            return "Not Funny"
        pos += 1
    return "Funny"

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    q = int(input().strip())

    for q_itr in range(q):
        s = input()

        result = funnyString(s)

        fptr.write(result + '\n')

    fptr.close()
