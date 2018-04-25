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
        super("sort", "Get sorted into your Hogwarts house!");
    }

    public void execute(Message message) {
        if (!userAlreadyInList(message.getAuthor())) {
            sendTextResponse(message, "Try to sort again to get sorted, because i need to try something");
            addUser(message.getAuthor());
        } else {
            HogwartsHouse house = Hogwarts.getInstance().sortToHouse();
            sendTextResponse(message, "You have been sorted into: " + house.getName());
            removeUser(message.getAuthor());
        }
    }
}
