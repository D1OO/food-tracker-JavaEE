## Nutrition tracking service (JavaEE)


#####Система похудения/трекинга питания <br>
* Клиент выбирает еду (название, кол. белки, жиры, углеволы),
  которую съел (из уже готового списка) и пишет Количество.<br>
* Клиент может добавить свой тип Еды (название, калории, кол. белки,
   жиры, углеволы). <br>
* Если Клиент привысил дневную норму, система сообщит ему об этом и напишет, на сколько была превышена Норма.<br>
* Норму брать из параметров Клиент (возраст, рост, вес, образ жизни и т.д.).
 <br>
 
 ---
 
 ### Installation:
 * ##### Clone the repository
 * ##### Import maven dependencies
 * ##### Remove "module-info.class" from jackson-core-2.10.3.jar
 * ##### Install MySQL RDBMS, import schema from dump.sql
 * ##### Customize data source connection settings in /webapp/META-INF/context.xml if needed
 ### To run, execute maven goal: 
 ```
    mvn tomcat7:run-war -f pom.xml
 ```
   