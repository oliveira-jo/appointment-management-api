
## Visão de Domínio 

In a beauty salon we have the entities

* Customer 
* Professional 
* Service 
* Appointment 

## The mean of system is:

* Appointment

## Configuration files

application.properties

````
spring.profiles.active=test
spring.jpa.open-in-view=false

````

application-test.properties

````
# H2 connection
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=

# H2 client
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Show sql
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
````
