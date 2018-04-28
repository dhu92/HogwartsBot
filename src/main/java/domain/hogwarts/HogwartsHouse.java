package domain.hogwarts;

/**
 * Created by David on 4/25/2018.
 */
public class HogwartsHouse {

    private String _name;
    private int _points;

    public HogwartsHouse(String name){
        _name = name;
        _points = 0;
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
}
