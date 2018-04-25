package commands;

import net.dv8tion.jda.core.entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 4/25/2018.
 */
public abstract class ContextCommand extends Command{

    private AnswerQueue _queue;

    public ContextCommand(String name, String description){
        super(name, description);
        _queue = AnswerQueue.getInstance();
    }

    public List<User> getUserList(){
        return _queue.getAllQueuedUsers();
    }

    public void setUsers(AnswerQueue queue){
        _queue = queue;
    }

    public void addUser(User user){
        _queue.addUserToList(user, this);
    }

    public void removeUser(User user){
        _queue.removeUser(user);
    }

    public boolean userAlreadyInList(User user){
        if(_queue.isQueuedForCommand(user, this)){
            return true;
        }
        return false;
    }

    public List<User> getAllQueuedUsers(){
        return _queue.getAllUsersByCommand(this);
    }
}
