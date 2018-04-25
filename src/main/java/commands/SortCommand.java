package commands;

import application.MessageHandler;
import hogwarts.Hogwarts;
import hogwarts.HogwartsHouse;
import net.dv8tion.jda.core.entities.Message;

/**
 * Created by David on 4/25/2018.
 */
public class SortCommand extends ContextCommand {

    public SortCommand() {
        super("sort", "Get sorted into your Hogwarts house!", 1);
    }

    public void execute(Message message) {
        if (!userAlreadyInList(message.getAuthor())) {
            sendTextResponse(message, "Which house would you like to be in?");
            addUser(message.getAuthor());
        } else {
            switch(getCurrentContextStage(message.getAuthor(), this)){
                case 0:
                    sendTextResponse(message, "You are currenty not doing anything that needs an answer");
                    break;
                case 1:
                    //HogwartsHouse house = Hogwarts.getInstance().sortToHouse();
                    String[] parameters = convertMessageToStringParameters(message);
                    if(parameters != null && parameters.length > 1) {
                        HogwartsHouse house = Hogwarts.getInstance().getHouseByName(convertMessageToStringParameters(message)[1]);

                        if (house != null) {
                            sendTextResponse(message, "You have been sorted into: " + house.getName());
                            removeUser(message.getAuthor());
                        } else {
                            sendTextResponse(message, "House not found");
                        }
                    } else {
                        sendTextResponse(message, "You are missing additional information to proceed with this command");
                    }

                    break;
                default:
                    sendTextResponse(message, "You are currenty not doing anything that needs an answer");
                    break;
            }
        }
    }
}
