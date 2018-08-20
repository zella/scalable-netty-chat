@Deprecated

# scalable-netty-chat
<b>Simple horisontal scalable tcp chat with netty and hazelcast pub/sub.</b>

###How it works:
Each server node holds it's own clients connections;  
Messages from client delivered to each server node over pub/sub;  
Each server node sends message to connected clients;

###How test on localhost:  
Start server(1) with port 6666;  
Start server(2) with port 6667;  
...  
Start server(n) with port 6666+n;

Сonnect clients to available servers what you want and start typing in the console


