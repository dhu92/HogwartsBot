package persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by David on 5/5/2018.
 */
public class TriviaRepository {

    public List<String> _questions;
    public HashMap<String, HashMap<String, Boolean>> _answers;

    private static TriviaRepository _instance;

    private TriviaRepository(){
        _questions = new ArrayList<String>();
        _answers = new HashMap<String, HashMap<String, Boolean>>();
        loadQuestions();
    }

    public static TriviaRepository getInstance(){
        if(_instance == null){
            _instance = new TriviaRepository();
        }
        return _instance;
    }
    public String getRandomQuestion(){
        Random rand = new Random();
        return _questions.get(rand.nextInt()%_questions.size());
    }

    public HashMap<String, Boolean> getAnswersForQuestion(String question){
        return _answers.get(question);
    }

    private void loadQuestions(){
        HashMap<String, Boolean> answersQuestion1 = new HashMap<String, Boolean>();
        String question1 = "From whom does Voldemort take his first and middle names?";
        answersQuestion1.put("His father and brother", false);
        answersQuestion1.put("His father and grandfather", true);
        answersQuestion1.put("Two Hogwarts headmasters", false);
        answersQuestion1.put("His grandfather and his uncle", false);
        _questions.add(question1);
        _answers.put(question1, answersQuestion1);

        String question2 = "Lord Voldemort killed Gellert Grindelwald. True or false?";
        HashMap<String, Boolean> answersQuestion2 = new HashMap<String, Boolean>();
        answersQuestion2.put("True", true);
        answersQuestion2.put("False", false);
    }
}
