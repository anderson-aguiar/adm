FROM eclipse-temurin:21-jdk-jammy
LABEL description="ADM - Spring Boot Application"

WORKDIR /app

# Copia o jar gerado pelo Gradle
COPY build/libs/adm-0.0.1-SNAPSHOT.jar app.jar

# Usuário não-root para execução segura
RUN addgroup --system spring && adduser --system --ingroup spring spring
USER spring:spring

EXPOSE 8080
ENV JAVA_OPTS="-Xmx512m -Xms256m"

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
