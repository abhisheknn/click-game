package com.micro.game.pojo;

public class User //implements Comparable
{
	public int count;
	public String id;
	Long lastUpdateTime;
	int lastPosition;
	int currentPosition=-1;
	long amount;
	long prevTime;
	
	public long getPrevTime() {
		return prevTime;
	}
	public void setPrevTime(long prevTime) {
		this.prevTime = prevTime;
	}
	public Long getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public int getLastPosition() {
		return lastPosition;
	}
	public void setLastPosition(int lastPosition) {
		this.lastPosition = lastPosition;
	}
	public int getCurrentPosition() {
		return currentPosition;
	}
	public void setCurrentPosition(int currentPosition) {
		this.currentPosition = currentPosition;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + count + id.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (count != other.count)
			return false;
		if (count == other.count) {
			return other.id==id;
		}
		return true;
	}

	
//	@Override
//	public int compareTo(Object o) {
//		User other = (User) o;
//		if(other.count==this.count) {
//			return this.id.compareTo(other.id);
//		}
//		return Integer.compare(other.count,this.count);
//	}
	
	
}
