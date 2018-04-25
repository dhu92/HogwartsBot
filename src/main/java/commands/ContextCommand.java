package commands;

import net.dv8tion.jda.core.entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 4/25/2018.
 */
public abstract class ContextCommand extends Command{

    private List<User> _users;

    public ContextCommand(String name) {
        super(name);
        _users = new ArrayList<User>();
    }

    public List<User> getUserList(){
        return _users;
    }

    public void setUsers(List<User> users){
        _users = users;
    }

    public void addUser(User user){
        _users.add(user);
    }

    public void removeUser(User user){
        _users.remove(user);
    }

    public boolean userAlreadyInList(User user){
        if(_users.contains(user)){
            return true;
        }
        return false;
    }
}
