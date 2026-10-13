package org.uob.a2;
import org.uob.a2.engine.*;
import org.uob.a2.parser.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Initilise all required variables
        String output = "";
        Scanner scan = new Scanner(System.in);
        SimulationState sim = new SimulationState();
        Engine engine = new Engine(sim);
        Context ctx = new Context(engine, sim);
        Parser parser = new Parser();
        //While the user has not attempted to quit
        while (!output.equals("QUITTING")){
            System.out.print(">>>  ");
            output = parser.parse(scan.nextLine()).execute(ctx);
            System.out.println(output);
        }   
    }
} 