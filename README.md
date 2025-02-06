Library Management System (Spring Boot & MySQL)
-----------------------------------------------------

**Overview:-**

The Library Management System is a Spring Boot-based monolithic application that provides services for managing books, members, and borrowing operations in a library. It includes features such as:

**Book Management:** Add, retrieve, update, and delete books.

**Member Management:** Register and manage members.

**Borrow Service:** Handle book borrowing and returning.

**Global Exception Handling:** Ensures consistent error responses.

**Input Validation:** Uses Spring Validator to validate requests.

**Logging:** Implements SLF4J with Logback for logging.

**MySQL Integration:** Uses Spring Data JPA for persistence.


**Technologies Used**
---------------------

Spring Boot 3.x

Spring Data JPA

Spring Validation

Spring Boot Logging (SLF4J & Logback)

MySQL Database

Maven

Java 17



**Project Setup**
--------------------------

**1. Clone the Repository**

git clone https://github.com/your-username/library-management-system.git
cd library-management-system


**2. Configure MySQL Database**

Ensure MySQL is installed and running. Create a database:

CREATE DATABASE library_db;

Update src/main/resources/application.properties:


**3. Build and Run the Project**

mvn clean install
mvn spring-boot:run



**API Endpoints**
--------------------------------

**1. Book Service**

**Method   **             ** Endpoint**       **Description**

POST                   api/books         Add a new book

GET                    api/books/{id}    Get book by ID

PUT                    api/books/{id}    Update book details

DELETE                 api/books/{id}    Delete a book

**2. Member Service**

**Method**               **Endpoint**        **Description**

POST                   api/members      Register a new member

GET                    api/members/{id}  Get member by ID

**3. Borrow Service**

**Method **               ** Endpoint**         **Description**

POST                   api/borrows/borrow  Borrow a book

POST                   api/borrows/return  Return a book


**Global Exception Handling**

The application implements @ControllerAdvice to handle exceptions centrally. 

**Logging**

The application logs system events using SLF4J and Logback:


**License**

This project is licensed under the MIT License.

