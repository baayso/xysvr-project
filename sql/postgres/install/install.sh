#!/bin/bash

################################################
# Program:
#     执行install.sql文件。
# History:
#     2015/04/14    ChenFangjie    v1.0
################################################

log()
{
    echo "["$(date '+%Y-%m-%d %H:%M:%S')"] $*"
    echo "["$(date '+%Y-%m-%d %H:%M:%S')"] $*" >> ${CURRENT_DIR}/install.sql.log
}

#main()
CURRENT_DIR=$(cd "$(dirname "$0")"; pwd)

SERVER=$1
PORT=$2
DATABASE=$3
USERNAME=$4

if [ -z ${SERVER} ]; then
    SERVER="localhost"
fi

if [ -z ${PORT} ]; then
    PORT="5432"
fi

if [ -z ${DATABASE} ]; then
    DATABASE="postgres"
fi

if [ -z ${USERNAME} ]; then
    USERNAME="postgres"
fi

log "============================================================================================================="

log "psql -h ${SERVER} -p ${PORT} -d ${DATABASE} -U ${USERNAME} -W -f install.sql"

# chown -R postgres:postgres ${CURRENT_DIR}/*
# su - postgres
psql -h ${SERVER} -p ${PORT} -d ${DATABASE} -U ${USERNAME} -W -f install.sql
