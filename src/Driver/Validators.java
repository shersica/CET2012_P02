package Driver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validators {

    /**
     * helper method for validating emails
     * @param emailAdd
     * @return true if email address matches format, else, false
     */
    public static boolean emailValid(String emailAdd) {
        String emailRegex =
            "^(?!.*[.-]{2})(?![.-])[A-Za-z0-9_](?:[A-Za-z0-9._-]*[A-Za-z0-9_])?" +
            "@[A-Za-z0-9]+(?:-[A-Za-z0-9]+)*" +
            "(?:\\.[A-Za-z0-9]+(?:-[A-Za-z0-9]+)*)*" +
            "\\.[a-z]{2,3}$";

        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(emailAdd);

        return matcher.matches() || (emailAdd.equals("Email"));
    }

    /**
     *
     * @param name in the form of Firstname or Lastname
     * @return true if name is consists only of alphabets, else, false
     */
// not used
//    public static boolean nameValid(String name) {
//        String alphaCharsRegex = "^[a-zA-Z]+$";
//
//        Pattern pattern = Pattern.compile(alphaCharsRegex);
//        Matcher matcher = pattern.matcher(name);
//
//        return matcher.matches();
//    }

    public static String toTitleCase(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }

        StringBuilder converted = new StringBuilder();

        boolean convertNext = true;
        for (char ch : text.toCharArray()) {
            if (Character.isSpaceChar(ch)) {
                convertNext = true;
            } else if (convertNext) {
                ch = Character.toTitleCase(ch);
                convertNext = false;
            } else {
                ch = Character.toLowerCase(ch);
            }
            converted.append(ch);
        }

        return converted.toString();
    }

}
