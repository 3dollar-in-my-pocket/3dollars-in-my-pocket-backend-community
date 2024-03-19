FROM openjdk:17
# DOcker Base Image <이미지이름>:<태그> 형식으로 설정, 프로젝트에 설정한 자바 버전에 맞게 입력해준다.

ARG JAR_FILE=build/libs/*.jar
# ARG: 컨테이너 내에서 사용할 수 있는 변수

COPY ${JAR_FILE} app.jar
# 위에 선언한 JAR_FILE 변수를 컨테이너의 app.jar로 복사한다.

ENTRYPOINT ["java", "-jar", "/app.jar"]

