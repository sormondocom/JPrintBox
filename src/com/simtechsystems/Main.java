package com.simtechsystems;

public class Main {

    public static void main(String[] args) {
        JPrintBox jPrintBox = new JPrintBox();
        jPrintBox.addTitle("Orc");
        jPrintBox.addFooter("A fictional creature typically used in fantasy games or novels.");
        jPrintBox.addItem("strength","10");
        jPrintBox.addItem("intelligence","2");
        jPrintBox.addItem("dexterity","5");
        jPrintBox.addItem("constitution","8");
        System.out.print(jPrintBox.toString());

    }
}
