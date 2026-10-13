package org.uob.a2.parser;

import java.util.List;
import org.uob.a2.engine.*;
import org.uob.a2.model.ResourceType;


public class CheatCommand extends Command{

    public CheatCommand(){
        super(List.of("+1000"));
    }

    public String execute(Context ctx){
        //Adds a lot of every resource
        ctx.state().updateResource(ResourceType.WOOD, 1000);
        ctx.state().updateResource(ResourceType.GEL, 1000);
        ctx.state().updateResource(ResourceType.GOBLINEAR, 1000);
        ctx.state().updateResource(ResourceType.ORKHIDE, 1000);
        ctx.state().updateResource(ResourceType.IMPHORN, 1000);
        ctx.state().updateResource(ResourceType.DRAGONSCALE, 1000);
        ctx.state().updateResource(ResourceType.GOBLINSKEWER, 1000);
        ctx.state().updateResource(ResourceType.ORKENJACKET, 1000);
        ctx.state().updateResource(ResourceType.HELLFIREDAGGER, 1000);
        ctx.state().updateResource(ResourceType.DRAGONSCALEARMOUR, 1000);
        ctx.state().updateResource(ResourceType.CREDITS, 1000000);
        return "UPDATED, ENJOY YOUR RESOURCES";
    }
}