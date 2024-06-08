package com.jhu_hw2.ship;

public abstract class Ship implements Contact {
  private int length;
  private int speed;
  private String name;
  private String type;

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
  public void getSpeed(int speed) {
    this.speed = speed;
  }

  @Override
  public void setSpeed(String speed) {
    this.speed = Integer.parseInt(speed);
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
