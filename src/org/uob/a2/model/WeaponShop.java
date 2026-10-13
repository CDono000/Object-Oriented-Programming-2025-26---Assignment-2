package org.uob.a2.model;

import org.uob.a2.engine.*;

public class WeaponShop extends Consumer {

    private int stock;
    private int price;

     public WeaponShop() {
        super("weapon shop", ResourceType.HELLFIREDAGGER, 1);
        this.stock = 0;
        this.price = 40;
    }

    public void consume(Context ctx){
        ctx.state().removeResource(this.consumedResource, this.amount);
        this.stock++;
    }

    public void tick(Context ctx){
        if (ctx.state().getResourceAmount(this.consumedResource) >= this.amount){
            this.consume(ctx);
        }
    }

    public void sell(Context ctx){
        ctx.state().addResource(ResourceType.CREDITS, this.stock * this.price);
        this.stock = 0;
    }

    public String toCSV(){
        return this.name+","+this.stock;
    }

    public void setStock(int newStock){
        this.stock = newStock;
    }
}