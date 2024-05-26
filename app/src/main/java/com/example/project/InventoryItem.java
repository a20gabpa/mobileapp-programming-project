package com.example.project;

public class InventoryItem {
    /* ================== VARIABLES ================== */
    private String ID, name, type, company;
    private int size, cost;
    private String location, category, auxdata;
    /* =============================================== */

    /* ================== CONSTRUCTOR ================== */
    public InventoryItem () {
        this.ID = "";
        this.name = "";
        this.type = "";     // <-- Login
        this.company = "";
        this.size = 0;
        this.cost = 0;
        this.location = "";
        this.category = "";
        this.auxdata = "";
    }

    public InventoryItem(String ID, String name, String type, String company, int size, int cost,
                         String location, String category, String auxdata) {
        this.ID = ID;
        this.name = name;
        this.type = type;
        this.company = company;
        this.size = size;
        this.cost = cost;
        this.location = location;
        this.category = category;
        this.auxdata = auxdata;
    }
    /* ================== SET ================== */
    public void setID(String id) { this.ID = id; }
    public void setName(String name) { this.name = name; }
    public void setType(String type) { this.company = type; }
    public void setCompany(String company) { this.company = company; }
    public void setSize(int size) { this.size = size; }
    public void setCost(int cost) { this.cost = cost; }
    public void setLocation(String location) { this.location = location; }
    public void setCategory(String category) { this.category = category; }
    public void setAuxdata(String auxdata) { this.auxdata = auxdata; }
    /* ================== GET ================== */
    public String getID() { return ID; }
    public String getName() { return name; }
    public String getType() { return type; }
    public String getCompany() { return company; }
    public int getSize() { return size; }
    public int getCost() { return cost; }
    public String getLocation() { return location; }
    public String getCategory() { return category; }
    public String getAuxdata() { return auxdata; }
    /* ========================================= */

    /* ================== METHODS ================== */
    public String GetBasicString() {
        return "ID: " + this.ID +
                ", Name: " + this.name +
                ",\n Company: " + this.company +
                ", Cost: " + this.cost +
                ", Login: " + this.type + "\n";
    }
    /* ============================================= */
}
