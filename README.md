Optimized the code for the following issues
- Thread safety and session sharing across microservices
- Memory leaks under high concurrency
- Deadlocks due to improper locking
- Task prioritization in concurrent processing
- Database connection pooling and monitoring using HikariCP


## Technologies Used

- Java 17
- Maven
- Spring (for dependency injection and HikariCP)
- Concurrency utilities 

## Setup Instructions

- git clone https://github.com/varunsh20/session-management.git
- cd session-management
- mvn clean install
