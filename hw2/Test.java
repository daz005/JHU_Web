package com.jhu.hw2;
import java.util.ArrayList;
import java.util.List;

  public class Test {
    public static void main(String[] args) {
    Destroyer destroyer1 = new Destroyer();
    destroyer1.setName("Destroyer 1");
    destroyer1.setNumberMissile(10);

    Destroyer destroyer2 = new Destroyer();
    destroyer2.setName("Destroyer 2");
    destroyer2.setNumberMissile(15);

    Submarine submarine1 = new Submarine();
    submarine1.setName("Submarine 1");
    submarine1.setNumberTorpedos(8);

    Submarine submarine2 = new Submarine();
    submarine2.setName("Submarine 2");
    submarine2.setNumberTorpedos("Foo");

    P8 p8_1 = new P8();
    p8_1.setName("P8 1");
    p8_1.setNumberEngines(4);

    P8 p8_2 = new P8();
    p8_2.setName("P8 2");
    p8_2.setNumberEngines(4);

    List<Destroyer> destroyerList = new ArrayList<>();
    destroyerList.add(destroyer1);
    destroyerList.add(destroyer2);

    List<Submarine> submarineList = new ArrayList<>();
    submarineList.add(submarine1);
    submarineList.add(submarine2);

    List<Ship> shipList = new ArrayList<>();
    shipList.add(destroyer1);
    shipList.add(destroyer2);
    shipList.add(submarine1);
    shipList.add(submarine2);

    List<Contact> contactList = new ArrayList<>();
    contactList.add(destroyer1);
    contactList.add(destroyer2);
    contactList.add(submarine1);
    contactList.add(submarine2);
    contactList.add(p8_1);
    contactList.add(p8_2);

    System.out.println("Destroyers:");
    for (Destroyer d : destroyerList) {
      System.out.println(d.getName() + " - Missiles: " + d.getNumberMissile());
    }

    System.out.println("Submarines:");
    for (Submarine s : submarineList) {
      System.out.println(s.getName() + " - Torpedos: " + s.getNumberTorpedos());
    }

    System.out.println("P8 Aircrafts:");
    System.out.println(p8_1.getName() + " - Engines: " + p8_1.getNumberEngines());
    System.out.println(p8_2.getName() + " - Engines: " + p8_2.getNumberEngines());

    System.out.println("Contacts:");
    for (Contact c : contactList) {
      System.out.println(c.toString());
    }
}
}
    
