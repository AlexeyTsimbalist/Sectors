build:
	echo "Building sectors-app"
	docker-compose up --build --detach
down:
	echo "Removing sectors-app"
	docker-compose down