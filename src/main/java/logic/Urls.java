package logic;

import java.security.InvalidParameterException;

public class Urls {

    public  Urls() {

    }

    public String createFullUrlString(String partialUrl)
                  throws InvalidParameterException{
        return "/Fitness-1.0-SNAPSHOT/jsp" + partialUrl + ".jsp";
    }


}
