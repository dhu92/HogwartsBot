package domain.wizards;

import com.sun.org.apache.bcel.internal.generic.AllocationInstruction;
import domain.hogwarts.Hogwarts;
import domain.hogwarts.HogwartsHouse;
import persistence.WizardRepository;

/**
 * Created by David on 4/28/2018.
 */
public class Wizard {

    private static WizardRepository _wizardRepository = new WizardRepository();

    private int _id;
    private String _name;
    private String _uid;
    private HogwartsHouse _house;
    private int _pointContribution;
    private Alignment _alignment;

    public Wizard(){
        _pointContribution = 0;
    }

    public int getId(){
        return _id;
    }

    public void setId(int id){
        _id = id;
    }

    public String getName(){
        return _name;
    }

    public void setName(String name){
        _name = name;
    }

    public String getUid(){
        return _uid;
    }

    public void setUid(String uid){
        _uid = uid;
    }

    public HogwartsHouse getHouse(){
        return _house;
    }

    public void setHouse(HogwartsHouse house){
        _house = house;
    }

    public int getPointContribution(){
        return _pointContribution;
    }

    public void setPointContribution(int pointContribution){
        _pointContribution = pointContribution;
    }

    public void addPointsToPointContribution(int points){
        _pointContribution += points;
    }

    public Alignment getAlignment(){
        return _alignment;
    }

    public void setAlignment(Alignment alignment){
        _alignment = alignment;
    }

    public String getInfoString(){
        return "Name: " + _name + "\nHogwarts house: " + _house.getName() + "\nPoint contribution: " + _pointContribution + "\nAlignment: " + _alignment.name();
    }

    public static WizardRepository getWizardRepository(){
        return _wizardRepository;
    }

    public boolean isSorted(){
        return _house.getName().equals(Hogwarts.DEFAULT_HOUSE.getName());
    }
}
