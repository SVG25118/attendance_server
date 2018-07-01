@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  Attendance_Server startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and ATTENDANCE_SERVER_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\Attendance_Server.jar;%APP_HOME%\lib\tomee-embedded-7.0.4.jar;%APP_HOME%\lib\tomee-webservices-7.0.4.jar;%APP_HOME%\lib\tomee-jaxrs-7.0.4.jar;%APP_HOME%\lib\tomee-catalina-7.0.4.jar;%APP_HOME%\lib\tomee-util-7.0.4.jar;%APP_HOME%\lib\openejb-cxf-rs-7.0.4.jar;%APP_HOME%\lib\openejb-rest-7.0.4.jar;%APP_HOME%\lib\openejb-cxf-7.0.4.jar;%APP_HOME%\lib\openejb-webservices-7.0.4.jar;%APP_HOME%\lib\openejb-cxf-transport-7.0.4.jar;%APP_HOME%\lib\openejb-http-7.0.4.jar;%APP_HOME%\lib\openejb-ejbd-7.0.4.jar;%APP_HOME%\lib\openejb-server-7.0.4.jar;%APP_HOME%\lib\tomee-myfaces-7.0.4.jar;%APP_HOME%\lib\tomee-loader-7.0.4.jar;%APP_HOME%\lib\tomee-common-7.0.4.jar;%APP_HOME%\lib\tomee-juli-7.0.4.jar;%APP_HOME%\lib\openejb-core-7.0.4.jar;%APP_HOME%\lib\openejb-api-7.0.4.jar;%APP_HOME%\lib\openejb-client-7.0.4.jar;%APP_HOME%\lib\javaee-api-7.0-1-tomcat.jar;%APP_HOME%\lib\javaee-api-7.0-1.jar;%APP_HOME%\lib\tomcat-websocket-8.5.20.jar;%APP_HOME%\lib\tomcat-websocket-api-8.5.20.jar;%APP_HOME%\lib\openwebbeans-jsf-1.7.4.jar;%APP_HOME%\lib\myfaces-impl-2.2.12.jar;%APP_HOME%\lib\myfaces-api-2.2.12.jar;%APP_HOME%\lib\commons-digester-1.8.jar;%APP_HOME%\lib\commons-beanutils-1.9.2.jar;%APP_HOME%\lib\taglibs-standard-jstlel-1.2.5.jar;%APP_HOME%\lib\xalan-2.7.2.jar;%APP_HOME%\lib\tomcat-jdbc-8.5.20.jar;%APP_HOME%\lib\tomcat-dbcp-8.5.20.jar;%APP_HOME%\lib\tomcat-catalina-ha-8.5.20.jar;%APP_HOME%\lib\tomcat-catalina-8.5.20.jar;%APP_HOME%\lib\tomcat-coyote-8.5.20.jar;%APP_HOME%\lib\tomcat-jasper-8.5.20.jar;%APP_HOME%\lib\tomcat-jasper-el-8.5.20.jar;%APP_HOME%\lib\ecj-4.4.jar;%APP_HOME%\lib\cxf-rt-ws-security-3.1.13.jar;%APP_HOME%\lib\cxf-rt-security-saml-3.1.13.jar;%APP_HOME%\lib\wss4j-ws-security-dom-2.1.11.jar;%APP_HOME%\lib\wss4j-ws-security-policy-stax-2.1.11.jar;%APP_HOME%\lib\wss4j-ws-security-stax-2.1.11.jar;%APP_HOME%\lib\wss4j-ws-security-common-2.1.11.jar;%APP_HOME%\lib\opensaml-xacml-saml-impl-3.1.1.jar;%APP_HOME%\lib\opensaml-saml-impl-3.1.1.jar;%APP_HOME%\lib\velocity-1.7.jar;%APP_HOME%\lib\tomee-jdbc-7.0.4.jar;%APP_HOME%\lib\openwebbeans-web-1.7.4.jar;%APP_HOME%\lib\openwebbeans-ee-1.7.4.jar;%APP_HOME%\lib\openwebbeans-ee-common-1.7.4.jar;%APP_HOME%\lib\openwebbeans-ejb-1.7.4.jar;%APP_HOME%\lib\openwebbeans-impl-1.7.4.jar;%APP_HOME%\lib\mbean-annotation-api-7.0.4.jar;%APP_HOME%\lib\openejb-jpa-integration-7.0.4.jar;%APP_HOME%\lib\bval-jsr-1.1.2.jar;%APP_HOME%\lib\commons-lang3-3.5.jar;%APP_HOME%\lib\openejb-jee-accessors-7.0.4.jar;%APP_HOME%\lib\openejb-loader-7.0.4.jar;%APP_HOME%\lib\openejb-javaagent-7.0.4.jar;%APP_HOME%\lib\openejb-jee-7.0.4.jar;%APP_HOME%\lib\commons-cli-1.2.jar;%APP_HOME%\lib\commons-collections-3.2.2.jar;%APP_HOME%\lib\activemq-ra-5.14.5.jar;%APP_HOME%\lib\activemq-kahadb-store-5.14.5.jar;%APP_HOME%\lib\activemq-broker-5.14.5.jar;%APP_HOME%\lib\activemq-jdbc-store-5.14.5.jar;%APP_HOME%\lib\geronimo-connector-3.1.4.jar;%APP_HOME%\lib\geronimo-transaction-3.1.4.jar;%APP_HOME%\lib\howl-1.0.1-1.jar;%APP_HOME%\lib\geronimo-javamail_1.4_mail-1.9.0-alpha-2.jar;%APP_HOME%\lib\xbean-finder-shaded-4.5.jar;%APP_HOME%\lib\openjpa-2.4.2.jar;%APP_HOME%\lib\xbean-asm5-shaded-4.5.jar;%APP_HOME%\lib\xbean-reflect-4.5.jar;%APP_HOME%\lib\xbean-naming-4.5.jar;%APP_HOME%\lib\xbean-bundleutils-4.5.jar;%APP_HOME%\lib\hsqldb-2.3.2.jar;%APP_HOME%\lib\commons-dbcp2-2.1.jar;%APP_HOME%\lib\commons-pool2-2.3.jar;%APP_HOME%\lib\swizzle-stream-1.6.2.jar;%APP_HOME%\lib\commons-logging-1.2.jar;%APP_HOME%\lib\quartz-openejb-shade-2.2.1.jar;%APP_HOME%\lib\slf4j-jdk14-1.7.21.jar;%APP_HOME%\lib\activemq-openwire-legacy-5.14.5.jar;%APP_HOME%\lib\activemq-client-5.14.5.jar;%APP_HOME%\lib\ehcache-2.10.4.jar;%APP_HOME%\lib\opensaml-xacml-impl-3.1.1.jar;%APP_HOME%\lib\opensaml-xacml-saml-api-3.1.1.jar;%APP_HOME%\lib\opensaml-xacml-api-3.1.1.jar;%APP_HOME%\lib\opensaml-xmlsec-impl-3.1.1.jar;%APP_HOME%\lib\opensaml-saml-api-3.1.1.jar;%APP_HOME%\lib\opensaml-soap-impl-3.1.1.jar;%APP_HOME%\lib\opensaml-profile-api-3.1.1.jar;%APP_HOME%\lib\opensaml-soap-api-3.1.1.jar;%APP_HOME%\lib\opensaml-xmlsec-api-3.1.1.jar;%APP_HOME%\lib\opensaml-security-impl-3.1.1.jar;%APP_HOME%\lib\opensaml-security-api-3.1.1.jar;%APP_HOME%\lib\opensaml-messaging-api-3.1.1.jar;%APP_HOME%\lib\opensaml-core-3.1.1.jar;%APP_HOME%\lib\opensaml-storage-api-3.1.1.jar;%APP_HOME%\lib\java-support-7.1.1.jar;%APP_HOME%\lib\wss4j-bindings-2.1.11.jar;%APP_HOME%\lib\xmlsec-2.0.9.jar;%APP_HOME%\lib\slf4j-api-1.7.25.jar;%APP_HOME%\lib\openwebbeans-spi-1.7.4.jar;%APP_HOME%\lib\bval-core-1.1.2.jar;%APP_HOME%\lib\taglibs-standard-spec-1.2.5.jar;%APP_HOME%\lib\taglibs-standard-impl-1.2.5.jar;%APP_HOME%\lib\serializer-2.7.2.jar;%APP_HOME%\lib\tomcat-jsp-api-8.5.20.jar;%APP_HOME%\lib\tomcat-util-scan-8.5.20.jar;%APP_HOME%\lib\tomcat-api-8.5.20.jar;%APP_HOME%\lib\tomcat-servlet-api-8.5.20.jar;%APP_HOME%\lib\tomcat-jni-8.5.20.jar;%APP_HOME%\lib\tomcat-util-8.5.20.jar;%APP_HOME%\lib\tomcat-jaspic-api-8.5.20.jar;%APP_HOME%\lib\tomcat-tribes-8.5.20.jar;%APP_HOME%\lib\tomcat-el-api-8.5.20.jar;%APP_HOME%\lib\ecj-3.12.3.jar;%APP_HOME%\lib\commons-lang-2.6.jar;%APP_HOME%\lib\jaxb-api-2.3.0.jar;%APP_HOME%\lib\cxf-rt-frontend-jaxws-3.1.13.jar;%APP_HOME%\lib\cxf-rt-frontend-simple-3.1.13.jar;%APP_HOME%\lib\cxf-rt-ws-addr-3.1.13.jar;%APP_HOME%\lib\cxf-rt-bindings-soap-3.1.13.jar;%APP_HOME%\lib\cxf-rt-databinding-jaxb-3.1.13.jar;%APP_HOME%\lib\jaxb-impl-2.3.0.jar;%APP_HOME%\lib\jaxb-core-2.3.0.jar;%APP_HOME%\lib\tomcat-juli-8.5.20.jar;%APP_HOME%\lib\openwebbeans-el22-1.7.4.jar;%APP_HOME%\lib\sxc-jaxb-core-0.8.jar;%APP_HOME%\lib\serp-1.15.1.jar;%APP_HOME%\lib\geronimo-atinject_1.0_spec-1.0.jar;%APP_HOME%\lib\xml-apis-1.3.04.jar;%APP_HOME%\lib\tomcat-annotations-api-8.5.20.jar;%APP_HOME%\lib\cxf-rt-rs-service-description-3.1.13.jar;%APP_HOME%\lib\cxf-rt-rs-security-oauth2-3.1.13.jar;%APP_HOME%\lib\cxf-rt-rs-client-3.1.13.jar;%APP_HOME%\lib\cxf-rt-rs-extension-search-3.1.13.jar;%APP_HOME%\lib\cxf-rt-rs-security-cors-3.1.13.jar;%APP_HOME%\lib\cxf-rt-rs-extension-providers-3.1.13.jar;%APP_HOME%\lib\cxf-rt-rs-security-jose-jaxrs-3.1.13.jar;%APP_HOME%\lib\cxf-rt-frontend-jaxrs-3.1.13.jar;%APP_HOME%\lib\johnzon-jaxrs-1.0.0.jar;%APP_HOME%\lib\geronimo-j2ee-connector_1.6_spec-1.0.jar;%APP_HOME%\lib\cxf-rt-wsdl-3.1.13.jar;%APP_HOME%\lib\cxf-rt-ws-policy-3.1.13.jar;%APP_HOME%\lib\wsdl4j-1.6.3.jar;%APP_HOME%\lib\cryptacular-1.0.jar;%APP_HOME%\lib\bcprov-jdk15on-1.57.jar;%APP_HOME%\lib\wss4j-policy-2.1.11.jar;%APP_HOME%\lib\sxc-runtime-0.8.jar;%APP_HOME%\lib\activemq-protobuf-1.1.jar;%APP_HOME%\lib\hawtbuf-1.11.jar;%APP_HOME%\lib\junit-3.8.1.jar;%APP_HOME%\lib\cxf-rt-transports-http-3.1.13.jar;%APP_HOME%\lib\cxf-rt-management-3.1.13.jar;%APP_HOME%\lib\cxf-rt-bindings-xml-3.1.13.jar;%APP_HOME%\lib\cxf-rt-rs-security-jose-3.1.13.jar;%APP_HOME%\lib\cxf-rt-security-3.1.13.jar;%APP_HOME%\lib\cxf-rt-rs-json-basic-3.1.13.jar;%APP_HOME%\lib\cxf-core-3.1.13.jar;%APP_HOME%\lib\javax.ws.rs-api-2.0.1.jar;%APP_HOME%\lib\javax.annotation-api-1.2.jar;%APP_HOME%\lib\johnzon-mapper-1.0.0.jar;%APP_HOME%\lib\neethi-3.1.0.jar;%APP_HOME%\lib\xml-resolver-1.2.jar;%APP_HOME%\lib\woodstox-core-asl-4.4.1.jar;%APP_HOME%\lib\xmlschema-core-2.2.2.jar;%APP_HOME%\lib\johnzon-core-1.0.0.jar;%APP_HOME%\lib\stax2-api-3.1.4.jar;%APP_HOME%\lib\asm-5.0.4.jar;%APP_HOME%\lib\jasypt-1.9.2.jar;%APP_HOME%\lib\geronimo-javamail_1.4_spec-1.7.1.jar;%APP_HOME%\lib\httpclient-4.3.6.jar;%APP_HOME%\lib\commons-codec-1.10.jar;%APP_HOME%\lib\jsr305-3.0.0.jar;%APP_HOME%\lib\guava-18.0.jar;%APP_HOME%\lib\joda-time-2.7.jar;%APP_HOME%\lib\httpcore-4.3.3.jar

@rem Execute Attendance_Server
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %ATTENDANCE_SERVER_OPTS%  -classpath "%CLASSPATH%" server.Main %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable ATTENDANCE_SERVER_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%ATTENDANCE_SERVER_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
