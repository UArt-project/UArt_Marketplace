docker-build:
	docker build --no-cache -t uart/marketplace .

docker-run:
	echo "Running on port 8080"
	docker run --env-file /home/seaeagle/Desktop/uart_marketplace.env -p 8080:8080 --net uart_net --name uart-marketplace -d uart/marketplace
docker-stop:
	docker stop uart-marketplace

docker-remove:
	make docker-stop
	docker rm uart-marketplace

docker-deploy:
	make docker-build
	make docker-run
