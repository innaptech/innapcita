web: java $JAVA_OPTS  -jar target/*.war --spring.profiles.active=prod,heroku,no-liquibase --server.port=$PORT
release: cp -R src/main/resources/config config && ./mvnw liquibase:clearCheckSums -Pheroku
