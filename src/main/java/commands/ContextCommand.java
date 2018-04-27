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
    private List<String> _response;

    public ContextCommand(String name, String description){
        super(name, description);
        _queue = AnswerQueue.getInstance();
        _response = new ArrayList<String>();
    }

    public ContextCommand(String name, String description, int questionCount){
        this(name, description);
        _questions = questionCount;
    }

    public void addResponse(String response){
        _response.add(response);
    }

    public String getNextResponse(User user){
        if(getCurrentContextStage(user, this) < _response.size()) {
            return _response.get(getCurrentContextStage(user, this));
        }
        return "";
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
        return _queue.isQueuedForCommand(user, this);
    }

    public void addAnswer(User user, String answer){
        _queue.addAnswerToQueue(user, answer);
    }

    public boolean commandIsFinished(User user){
        return _queue.commandIsFinished(user, getCurrentContextStage(user, this));
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

    public abstract void setResponses();
}
