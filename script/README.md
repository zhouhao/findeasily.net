### How to start mysql and redis in docker
1. Install docker & docker compose
2. run `docker-compose up` in this folder, if everything goes well, run `docker ps`, you should see docker and redis instance
3. to stop mysql and redis, you can:
    1. run `docker-compose down`, this will stop and remove containers, networks, images, and volumes
    2. run `docker-compose stop`, this will stop the services without cleaning the data
    3. press `ctrl+c`, this will kill the services without cleaning the data
