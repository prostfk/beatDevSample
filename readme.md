# Sample project for beatDev
#####Mappings:
- / - index page, shows date
- /all - shows table of users
- /online - shows online users
- /{username} - user information
- /registration - create new user
- /auth - user auth 
- /logout - leave system 
------------
#### Controllers
AuthController -  user authentication/registration 
MainController - index and 'all' requests
UserController - returns personal data 
#### Model
##### Entity
There are only 1 entity-class. It's user-entity
##### Repository 
UserRepository provides database crud-working: 
```
User findUserByUsername(String username);
    User findUserById(Long id);
    List<User> findUsersByOnline(boolean online);

    @Transactional
    @Modifying
    @Query("update User u set u.online = :status where u.username = :username")
    void updateOnlineStatus(@Param("status") boolean status, @Param("username") String username);

    @Transactional
    @Modifying
    @Query("update User u set u.lastTimeSeen = :time where u.username = :username")
    void updateLastVisitedTime(@Param("time")long time, @Param("username")String username);


```
##### Util classes
File util provides file saving.
Strings util helps to hash password and calculate time from to long values.
#### Templates
There are only two templates for auth and registration. All the rest are json values.
#### Project properties
```
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/TaskBase
spring.datasource.username=root
spring.datasource.password=0
spring.jpa.open-in-view=false
spring.jpa.database=mysql
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
```