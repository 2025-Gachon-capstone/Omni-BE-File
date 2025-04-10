# builder image
FROM amazoncorretto:17-al2-jdk AS builder

RUN mkdir /Omni-BE-File
WORKDIR /Omni-BE-File

COPY . .

RUN chmod +x gradlew
RUN ./gradlew clean bootJar

# runtime image
FROM amazoncorretto:17.0.12-al2

ENV TZ=Asia/Seoul
ENV PROFILE=${PROFILE}

RUN mkdir /Omni-BE-File
WORKDIR /Omni-BE-File

COPY --from=builder /Omni-BE-File/build/libs/Omni-BE-File-* /Omni-BE-File/app.jar

CMD ["sh", "-c", " \
    java -Dspring.profiles.active=${PROFILE} \
         -jar /Omni-BE-File/app.jar"]
