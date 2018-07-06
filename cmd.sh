#!/bin/bash
#建议使用 . xx.sh 命令执行脚本。如果使用sh xx.sh执行，注意is_exist方法里的注释
#获取脚本名称
SCRIPT=$0
#获取进程名称，必须为完整程序名，否则可能会误操作其他进程
APP_NAME=target/groovy-1.0-SNAPSHOT.jar
STOP_URL=http://localhost:8484/app/shutdown
#获取操作符
OPERATOR=$1

usage() {
    echo "Usage: sh $SCRIPT [start|stop|restart|status|shutdown]"
    exit 1
}

#判断是否输入了1个参数
#注意①
if [ $# != 1 ]; then
    usage
fi

is_exist(){
  #过滤grep命令本身
  #注意②
  #ps aux | grep ${_JAR_KEYWORDS} | grep -v grep | awk '{print $2}'
  pid=`ps -ef|grep $APP_NAME|grep -v grep|awk '{print $2}' `
  #使用sh xx.sh命令执行的话，启用下面代码
  #pid=`ps -ef|grep $APP_NAME|grep -v grep|grep -v $SCRIPT|awk '{print $2}' `
  if [ -z "${pid}" ]; then
    return 1
  else
    ps -p ${pid} | grep "java"
    return 0
  fi
}

start(){
  is_exist
  if [ $? -eq "0" ]; then
    echo "${APP_NAME} is already running. pid=${pid} ."
  else
    nohup java -jar $APP_NAME > /dev/null 2>&1 &
    #nohup java -jar $APP_NAME > /tmp/a.txt &

    echo -ne "\033[32m Starting \033[0m"
    for i in {1..20}; do
       echo -ne "\033[32m.\033[0m"
       sleep 1
    done
    echo ""
    status
  fi
}

stop(){
  is_exist
  if [ $? -eq "0" ]; then
    curl -X POST ${STOP_URL}
  else
    echo "${APP_NAME} is not running"
  fi
}


shutdown(){
  is_exist
  if [ $? -eq "0" ]; then
    #kill -9 $pid
    kill $pid
  else
    echo "${APP_NAME} is not running"
  fi
}

status(){
  is_exist
  if [ $? -eq "0" ]; then
    echo -e "${APP_NAME} is running. Pid is \033[32m ${pid} \033[0m"
  else
    echo "${APP_NAME} is NOT running."
  fi
}

restart(){
  stop
  start
}

case "$OPERATOR" in
  "start")
    start ;;
  "stop")
    stop ;;
  "status")
    status ;;
  "restart")
    restart ;;
  "shutdown")
    shutdown ;;
  *)
    usage ;;
esac
