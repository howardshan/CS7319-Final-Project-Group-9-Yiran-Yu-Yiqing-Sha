#Find Country Flag Game - Readme
## Compilation & Implementation Platform
### Required Platform
- **Java Development Kit (JDK)**: JDK 11 or higher is recommended.

- **Integrated Development Environment (IDE).
- **Database: MySQL

### Download & Installation
- Install the latest version of MySQL(https://dev.mysql.com/downloads/mysql/)




## How to Compile the Code
1. Open the IDE and import the project by clone.
2. Ensure the project SDK is set to the installed JDK.
3. Build the project using the IDE's build functionlity.
4. Setting up the MySQL Database and create your own loca database
   - **Create the Database:

     - Log in to your MySQL server and create a new database named `FindTheCountryFlag`.
     - Create the UsersInformation Table: Switch to the `FindTheCountryFlag` database and create a table named UsersInformation and create columns: id(int), score(int) ,username(varchar).
     - After setting up the database, you need to configure the application to connect to it.
         1. Open the GameData.java file.
         2. Update the following variables with your MySQL credentials:
            - private static final String URL = "jdbc:mysql://localhost:3306/FindTheCountryFlag";
            - private static final String USER = "your_username"; ( Replace with your MySQL username)
            - private static final String PASSWORD = "your_password"; ( Replace with your MySQL password)
         3. Open the UserDataAccess class and choose all the "UserInformation" point to your database
5. Path to the source code: src/main/java
            
## How to Execute the system
-After compileing, run the 'HomePage' class in com.architecture1 to execute option 1.
-run the 'HomePage' class in java.Group9.MainProgram to execute option 2.

## Architectural Design Differences
- **Architecture Option 1** is designed with pipe-and-filter style. Seperating the game process into distinct classes {select, compare, determin, update)
- **Architecture Option 2** is designed with MainProgram and Subroutine style. HomePage is the MainPrograms. Database,MainGame and Guidance are the three subroutines.
### Rationale for Final Selection
1. Scale of project: 
   - Since the project only has one major game and a total of three interfaces. The pipe-and-filter style is much easier to manage compared to MainProgram and Subroutine Style. It’s much easier to update the logic of the game.
2. Understandable: 
   - The game is more focused on the logic to determine whether the player chooses the paired country flag and country name. Using a pipe-and-filter style makes it easier to understand and describe the project. In the MainProgram and Subroutine Style, all the game logic is in one class. It does not clearly show the process of the game.
3. Isolation:
   - Since each filter is independent, an error in the system will only affect a single filter, not the entire system, making it easy to isolate and fix problems.
4. Parallel Processing:
   - If an project needs to process large amounts of data or perform multiple independent processing tasks, the pipeline-filter architecture allows individual filters to run in parallel, improving processing efficiency and system performance.
## Changes from Project Proposal
- We change the final style from Layer Style to pipe-and-filter style because we figured out there are not enough layers in the project. Homepage is the first layer and Game and Guidance are the second layer. There doesn't have third layer
- Based on the rationale for the final selection, we decide to choose pipe-and-filter as our final architecture style.

Thank you for reviewing this project. For any inquiries or further discussion on the architecture design, please contact Yiqing Sha & Yiran Yu.