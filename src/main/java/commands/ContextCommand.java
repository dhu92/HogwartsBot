package commands;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 4/25/2018.
 */
public abstract class ContextCommand extends Command{

    private AnswerQueue _queue;
    private int _questions;

    public ContextCommand(String name, String description){
        super(name, description);
        _queue = AnswerQueue.getInstance();
    }

    public ContextCommand(String name, String description, int questsionCount){
        this(name, description);
        _questions = questsionCount;
    }

    public int getQuestionCount(){return _questions;}

    public List<User> getUserList(){
        return _queue.getAllQueuedUsers();
    }

    public int getCurrentContextStage(User user, Command command){
        return _queue.getCurrentQuestionCount(user, command);
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

    @Override
    public String[] convertMessageToStringParameters(Message message){
        String[] parameters = super.convertMessageToStringParameters(message);
        if(parameters != null){
            if(parameters.length > 1){
                return parameters;
            }
        }
        return null;
    }
}
