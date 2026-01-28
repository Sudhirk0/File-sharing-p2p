Project Overview

This project is a Java-based Peer-to-Peer (P2P) File Sharing Application that allows multiple peers to share files directly with each other over a network. Each peer acts as both a client and a server, enabling decentralized file transfer without any central server.

The application uses TCP socket programming and file streams to ensure reliable and efficient file sharing.


#Features

*  Peer-to-Peer (P2P) architecture
*  Each peer acts as both client and server
*  List files available on another peer
*  Download files directly from peers
*  Uses TCP sockets for communication
*  File transfer using byte streams
*  Graceful termination using EXIT command


#Technologies Used

*  Language: Java
*  Networking: TCP Socket Programming
   Concepts:
      * Client–Server Model
      * Peer-to-Peer Architecture
      * File I/O Streams
      * Distributed Systems
 

#Project Structure


p2p-file-sharing/                                                                                       
├── App.java                                                                                            
├── PeerServer.java                                                                                     
├── PeerClient.java                                                                                     
├── FileManager.java                                                                                    
├── shared/           # Files to be shared                                                              
│   └── test.txt                                                                                        
└── downloads/          # Downloaded files                                                                     
