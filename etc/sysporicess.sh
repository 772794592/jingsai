#!/bin/bash
if [ $1 = 'get_process' ]; then
  top -p $2 -b -n 1 | awk '{if(NR>7) print $1,$2,$3,$4,$5,$6,$7,$8,$9,$10,$11,$12}'

elif [ $1 = 'get_service_name' ]; then
  service $2 status  |grep -w 'active' |awk '{print $2}'
fi

