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
            addUser(message.getAuthor());
            addAnswer(message.getAuthor(), "init");
            sendTextResponse(message, "Which house would you like to be in?");
        } else {
            switch(getCurrentContextStage(message.getAuthor(), this)){
                case 0:
                    sendTextResponse(message, "You are currenty not doing anything that needs an answer");
                    break;
                case 1:
                    String[] parameters = convertMessageToStringParameters(message);
                    if(parameters != null && parameters.length > 1) {
                        HogwartsHouse house = Hogwarts.getInstance().getHouseByName(convertMessageToStringParameters(message)[1]);
                        if (house != null) {
                            addAnswer(message.getAuthor(), convertMessageToStringParameters(message)[1]);
                            sendTextResponse(message, "You have been sorted into: " + house.getName());
                        } else {
                            sendTextResponse(message, "House not found");
                        }
                    } else {
                        sendTextResponse(message, "You are missing additional information to proceed with this command");
                    }
                    if(commandIsFinished(message.getAuthor())){
                        removeUser(message.getAuthor());
                    }
                    break;
                default:
                    sendTextResponse(message, "You are currenty not doing anything that needs an answer");
                    break;
            }
        }
    }

    //handle later
    public void setResponses() {
        addResponse("Which house would you like to be in?");
        addResponse("You have been sorted into: ");
    }
}
