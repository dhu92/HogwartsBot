package hogwarts;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by David on 4/25/2018.
 */
public class Hogwarts {

    private static Hogwarts _instance;
    private List<HogwartsHouse> _houses;

    private Hogwarts(){
        _houses = new ArrayList<HogwartsHouse>();
        //TODO: DB
        _houses.add(new HogwartsHouse("Ravenclaw"));
        _houses.add(new HogwartsHouse("Slytherin"));
        _houses.add(new HogwartsHouse("Hufflepuff"));
        _houses.add(new HogwartsHouse("Gryffindor"));
    }

    public static Hogwarts getInstance(){
        if(_instance == null){
            _instance = new Hogwarts();
        }
        return _instance;
    }

    public HogwartsHouse sortToHouse(){
        Random rand = new Random();
        return _houses.get(rand.nextInt(_houses.size()));
    }

    public HogwartsHouse getHouseByName(String name){
        for(HogwartsHouse house : _houses){
            if(house.getName().toUpperCase().equals(name.toUpperCase())){
                return house;
            }
        }
        return null;
    }

    public List<HogwartsHouse> getHogwartsHouseList(){
        return _houses;
    }

    public void addHogwartsHouse(HogwartsHouse house){
        _houses.add(house);
    }
}
