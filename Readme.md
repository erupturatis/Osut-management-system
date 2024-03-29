## Osut Management Application Documentation

### Introduction

Osut management app is a system designed for managing a student organization. It offers a robust platform for handling
departments, projects, roles, and user interactions within the organization.
You can find the github repo [Here](https://github.com/erupturatis/Osut-management-system)
You can login in the app either:

* **admin** accounts which can visualize everything and change user
* **normal** accounts which can only see their profiles, cannot change themselves or other users and can only see
  departments

### Setup

#### Technology Stack

- **Framework**: Spring with Thymeleaf for HTML views
- **Build Tool**: Gradle
- **Database**: PostgreSQL

### Setting up database

* This project uses PostgreSQL as a database. To set it up, you need to install PostgreSQL and create a database called
  osutdb ( or change the name inside application.properties )
* Then connect to the database and run the `dump-osutdb.sql` SQL file to create the tables and insert the data.
* Also make sure password, username, database name and port are correct inside `application.properties` file.

### Running the Project

1. **Configuration**: Check `src/main/resources` for application properties. Adjust the database URL, username, and
   password as per your PostgreSQL setup.
2. **Database Setup**:  Install postgreSQL and make a database. Then change inside application.settings the username,
   password and databse accordingly.
3. **Running the App**: Execute `./gradlew bootRun` to start the application.

## Project Structure

The project adheres to the MVC architecture, organized into the following directories:

- `src/main/java/javaspring.osutappjava` is the base path for the project. Inside this path there are :
    - `/controller` for all controllers of the java app
    - `/dto` for interfaces between the app and external data sources such as the database or cookies
    - `/middleware` for middlewares used inside the controller
    - `/model` for models used that interact with the database and process data
    - `/variables` for additional global variables such as the endpoint paths which allow for easy development and
      refactoring over the long term
- `src/main/resources/application.properties` for configuration of the app
- `src/main/resources/templates` for Thymeleaf views

## CRUD Functionalities

The main CRUD operation is user-centric, allowing for:

- **Create**: Add new users
- **Read**: View user, user departments and user projects
- **Update**: Edit user properties such as name, but also add and remove user from departments.
- **Delete**: Delete users

Paths for CRUD operations are defined in `PathsPostVariables`.

## API Paths Documentation

- `/auth-view`
    - Displays the authentication view page for users to login or authenticate.

- `/admin`
    - Provides access to the admin dashboard for system management if you are logged in as admin.

- `/user/{name}`
    - Retrieves the profile information of a specific user identified by their username.

- `/user/{name}/edit`
    - Endpoint designated for editing the details of a specific user as an Admin.

- `/user/create`
    - Used for creating a new user account in the system as an Admin.

- `/department/{department_id}`
    - Fetches information related to a specific department, identified by its unique ID.

- `/logout`
    - Handles user logout functionality, ending the current user session.

- `/project/{project_id}`
    - Retrieves details about a specific project, identified by its unique project ID.

The project also has the additional properties

- ✅ Compilation (1p)
- ✅ MVC (1p) - see project structure
- ✅ Inheritance (Override & Override) (0.5p) - see in `/model/DepartmentModel` and `/model/ProjectModel`
- ✅ Enumerations (0.5p) - inside `/variables/PathsVariables`
- ❌ Desktop Application (0.5p)
- ✅ Min 5 Views (1p) - user, user_edit, auth, department view, project view, admin dashboard
- ✅ 2 Types of user (1p) - admin and normal user
- ✅ Exception Handling (0.5p) - see controllers and models
- ✅ Code Style (1p) - clear project structure, no technical debt, respects naming conventions
- ✅ Database Connection (1p) - uses postgresql and connects to it
- ✅ CRUD on entities (1p) - CRUD on user
- ✅ Documentation (1p) - This
