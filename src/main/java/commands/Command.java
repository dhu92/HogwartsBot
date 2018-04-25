package commands;

import application.MessageHandler;
import net.dv8tion.jda.core.entities.Message;

/**
 * Created by David on 4/25/2018.
 */
public abstract class Command {

    private String _name;

    public Command(String name){
        _name = name;
    }

    public String getName(){
        return _name;
    }

    public void setName(String name){
        _name = name;
    }

    public boolean matchesInput(Message message){
        String[] parameters = message.getContentDisplay().split(" ");
        if(parameters.length > 0){
            if(parameters[0].equals(MessageHandler.getPrefix() + _name)){
                return true;
            }
        }
        return false;
    }

    public void sendTextResponse(Message message, String text){
        MessageHandler.sendTextResponse(message, text);
    }

    public abstract void execute(Message message);
}
