package com.micro.game.service;

import java.lang.reflect.Type;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.micro.game.common.Constants;
import com.micro.game.mqtt.MQTTConnector;
import com.micro.game.pojo.Toppers;
import com.micro.game.pojo.User;
import com.mirco.mqtt.MQTTAsync;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	MQTTConnector mqttConnector;
	Gson gson = new GsonBuilder().disableHtmlEscaping().create();
	Type mapType = new TypeToken<Map<String, Object>>() {
	}.getType();

	@Override
	public void sendToMQTTBroker(String topic, User user) {
	}

	@Override
	public void reciveFromMQTTBroker(String topicName) {
		mqttConnector.getMqttAsyncClient().ifPresent((client) -> {
			MQTTAsync.subscribe(client, topicName, 2, (topic, message) -> {
				String str = new String(message.getPayload());
				User user = gson.fromJson(str, User.class);
				User userFromMap = Constants.users.get(user.getId());
				if (null != userFromMap) {
					userFromMap.setCount(userFromMap.getCount() + 1);
					userFromMap.setLastUpdateTime(user.getLastUpdateTime());
					Constants.users.put(user.getId(), userFromMap);
					Constants.TOP10.remove(userFromMap); // 10logn
					Constants.TOP10.add(userFromMap); // 10 logn
				} else {
					user.setCount(1);
					Constants.users.put(user.getId(), user);
					Constants.TOP10.add(user); // 10logn
				}

				User[] users = Constants.TOP10.toArray(new User[Constants.TOP10.size()]);
				
				Arrays.sort(users,Constants.forTop10Clicks); // 10logn

				// 10
				for (int i = 0; i < users.length; i++) {
					User user1 = (User) users[i];
					int currentPostion = user1.getCurrentPosition();
					if (currentPostion != -1) {
						long currentTime = Instant.now().toEpochMilli();
						long currentSecs = (currentTime - user1.getLastUpdateTime()) / 1000;
						if (currentSecs == 0) {
							currentTime = user.getLastUpdateTime();
						}
						user1.setLastUpdateTime(currentTime);
						user1.setAmount(user1.getAmount() + ((10 - currentPostion) * currentSecs));
					}
					user1.setCurrentPosition(i);
					if(Constants.TOP10EARNERS.contains(user1))  //10
						Constants.TOP10EARNERS.remove(user1);   // 10 log 10
					Constants.TOP10EARNERS.add(user1);          // 10 log 10
				}
				
				User[] topUsers = Constants.TOP10EARNERS.toArray(new User[Constants.TOP10EARNERS.size()]);
				Arrays.sort(topUsers,Comparator.comparingLong(User::getAmount).reversed()); // 10log10
					
				Toppers toppers= new Toppers();
				toppers.setTop10(users);
				toppers.setTop10Earners(topUsers);
				String listJson = gson.toJson(toppers);
				MqttMessage msg = new MqttMessage(listJson.getBytes());
				msg.setQos(2);
				MQTTAsync.publish(client, "game", msg);
			});
		});
	}
}
