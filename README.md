# CS611-<Final Project>-December 16th, 2022
 
## ATM Bank
 
---------------------------------------------------------------------------
 
Yujie Yan
cdyyj@bu.edu

Ran Wei
weiran22@bu.edu

Peiying Ye
peiyingy@bu.edu

Showndarya Madhavan
shmadhav@bu.edu

 
## Packages
---------------------------------------------------------------------------
– bussiness_logic_layer
	– impl 
contains the implementation of the interfaces in the business logic layer.
	– interfaces
		Contains the interfaces of each part of the project, including stock, loan, transfer money, account etc.
– controller_layer
	Contains wrapper classes to call ther service layers which can also be reused as the API endpoint layer if the backend project is changed to a microservice.
– data_access_layer
	– impl 
		Contains the implementation of the interfaces of the data access layer
	– interfaces
		Contains the interfaces of data access
– dto
	Contains the model used to transfer data to the front end
– enums
	Contains teh enums for account, transaction and user types used for reusing the code and controlling the command execution based on various combinations of these types.
– frontend
	Contains the front end views we used
	– component
		Contains the component shared by some views
– models
	– account
	– transaction
	– users
– utilities
	Contains some general utilities used acrossing the whole project include the class to record the logged in user, some factories to generate models etc.

## Object model
---------------------------------------------------------------------------

– Users:
  - The User is an abstract class that contains basic information and methods for a user and there are three types of users that inherit from User, which are Bank, Customer, and Manager.
  - The Manager class extends User, and exists as a singleton. Also, it is marked as in UserType as Manager, to store in the user database table.
  - The UserDao is the basic interface to relate the database and the model of users.
  - The CustomerDao extends UserDao and adds some methods for customers specifically.
  - The CustomerService is an interface to relate frontend and Dao of customers, which gets data from CustomerDao and returns requested data to frontend.
– Transactions:
  - The stock related dao and service contains the logic of buying and selling the stocks.
  - The stockTransaction related dao and service contains the logic to record the buying and selling behavior.
  - The loanTransatction related data and service contain the logic to recode adding and deleting loans and collateral for each customer. LoanTransaction class extends the Transaction class as a child class.
  - The currentTransactions related data, services and controllers contain logic to add, update and get balances of different accounts owned by a customer, like checking and savings account. The type of transaction and the type of account is enum driven. Functions for the transactions are reused and certains operations are distinguished based on the enum type.
– Account:
  - The Account is an abstract class that contains basic information and methods for an account and there are four types of accounts that inherit from the Account, which are BankAccount, Checking Account, SavingsAccount, and SecurityAccount.
  - The CheckingAccountDao and SavingsAccountDao extend AccountDao and add some specific methods for checking accounts and savings accounts, respectively.
  - The securityAccount related dao and service contain the logic to modify the money in the security account.
  - The AccountDao is the basic interface to relate the database and the model of accounts.
  - The AccountService is an interface to relate frontend and Dao of accounts, which gets data from AccountDao and returns requested data to frontend.
  - The AccountOperation is used for doing modifications on the checking and savings account. The DepositTransaction and WithdrawTransaction forms use these operations.
  - The AccountInfoPanel is a basic panel that shows all information of an account and there are two types of account panels that inherit from this class, which are CheckingAccountInfoPanel and SavingsAccountInfoPanel.
– Others
  - The openPosition related dao and service contain the logic to modify the open position in the client’s stock account.
  - The OpenAccountPanel is a basic panel that shows all operations needed to open an account and there are two types of open account panels that inherit from this class, which are OpenCheckingAccountPanel and OpenSavingsAccountPanel.
  - The MainFrame is a singleton class and the main frame of our GUI.
  - The FancyBank is a singleton class and contains attributes and methods needed for the whole process of the software.
  - The MD5Encryptor contains static methods to generate MD5 encryption.
– Summry
  - The daos are all interface driven and there are corresponding classes that implement each of them. The dto contains the row data aligned with the data format required by the front end, and these Services are all interfaces and there are corresponding classes that implement each of them.

## Object and GUI Relationship
---------------------------------------------------------------------------

  - Dao is isolated from the service layer separating business logic and data logic. 
  - The database is not accessible directly from GUI.
  - Models are not operated directly from frontend. 
  - Dto is used to encapsulate data for data transfer instead of sending separate parameters. For instance, TableList in dto is used to transfer data into JTable format in frontend.
  - Finally, the frontend is composed of different components that are added to panels. After the login page, the program would check the user object type to see which panel to display.

## Benefits of Design
---------------------------------------------------------------------------

  - In our developing process, as the parts are separated as feature-based, each member of the team individually tests their codes and checks frontend components, which guarantees the whole project can be easily assembled and the design is by nature loosely coupled.
  - Loosely coupled design helps swap the frontend to any different version/type of frontend in the future.
  - Hidden database visibility protects data exploitation.
  - Frontend separation into components helps screen isolation which helps maintenance and change tolerance(changing one component doesn't affect other components). For instance, changing the mechanism of stock won’t affect the manager's view on transactions.

## How to compile and run
---------------------------------------------------------------------------

Navigate to directory of src
1. javac -cp .;jgoodies-common-1.8.0.jar;jgoodies-forms-1.8.0.jar;protobuf-java-3.6.1.jar;mysql-connector-java-8.0.13.jar *.java -Xlint:unchecked
2. java -cp .;jgoodies-common-1.8.0.jar;jgoodies-forms-1.8.0.jar;protobuf-java-3.6.1.jar;mysql-connector-java-8.0.13.jar Main

 

