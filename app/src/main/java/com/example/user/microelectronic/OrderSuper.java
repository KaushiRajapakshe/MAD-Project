package com.example.user.microelectronic;

public class OrderSuper {
    private String itemNumber;
    private  String itemName;
    private String discription;
    private String qty;

    public OrderSuper(){

    }
    public OrderSuper(String itemNumber,String itemName, String discription, String total){
        this.itemNumber = itemNumber;
        this.itemName = itemName;
        this.discription = discription;
        this.qty = total;
    }
    public void setItemNumber(String itemNumber){
        this.itemNumber = itemNumber;
    }

    public void setItemName(String itemName){
        this.itemName = itemName;
    }
    public void setDiscription(String discription){
        this.discription = discription;
    }
    public void setQty(String total){
        this.qty = total;
    }

    public String getItemNumber(){
        return  itemNumber;
    }
    public String getItemName(){
        return itemName;
    }
    public String getDiscription(){
        return discription;
    }

    public String getQty() {
        return qty;
    }
}
