package commands;

import domain.hogwarts.Hogwarts;
import domain.wizard.Alignment;
import domain.wizard.Wizard;
import net.dv8tion.jda.core.entities.Message;
import utility.Command;

/**
 * Created by David on 4/28/2018.
 */
public class ReceiveLetterCommand extends Command {

    public ReceiveLetterCommand() {
        super("receiveletter", "Start your career at Hogwarts!");
    }

    public void performAction(Message message) {
        Wizard wizard = new Wizard();
        wizard.setUid(message.getAuthor().getId());
        wizard.setName(message.getAuthor().getName());
        wizard.setAlignment(Alignment.Neutral);
        wizard.setHouse(Hogwarts.DEFAULT_HOUSE);
        Wizard.getWizardRepository().save(wizard);
        sendTextResponse(message, "You are a wizard, " + message.getAuthor().getName());

    }

    public boolean commandShouldBeExecuted(Message message) {
        for(Wizard wizard : Wizard.getWizardRepository().getAllWizards()){
            if(wizard.getUid().equals(message.getAuthor().getId())){
                System.out.println("Wizard already exists");
                return false;
            }
        }
        return true;
    }
}
