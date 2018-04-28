package commands;

import hogwarts.Hogwarts;
import hogwarts.HogwartsHouse;
import net.dv8tion.jda.core.entities.User;

import java.util.ArrayList;

/**
 * Created by David on 4/25/2018.
 */
public class SortCommand extends ContextCommand {

    public SortCommand() {
        super("sort", "Get sorted into your Hogwarts house!", 1);
    }

    public String buildUserAnswerString(String[] params){
        StringBuilder sb = new StringBuilder("");
        for(int i = 1; i < params.length; i++){
            sb.append(params[i] + ",");
        }
        return sb.toString();
    }

    public void setResponses() {
        addResponse(new BotResponse("Which house would you like to be in?"));
        ArrayList<String> params = new ArrayList<String>();
        for(HogwartsHouse house : Hogwarts.getInstance().getHogwartsHouseList()){
            params.add(house.getName());
        }
        BotResponse response = new BotResponse("You have been sorted into: ");
        response.addValidParameters(params);
        addResponse(response);
    }

    public String buildBotAnswerForCurrentStage(User user, String[] params){
        String answer;
        BotResponse botResponse = getNextResponse(user);
        switch(getCurrentContextStage(user, this)){
            case 0:
                answer = botResponse.createResponse();
                break;
            case 1:
                HogwartsHouse house = Hogwarts.getInstance().getHouseByName(params[1]);
                if (house != null) {
                    answer = botResponse.createResponse(house.getName());
                } else {
                    answer = "House not found\n" + ERROR_MESSAGE;
                }
                break;
            default:
                answer = ERROR_MESSAGE;
                break;
        }
        return answer;
    }
}
