package org.uob.a2.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.uob.a2.engine.*;
import org.uob.a2.model.ResourceType;


public class GraphCommand extends Command{

    public GraphCommand(List<String> graph){
        super(graph);
    }

    public String execute(Context ctx){
        try {
            //Convert string to resource types
            ResourceType graphResource;
            switch (this.words.get(0)){
                case "gold": graphResource = ResourceType.CREDITS; break;
                case "wood": graphResource = ResourceType.WOOD; break;
                case "gel": graphResource = ResourceType.GEL; break;
                case "ork": graphResource = ResourceType.ORKHIDE; break;
                case "imp": graphResource = ResourceType.IMPHORN; break;
                case "orken": graphResource = ResourceType.ORKENJACKET; break;
                case "hellfire": graphResource = ResourceType.HELLFIREDAGGER; break;
                case "goblin":
                    if (this.words.get(1).equals("ear")){
                        graphResource = ResourceType.GOBLINEAR;
                    }
                    else if (this.words.get(1).equals("skewer")){
                        graphResource = ResourceType.GOBLINSKEWER;
                    }
                case "dragon":
                    if (this.words.get(2).equals("armour")){
                        graphResource = ResourceType.DRAGONSCALEARMOUR;
                    }
                    else if (this.words.get(1).equals("scale") && this.words.get(2) == null){
                        graphResource = ResourceType.DRAGONSCALE;
                    }
                default: graphResource = null; break;
            }
            if (graphResource == null){return "Resource not recognised";}
            //Creates a list of the resources history from the initial tick
            //We also store maximum amount of the resource that the play has had
            List<Integer> resourceHistory = new ArrayList<>();
            List<Map<ResourceType, Integer>> history = ctx.state().getHistory();
            int max = -100;
            for (int i=0; i < history.size(); i++){
                int current = (history.get(i).get(graphResource) == null) ? 0: history.get(i).get(graphResource);
                max = Math.max(max, current);
                resourceHistory.add(current);
            }
            //We now create the graph from the top down
            //Each level of the graph represents 10% (e.g. first level is 0 - 10%, second level is 11-20%, etc)
            String graph = "";
            for (int y = 9; y >= 0; y--){
                graph += "\n|";
                for (int x = 0; x < resourceHistory.size(); x++){
                    double percent = y / 10.0;
                    double amountToBeat = percent * max;
                    graph += (resourceHistory.get(x) > amountToBeat) ? "X":" ";
                }
            }
            graph += "\n-";
            //Creates an x-axis
            for (int i = 0; i < resourceHistory.size(); i++){graph += "-";}
            return graph+"\n";
        }
        catch (Exception e) {return "Resource Not Recognised";}
    }
}