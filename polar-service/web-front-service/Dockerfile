# 첫 번째 스테이지: 빌드 스테이지
FROM gradle:jdk21-graal-jammy AS builder
# 작업 디렉토리 설정
WORKDIR /workspace
# 소스 코드와 Gradle 래퍼 복사
COPY build.gradle .
COPY settings.gradle .
# Gradle 래퍼에 실행 권한 부여
RUN gradle wrapper
# 종속성 설치
RUN ./gradlew dependencies
# 소스 코드 복사
COPY src src
# 애플리케이션 빌드
RUN ./gradlew build -x test
# 두 번째 스테이지: 실행 스테이지
FROM container-registry.oracle.com/graalvm/jdk:21
# 작업 디렉토리 설정
WORKDIR /workspace
# 첫 번째 스테이지에서 빌드된 JAR 파일 복사
COPY --from=builder /workspace/build/libs/*.jar app.jar
# 실행할 JAR 파일 지정
ENTRYPOINT ["java", "-jar", "app.jar"]