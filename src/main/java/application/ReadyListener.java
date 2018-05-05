package application;

import commands.*;
import utility.AnswerCommand;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;
import utility.ConfigLoader;
import utility.MessageHandler;

import javax.security.auth.login.LoginException;
import java.io.IOException;


/**
 * Created by David on 4/25/2018.
 */
public class ReadyListener implements EventListener {

    private static JDA _jda;
    private MessageHandler _handler;

    public static void main(String[] args) throws LoginException, InterruptedException, IOException {
        //token in config.properties file (token=<token>)
        String token = getToken();
        _jda = new JDABuilder(AccountType.BOT)
                .setToken(token)
                .addEventListener(new ReadyListener())
                .buildBlocking();
    }

    private ReadyListener(){
        _handler = MessageHandler.getInstance();
        _handler.setPrefix("h!");

        _handler.addCommand(new SortCommand());
        _handler.addCommand(new HelpCommand());
        _handler.addCommand(new AnswerCommand());
        _handler.addCommand(new InfoCommand());
        _handler.addCommand(new ReceiveLetterCommand());
        _handler.addCommand(new HouseInfoCommand());
        TriviaCommand triviaCommand = new TriviaCommand();
        _handler.addCommand(triviaCommand);
        _handler.addTriggerCommand(triviaCommand);
    }

    public void onEvent(Event event){
        if(event instanceof ReadyEvent){
            System.out.println("API is ready!");
        } else if(event instanceof MessageReceivedEvent){
            _handler.handleMessage((MessageReceivedEvent)event);

        }
    }

    public static JDA getJda(){
        return _jda;
    }

    public static String getToken(){
        return new ConfigLoader().getToken();
    }
}



