package am.aca.phonebook.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Roman on 9/17/2016.
 */
public class RegullarExpressions {
    public static boolean isNameValid(String name)
    {
        return Pattern.matches("^[a-zA-Z]+",name);
    }
    public static boolean isEmailValid(String emailStr) {
        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern .matcher(emailStr);
        return matcher.find();
    }
    public static boolean numberStarts(String number) {
        return number.trim().startsWith("+374")?true:false;
    }
    public static boolean numberContains(String number) {
        return number.replaceAll(" ","").substring(4,6).startsWith("10")?true:false;
    }
}
