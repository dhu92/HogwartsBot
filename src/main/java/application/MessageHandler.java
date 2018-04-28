package application;

import commands.Command;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by David on 4/25/2018.
 */
public class MessageHandler {

    private static final String PREFIX = "h!";
    private List<Command> _commands;

    private static MessageHandler _instance;

    public static MessageHandler getInstance(){
        if(_instance == null){
            _instance = new MessageHandler();
        }
        return _instance;
    }

    private MessageHandler(){
        _commands = new ArrayList<Command>();
    }

    public void handleMessage(MessageReceivedEvent event){
        User author = event.getAuthor();
        if(!author.isBot()) {
            Message message = event.getMessage();
            if (hasPrefix(message.getContentDisplay())) {
                Command command = findMatchingCommand(message);
                if(command != null){
                    command.execute(message);
                } else {
                    sendTextResponse(message, "Invalid command! Type h!help to see all commands of this bot.");
                }
            }
        }
    }

    public static void sendTextResponse(Message message, String response){
        message.getChannel().sendMessage(response).queue();
    }

    private Command findMatchingCommand(Message message){
        for(Command command : _commands){
            if(command.matchesInput(message)){
                return command;
            }
        }
        return null;
    }

    private boolean hasPrefix(String message){
        if(message.startsWith(PREFIX)){
            return true;
        }
        return false;
    }

    public void addCommand(Command command){
        _commands.add(command);
    }
    public List<Command> getRegisteredCommands(){return _commands;}

    public static String getPrefix(){
        return PREFIX;
    }
}
