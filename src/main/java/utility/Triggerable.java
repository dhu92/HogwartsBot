package utility;

import net.dv8tion.jda.core.entities.Message;

/**
 * Created by David on 5/5/2018.
 */
public interface Triggerable {

    public boolean isTriggered(Message message);
    public void trigger(Message message);
}
