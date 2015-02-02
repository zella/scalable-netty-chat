# scalable-netty-chat
<b>Simple horisontal scalable tcp chat with netty and hazelcast pub/sub.</b>

###How works:
Each server node holds it's own clients connections;  
Messages from client delivered to each server node over pub/sub;  
Each server node sends message to connected clients;

###How test on localhost:  
Start server(1) with port 6666;  
Start server(2) with port 6667;  
...  
Start server(n) with port 6666+n;

Сonnect multiple clients to available servers and start typing in the console


