package utility;

import net.dv8tion.jda.core.entities.Message;

/**
 * Created by David on 4/25/2018.
 */
public abstract class Command {

    private String _name;
    private String _description;
    protected final String ERROR_MESSAGE = "Something went wront!\nUser h!help to see the proper usage for the bots commands!";

    public Command(String name){
        _name = name;
        _description = "No description available";
    }

    public Command(String name, String description){
        _name = name;
        _description = description;
    }

    public String getName(){
        return _name;
    }

    public void setName(String name){
        _name = name;
    }

    public String getDescription(){return _description;}

    public void setDescription(String description){_description = description;}

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

    public void sendEmbeddedMessage(Message message, String title, String text, String pictureUrl){
        MessageHandler.sendEmbeddedMessage(message,title, text, pictureUrl);
    }

    public void execute(Message message){
        if(commandShouldBeExecuted(message)){
            before(message);
            performAction(message);
            after(message);
        }
    }

    public void before(Message message){

    }

    public abstract void performAction(Message message);

    public void after(Message message){

    }

    public boolean equals(Command command){
        if(_name.equals(command.getName())){
            return true;
        }
        return false;
    }

    public String[] convertMessageToStringParameters(Message message){
        String[] parameters = message.getContentDisplay().split(" ");
        return parameters;
    }

    public abstract boolean commandShouldBeExecuted(Message message);
}
