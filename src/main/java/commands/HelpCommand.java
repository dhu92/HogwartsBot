package commands;

import application.MessageHandler;
import net.dv8tion.jda.core.entities.Message;
import utility.Command;

import java.util.List;

/**
 * Created by David on 4/25/2018.
 */
public class HelpCommand extends Command {

    public HelpCommand() {
        super("help", "Shows all currently available commands");
    }

    public void performAction(Message message) {
        List<Command> registeredCommands = MessageHandler.getInstance().getRegisteredCommands();
        StringBuilder sb = new StringBuilder();

        sb.append("Currently available commands:\n");
        sb.append("Name\tUsage\tDescription\n");
        for(Command command : registeredCommands){
            sb.append(command.getName() + "\t\t" + MessageHandler.getPrefix() + command.getName() + "\t" +  command.getDescription() + "\n");
        }
        sendTextResponse(message, sb.toString());
    }

    public boolean commandShouldBeExecuted(Message message) {
        return true;
    }
}
