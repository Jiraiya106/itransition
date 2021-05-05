#!/bin/bash

read string 
if [ ${#string} == 1 ]
then
	 case "$string" in
	   [[:blank:]]) echo "BLANK" ;;
	   [[:digit:]]) echo "DIGIT" ;;
	   [[:upper:]]) echo "UPPER" ;;
	   [[:lower:]]) echo "LOWER" ;;
	   [[:print:]]) echo "PRINT" ;;
	   [[:punct:]]) echo "PUNCT" ;;
	   [[:space:]]) echo "SPACE" ;;
	   *) echo "$1 is not an option" ;;
	   esac
else echo "Many or few characters"
fi
