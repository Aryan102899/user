# Docker image for springboot file run
FROM openjdk:8
MAINTAINER aryan <zheng_sirshine@163.com>
# VOLUME 指定了临时文件目录为/tmp。
VOLUME /tmp
# 将jar包添加到容器中并更名为app.jar
ADD target/user-0.0.1-SNAPSHOT.jar app.jar
# 运行jar包
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]