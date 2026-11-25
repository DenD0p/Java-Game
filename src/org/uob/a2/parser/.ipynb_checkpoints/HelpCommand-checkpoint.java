package org.uob.a2.parser;

import org.uob.a2.*;
import org.uob.a2.engine.*;
import java.util.List;

public class HelpCommand extends Command
{  
    public HelpCommand(List<String> commandToExec)
    {
        super(commandToExec);
    }
    @Override
    public String execute(Context ctx)
    {
        System.out.println("""
        build *class of the building* *name of the building* - to build a building 
        Possible buildings:
            - iron_mine (200 Credits, 5 Wood)
                           
            - forest (100 Credits)
                           
            - water_pump (500 Credits, 1 Furniture, 5 Wooden planks)
                           
            - flower_garden (500 Credits, 5 Wooden planks)
                           
            - furnace (500 Credits, 10 Wood, 10 Iron ore)
                           
            - woodcutting_facility (200 Credits, 20 Wood)
                           
            - carpentry (300 Credits, 10 Wooden planks)
                           
            - alchemical_facility (100 Credits, 10 Iron bars, 10 Wooden planks)
                           
            - auction_house (1000 Credits, 2 Golden bars)
                           
            - household_item_shop (400 Credits, 10 Furniture)
                           
        tick|t| - to progress a game by 1 day
        cheat|c - to get 10000 credits instantly
        graph|g *name of the resource* - to plot resource progression history
        help|h - to see help message again
        info|i - to see the detailed stats of current save file
        launch|la - to do complete a tick of selling products
        load|lo *name of the file* - to load the save file
        save|s *name of the file* - to save the progress into CSV file
        quit|q - to end the game
                """);
        return "";
    }

}