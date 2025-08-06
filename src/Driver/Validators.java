package Driver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validators {

    /**
     * helper method for validating emails
     * @param emailAdd
     * @return true if email address matches format, else, false
     */
    public static boolean data3Valid(String emailAdd) {
        String emailRegex =
            "^(?!.*[.-]{2})(?![.-])[A-Za-z0-9_](?:[A-Za-z0-9._-]*[A-Za-z0-9_])?" +
            "@[A-Za-z0-9]+(?:-[A-Za-z0-9]+)*" +
            "(?:\\.[A-Za-z0-9]+(?:-[A-Za-z0-9]+)*)*" +
            "\\.[a-z]{2,3}$";

        // Latin letters (case insensitive), digits 0 to 9 and underscores
        String latinDigitsUnderscores =
               "^[A-Za-z0-9_]+$";

        Pattern pattern0 = Pattern.compile(emailRegex);
        Matcher matcher0 = pattern0.matcher(emailAdd);

        Pattern pattern1 = Pattern.compile(latinDigitsUnderscores);
        Matcher matcher1 = pattern1.matcher(emailAdd);


        return matcher0.matches() || matcher1.matches();
    }

    /**
     *
     * name in the form of Firstname or Lastname
     * return true if name is consists only of alphabets, else, false
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
