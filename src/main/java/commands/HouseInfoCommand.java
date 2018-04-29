package commands;

import domain.hogwarts.Hogwarts;
import domain.hogwarts.HogwartsHouse;
import net.dv8tion.jda.core.entities.Message;
import utility.Command;

/**
 * Created by David on 4/28/2018.
 */
public class HouseInfoCommand extends Command{


    public HouseInfoCommand() {
        super("house", "See information about a Hogwarts house");
    }

    public void performAction(Message message) {
            String houseName = convertMessageToStringParameters(message)[1];
            if (Hogwarts.getInstance().houseExists(houseName)) {
                HogwartsHouse house = Hogwarts.getInstance().getHouseByName(houseName);
                sendEmbeddedMessage(message, house.getName(), house.getInfoString(), house.getPictureUrl());
            } else {
                sendTextResponse(message, "Hogwarts house " + houseName + " does not exist!");
            }
    }

    public boolean commandShouldBeExecuted(Message message) {
        if(convertMessageToStringParameters(message).length == 2){
            return true;
        }
        return false;
    }
}
