package com.example.lostandfound;

public class LostFoundItem {

    public LostFoundItem(String id, String type, String itemName, String contact, String description, String time, String location) {
        this.id = id;
        this.type = type;
        this.itemName = itemName;
        this.contact = contact;
        this.description = description;
        this.time = time;
        this.location = location;
    }

    private String id;
    public String getId(){return id;}
    public void setId(String id) {this.id = id;}

    private String type;
    public String getType(){return type;}
    public void setType(String type) {this.type = type;}

    private String itemName;
    public String getItemName(){return itemName;}
    public void setItemName(String itemName) {this.itemName = itemName;}

    private String contact;
    public String getContact(){return contact;}
    public void setContact(String contact) {this.contact = contact;}

    private String description;
    public String getDescription(){return description;}
    public void setDescription(String description) {this.description = description;}

    private String time;
    public String getTime(){return time;}
    public void setTime(String time) {this.time = time;}

    private String location;
    public String getLocation(){return location;}
    public void setLocation(String location) {this.location = location;}

    @Override
    public String toString() {
        return "Item{" +
                "id+'" + id + '\'' +
                ", type='" + type + '\'' +
                ", itemName='" + itemName + '\'' +
                ", contact='" + contact + '\'' +
                ", desc='" + description + '\'' +
                ", time='" + time + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}