#!/bin/sh
#$1: unique command name, $2: parameter
if [ $1 = 'top' ]; then
  `top -b -n 1 -o +RES`
fi