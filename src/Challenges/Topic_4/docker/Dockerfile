FROM mysql:8.0
# define the database name to be created during the container startup
ENV MYSQL_DATABASE docker_academy_db
# copy the sql script with the sentences of create and insert
COPY ./dump.sql /docker-entrypoint-initdb.d/

