## [Nutrition tracking service](https://dreamfit-javaee.herokuapp.com) (JavaEE)

#### Система похудения/трекинга питания <br>
* Клиент выбирает еду (название, кол. белки, жиры, углеволы),
  которую съел (из уже готового списка) и пишет Количество.<br>
* Клиент может добавить свой тип Еды (название, калории, кол. белки, жиры, углеволы). <br>
* Если Клиент привысил дневную норму, система сообщит ему об этом и напишет, на сколько была превышена Норма.<br>
* Норму брать из параметров Клиент (возраст, рост, вес, образ жизни и т.д.). <br>
###### Additional: <br>
* Клиент может выполнить поиск по названию из общего списка еды всех пользователей <br>
* Раздел ленты, где администраторы могут размещать статьи (новости/рецепты) <br>
* Раздел группы для аккаунта администратора, куда можно приглашать пользователей, просматривать их дневники и статистику <br>
 
 ---
 
 ### Installation:
 * ##### Clone the repository
 * ##### Import maven dependencies
 * ##### (Optional) Remove "module-info.class" from jackson-core-2.10.3.jar and log4j-api-2.13.3.jar files
 * ##### Install MySQL RDBMS, import schema from sql_dump.zip
 * ##### Customize data source connection settings in /webapp/META-INF/context.xml if needed
 ### To run, execute maven goal: 
 ```
    mvn tomcat7:run-war -f pom.xml
 ```
   