package com.example.postpc_ex7;
import java.util.UUID;



public class OrderModel {
    private final String id;
    private String costumerName;
    private int pickles;
    private boolean hummus;
    private boolean tahini;
    private String comment;
    private String status; //waiting,in-progress,ready,done

    public OrderModel(String costumerName, int pickles, boolean hummus, boolean tahini, String comment){
        this.id = UUID.randomUUID().toString();
        this.costumerName = costumerName;
        this.pickles = pickles;
        this.hummus = hummus;
        this.tahini = tahini;
        this.comment = comment;
        this.status="waiting";
    }





}
