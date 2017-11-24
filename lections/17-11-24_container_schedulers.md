## DevOps lection 24.11.17

# Container Schedulers

## About

Systems which can manage containers inside environments

## Examples

* Simple example: Systemd :)
* Docker Swarm, Apache Mesos, CNCF/Google Kubernetes, Pivotal CloudFoundry

## Apache Mesos

* One click install
* Good for Big Data (for apps requiring many resources)

### Architecture

Stores data in `ZooKeeper`

Has several mesos agents

Mesos tracks container's state, has health-checks

We can add self-written schedulers to standard ones (Hadoop, MPI)

## Pivotal CloudFoundry

--

## Kubernetes

* Written with Go

### Scheme

A lot of user containers => Kubernetes => Managed and packed into nodes

Data stored in `etcd`

Docker is used into nodes

Can be accessed via API server

Kubernetes can start services

### Commands

If you want to run with tty: `kubectl run test --image ubuntu --tty=true -i`

To delete container: `kubectl delete deploy test`

View stats in browser: `kubectl dashboard`

View stats in terminal: `kubectl get deploy`, `.. get pods`

We can use yaml files to create new containers with configuration:

`webserver.yaml`

	apiVersion: extensions/v1beta1
	kind: Deployment
	metadata:
	  name: webserver
	spec:
	  replicas: 1
	  template:
	    metadata:
	      labels:
	        app: webserver
	    spec:
	      containers:
	      - name: webserver
	        image: nginx:alpine
	        ports:
	        - containerPort: 80
	        volumeMounts:
	        - name: hostvol
	          mountPath: /usr/share/nginx/html
	      volumes:
	      - name: hostvol
	        hostPath:
	          path: /home/docker/vol

`kubectl create -f webserver.yaml`

Service example:

	apiVersion: v1
	kind: Service
	metadata:
	  name: web-service
	  labels:
	    run: web-service
	spec:
	  type: NodePort
	  ports:
	  - port: 80
	    protocol: TCP
	  selector:
	    app: webserver

`kubectl create -f webserver-svc.yaml`

`kubectl get svc`

Scaling pods: `kubectl scale --replicas=3 -f webserver.yaml`

After that: `kubectl scale --replicas=2 -f webserver.yaml`

Result: one pod will be terminated by scheduler

If we'll try to stop one pod, Kubernetes automatically reload it into new one

Quit server: `kubectl.exe delete deploy webserver`
Quit service: `kubectl.exe delete svc web-service`

Quit all: `minicube stop`