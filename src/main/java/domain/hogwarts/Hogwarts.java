package domain.hogwarts;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by David on 4/25/2018.
 */
public class Hogwarts {

    public static HogwartsHouse DEFAULT_HOUSE = new HogwartsHouse("You are not sorted yet! Use h!sort to get sorted into your house!", "https://image.freepik.com/free-icon/question-mark_318-52837.jpg");
    private static Hogwarts _instance;
    private List<HogwartsHouse> _houses;

    private Hogwarts(){

        _houses = new ArrayList<HogwartsHouse>();
        //TODO: DB
        _houses.add(new HogwartsHouse("Ravenclaw", "https://vignette.wikia.nocookie.net/harrypotter/images/2/29/0.41_Ravenclaw_Crest_Transparent.png/revision/latest?cb=20161020182442"));
        _houses.add(new HogwartsHouse("Slytherin", "https://vignette.wikia.nocookie.net/harrypotter/images/d/d3/0.61_Slytherin_Crest_Transparent.png/revision/latest?cb=20161020182557"));
        _houses.add(new HogwartsHouse("Hufflepuff", "https://vignette.wikia.nocookie.net/harrypotter/images/5/50/0.51_Hufflepuff_Crest_Transparent.png/revision/latest?cb=20161020182518"));
        _houses.add(new HogwartsHouse("Gryffindor", "https://vignette.wikia.nocookie.net/harrypotter/images/8/8e/0.31_Gryffindor_Crest_Transparent.png/revision/latest?cb=20161124074004"));
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

    public boolean houseExists(String name){
        for(HogwartsHouse house : _houses){
            if(house.getName().toLowerCase().equals(name.toLowerCase())){
                return true;
            }
        }
        return false;
    }

    public void addHogwartsHouse(HogwartsHouse house){
        _houses.add(house);
    }
}
