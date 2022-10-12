#!/bin/bash
top -p $1 -b -n 1 | awk '{if(NR>7) print}' > /root/rop.txt