# Multi-Client Server Implementations in Java

This repository contains three different implementations of a server in Java that handles multiple client connections. Each implementation demonstrates a different approach to handling concurrency and improving performance.

---

## **🛠 Implementations**
### **1️⃣ Single-Threaded Server**
- **File:** `SingleThreadedServer.java`
- **Approach:**  
  - The server handles one client at a time.
  - New clients must wait until the current client is processed.
  - Simple but inefficient under heavy load.

### **2️⃣ Multi-Threaded Server**
- **File:** `MultiThreadedServer.java`
- **Approach:**  
  - Creates a **new thread** for every client connection.
  - Allows handling multiple clients **simultaneously**.
  - **Issue:** Too many threads can lead to **high memory usage and performance degradation**.

### **3️⃣ Multi-Threaded Server with Thread Pooling**
- **File:** `ThreadPooledServer.java`
- **Approach:**  
  - Uses **Thread Pooling (`ExecutorService`)** to efficiently manage threads.
  - Instead of creating a new thread for every client, it **reuses** a fixed number of worker threads.
  - **Benefits:**  
    ✅ Reduced CPU & memory overhead  
    ✅ Better resource management  
    ✅ Scalable for handling high client requests  

---

## **🔑 Key Learnings**
✅ **Understanding Concurrency in Java** – How to manage multiple client connections efficiently.  
✅ **Limitations of Single-Threaded Servers** – They block new clients until the current client is processed.  
✅ **Thread Creation Overhead** – Creating too many threads can lead to high memory usage.  
✅ **Thread Pooling for Optimization** – Efficiently managing resources using `ExecutorService`.  
✅ **Best Practices for Server Implementation** – Closing sockets properly to avoid resource leaks.  

---

## **🚀 How to Run the Servers**
1. **Compile the Java files:**  
   ```sh
   javac <filename>.java
