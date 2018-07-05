#!/bin/bash
JAVA_OPTIONS_INITIAL=-Xms1024M
JAVA_OPTIONS_MAX=-Xmx2048M
_JAR_KEYWORDS=target/groovy-1.0-SNAPSHOT.jar
APP_NAME=groovy
#APPLICATION_FILE=/opt/scpip_monitor/application.properties
PID=$(ps aux | grep ${_JAR_KEYWORDS} | grep -v grep | awk '{print $2}' )
#ALARM_CONFIG_FILE=`pwd`/alarmConfig.yaml

function check_if_process_is_running {
if [ "$PID" = "" ]; then
return 1
fi
ps -p $PID | grep "java"
return $?
}


case "$1" in
status)
if check_if_process_is_running
then
echo -e "\033[32m $APP_NAME is running \033[0m"
else
echo -e "\033[32m $APP_NAME not running \033[0m"
fi
;;
stop)
if check_if_process_is_running
then
curl -X POST http://localhost:8484/app/shutdown
else
echo -e "\033[32m $APP_NAME already stoped  \033[0m"
fi
;;
start)
if [ "$PID" != "" ] && check_if_process_is_running
then
echo -e "\033[32m $APP_NAME already running \033[0m"
exit 1
fi
nohup java -jar $JAVA_OPTIONS_INITIAL $JAVA_OPTIONS_MAX $_JAR_KEYWORDS  > /dev/null 2>&1 &
echo -ne "\033[32m Starting \033[0m"
for i in {1..20}; do
echo -ne "\033[32m.\033[0m"
sleep 1
done
if check_if_process_is_running
then
echo -e "\033[32m $APP_NAME fail \033[0m"
else
echo -e "\033[32m $APP_NAME started \033[0m"
fi
;;
restart)
$0 stop
if [ $? = 1 ]
then
exit 1
fi
$0 start
;;
*)
echo "Usage: $0 {start|stop|restart|status}"
exit 1
esac

exit 0
