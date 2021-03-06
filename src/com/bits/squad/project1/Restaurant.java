package com.bits.squad.project1;

import com.bits.squad.project1.orders.Order;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Restaurant {
    private String name;
    private String address;
    private byte tables;
    private byte cooksNum;
    private HashMap<Integer, Dish> menu;
    private ArrayList<Order> waitingOrders;
    private ArrayList<Order> realisedOrders;
    private long dayTurnover;

    public Restaurant(String name, String address, int tables, int cooksNum) {
        this.name = name;
        this.address = address;
        this.tables = (byte) tables;
        this.cooksNum = (byte) cooksNum;
        menu = new HashMap<>();
        waitingOrders = new ArrayList<>();
        realisedOrders = new ArrayList<>();
        dayTurnover = 0;
    }

    public void addToMenu(Dish val) {
        menu.put(menu.size() + 1, val);
    }

    public void markAsOutOfStock(int index) {
        menu.get(index).setAvailable(false);
    }

    public void markAsInStock(int index) {
        menu.get(index).setAvailable(true);
    }

    public void printMenu() {
        menu.forEach((k, v) -> {
            System.out.println(k + ". " + v);
        });
    }

    public void writeMenuToText() throws IOException {
        FileWriter fileWriter = new FileWriter("src/com/bits/squad/project1/menu/menu.txt");
        menu.forEach((k, v) -> {
            try {
                fileWriter.append(v.getName() + "\t" + v.getDescription() + "\t" + v.isAvailable() + "\t" + v.getPrice() + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        fileWriter.close();
    }

    public void readMenuFromText(String path) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        int index = 1;
        String txt;
        menu = new HashMap<>();
        while ((txt = bufferedReader.readLine()) != null) {
            String[] values = txt.trim().split("\t");
            menu.put(index++, new Dish(values[0], values[1], values[2].equals("true"), Integer.parseInt(values[3])));
        }
    }

    public String getName() {
        return name;
    }
}
