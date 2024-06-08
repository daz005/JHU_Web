//package com.jhu.hw2;

public abstract class Aircraft implements Contact {
  private int length;
  private int speed;
  private String name;
  private String type;
  private int altitude;

  public int getAltitude() {
    return altitude;
  }

  public void setAltitude(int altitude) {
    this.altitude = altitude;
  }
  
  @Override
  public int getLength() {
    return length;
  }

  @Override
  public void setLength(int length) {
    this.length = length;
  }

  @Override 
  public int getSpeed() {
    return speed;
  }
    
  @Override
  public void setSpeed(int speed) {
    this.speed = speed;
  }

  @Override
  public void setSpeed(String speed) {
    try {
      this.speed = Integer.parseInt(speed);
    } catch (NumberFormatException e) {
      this.speed = 0;
    }
  }

  @Override 
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getType() {
    return type;
  }

  @Override
  public void setType(String type) {
    this.type = type;
  }
}
