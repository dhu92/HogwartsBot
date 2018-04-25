package commands;

import net.dv8tion.jda.core.entities.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by David on 4/25/2018.
 */
public class AnswerQueue {

    private HashMap<User, Command> _waiting;
    private static AnswerQueue _instance;

    private AnswerQueue(){
        _waiting = new HashMap<User, Command>();
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
            _waiting.put(user, command);
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
            if(_waiting.get(user) != null){
                if(!users.contains(user)) {
                    users.add(user);
                }
            }
        }
        return users;
    }

    public boolean isQueuedForCommand(User user, Command command){
        if(_waiting.get(user) != null){
            if(_waiting.get(user).equals(command)){
                return true;
            }
        }
        return false;
    }

    public List<User> getAllUsersByCommand(Command command){
        List<User> users = new ArrayList<User>();
        for(User user : _waiting.keySet()){
            if(_waiting.get(user).equals(command)){
                users.add(user);
            }
        }
        return users;
    }

}
