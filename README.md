# SAH Shortener

SAH Shortener is a URL-shortening website that enables users to convert long URLs into short, manageable links. The project is built with a clean separation between the front-end and back-end for optimal performance and maintainability.

## Features

### Front-End
- **HTML**: Provides the structure and skeleton of the web page.
- **CSS**: Adds styling for an appealing user interface.
- **JavaScript**: Handles user interactions, sends requests to the server, and updates the UI dynamically.

### Back-End
- **Java Spring Boot**: Serves as the core framework for the back-end.
- **MySQL**: Used for persistent storage of URLs.
- **JPA Repository**: Facilitates database operations.
- **Service Layer**: Connects the controller with the repository for a clean architecture.

## API Endpoints

### 1. **POST /api/url**
   - **Description**: Shortens a given URL and saves it in the system.
   - **Request Body**:
     ```Raw String:
       https://example.com     
     ```
   - **Response**:
     ```json
     {
       "urlKey": "abc123",
       "fullUrl": "https://example.com",
       "shortUrl": "http://localhost:8080/api/abc123"
     }
     ```

### 2. **GET /api/{key}**
   - **Description**: Redirects to the original URL using the short URL key.
   - **Response**:
      - **302 FOUND**: Redirects to the full URL if the key is valid.
      - **404 NOT FOUND**:the page is not found .

### 3. **DELETE /api/{key}**
   - **Description**: Deletes a URL mapping from the system (admin only).
   - **Response**:
     - **200 OK**: URL deleted successfully.
     - **404 NOT FOUND**: URL key not found.


## Technical Details
- The system uses SHA-256 hashing to generate unique keys for URLs.
- To handle hash collisions, the key length increases dynamically.
- The back-end ensures that duplicate URLs are not stored by checking for existing entries before saving.
- Idempotency is a key feature, ensuring that the same input URL always results in the same short URL.

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 11+
- MySQL Database
- Node.js (optional, for testing front-end independently)

### Installation

#### Clone the Repository
```bash
$ git clone https://github.com/yourusername/SAH-Shortener.git
$ cd SAH-Shortener
```

#### Back-End Setup
1. Configure MySQL:
   - Create a database named `URLs`.
   - Use the provided SQL script in the `database` folder to set up the schema.

2. Run the Spring Boot application:
   ```bash
   $ ./mvnw spring-boot:run
   ```

#### Front-End Setup
1. Open the `index.html` file in a browser.
2. Ensure the back-end server is running to test the full functionality.

## Usage
- Use the main page to input long URLs and receive shortened URLs.
- Access the short URL to redirect to the original page.
- Admin users can delete URLs via the delete API.

## Acknowledgments
This project was inspired by the [Coding Challenges](https://codingchallenges.fyi/challenges/challenge-url-shortener).

---

Happy shortening!

