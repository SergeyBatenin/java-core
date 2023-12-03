FROM openjdk:11
COPY ./src /usr/myapp/src
WORKDIR /usr/myapp
RUN javac -sourcepath ./src -d out ./src/main/homework1/sample/Main.java
CMD java -classpath ./out main.homework1.sample.Main