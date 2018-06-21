
Spring Boot

Spring boot Jdbc

Spring Transaction 
  (@Transactional ((propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ,rollbackFor =   
    SomeException.class)

HikariCP Connection pooling

DB: MySql

Runner Class:  AppRunner


Transactional logic in ChangeAddressServiceImpl (Declarative transactional support)


Three transactions are initiatied: 2 DB 1 fake rest call ... Exception in any scenario reults in DB roll back

KeyHolder logic in AddressDaoImpl
  
  




