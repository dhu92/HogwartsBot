package utility;

import domain.wizard.Wizard;
import net.dv8tion.jda.core.entities.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 5/5/2018.
 */
public abstract class PublicCommand extends Command {

    List<BotResponse> _stages;
    protected int _currentStage;
    protected boolean _isActive;

    public PublicCommand(String name, String description) {
        super(name, description);
        _stages = new ArrayList<BotResponse>();
        _currentStage = 0;
        _isActive = false;
    }

    public abstract void performAction(Message message);

    public boolean commandShouldBeExecuted(Message message){
        if(_isActive) {
            if (Wizard.getWizardRepository().wizardExists(message.getAuthor().getId())) {
                if (_currentStage < _stages.size()) {
                    if (_stages.get(_currentStage).needsParameter()) {
                        if (_stages.get(_currentStage).checkParameters(convertMessageToStringParameters(message))) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void after(Message message){
        _currentStage++;
        if(_currentStage >= _stages.size()){
            _isActive = false;
        }
    }

    public void addStage(BotResponse response){
        _stages.add(response);
    }

    public void reduceCurrentStage(){
        _currentStage--;
    }

    public int getCurrentStage(){
        return _currentStage;
    }
}
