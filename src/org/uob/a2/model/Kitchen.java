package org.uob.a2.model;

import org.uob.a2.engine.*;

public class Kitchen extends Converter {
    private ResourceType input2;
    private int input2Amount;

     public Kitchen() {
        super("kitchen", ResourceType.GOBLINEAR, 6, ResourceType.GOBLINSKEWER, 1);
        this.input2 = ResourceType.GEL;
        this.input2Amount = 8;
    }

    public ResourceType getInput2(){
        return this.input2;
    }
    
    public int getInput2Amount(){
        return this.input2Amount;
    }

    public void convert(Context ctx){;
        ctx.state().removeResource(this.input, this.inputAmount);
        ctx.state().removeResource(this.input2, this.input2Amount);
        ctx.state().addResource(this.output, this.outputAmount);
    }

    public void tick(Context ctx){
        if (ctx.state().getResourceAmount(this.input) >= this.inputAmount && ctx.state().getResourceAmount(this.input2) >= this.input2Amount){
            this.convert(ctx);
        }
    }

    public String toCSV(){
        return this.name+";"+this.getInput()+";"+this.getInputAmount()+";"
        +this.getInput2()+";"+this.getInput2Amount()+";"+this.getOutput()
        +";"+this.getOutputAmount();
    }
}