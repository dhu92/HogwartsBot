package domain.hogwarts;

import domain.wizard.Wizard;
import persistence.WizardRepository;

import java.util.List;

/**
 * Created by David on 4/25/2018.
 */
public class HogwartsHouse {

    private int _id;
    private String _name;
    private int _points;

    public HogwartsHouse(String name){
        _name = name;
        _points = 0;
    }

    public int getId(){
        return _id;
    }

    public void setId(int id){
        _id = id;
    }
    public void raisePoints(int value){
        _points += value;
    }

    public void takePoints(int value){
        _points -= value;
    }

    public int getPoints(){
        return _points;
    }

    public void setPoints(int value){
        _points = value;
    }

    public void setName(String name){
        _name = name;
    }

    public String getName(){
        return _name;
    }

    public String getInfoString(){
        StringBuilder sb = new StringBuilder();
        for(Wizard wizard : getAllWizards()){
            sb.append(wizard.getName() + "|");
        }
        return "Name: " + _name + "\nCurrent Points: " + _points + "\nWizards in this house: " + sb.toString();
    }

    public List<Wizard> getAllWizards(){
        return Wizard.getWizardRepository().getWizardsByHouse(this);

    }

    public boolean equals(HogwartsHouse house){
        return _name.equals(house.getName());
    }

}
