package com.micro.game.common;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.google.common.collect.MinMaxPriorityQueue;
import com.micro.game.pojo.User;

public class Constants {
	public static final  String GAMEPUBLISHER = "game_52";
	public static final  String MQTTBROKER =  System.getenv("MQTTBROKER");// 
	public static Map<String,User> users= new ConcurrentHashMap<>();  // Storing it to get lastTime and total count
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Comparator<User>  forTop10Clicks=new Comparator() {
		@Override
		public int compare(Object o1, Object o2) {
			User other = (User) o1;
			User other2 = (User) o2;
			if(other.getCount()==other2.getCount()) {
				return other.getId().compareTo(other2.getId());
			}
			return Integer.compare(other2.getCount(),other.getCount());
			
		};
		};
	
	public static MinMaxPriorityQueue<User> TOP10= MinMaxPriorityQueue 
			.orderedBy(forTop10Clicks)				
				.maximumSize(10)
				.create();
	
	public static MinMaxPriorityQueue<User> TOP10EARNERS= MinMaxPriorityQueue
			.orderedBy(Comparator.comparingLong(User::getAmount))
			.maximumSize(10)
			.create();
	
}
