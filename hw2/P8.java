//package com.jhu.hw2;

public class P8 extends Aircraft {
  private int numberEngines;

  public int getNumberEngines() {
    return numberEngines;
  }

  public void setNumberEngines(int numberEngines) {
    this.numberEngines = numberEngines;
  }

  @Override
  public String toString() {
      return "P8{name=" + getName() + ", length=" + getLength() + ", speed=" + getSpeed() + 
             ", altitude=" + getAltitude() + ", numberEngines=" + numberEngines + "}";
  }
}
