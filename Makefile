APP_CONTAINER = adm-app
DB_CONTAINER = adm-db
PGADMIN_CONTAINER = adm-pgadmin

APP_IMAGE = adm-app:latest

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

reinstall:
	docker compose down -v
	docker rmi -f $(IMAGE_NAME) || true
	./gradlew clean build -x test
	docker compose up -d --build