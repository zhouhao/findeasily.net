### How to start mysql and redis in docker
1. Install docker & docker compose
2. run `docker-compose up` in this folder, if everything goes well, run `docker ps`, you should see docker and redis instance
3. to stop mysql and redis, you can:
    1. run `docker-compose down`, this will clean all data
    2. press `ctrl+c`, this will not clean all data, and you can re-use the test data next time
