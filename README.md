	 _	 __  ______	   ______    ___       ___ 
 	| |	/ / |  __  |  |_    _|  |   \     /   |
 	| |/ /  | |__| |    |  |    | |\ \   / /| |
 	|	/   |	 __|    |  |    | | \ \_/ / | |
 	|   \   | |\ \      |  |    | |  \   /  | |
 	| |\ \  | | \ \    _|  |_   | |   \_/   | |
 	|_| \_\ |_|  \_\  |______|  |_|         |_|
  

## Technologies:

-JSF
-Primefaces
-JPA
-Hibernate JPA
-CDI
-EJB 
-MySsql
-Maven
-POI for Excel


## Requirements:

1. Java - 1.8.x
2. Maven - 3.x.x
3. MYSQL 8.0.12 or greater
4. Wildfly 10.0 or greater.


## Steps to import app and run:

1. refer to Requirements.
2. Add mysql driver to wildfly modules.
3. Run DB script to any mysql host.
4. import project source to any java IDE support maven.
5. add wildfly to your IDE.
6. add  wildfly runtime to project libraries.
7. change values of JNDI parameters java:global/resourcsFolder and java:global/QuizURL in web.xml
8. start wildfly and access the project home page using url:http://localhost:8080/Quiz/quiz/home.xhtml




