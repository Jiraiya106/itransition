#!/bin/bash

wget -q -O - "https://stooq.pl/q/?s=usdpln&c=3d&t=l&a=lg&b=0" | 
awk -F'[><]' '{
    for (i = 1; i <= NF; i++)
        if ($i == "span id=aq_usdpln_c5")
            printf "USD: %.2f\n", $(i+1)
}'