# Backend-solution
This is a solution of Backend-section task.  

About:

The entire code of the program has been posted in the repository in the directory demo\src\main\java\projektNBP\demo under the name NBPController. The project was written in Java language JDK 17 version and SpringBoot framework. 


How to start:

To start the server, you need to enter this command in the terminal in the directory where the app.jar file is located. This application is located in "\demo\target'': 

java - jar app.jar


Operations:

After entering the above command, our server started and we can now use three operations: currency, quotes, difference based on the number of quotations, date, or currency code.Here are some examples:

-http://localhost:8081/api/v1/quotes/GBP/250  
output: Max:5.7338, Min:5.2086 

(/quotes/{code}/{quotationNumber}) after /quotes server takes also currency code and number of last quotations (N) where N<=255 and also N>0

-http://localhost:8081/api/v1/difference/GBP/250
output: The major difference between the buy and ask rate:0.6311999999999998 

(/difference/{code}/{quotationNumber}) after /difference server takes also currency code and number of last quotations (N) where N<=255 and also N>0

-http://localhost:8081/api/v1/currency/GBP/2023-02-03
output: Average exchange rate: 5.2571 

(/currency/{code}/{date}) after /currency server takes also currency code and date

We can also change the server port. By default, it is set to 8081. We can change it by entering this command during the application's app.jar runtime: 

java -jar app.jar --server.port=3000



How to stop:

To shut down the server, we need to press the CTRL+C combination in the command prompt or manually stop the server if it was launched in an IDE environment, such as IntelliJ.


*I have also included a folder with photos showing the process of the server running after entering the above commands. It can be found in the repository in a folder named: photosOfRunningServer :).


Update: 25.04.2023 15:17 
Creating the same project, but add Dependency Injection (directory demo_Dependency_Injection) -> Creating NBPService class and add this to NBPController. 

