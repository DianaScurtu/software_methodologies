# **Software Development Methodologies - Labs**

## **Overview**
This course consists of **six laboratory sessions** that guide students through the entire software development process, from **requirement analysis to deployment**. The project follows a **full-stack architecture** using:

- **Backend:** Spring Boot Microservices
- **Frontend:** Flutter with Bloc (Cubit) for state management
- **Authentication:** Firebase Auth
- **Database:** PostgreSQL
- **Deployment:** Railway for backend and Firebase Hosting for frontend
- **CI/CD:** GitHub Actions for automated deployment

Each lab covers a critical aspect of software development, ensuring students gain hands-on experience in building scalable applications.

---

## **Project Structure**
```
├── backend/
│   ├── user-service/  # Manages users and authentication
│   ├── task-service/  # Handles task creation and management
│   ├── gateway/       # API Gateway for routing requests
│   ├── common/        # Shared models and configurations
│
├── frontend/
│   ├── lib/
│   │   ├── blocs/      # Bloc Cubits for state management
│   │   ├── screens/    # UI screens
│   │   ├── services/   # API calls using Dio
│
├── docs/              # Documentation and API contracts
│
├── .github/workflows/  # CI/CD pipeline for automated deployment
│
└── README.md          # Project overview
```

---

## **Laboratory Breakdown**

### **Lab 2: Introduction & Requirement Analysis** ([Lab2_Introduction_Requirement_Analysis.md](Lab2_Introduction_Requirement_Analysis.md))
- Understanding software development methodologies (Agile vs. Waterfall)
- Defining project requirements and writing user stories
- Creating system flowcharts using **Mermaid.js**

---

### **Lab 3: Architecture Design and API Planning** ([Lab3_Architecture_Design_API_Planning.md](Lab3_Architecture_Design_API_Planning.md))
- Designing the system architecture
- Creating **Use Case, Class, and System Diagrams**
- Planning and documenting REST API endpoints with **Swagger (OpenAPI)**

---

### **Lab 4: Backend Development with Spring Boot Microservices** ([Lab4_Backend_Development_Spring_Boot.md](Lab4_Backend_Development_Spring_Boot.md))
- Setting up **Spring Boot microservices**
- Implementing **PostgreSQL database integration**
- Implementing **Firebase Authentication**
- Exposing REST APIs for task management

---

### **Lab 5: Frontend Development with Flutter** ([Lab5_Frontend_Development_Flutter.md](Lab5_Frontend_Development_Flutter.md))
- Setting up a Flutter project with **Bloc (Cubit)** for state management
- Implementing **Firebase Authentication**
- Integrating REST APIs using **Dio**
- Building UI components for login, registration, and task management

---

### **Lab 6: Testing, Deployment, and CI/CD** ([Lab6_Testing_Deployment_CICD.md](Lab6_Testing_Deployment_CICD.md))
- Writing **JUnit tests for backend services**
- Writing **widget tests for Flutter UI**
- Setting up **GitHub Actions for automated deployment**
- Deploying **backend on Railway** and **frontend on Firebase Hosting**

---

## **Final Outcome**
By the end of this course, students will have:
- ✅ Built a **full-stack Flutter + Spring Boot microservices application**
- ✅ Implemented **Firebase Authentication**
- ✅ Designed a **scalable REST API** and **database schema**
- ✅ Integrated **CI/CD for automatic deployment**
- ✅ Deployed the **backend on Railway** and **frontend on Firebase Hosting**
