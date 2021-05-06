#!/bin/bash
ansible-playbook -vv -i  inventories/dev/hosts  $1.yml
