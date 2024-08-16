# GitHub Repository Listing

A Spring Boot application that provides an API to fetch GitHub repositories for a given user, excluding forked repositories. 
The API returns the repositories which are not forks. It returns repository name, owner login, branch names, and the last commit SHA for each branch.

## Features

- List all repositories of a GitHub user, excluding forks.
- Fetch branch details and the last commit SHA for each branch.
- Handles errors gracefully, including returning a 404 for non-existent users.

## Getting Started

### Prerequisites

- Java 21+
- Maven 3.6+
- A GitHub Personal Access Token (PAT) with the `repo` or `public_repo` scope.

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/olek2303/assigmnent-java.git
   ```
2. Build the project:
   ```
   mvn clean install
   ```

### Configuration
In `src/main/resources/application.yml` file paste:
```
github:
  token: your_github_token
```

## Running the application 

```
mvn spring-boot:run
```

The api will be available at `http://localhost:8080`

## Running tests 

To run tests, execute 
```
mvn test
```

## API Documemntation

List repositories which are not forks. You receive ownerLogin, name of the repository, last commit SHA for each branch.

```
GET /api/v1/github/{username}
```

If it is non-existing user you will receive status 404 and a message that there is no user with this username.


