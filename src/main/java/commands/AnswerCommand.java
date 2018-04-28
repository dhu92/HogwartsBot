package commands;

import application.MessageHandler;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;

import java.util.List;

/**
 * Created by David on 4/28/2018.
 */
public class AnswerCommand extends Command {


    public AnswerCommand() {
        super("answer", "You can use this to answer to the bot!");
    }

    public void execute(Message message) {
        AnswerQueue queue = AnswerQueue.getInstance();
        if(queue.isInList(message.getAuthor())){
            Command command = queue.getCommandForUser(message.getAuthor());
            if(command != null){
                command.execute(message);
                return;
            }
        }
        sendTextResponse(message, ERROR_MESSAGE);
    }
}
