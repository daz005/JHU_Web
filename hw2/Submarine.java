package com.jhu.hw2

public class Submarine extends Ship {
  private int numberTorpedos;

  public int getNumberTorpedos() {
    return numberTorpedos;
  }

  public void setNumberTorpedos(int numberTorpedos) {
    this.numberTorpedos = numberTorpedos;
  }

  public void setNumberTorpedos(String numberTorpedos) {
    try {
      this.numberTorpedos = Integer.parseInt(numberTorpedos);
    } catch (NumberFormatException e) {
      this.numberTorpedos = 2;
    }
}
