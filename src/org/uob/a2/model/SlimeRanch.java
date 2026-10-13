package org.uob.a2.model;

import org.uob.a2.engine.*;
import org.uob.a2.model.ResourceType;

public class SlimeRanch extends Producer{

    public SlimeRanch(){
        super("slime ranch", ResourceType.GEL, 5);
    }

    public void produce(Context ctx){
        ctx.state().addResource(this.product, this.amount);
    
    }

    public void tick(Context ctx){
        this.produce(ctx);
    }

    public String toCSV(){
        return this.name+";GEL;5";
    }
}