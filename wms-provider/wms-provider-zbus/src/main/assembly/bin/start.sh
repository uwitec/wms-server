#/usr/bin
ZBUS_HOME=../
JAVA_OPTS="-Dfile.encoding=UTF-8 -server -Xms64m -Xmx1024m -XX:+UseParallelGC"
MAIN_CLASS=com.yorma.wms.zbus.provider.ZbusProvider
if [ -z "$1" ]
  then
    MAIN_OPTS="-conf ../conf/zbus.xml"
else
	MAIN_OPTS="-conf $1"
fi

LIB_OPTS="$ZBUS_HOME/lib/*:$ZBUS_HOME/classes:$ZBUS_HOME/*"
nohup $JAVA_HOME/bin/java $JAVA_OPTS -Dlog4j.configuration="file:$ZBUS_HOME/conf/log4j.properties" -cp $LIB_OPTS $MAIN_CLASS $MAIN_OPTS >/dev/null 2>&1 &