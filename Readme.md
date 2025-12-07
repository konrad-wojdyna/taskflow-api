# 🚀 TaskFlow API

> Project Management REST API built with Spring Boot

A modern, production-ready REST API for managing projects and tasks.

A robust, scalable REST API for managing projects, tasks, and team collaboration. Built with enterprise-grade architecture and best practices.

---

## 📋 Features

### Current
- ✅ RESTful API architecture
- ✅ Health monitoring endpoint
- ✅ Type-safe DTOs with Java Records
- ✅ Professional logging configuration


### Planned
- 🔐 JWT-based authentication & authorization
- 📊 Complete project management system
- ✅ Task assignment & tracking
- 🗄️ PostgreSQL database integration
- 🐳 Docker containerization
- 📝 Comprehensive API documentation (Swagger)
- 🧪 Unit & Integration testing

---

## 🛠️ Tech Stack

- **Java 21** - Modern Java features (Records, Pattern Matching)
- **Spring Boot 3.4** - Framework
- **Maven** - Dependency management
- **PostgreSQL** - Relational Database (coming soon)
- **Docker** - Containerization (coming soon)
- **JWT** - Authentication (planned)

---

## 🚀 Getting Started

### Prerequisites

- Java 21 
- Maven 3.9+
- Git

### Installation
```bash
# Clone repository
git clone https://github.com/konrad-wojdyna/taskflow-api.git
cd taskflow-api

# Run application
./mvnw spring-boot:run

# Application will start on http://localhost:8080
```

### Test Health Endpoint
```bash
# Browser
http://localhost:8080/health

# Or with curl
curl http://localhost:8080/health
```

**Response:**
```json
{
  "status": "UP",
  "message": "TaskFlow API is running",
  "timestamp": "2025-12-07T15:26:39.0699337"
}
```

---

## 📁 Project Structure
```
taskflow-api/
├── src/
│   ├── main/
│   │   ├── java/com/taskflow/api/
│   │   │   ├── controller/      # REST endpoints
│   │   │   ├── dto/              # Data Transfer Objects
│   │   │   ├── model/            # Entity classes (soon)
│   │   │   ├── repository/       # Database access (soon)
│   │   │   ├── service/          # Business logic (soon)
│   │   │   ├── exception/        # Exceptions (soon)
│   │   │   └── TaskFlowApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/                     # Unit & Integration tests
├── pom.xml                       # Maven dependencies
└── README.md
```

---

*Full API documentation coming soon with Swagger UI*


## 🔄 Roadmap

- [x] Project setup & basic architecture
- [x] Health check endpoint
- [ ] User authentication system
- [ ] Project CRUD operations
- [ ] Task management
- [ ] PostgreSQL integration
- [ ] Docker deployment
- [ ] CI/CD pipeline

---

## 🤝 Contributing

Contributions, issues, and feature requests are welcome!

---

## 📝 License

MIT License - feel free to use this code for learning purposes.

---

## 👨‍💻 Author

**Konrad Wojdyna**   
Learning Path: Frontend (React/TS) → Backend (Spring Boot) → DevOps (Docker/AWS)

---

**Last Updated:** Day 4 - Spring Boot Setup + First Endpoint ✅