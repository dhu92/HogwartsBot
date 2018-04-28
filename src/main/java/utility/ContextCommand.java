package utility;

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
    private List<BotResponse> _response;

    public ContextCommand(String name, String description){
        super(name, description);
        _queue = AnswerQueue.getInstance();
        _response = new ArrayList<BotResponse>();
        setResponses();
    }

    public ContextCommand(String name, String description, int questionCount){
        this(name, description);
        _questions = questionCount;
    }

    public void addResponse(BotResponse response){
        _response.add(response);
    }

    public BotResponse getNextResponse(User user){
        if(getCurrentContextStage(user, this) < _response.size()) {
            return _response.get(getCurrentContextStage(user, this));
        }
        return new BotResponse("Something went wrong");
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
        if(_response.size() <= getCurrentContextStage(user, this)){
            return true;
        }
        return _queue.commandIsFinished(user, getCurrentContextStage(user, this));
    }

    public List<User> getAllQueuedUsers(){
        return _queue.getAllUsersByCommand(this);
    }

    public void execute(Message message) {
        User user = message.getAuthor();
        String[] params = convertMessageToStringParameters(message);
        if(!userAlreadyInList(user)){
            addUser(user);
        }
        String botAnswer;
        BotResponse response = getNextResponse(user);
        if(response.checkParameters(params)){
            botAnswer = buildBotAnswerForCurrentStage(user, params);
            addAnswer(user, buildUserAnswerString(params));

            //System.out.println("New stage: " + getCurrentContextStage(user, this));
        } else {
            botAnswer = ERROR_MESSAGE;
        }
        sendTextResponse(message, botAnswer);
        if(commandIsFinished(user)){
            //System.out.println("Command finished");
            removeUser(user);
        }
    }

    public abstract String buildBotAnswerForCurrentStage(User user, String[] params);
    public abstract String buildUserAnswerString(String[] params);
    public abstract void setResponses();
}
