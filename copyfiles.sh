~/jruby-1.5.1/bin/jruby -S buildr clean package
rm -rf /home/veresh/apache-tomcat-6.0.33/webapps/android*
cp -v ./target/androidwar-*-SNAPSHOT.war /home/veresh/apache-tomcat-6.0.33/webapps/android.war
