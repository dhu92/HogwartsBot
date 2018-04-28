package commands;

import domain.hogwarts.Hogwarts;
import domain.wizards.Wizard;
import net.dv8tion.jda.core.entities.Message;
import utility.Command;

/**
 * Created by David on 4/28/2018.
 */
public class InfoCommand extends Command {

    public InfoCommand() {
        super("info", "Get information about your wizard!");
    }

    public void execute(Message message) {
        Wizard wizard = Wizard.getWizardRepository().getWizardByUid(message.getAuthor().getId());
        if(wizard != null) {
            sendTextResponse(message, wizard.getInfoString());
        } else {
            sendTextResponse(message, "You are not a wizard yet!");
        }
    }

    public boolean commandShouldBeExecuted(Message message) {
        return Wizard.getWizardRepository().wizardExists(message.getAuthor().getId());
    }
}
