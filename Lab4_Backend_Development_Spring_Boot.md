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

Firebase Authentication allows secure user identity verification and integrates seamlessly with Firebase services. In this section, we'll use Firebase Auth to authenticate users and validate JWTs (JSON Web Tokens) on the backend.

### ✅ Steps to Implement Firebase Authentication:

1. **Create a Firebase Project**  
   - Go to the [Firebase Console](https://console.firebase.google.com) and create a new project.

2. **Enable Email/Password Authentication**  
   - In the Firebase Console:
     - Navigate to **Build** > **Authentication**.
     - Click on **Sign-in method**.
     - Enable **Email/Password** as a sign-in provider.

3. **Generate Admin SDK Credentials**  
   - Go to **Project Settings** > **Service accounts**.
   - Click on **Generate new private key** under the **Admin SDK** tab.
   - Download the JSON file and add it to your backend project under `/resources`.

4. **Validate JWT Tokens in Backend**  
   - When a user logs in via Firebase, a JWT token is sent from the frontend to the backend.
   - Use Firebase Admin SDK to **verify the JWT token** and authenticate the user.
   - This token contains user identity data, such as UID, email, and expiration time.

5. **Use JWT Tools for Debugging**  
   - You can inspect and decode JWTs using this tool: [JWT Decoder](https://fusionauth.io/dev-tools/jwt-decoder)

### 🔐 What is JWT?

JWT (JSON Web Token) is a compact and self-contained way for securely transmitting information between parties as a JSON object. It is commonly used for authentication and authorization.

- **Header** – contains metadata about the token (e.g., signing algorithm).
- **Payload** – contains user data (claims).
- **Signature** – verifies the token wasn't tampered with.

### 📌 JWT Structure (Visual)

![JWT Schema](https://media2.dev.to/dynamic/image/width=800%2Cheight=%2Cfit=scale-down%2Cgravity=auto%2Cformat=auto/https%3A%2F%2Fdev-to-uploads.s3.amazonaws.com%2Fuploads%2Farticles%2Figvye1880i9491jbz525.png)

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
