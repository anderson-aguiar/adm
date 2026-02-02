run:
	./gradlew build -x test
	docker compose up -d --build

stop:
	docker compose down

restart: stop run

rebuild:
	./gradlew clean build -x test
	docker compose build --no-cache
	docker compose up -d
