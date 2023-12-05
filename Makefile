.PHONY: run1 run2

run1:
	mvn spring-boot:run -Dspring-boot.run.arguments=8081
run2:
	mvn spring-boot:run -Dspring-boot.run.arguments=8082
