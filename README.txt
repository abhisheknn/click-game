#Build Prerequisites:

1.Java
2.Maven
3.Docker 


#Steps to build :

1.mvn clean install
2.docker push abhisheknn/game


#Steps to deploy :
MQTT over Websocket is enabled 

1.docker run -p 1883:1883 -p 8080:8080 -p 8000:8000 abhisheknn/hivemq3
2.docker run -e MQTTBROKER="ipOfHiveMQ:8000" abhisheknn/game 
3.Download frontend.html and add ipOfHiveMQ .
4.Run frontend.html in browser.


Technology Used :

1.Spring Boot
2.MQTT
3.Angular js
4.Cassandra/Redis to store historical data
