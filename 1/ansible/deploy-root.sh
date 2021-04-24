#!/bin/bash
ansible-playbook -v  -u root -i  inventories/dev/hosts  $1.yml
