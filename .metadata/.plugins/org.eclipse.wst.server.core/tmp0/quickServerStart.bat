@REM C:\Users\Wei Cong\IBM\rationalsdp\integrate\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\quickServerStart.bat
@REM Generated: Thu Jan 29 22:45:03 GMT-12:00 2015

@setlocal
@echo off

@REM Bootstrap values ...
cd C:\Program Files\IBM\WebSphere\AppServer\profiles\AppSrv1\bin
call "C:\Program Files\IBM\WebSphere\AppServer\profiles\AppSrv1\bin\setupCmdLine.bat"
@REM For debugging the server process:
@REM set DEBUG=-Djava.compiler=NONE -Xdebug -Xnoagent -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=7777

@REM Environment Settings
SET PATH=%WAS_PATH%


@REM Launch Command
"C:\Program Files\IBM\WebSphere\AppServer/java/bin/java"  %DEBUG% "-Declipse.security" "-Dosgi.install.area=C:\Program Files\IBM\WebSphere\AppServer" "-Dosgi.configuration.area=C:\Program Files\IBM\WebSphere\AppServer\profiles\AppSrv1/servers/server1/configuration" "-Dosgi.framework.extensions=com.ibm.cds,com.ibm.ws.eclipse.adaptors" "-Xshareclasses:name=webspherev85_1.6_32,nonFatal" "-Dsun.reflect.inflationThreshold=250" "-Dcom.ibm.xtq.processor.overrideSecureProcessing=true" "-Xbootclasspath/p:C:\Program Files\IBM\WebSphere\AppServer/java/jre/lib/ibmorb.jar" "-classpath" "C:\Program Files\IBM\WebSphere\AppServer\profiles\AppSrv1/properties;C:\Program Files\IBM\WebSphere\AppServer/properties;C:\Program Files\IBM\WebSphere\AppServer/lib/startup.jar;C:\Program Files\IBM\WebSphere\AppServer/lib/bootstrap.jar;C:\Program Files\IBM\WebSphere\AppServer/lib/jsf-nls.jar;C:\Program Files\IBM\WebSphere\AppServer/lib/lmproxy.jar;C:\Program Files\IBM\WebSphere\AppServer/lib/urlprotocols.jar;C:\Program Files\IBM\WebSphere\AppServer/deploytool/itp/batchboot.jar;C:\Program Files\IBM\WebSphere\AppServer/deploytool/itp/batch2.jar;C:\Program Files\IBM\WebSphere\AppServer/java/lib/tools.jar" "-Dibm.websphere.internalClassAccessMode=allow" "-Xms50m" "-Xmx256m" "-Xscmaxaot4M" "-Xscmx60M" "-Dws.ext.dirs=C:\Program Files\IBM\WebSphere\AppServer/java/lib;C:\Program Files\IBM\WebSphere\AppServer\profiles\AppSrv1/classes;C:\Program Files\IBM\WebSphere\AppServer/classes;C:\Program Files\IBM\WebSphere\AppServer/lib;C:\Program Files\IBM\WebSphere\AppServer/installedChannels;C:\Program Files\IBM\WebSphere\AppServer/lib/ext;C:\Program Files\IBM\WebSphere\AppServer/web/help;C:\Program Files\IBM\WebSphere\AppServer/deploytool/itp/plugins/com.ibm.etools.ejbdeploy/runtime" "-Dderby.system.home=C:\Program Files\IBM\WebSphere\AppServer/derby" "-Dcom.ibm.itp.location=C:\Program Files\IBM\WebSphere\AppServer/bin" "-Djava.util.logging.configureByServer=true" "-Duser.install.root=C:\Program Files\IBM\WebSphere\AppServer\profiles\AppSrv1" "-Djava.ext.dirs=C:\Program Files\IBM\WebSphere\AppServer/tivoli/tam;C:\Program Files\IBM\WebSphere\AppServer/java/jre/lib/ext" "-Djavax.management.builder.initial=com.ibm.ws.management.PlatformMBeanServerBuilder" "-Dpython.cachedir=C:\Program Files\IBM\WebSphere\AppServer\profiles\AppSrv1/temp/cachedir" "-Dwas.install.root=C:\Program Files\IBM\WebSphere\AppServer" "-Djava.util.logging.manager=com.ibm.ws.bootstrap.WsLogManager" "-Dserver.root=C:\Program Files\IBM\WebSphere\AppServer\profiles\AppSrv1" "-Dcom.ibm.security.jgss.debug=off" "-Dcom.ibm.security.krb5.Krb5Debug=off" "-Dcom.ibm.ws.management.event.pull_notification_timeout=120000" "-Xquickstart" "-Djava.library.path=C:\Program Files\IBM\WebSphere\AppServer/lib/native/win/x86_32/;C:\Program Files\IBM\WebSphere\AppServer\java\jre\bin\default;C:\Program Files\IBM\WebSphere\AppServer\java\jre\bin;C:\Windows\SysWOW64;C:\Windows;C:\Program Files\IBM\WebSphere\AppServer\lib\native\win\x86_32;C:\Program Files\IBM\WebSphere\AppServer\bin;C:\Program Files\IBM\WebSphere\AppServer\java\bin;C:\Program Files\IBM\WebSphere\AppServer\java\jre\bin;C:\Python27\;C:\maven\bin;C:\oraclexe\app\oracle\product\11.2.0\server\bin;C:\Windows\system32;C:\Windows;C:\Windows\System32\Wbem;C:\Windows\System32\WindowsPowerShell\v1.0\;C:\Program Files (x86)\Intel\OpenCL SDK\2.0\bin\x86;C:\Program Files (x86)\Intel\OpenCL SDK\2.0\bin\x64;C:\Program Files (x86)\Windows Kits\8.1\Windows Performance Toolkit\;C:\Program Files\Microsoft SQL Server\110\Tools\Binn\;C:\Program Files (x86)\Microsoft SDKs\TypeScript\1.0\;C:\Program Files\Microsoft\Web Platform Installer\;C:\Program Files (x86)\MySQL\MySQL Utilities 1.3.6\;C:\xampp\mysql\bin;C:\xampp\php;C:\Program Files (x86)\nodejs\;C:\Go\bin;C:\Program Files\Sublime Text 3;C:\Windows\SysWOW64;C:/ant/bin;C:\Users\Wei Cong\AppData\Local\Android\android-studio\sdk\platform-tools;C:\Program Files (x86)\Git\bin;C:\Users\Wei Cong\AppData\Roaming\npm;.;" "-Djava.endorsed.dirs=C:\Program Files\IBM\WebSphere\AppServer/endorsed_apis;C:\Program Files\IBM\WebSphere\AppServer/java/jre/lib/endorsed;C:\Program Files\IBM\WebSphere\AppServer\endorsed_apis;C:\Program Files\IBM\WebSphere\AppServer\java\jre\lib\endorsed" "-Djava.security.auth.login.config=C:\Program Files\IBM\WebSphere\AppServer\profiles\AppSrv1/properties/wsjaas.conf" "-Djava.security.policy=C:\Program Files\IBM\WebSphere\AppServer\profiles\AppSrv1/properties/server.policy" "com.ibm.wsspi.bootstrap.WSPreLauncher" "-nosplash" "-application" "com.ibm.ws.bootstrap.WSLauncher" "com.ibm.ws.runtime.WsServer" "C:\Program Files\IBM\WebSphere\AppServer\profiles\AppSrv1\config" "user-0bc50d2021Node01Cell" "user-0bc50d2021Node01" "server1"

@endlocal
