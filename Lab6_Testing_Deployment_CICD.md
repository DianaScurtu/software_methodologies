
# **Lab 6: Testing, Deployment, and CI/CD**

## **Objective**
Students will **test, deploy, and automate** the CI/CD pipeline.

---

## **1. Backend Testing**
- Write **JUnit tests** for services and repositories.
- Use **Mockito** for mocking dependencies.

### **Example: Service Test**
```java
@Test
void testCreateTask() {
    when(taskRepository.save(any(Task.class))).thenReturn(new Task());
    assertNotNull(taskService.createTask(new TaskDto()));
}
```

---

## **2. Frontend Testing**
- Write **widget tests** with `flutter_test`.
- Test API integration with `mockito`.

### **Example: Widget Test**
```dart
testWidgets("Login Screen", (tester) async {
  await tester.pumpWidget(MyApp());
  expect(find.text("Login"), findsOneWidget);
});
```

---

## **3. Deployment**
- **Backend:** Deploy Spring Boot microservices on **Railway**.
- **Frontend:** Deploy Flutter web app using **Firebase Hosting**.

---

## **4. CI/CD with GitHub Actions**

Automate testing and deployment using GitHub Actions to streamline your development workflow.

### **4.1 Setting Up a Project Token**

To allow GitHub Actions to interact with your Railway project:

1. **Navigate to your Railway project settings:**
    - Go to the `Settings` page of your project dashboard.

2. **Create a new project token:**
    - Within the `Tokens` submenu, generate a new token.

3. **Add the token to your GitHub repository secrets:**
    - In your GitHub repository, go to `Settings` > `Secrets` > `Actions`.
    - Click on `New repository secret`.
    - Name it `RAILWAY_TOKEN` and paste the token value.

### **4.2 Configuring GitHub Actions Workflow**

Modify your GitHub Actions workflow to automatically deploy to Railway on every push to the `main` branch.

```yaml
name: CI/CD Pipeline

on:
  push:
    branches: [main]

jobs:
  deploy:
    runs-on: ubuntu-latest

    steps:
      - name: Checkout code
        uses: actions/checkout@v3

      - name: Deploy to Railway
        env:
          RAILWAY_TOKEN: ${{ secrets.RAILWAY_TOKEN }}
        run: |
          curl -fsSL https://railway.app/install.sh | sh
          export PATH="$HOME/.railway/bin:$PATH"
          railway up --service main
```

### **4.3 Explanation:**

- The workflow is triggered on every push to the `main` branch.
- The `deploy` job checks out the code from GitHub.
- It sets up the Railway CLI within the GitHub Actions runner.
- Finally, it uses `railway up --service main` to deploy the latest code from `main` to Railway.

By implementing this workflow, your application is automatically deployed to Railway whenever new changes are pushed to `main`, eliminating the need for manual deployments.

*Reference: [Using the Railway CLI](https://docs.railway.app/develop/cli)*

---

## **Deliverables**
1. **Backend tests (JUnit, Mockito).**
2. **Frontend tests (Flutter Test).**
3. **Deployed application (Railway & Firebase).**
4. **CI/CD pipeline (GitHub Actions).**

---

## **Final Outcome**
At the end, students will have a **fully functional Flutter + Spring Boot microservices application**, deployed and tested with CI/CD automation.

