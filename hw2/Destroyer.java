package com.jhu.hw2;

public class Destroyer extends Ship {
  private int numberMissile;

  public int getNumberMissile(){
    return numberMissile;
  }

  public void setNumberMissile(int numberMissile){
    this.numberMissile = numberMissile;
  }

  public void setNumberMissile(String numberMissile){
    try {
      this.numberMissile = Integer.parseInt(numberMissile);
    } catch (NumberFormatException e){
      this.numberMissile = 2;
    }
  }
}
