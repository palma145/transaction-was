# transaction-was

# Deploy Example
  
    NETWORK 
  - docker network create local_net
  
    DATABASE
  - docker run -d --name db2 --privileged=true -p 50000:50000 --network local_net -e LICENSE=accept -e DB2INST1_PASSWORD=pass -e  DBNAME=testdb ibmcom/db2
  - execute commands sql folder data (db2_data)
  
  - docker run -d --name db2ds2 --privileged=true -p 50001:50000 --network local_net -e LICENSE=accept -e DB2INST1_PASSWORD=pass -e DBNAME=testdb ibmcom/db2
  - execute commands sql folder data (db2ds2_data)
  
    WEBSPHERE
  - docker run --name was -p 9043:9043 -p 9443:9443 -p 9080:9080 --network local_net -d ibmcom/websphere-traditional:latest
  - docker exec -it was cat /tmp/PASSWORD
  - docker cp jcc-11.5.0.0.jar was:/home/was/db2jcc.jar
  - Admin Console WAS :: https://localhost:9043/ibm/console
  - Admin User :: wsadmin//<password>
  - Add Provider JDBC in WAS (XA Datasource)
  - Add Alias global for datasource
  - Add two datasources (Mapping-configuration alias -> DefaultPrincipalMapping), test connectivity
  - Install and Deploy war in WAS
 
  TEST
  http://localhost:9080/create/false
  http://localhost:9080/data
  http://localhost:9080/create/true -> Check transaction manager is WebSphereUowTransactionManager in logs
  
  
