
set "JAVA_OPTS=-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8089"

java  -Dlog4j.configuration=file:conf/log4j.properties -Dderby.system.home=conf %JAVA_OPTS% -jar developer-tools-1.0-SNAPSHOT.jar

pause