package commands;

import net.dv8tion.jda.core.entities.Message;
import persistence.TriviaRepository;
import utility.PublicCommand;
import utility.Triggerable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by David on 5/5/2018.
 */
public class TriviaCommand extends PublicCommand implements Triggerable {

    public String _question;
    public List<String> _possibleAnswers;
    public int _rightAnswer;
    private int _triggerCounter;
    private int _trigger;

    public TriviaCommand() {
        super("trivia", "Answer questions to become a better wizard!");
        _possibleAnswers = new ArrayList<String>();
        _triggerCounter = 0;
        _trigger = 10;
        _currentStage = 0;
    }

    public boolean isTriggered(Message message) {
        _triggerCounter++;
        System.out.println("Current counter: " + _triggerCounter);
        if(_triggerCounter >= _trigger){
            _triggerCounter = 0;
            return true;
        }
        return false;
    }

    public void trigger(Message message) {
        System.out.println("Triggering..." + _currentStage);
        _isActive = true;
        _question = TriviaRepository.getInstance().getRandomQuestion();
        HashMap<String, Boolean> answers = TriviaRepository.getInstance().getAnswersForQuestion(_question);

        int i = 0;
        for(String answer : answers.keySet()){
            _possibleAnswers.add("["+i+"] " + answer);
            i++;
        }
        int j = 0;
        for(Boolean correct : answers.values()){
            if(correct){
                _rightAnswer = j;
            }
            j++;
        }
        performAction(message);
    }

    public void performAction(Message message) {
        System.out.println("Performing triggered command " + _currentStage);
        switch(getCurrentStage()){
            case 0:
                String answer = "Answer this question to become a better wizard!";
                for(String possibleAnswer : _possibleAnswers){
                    answer += "\n"+ possibleAnswer;
                }
                sendTextResponse(message,answer);
                break;
            case 1:
                if(isRightAnswer(convertMessageToStringParameters(message)[0])){
                    sendTextResponse(message, "That was the right answer!");
                } else {
                    sendTextResponse(message, "Wrong answer!");
                    reduceCurrentStage();
                }
                break;
            default:
                break;
        }
    }

    private boolean isRightAnswer(String answer){
        answer.trim();
        for(char c : answer.toCharArray()){
            if(Character.isDigit(c)){
                int result = Integer.parseInt(c+"");
                if(result == _rightAnswer){
                    return true;
                }
            }
        }
        return false;
    }

}
