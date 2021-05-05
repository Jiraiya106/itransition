#!/bin/bash
if read -t 5 -p "Enter string: " string
then
echo "$string"
else
echo "Sorry, too slow! "
fi
