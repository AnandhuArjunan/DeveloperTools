
set "JAVA_OPTS=-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8089"

java -Dlog4j.configuration=file:conf/log4j.properties -javaagent:lib/jamm-0.3.3.jar -Dderby.system.home=conf %JAVA_OPTS% -jar DeveloperTools-0.0.1-SNAPSHOT.jar