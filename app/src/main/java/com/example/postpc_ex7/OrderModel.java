package com.example.postpc_ex7;
import java.io.Serializable;
import java.util.UUID;



public class OrderModel implements Serializable {
    public String id;
    public String costumerName;
    public int pickles;
    public boolean hummus;
    public boolean tahini;
    public String comment;
    public String status; //waiting,in-progress,ready,done

    public OrderModel(String costumerName, int pickles, boolean hummus, boolean tahini, String comment){
        this.id = UUID.randomUUID().toString();
        this.costumerName = costumerName;
        this.pickles = pickles;
        this.hummus = hummus;
        this.tahini = tahini;
        this.comment = comment;
        this.status="waiting";
    }

    public OrderModel(){
    }




}
