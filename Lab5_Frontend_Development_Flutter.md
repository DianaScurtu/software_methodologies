# **Lab 5: Frontend Development with Flutter**

## **Objective**
Students will build the **Flutter UI** and integrate it with the backend APIs using **Bloc (Cubit) for state management** and **Firebase Authentication**.

---

## **1. Setting Up Flutter Project**
- Install **Flutter SDK** and create a new project.
- Add dependencies: `flutter_bloc`, `dio`, `flutter_secure_storage`, `firebase_auth`, and `firebase_core`.
- Initialize **Firebase** in the project.

### **1.1 Initializing Firebase**
In `main.dart`, initialize Firebase before running the app:
```dart
import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/material.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp();
  runApp(MyApp());
}
```

---

## **2. Implementing UI Screens**
- **Login & Registration Screens** using `TextField` and `ElevatedButton`.
- **Task List Screen** using `ListView.builder`.

### **Example: Login Form**
```dart
TextField(
  controller: emailController,
  decoration: InputDecoration(labelText: "Email"),
),
ElevatedButton(
  onPressed: () => login(),
  child: Text("Login"),
),
```

---

## **3. Firebase Authentication with Bloc (Cubit)**
- Use **Cubit** to manage Firebase authentication.

### **3.1 Creating the Auth Cubit**
```dart
import 'package:bloc/bloc.dart';
import 'package:equatable/equatable.dart';
import 'package:firebase_auth/firebase_auth.dart';

part 'auth_state.dart';

class AuthCubit extends Cubit<AuthState> {
  final FirebaseAuth _auth = FirebaseAuth.instance;

  AuthCubit() : super(AuthInitial());

  Future<void> signIn(String email, String password) async {
    emit(AuthLoading());
    try {
      await _auth.signInWithEmailAndPassword(email: email, password: password);
      emit(AuthAuthenticated(_auth.currentUser));
    } catch (e) {
      emit(AuthError(e.toString()));
    }
  }

  Future<void> signOut() async {
    await _auth.signOut();
    emit(AuthInitial());
  }
}
```

### **3.2 Creating Auth States**
```dart
part of 'auth_cubit.dart';

abstract class AuthState extends Equatable {
  const AuthState();
  @override
  List<Object> get props => [];
}

class AuthInitial extends AuthState {}

class AuthLoading extends AuthState {}

class AuthAuthenticated extends AuthState {
  final User? user;
  const AuthAuthenticated(this.user);
  @override
  List<Object> get props => [user!];
}

class AuthError extends AuthState {
  final String message;
  const AuthError(this.message);
  @override
  List<Object> get props => [message];
}
```

### **3.3 Using AuthCubit in the UI**
```dart
BlocProvider(
  create: (context) => AuthCubit(),
  child: BlocBuilder<AuthCubit, AuthState>(
    builder: (context, state) {
      if (state is AuthLoading) {
        return CircularProgressIndicator();
      } else if (state is AuthAuthenticated) {
        return Text("Welcome ${state.user!.email}");
      } else if (state is AuthError) {
        return Text(state.message);
      }
      return ElevatedButton(
        onPressed: () => context.read<AuthCubit>().signIn("test@example.com", "password123"),
        child: Text("Login"),
      );
    },
  ),
)
```

---

## **4. API Integration with Dio**
- Configure API base URL.
- Handle **JWT authentication**.

### **Example: Fetching Tasks**
```dart
final response = await dio.get(
  "https://api.example.com/tasks",
  options: Options(headers: {"Authorization": "Bearer $token"}),
);
```

---

## **5. State Management with Bloc (Cubit)**
- Use **Cubit** to manage state.
- Implement `TaskCubit` for fetching and storing tasks.

### **5.1 Creating the Task Cubit**
```dart
import 'package:bloc/bloc.dart';
import 'package:equatable/equatable.dart';
import 'package:dio/dio.dart';

part 'task_state.dart';

class TaskCubit extends Cubit<TaskState> {
  final Dio dio;

  TaskCubit(this.dio) : super(TaskInitial());

  Future<void> fetchTasks() async {
    emit(TaskLoading());
    try {
      final response = await dio.get("https://api.example.com/tasks");
      emit(TaskLoaded(response.data));
    } catch (e) {
      emit(TaskError("Failed to fetch tasks"));
    }
  }
}
```

### **5.2 Creating Task States**
```dart
part of 'task_cubit.dart';

abstract class TaskState extends Equatable {
  const TaskState();
  @override
  List<Object> get props => [];
}

class TaskInitial extends TaskState {}

class TaskLoading extends TaskState {}

class TaskLoaded extends TaskState {
  final List tasks;
  const TaskLoaded(this.tasks);
  @override
  List<Object> get props => [tasks];
}

class TaskError extends TaskState {
  final String message;
  const TaskError(this.message);
  @override
  List<Object> get props => [message];
}
```

---

## **6. Using Firebase Auth with TaskCubit**
Ensure **TaskCubit** only loads tasks when a user is authenticated:
```dart
BlocListener<AuthCubit, AuthState>(
  listener: (context, state) {
    if (state is AuthAuthenticated) {
      context.read<TaskCubit>().fetchTasks();
    }
  },
  child: TaskListScreen(),
)
```

---

## **Deliverables**
1. **Flutter project with UI screens.**
2. **Integrated backend APIs.**
3. **State management using Bloc (Cubit).**
4. **Firebase Authentication for login/logout.**
5. **Error handling implementation.**