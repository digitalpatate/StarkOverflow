# How to run e2e tests

1. Up a clean topology

   ```shell
   cd topology
   #Make sure you have the latest version of the image
   docker-compose pull
   #Make sure you have an empty database
   docker-compose down --v
   
   docker-compose up -d
   ```

   (!) Make sure that OpenLiberty is started: `docker logs e2e_test_backend`

2. Run CodeceptJS

   ```shell
   cd src
   npm install
   npm run e2e-test
   ```

   

   

