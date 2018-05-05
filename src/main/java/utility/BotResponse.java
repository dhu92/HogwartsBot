package utility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 4/28/2018.
 */
public class BotResponse {

    private String _text;
    private List<ArrayList<String>> _validParameters;

    private BotResponse(String text){
        _text = text;
        _validParameters = new ArrayList<ArrayList<String>>();
    }

    public static BotResponse createBotResponse(String text){
        return new BotResponse(text);
    }

    public void addValidParameters(ArrayList<String> params){
        _validParameters.add(params);
    }

    public String createResponse(String additionalInfo){
        return _text + additionalInfo;
    }

    public String createResponse(){
        return createResponse("");
    }

    private boolean isValidParameter(String param){
        for(ArrayList<String> list : _validParameters){
            for(String s : list){
                if(s.toUpperCase().equals(param.toUpperCase())){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkParameters(String[] params){
        if(needsParameter()) {
            for (int i = 1; i < params.length; i++) {
                if (!messageHasSufficientParameters(params)) {
                    return false;
                }
                if (!isValidParameter(params[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean messageHasSufficientParameters(String[] params){
        if(params.length-1 == _validParameters.size()){
            return true;
        }
        return false;
    }

    public boolean needsParameter(){
        if(_validParameters.size() > 0){
            return true;
        }
        return false;
    }
}
