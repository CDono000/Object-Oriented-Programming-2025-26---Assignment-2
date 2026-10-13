package org.uob.a2.model;

import org.uob.a2.engine.*;

public class ArmourForge extends Converter {
    private final ResourceType input2;
    private final int input2Amount;

     public ArmourForge() {
        super("armour forge", ResourceType.DRAGONSCALE, 4, ResourceType.DRAGONSCALEARMOUR, 1);
        this.input2 = ResourceType.GEL;
        this.input2Amount = 20;
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

    @Override
    public String toCSV(){
        return this.name+";"+this.getInput()+";"+this.getInputAmount()+";"
        +this.getInput2()+";"+this.getInput2Amount()+";"+this.getOutput()
        +";"+this.getOutputAmount();
    }
}