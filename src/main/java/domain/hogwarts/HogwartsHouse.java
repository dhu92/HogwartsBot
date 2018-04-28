package domain.hogwarts;

import domain.wizard.Wizard;

import java.util.List;

/**
 * Created by David on 4/25/2018.
 */
public class HogwartsHouse {

    private int _id;
    private String _name;
    private int _points;
    private String _pictureUrl;

    public HogwartsHouse(String name, String pictureUrl){
        _name = name;
        _points = 0;
        _pictureUrl = pictureUrl;
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

    public String getPictureUrl(){return _pictureUrl;}

    public void setPictureUrl(String url){_pictureUrl = url;}

    public void setName(String name){
        _name = name;
    }

    public String getName(){
        return _name;
    }

    public String getInfoString(){
        List<Wizard> wizards = getAllWizards();
        return "Current Points: " + _points + "\nWizards in this house:\n" + wizards.size();
    }

    public List<Wizard> getAllWizards(){
        return Wizard.getWizardRepository().getWizardsByHouse(this);

    }

    public boolean equals(HogwartsHouse house){
        return _name.equals(house.getName());
    }

}
