package org.uob.a2.parser; 
import org.uob.a2.*; 
import org.uob.a2.model.*; 
import org.uob.a2.engine.*; 
import java.util.List; 
import java.util.ArrayList; 
import java.util.Map; 
import java.util.HashMap; 

public class GraphCommand extends Command 
{ 
    public GraphCommand(List<String> words) 
    { 
        super(words); 
    } 
    
    @Override 
    public String execute(Context ctx) 
    { 
        ResourceType resource = getResource(words.get(1));
        if(ctx.state().getHistory().size() < 5)
        {
            System.out.println("There is very little history at the moment. Cannot graph it.");
            return "";
        }
        int maxResource = 0; 
        for(Map<ResourceType, Integer> map : ctx.state().getHistory()) 
        { 
            if(maxResource < map.get(resource))
            {
                maxResource = map.get(resource);
            } 
        } 
        int heightMarkings = 10;
        int width = (ctx.state().getHistory().size()+5)/5;
        int numericalWidth = String.valueOf(maxResource).length();
        int tableWidth = width + numericalWidth + 1;
        int tableHeight = heightMarkings;
        String[][] graph = new String[tableHeight][tableWidth]; 
        for(int i = 0; i < heightMarkings; i++) 
        {
            for(int j = 0; j < numericalWidth; j++)
            {
                String value = String.valueOf(maxResource/heightMarkings*(heightMarkings-i));
                String character = " ";
                if((value.length() - 1 - j) >= 0)
                {
                    character = String.valueOf(value.charAt(value.length() - 1 - j));
                }
                graph[i][numericalWidth - 1 - j] = character;
                
            }
        } 
        for(int i = 0; i < heightMarkings; i++) 
        { 
            graph[i][numericalWidth] = "|";
        } 

        for(int i = 0; i < heightMarkings; i++) 
        { 
            for(int j = numericalWidth+1; j < tableWidth; j++) 
            { 
                graph[i][j] = "."; 
            } 
        }
        
        int counter = 0;
        int currentRecord = 0;
        ArrayList<Integer> avgValues = new ArrayList<Integer>();
        for(Map<ResourceType, Integer> map : ctx.state().getHistory())
        {
            if(counter == 5)
            {
                avgValues.add(currentRecord/5);
                counter = 0;
                currentRecord = 0;
            }
            currentRecord += map.get(resource);
            counter++;
        }

        int pastRecord = 0;
        int orderCounter = 0;
        for(Integer integer : avgValues)
        {
            for(int i = 0; i < heightMarkings+1; i++)
            {
                if(integer > (int)Math.floor(maxResource*0.1*i))
                {
                    continue;
                }
                else
                {
                    if(pastRecord < integer)
                    {
                        graph[heightMarkings-i][numericalWidth+1+orderCounter] = "/";
                    }
                    else if(pastRecord == integer)
                    {
                        graph[heightMarkings-i][numericalWidth+1+orderCounter] = "-";
                    }
                    else
                    {
                        graph[heightMarkings-i][numericalWidth+1+orderCounter] = "\\";
                    }
                    pastRecord = integer;
                    orderCounter+=1;
                    break;
                }
            }
        }

        String output = ""; 
        for(int i = 0; i < heightMarkings; i++) 
        { 
            String tempResult = ""; 
            for(int j = 0; j < tableWidth-1; j++) 
            { 
                tempResult += graph[i][j]; 
            } 
            output = output + tempResult + "\n"; 
        } 
    
        System.out.println(output); 
        return ""; 
    } 

    private ResourceType getResource(String word)
    {
        return ResourceType.valueOf(word.toUpperCase());
    }
}