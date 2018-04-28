package commands;

import net.dv8tion.jda.core.entities.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by David on 4/25/2018.
 */
public class AnswerQueue {

    private HashMap<User, CommandEntry> _waiting;
    private static AnswerQueue _instance;

    private AnswerQueue(){
        _waiting = new HashMap<User, CommandEntry>();
    }

    public static AnswerQueue getInstance(){
        if(_instance == null){
            _instance = new AnswerQueue();
        }
        return _instance;
    }

    public boolean isInList(User user){
        if(_waiting.get(user) != null){
            return true;
        }
        return false;
    }

    public boolean addUserToList(User user, Command command){
        if(_waiting.get(user) == null) {
            _waiting.put(user, new CommandEntry(command));
            return true;
        }
        return false;
    }

    public void removeUser(User user){
        _waiting.put(user, null);
    }

    public List<User> getAllQueuedUsers(){
        List<User> users = new ArrayList<User>();
        for(User user : _waiting.keySet()){
            if(isInList(user)){
                if(!users.contains(user)) {
                    users.add(user);
                }
            }
        }
        return users;
    }

    public boolean isQueuedForCommand(User user, Command command){
        if(isInList(user)){
            if(_waiting.get(user).getCommand().equals(command)){
                return true;
            }
        }
        return false;
    }

    public List<User> getAllUsersByCommand(Command command){
        List<User> users = new ArrayList<User>();
        for(User user : _waiting.keySet()){
            if(_waiting.get(user).getCommand().equals(command)){
                users.add(user);
            }
        }
        return users;
    }

    public int getCurrentQuestionCount(User user, Command command){
        if(isInList(user)){
            if(_waiting.get(user).getCommand().equals(command)){
                return _waiting.get(user).getStage();
            }
        }
        return 0;
    }

    public void addAnswerToQueue(User user, String answer){
        if(isInList(user)){
            _waiting.get(user).addAnswer(answer);
        }
    }

    public boolean commandIsFinished(User user, int stage){
        if(isInList(user)){
            if(_waiting.get(user).getStage() > stage){
                return true;
            }
        }
        return false;
    }


    private class CommandEntry{

        private List<String> _previousAnswers;
        private Command _command;

        public CommandEntry(Command command){
            _previousAnswers = new ArrayList<String>();
            _command = command;
        }

        public void addAnswer(String answer){
            _previousAnswers.add(answer);
        }

        public int getStage(){
            return _previousAnswers.size();
        }

        public Command getCommand(){
            return _command;
        }
    }

}
