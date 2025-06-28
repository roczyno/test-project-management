# Project Management API

A comprehensive RESTful API for project management, built with Spring Boot. This API provides endpoints for managing projects, issues, comments, users, subscriptions, and more.

## Architecture

The Project Management API follows a layered architecture pattern typical of Spring Boot applications. Below is a high-level architectural diagram of the system:

```
┌─────────────────────────────────────────────────────────────────────────┐
│                           Client Applications                            │
└───────────────────────────────────┬─────────────────────────────────────┘
                                    │
                                    ▼
┌─────────────────────────────────────────────────────────────────────────┐
│                               API Layer                                  │
│  ┌─────────────┐  ┌──────────┐  ┌────────┐  ┌───────────┐  ┌──────────┐ │
│  │ Auth        │  │ Project  │  │ Issue  │  │ Comment   │  │ User     │ │
│  │ Controller  │  │Controller│  │Controller│ │Controller │  │Controller│ │
│  └─────────────┘  └──────────┘  └────────┘  └───────────┘  └──────────┘ │
│  ┌─────────────┐  ┌────────────────┐                                    │
│  │ Message     │  │ Subscription   │                                    │
│  │ Controller  │  │ Controller     │                                    │
│  └─────────────┘  └────────────────┘                                    │
└───────────────────────────────────┬─────────────────────────────────────┘
                                    │
                                    ▼
┌─────────────────────────────────────────────────────────────────────────┐
│                            Service Layer                                 │
│  ┌─────────────┐  ┌──────────┐  ┌────────┐  ┌───────────┐  ┌──────────┐ │
│  │ Auth        │  │ Project  │  │ Issue  │  │ Comment   │  │ User     │ │
│  │ Service     │  │ Service  │  │ Service│  │ Service   │  │ Service  │ │
│  └─────────────┘  └──────────┘  └────────┘  └───────────┘  └──────────┘ │
│  ┌─────────────┐  ┌────────────┐ ┌────────┐  ┌───────────┐              │
│  │ Message     │  │Subscription│ │ Email  │  │Invitation │              │
│  │ Service     │  │ Service    │ │ Service│  │ Service   │              │
│  └─────────────┘  └────────────┘ └────────┘  └───────────┘              │
└───────────────────────────────────┬─────────────────────────────────────┘
                                    │
                                    ▼
┌─────────────────────────────────────────────────────────────────────────┐
│                         Repository Layer                                 │
│  ┌─────────────┐  ┌──────────┐  ┌────────┐  ┌───────────┐  ┌──────────┐ │
│  │ User        │  │ Project  │  │ Issue  │  │ Comment   │  │ Chat     │ │
│  │ Repository  │  │Repository│  │Repository│ │Repository │  │Repository│ │
│  └─────────────┘  └──────────┘  └────────┘  └───────────┘  └──────────┘ │
│  ┌─────────────┐  ┌────────────┐ ┌─────────────┐                        │
│  │ Message     │  │Subscription│ │ Invitation  │                        │
│  │ Repository  │  │ Repository │ │ Repository  │                        │
│  └─────────────┘  └────────────┘ └─────────────┘                        │
└───────────────────────────────────┬─────────────────────────────────────┘
                                    │
                                    ▼
┌─────────────────────────────────────────────────────────────────────────┐
│                            Data Layer                                    │
│  ┌─────────────┐  ┌──────────┐  ┌────────┐  ┌───────────┐  ┌──────────┐ │
│  │ User        │  │ Project  │  │ Issue  │  │ Comment   │  │ Chat     │ │
│  │ Entity      │  │ Entity   │  │ Entity │  │ Entity    │  │ Entity   │ │
│  └─────────────┘  └──────────┘  └────────┘  └───────────┘  └──────────┘ │
│  ┌─────────────┐  ┌────────────┐ ┌─────────────┐                        │
│  │ Message     │  │Subscription│ │ Invitation  │                        │
│  │ Entity      │  │ Entity     │ │ Entity      │                        │
│  └─────────────┘  └────────────┘ └─────────────┘                        │
└───────────────────────────────────┬─────────────────────────────────────┘
                                    │
                                    ▼
┌─────────────────────────────────────────────────────────────────────────┐
│                         PostgreSQL Database                              │
└─────────────────────────────────────────────────────────────────────────┘
```

### Key Components

1. **API Layer (Controllers)**: Handles HTTP requests and responses, input validation, and routing.
   - AuthenticationController, UserController, ProjectController, IssueController, etc.

2. **Service Layer**: Contains business logic and orchestrates operations.
   - AuthenticationService, UserService, ProjectService, IssueService, etc.

3. **Repository Layer**: Provides data access abstractions.
   - UserRepository, ProjectRepository, IssueRepository, CommentRepository, etc.

4. **Data Layer (Entities)**: Represents the database tables and relationships.
   - User, Project, Issue, Comment, Message, Subscription, etc.

5. **Cross-Cutting Concerns**:
   - Security: JWT-based authentication and authorization
   - Exception Handling: Global exception handler and specific exceptions
   - DTOs: Request and response objects for data transfer

### Technology Stack

- **Backend**: Spring Boot, Spring Security, Spring Data JPA
- **Database**: PostgreSQL
- **Authentication**: JWT (JSON Web Tokens)
- **API Documentation**: OpenAPI/Swagger
- **Build Tool**: Maven

## Table of Contents

- [Architecture](#architecture)
- [Setup](#setup)
- [Authentication](#authentication)
- [API Endpoints](#api-endpoints)
  - [Authentication](#authentication-endpoints)
  - [User](#user-endpoints)
  - [Project](#project-endpoints)
  - [Issue](#issue-endpoints)
  - [Comment](#comment-endpoints)
  - [Message](#message-endpoints)
  - [Subscription](#subscription-endpoints)

## Setup

### Prerequisites

- Java 11 or higher
- PostgreSQL
- Maven

### Environment Variables

Create a `.env` file in the root directory with the following variables:

```
ACTIVE_PROFILE=dev
POSTGRESQL_PORT=5432
POSTGRESQL_USERNAME=postgres
POSTGRESQL_PASSWORD=your_password
POSTGRESQL_HOST=localhost
POSTGRESQL_DATABASE=pm_db
EMAIL_USERNAME=your_email@gmail.com
EMAIL_PASSWORD=your_email_password
EMAIL_HOST=smtp.gmail.com
EMAIL_PORT=587
SECRET_KEY=your_secret_key
JWT_HEADER=Authorization
FRONTEND_URL=http://localhost:5173
```

### Running the Application

```bash
# Clone the repository
git clone https://github.com/your-username/java-project-management-api.git

# Navigate to the project directory
cd java-project-management-api

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

## Authentication

The API uses JWT (JSON Web Token) for authentication. Most endpoints require a valid JWT token in the Authorization header.

## API Endpoints

### Authentication Endpoints

#### Register a new user

```
POST /api/v1/auth/register
```

Request body:
```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "password123"
}
```

#### Login

```
POST /api/v1/auth/login
```

Request body:
```json
{
  "email": "john@example.com",
  "password": "password123"
}
```

### User Endpoints

#### Get user profile

```
GET /api/v1/user/profile
```

Headers:
```
Authorization: Bearer {jwt_token}
```

#### Delete user

```
DELETE /api/v1/user/{id}
```

Headers:
```
Authorization: Bearer {jwt_token}
```

### Project Endpoints

#### Create a new project

```
POST /api/v1/project/create
```

Headers:
```
Authorization: Bearer {jwt_token}
```

Request body:
```json
{
  "name": "Project Name",
  "description": "Project Description",
  "category": "Project Category",
  "tags": ["tag1", "tag2"]
}
```

#### Get a project by ID

```
GET /api/v1/project/{id}
```

Headers:
```
Authorization: Bearer {jwt_token}
```

#### Get all projects

```
GET /api/v1/project/all
```

Headers:
```
Authorization: Bearer {jwt_token}
```

Query parameters (optional):
- `category`: Filter by category
- `tag`: Filter by tag

#### Update a project

```
PUT /api/v1/project/{projectId}
```

Headers:
```
Authorization: Bearer {jwt_token}
```

Request body:
```json
{
  "name": "Updated Project Name",
  "description": "Updated Project Description",
  "category": "Updated Project Category",
  "tags": ["tag1", "tag2", "tag3"]
}
```

#### Delete a project

```
DELETE /api/v1/project/{projectId}
```

Headers:
```
Authorization: Bearer {jwt_token}
```

#### Add user to project

```
POST /api/v1/project/add/{projectId}
```

Headers:
```
Authorization: Bearer {jwt_token}
```

#### Remove user from project

```
POST /api/v1/project/remove/{projectId}
```

Headers:
```
Authorization: Bearer {jwt_token}
```

#### Search projects

```
GET /api/v1/project/search
```

Headers:
```
Authorization: Bearer {jwt_token}
```

Query parameters:
- `tag`: Search by tag

#### Get project chat

```
GET /api/v1/project/{projectId}/chat
```

Headers:
```
Authorization: Bearer {jwt_token}
```

#### Invite user to project

```
POST /api/v1/project/invite
```

Headers:
```
Authorization: Bearer {jwt_token}
```

Request body:
```json
{
  "email": "user@example.com",
  "projectId": 1
}
```

#### Accept project invitation

```
POST /api/v1/project/invite/accept
```

Headers:
```
Authorization: Bearer {jwt_token}
```

Query parameters:
- `token`: Invitation token

### Issue Endpoints

#### Get an issue by ID

```
GET /api/v1/issues/{issueId}
```

Headers:
```
Authorization: Bearer {jwt_token}
```

#### Get issues by project ID

```
GET /api/v1/issues/project/{projectId}
```

Headers:
```
Authorization: Bearer {jwt_token}
```

#### Create an issue

```
POST /api/v1/issues/project/{projectId}
```

Headers:
```
Authorization: Bearer {jwt_token}
```

Request body:
```json
{
  "title": "Issue Title",
  "description": "Issue Description",
  "status": "OPEN",
  "priority": "HIGH",
  "dueDate": "2023-12-31"
}
```

#### Delete an issue

```
DELETE /api/v1/issues/delete/{issueId}
```

Headers:
```
Authorization: Bearer {jwt_token}
```

#### Add user to issue

```
POST /api/v1/issues/add_user/{issueId}
```

Headers:
```
Authorization: Bearer {jwt_token}
```

#### Update issue status

```
PUT /api/v1/issues/update/{issueId}/{status}
```

Headers:
```
Authorization: Bearer {jwt_token}
```

### Comment Endpoints

#### Add a comment to an issue

```
POST /api/v1/comment/issue/{issueId}
```

Headers:
```
Authorization: Bearer {jwt_token}
```

Request body:
```json
{
  "content": "Comment content"
}
```

#### Delete a comment

```
DELETE /api/v1/comment/delete/{id}
```

Headers:
```
Authorization: Bearer {jwt_token}
```

#### Get comments by issue ID

```
GET /api/v1/comment/issue/{issueId}
```

Headers:
```
Authorization: Bearer {jwt_token}
```

### Message Endpoints

#### Send a message

```
POST /api/message/send/chat/{chatId}
```

Headers:
```
Authorization: Bearer {jwt_token}
```

Request body:
```json
{
  "content": "Message content"
}
```

#### Get messages by project ID

```
GET /api/message/project/{projectId}
```

Headers:
```
Authorization: Bearer {jwt_token}
```

### Subscription Endpoints

#### Create a subscription

```
POST /api/subscription
```

Headers:
```
Authorization: Bearer {jwt_token}
```

#### Get user subscription

```
GET /api/subscription/user
```

Headers:
```
Authorization: Bearer {jwt_token}
```

#### Upgrade subscription

```
GET /api/subscription/user/upgrade
```

Headers:
```
Authorization: Bearer {jwt_token}
```

Query parameters:
- `planType`: Type of plan to upgrade to (e.g., BASIC, PREMIUM, ENTERPRISE)
# test-project-management
