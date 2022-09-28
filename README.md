## Test Task - Crypto Advisor

### Were implemented:
● Requirements for the recommendation service:

● Reads all the prices from the csv files

● Calculates oldest/newest/min/max for each crypto for the whole month

● Exposes an endpoint that will return a descending sorted list of all the cryptos,
comparing the normalized range (i.e. (max-min)/min)

● Exposes an endpoint that will return the oldest/newest/min/max values for a requested
crypto

### Possible improvements
- add a caching to database queries to significantly speed up the program.
- add more test.
- add more java doc.
- add more logging.

### Generate Java Doc
mvn javadoc:javadoc

### Build docker image
mvn clean install

docker build -t crypto:latest .

docker push crypto/crypto:latest .

### Deploying the  Java application in Kubernetes
minikube start --cpus 2 --memory 4096

kubectl apply -f deployment.yaml

kubectl get pods
