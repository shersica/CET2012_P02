package Driver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Helper methods to help in validation of inputs and formatting in Title Case.
 */
public class Validators {

    /**
     * Validates data3 by checking if matches the required email
     * format, or latin letters (case-insensitive), digits 0 to 9 and underscores
     *
     * @param emailAdd
     * @return true if data3 matches format, else, false
     */
    public static boolean data3Valid(String emailAdd) {
        String emailRegex =
            "^(?!.*[.-]{2})(?![.-])[A-Za-z0-9_](?:[A-Za-z0-9._-]*[A-Za-z0-9_])?" +
            "@[A-Za-z0-9]+(?:-[A-Za-z0-9]+)*" +
            "(?:\\.[A-Za-z0-9]+(?:-[A-Za-z0-9]+)*)*" +
            "\\.[a-z]{2,3}$";

        // Latin letters (case-insensitive), digits 0 to 9 and underscores
        String latinDigitsUnderscores =
               "^[A-Za-z0-9_]+$";

        Pattern pattern0 = Pattern.compile(emailRegex);
        Matcher matcher0 = pattern0.matcher(emailAdd);

        Pattern pattern1 = Pattern.compile(latinDigitsUnderscores);
        Matcher matcher1 = pattern1.matcher(emailAdd);


        return matcher0.matches() || matcher1.matches();
    }

    /**
     * Checks if data3 is an email address, and returns the string in Title Case if it is not
     * @param strUnknownFormat
     * @return data3 in Title Case if it matches the format, else, return the original
     */
    public static String formatData3(String strUnknownFormat) {
        // if format matches
        if (strUnknownFormat.matches("^[A-Za-z0-9_]+$")) {
            return toTitleCase(strUnknownFormat);
        }

        // else, return original (email address)
        return strUnknownFormat;
    }

    /**
     * Helper method for formatting names to Title Case
     * @param text original text
     * @return text in Title Case
     */
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