# **Lab 4: Backend Development with Spring Boot Microservices**

## **Objective**
Students will implement a backend system using **Spring Boot Microservices**, focusing on API development, database integration with **PostgreSQL**, and authentication using **Firebase Auth**.

---

## **1. Setting Up the Backend**
- Initialize **Spring Boot Microservices** for **UserService** and **TaskService**.
- Add dependencies: `Spring Web`, `Spring Data JPA`, `PostgreSQL Driver`, `Spring Security`, and `Firebase Admin SDK`.
- Configure `application.properties` for PostgreSQL connection.

---

## **2. Database Design**
- Define **JPA entities** for `User` and `Task`.
- Implement relationships (e.g., `User` has many `Tasks`).
- Use **Lombok** to reduce boilerplate code.

### **Example: User and Task Entities**
```java
@Entity
public class User {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String email;
    
    @OneToMany(mappedBy = "user")
    private List<Task> tasks;
}

@Entity
public class Task {
    @Id @GeneratedValue
    private Long id;
    private String title;
    private String description;
    private LocalDateTime deadline;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
```

---

## **3. Implementing REST APIs**
- Create **Repository** and **Service** layers.
- Implement **Controller** with CRUD operations.
- Use **Spring Cloud OpenFeign** for microservices communication.

### **Example: Task Controller**
```java
@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskDto taskDto) {
        return ResponseEntity.ok(taskService.createTask(taskDto));
    }
}
```

---

## **4. Authentication with Firebase Auth**
- Implement Firebase authentication for **user verification**.
- Validate tokens received from the frontend.

### **Example: FirebaseAuthService**
```java
@Service
public class FirebaseAuthService {
    public String verifyToken(String token) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
        return decodedToken.getUid();
    }
}
```

---

## **5. Testing the API**
- Use **Postman** or **Swagger UI** for testing endpoints.
- Ensure proper responses for authentication and CRUD operations.
