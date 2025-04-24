# E-Commerce-Docker

A fully containerized e-commerce platform leveraging Docker and Docker Compose for simplified development and deployment.

---
## 🛍️ Overview

This project encapsulates a full-stack e-commerce platform, comprising:

- **Frontend**: A user-facing interface built with modern web technologies.
- **Backend**: A robust server-side application handling business logic and data management.
- **Dockerized Environment**: Utilizes Docker and Docker Compose to orchestrate services, ensuring consistency across development and production environments.
---

## 📌 Table of Contents
- [✨ Features](#-features)
- [👨‍💻 Project Content](#-project-content)
- [📂 Project Structure](#-project-structure)
- [🛠 Technologies Used](#-technologies-used)
- [⚡ Prerequisites](#-prerequisites)
- [📥 Setup and Installation](#-setup-and-installation)
- [🤝 Contributing](#-contributing)
- [📜 License](#-license)

---

## ✨ Features

### Project Features
    
* Fast local setup using Docker and Docker Compose
* Modular architecture with separated frontend and backend services
* Easy-to-maintain and scalable project structure
* Environment-ready for development and production
* Full shopping experience with product browsing, cart management, and order processing 

---

## 👨‍💻 Project Content

1. Frontend Features (React)
   - Responsive and modern UI using React.js
   - Product listing with search functionality   
   - Add-to-cart and Buy Now options    
   - Cart management (update quantity, remove items)  
   - Checkout workflow integration  
   - Orders history page  
   - Auth screens for login and registration
   - Interacts with backend via REST APIs 


2. Backend Features (Spring Boot)
    - RESTful API for managing products, carts, users, and orders  
    - JWT-based authentication and authorization  
    - Spring Data JPA for database interaction  
    - Custom exception handling and validation  
    - Order lifecycle support (create, cancel, complete)  
    - Cart persistence and real-time updates  
    - API endpoints secured with role-based access  
    - Dockerized MySQL database

---   

## 📁 Project Structure

```
e-commerce-docker/
├── frontend/               # React frontend application
│   ├── src/
│   │   ├── components/     # Reusable UI components
│   │   ├── pages/          # Page components
│   │   ├── services/       # API services
│   │   ├── context/        # Authentication functions
│   │   └── app.js/         # Application Structure
│   ├── public/             # Public assets
│   ├── Dockerfile          # Frontend Docker config
│   └── package.json        # Dependencies
│
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/      # Java source files
│   │   │   └── resources  # Application properties
│   │   └── test/          # Test files
│   ├── Dockerfile         # Backend Docker config
│   └── pom.xml            # Maven dependencies
│
└── docker-compose.yml     # Docker file for the entire application
```
---

## 🛠 Technologies Used

| Technology             | Description |
|------------------------|-------------|
| **Spring Boot**        | Framework for building microservices |
| **Spring Cloud**       | Tools for distributed systems |
| **Spring Data JPA**    | Data persistence |
| **H2/MySQL/PostgreSQL** | Database options |
| **React**              | Frontend framework |
| **Maven**              | Dependency management and build |
| **Docker**             | Containerization platform |
| **Docker Compose**     | Tool for defining and running multi-container Docker applications |
| **JWT**                | Authentication and authorization |

---

## ⚡ Prerequisites

Ensure you have the following installed:

✅ **JDK 11** or newer  
✅ **Maven 3.6.0** or newer  
✅ **React.js** (v16 or newer)  
✅ **npm** (v6 or newer)  
✅ **Docker** (v20.10 or newer)  
✅ **Docker Compose** (v1.27.0 or newer)  
✅ **Git**  
✅ **Preferred IDE**: IntelliJ IDEA / Eclipse / VS Code

---

## 📥 Setup and Installation

Follow these steps to get the application up and running:

1. **Clone the Repository**

   ```bash
   git clone https://github.com/Tanooj-Seelam/E-Commerce-Docker.git
    ```
2. **Navigate to theProject Directory**

   ```bash 
   cd E-Commerce-Docker
    ```
   
3. **Build and Start the Containers**

    ```bash
    docker-compose up --build
    ```
    This command will build the Docker images and start the containers as defined in the docker-compose.yml file.


4. **Access the Application**

   - Frontend: Open your browser and navigate to `http://{localhost}:3000`
   - Backend: Access the API at `http://{localhost}:8080`

---

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

---

## 📜 License
This project is licensed under the Apache 2.0 License.

---

Made with ❤️ by Tanooj Seelam