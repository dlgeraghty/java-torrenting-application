# MULTITHREADED MEDIA SHARING APP USING JAVA 
## What is the project about?
This was an exercise of the university to fully understand and implement concurrent applications. 
The application is like a torrenting platform, in the sense that there is a central server that allows different clients to connect using a central server and then share files using a point-to-point connection between them.

## Aplication architecture:
- There is a central server that can handle multiple clients using a multithreaded system.
- Clients connect to it, so that they can ask for files to other clients. When a client wants a file, the central server will help him find who has that file and then both clients (with a client-server architecture) will connect and send/receive files.

## Programming language:
- The programming language is JAVA
- Some key features of the code construction:
  + Socket & ServerSocket to handle server-client connection
  + Multithreading extending the Thread class
  + Concurrency handled by locks

