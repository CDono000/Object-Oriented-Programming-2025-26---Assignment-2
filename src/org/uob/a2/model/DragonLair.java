package org.uob.a2.model;

import org.uob.a2.engine.*;
import org.uob.a2.model.ResourceType;

public class DragonLair extends Producer{

    public DragonLair(){
        super("dragon lair", ResourceType.DRAGONSCALE, 1);
    }

    public void produce(Context ctx){
        ctx.state().addResource(this.product, this.amount);
    
    }

    public void tick(Context ctx){
        this.produce(ctx);
    }

    public String toCSV(){
        return this.name+";DRAGONSCALE;1";
    }
}