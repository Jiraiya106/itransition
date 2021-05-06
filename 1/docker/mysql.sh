docker run --rm  -h r-mysql --name=r-mysql \
--mount type=volume,source=mysql,destination=/var/lib/mysql -it mysql bash
