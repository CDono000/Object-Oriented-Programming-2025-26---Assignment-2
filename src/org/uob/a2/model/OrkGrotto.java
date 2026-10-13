package org.uob.a2.model;

import org.uob.a2.engine.*;
import org.uob.a2.model.ResourceType;

public class OrkGrotto extends Producer{

    public OrkGrotto(){
        super("ork grotto", ResourceType.ORKHIDE, 3);
    }

    public void produce(Context ctx){
        ctx.state().addResource(this.product, this.amount);
    
    }

    public void tick(Context ctx){
        this.produce(ctx);
    }

    public String toCSV(){
        return this.name+";ORKHIDE;3";
    }
}