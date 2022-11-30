package com.myfarm.mco_2_gutierrezvillaceran;

public class Tools {
    private String name;
    private int cost;

    public Tools(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }
    
    public void useTool() {
        System.out.println("Using " + name);

//        tools are watering can and plow
//        use switch case to determine which tool is being used
        switch (name) {
            case "Watering Can" -> System.out.println("Watering the tile");
            case "Plow" -> System.out.println("Plowing the tile");
        }
    }

//    getters
    public String getName() {
        return name;
    }
    public int getCost() {
        return cost;
    }

//    setters
    public void setName(String name) {
        this.name = name;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }
}
