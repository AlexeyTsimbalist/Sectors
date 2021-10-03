build:
	echo "Building application"
	docker-compose up --build --detach
down:
	echo "Removing application"
	docker-compose down