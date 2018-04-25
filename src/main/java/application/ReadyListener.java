package application;

import commands.HelpCommand;
import commands.SortCommand;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

import javax.security.auth.login.LoginException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;


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

        _handler.addCommand(new SortCommand());
        _handler.addCommand(new HelpCommand());
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



