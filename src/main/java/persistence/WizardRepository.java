package persistence;

import domain.wizards.Wizard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 4/28/2018.
 */
public class WizardRepository {

    private List<Wizard> _wizards;

    public WizardRepository(){
        _wizards = new ArrayList<Wizard>();
    }

    public void save(Wizard wizard){
        _wizards.add(wizard);
    }

    public Wizard getWizardByUid(String uid){
        for(Wizard wizard : _wizards){
            if(wizard.getUid().equals(uid)){
                return wizard;
            }
        }
        return null;
    }

    public List<Wizard> getAllWizards(){
        return _wizards;
    }

    public boolean wizardExists(String uid){
        if(getWizardByUid(uid) != null){
            return true;
        }
        return false;
    }
}
