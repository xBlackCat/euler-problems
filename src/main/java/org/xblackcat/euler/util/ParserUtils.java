package org.xblackcat.euler.util;


import java.util.function.Function;

/**
 * Copied from SJPU Settings provider project
 */
public final class ParserUtils {
    private ParserUtils() {
    }

    @SuppressWarnings({"unchecked"})
    public static Function<String, ?> getToObjectConverter(Class<?> targetType) {
        if (String.class.equals(targetType)) {
            return valueStr -> valueStr;
        } else if (Integer.class.equals(targetType)) {
            return valueStr -> isBlank(valueStr) ? null : Integer.parseInt(valueStr);
        } else if (int.class.equals(targetType)) {
            return Integer::parseInt;
        } else if (Long.class.equals(targetType)) {
            return valueStr -> isBlank(valueStr) ? null : Long.parseLong(valueStr);
        } else if (long.class.equals(targetType)) {
            return Long::parseLong;
        } else if (Short.class.equals(targetType)) {
            return valueStr -> isBlank(valueStr) ? null : Short.parseShort(valueStr);
        } else if (short.class.equals(targetType)) {
            return Short::parseShort;
        } else if (Byte.class.equals(targetType)) {
            return valueStr -> isBlank(valueStr) ? null : Byte.parseByte(valueStr);
        } else if (byte.class.equals(targetType)) {
            return Byte::parseByte;
        } else if (Boolean.class.equals(targetType)) {
            return valueStr -> isBlank(valueStr) ? null : toBoolean(valueStr);
        } else if (boolean.class.equals(targetType)) {
            return ParserUtils::toBoolean;
        } else if (Character.class.equals(targetType)) {
            return valueStr -> isBlank(valueStr) ? null : valueStr.toCharArray()[0];
        } else if (char.class.equals(targetType)) {
            return valueStr -> valueStr.toCharArray()[0];
        } else if (Enum.class.isAssignableFrom(targetType)) {
            return valueStr -> isBlank(valueStr) ? null : searchForEnum((Class<Enum>) targetType, valueStr);
        } else {
            throw new IllegalArgumentException("Unknown type to parse: " + targetType.getName());
        }
    }

    private static boolean isBlank(String valueStr) {
        if (valueStr == null || valueStr.length() == 0) {
            return true;
        }
        for (int i = 0; i < valueStr.length(); i++) {
            if (!Character.isWhitespace(valueStr.codePointAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static <T extends Enum<T>> T searchForEnum(Class<T> clazz, String name) throws IllegalArgumentException {
        try {
            return Enum.valueOf(clazz, name);
        } catch (IllegalArgumentException e) {
            // Try to search case-insensitive
            for (T c : clazz.getEnumConstants()) {
                if (name.equalsIgnoreCase(c.name())) {
                    return c;
                }
            }

            throw e;
        }
    }

    // String to Boolean methods
    //-----------------------------------------------------------------------

    /**
     * <p>Converts a String to a Boolean.</p>
     * <p>
     * <p>{@code 'true'}, {@code 'on'}, {@code 'y'}, {@code 't'} or {@code 'yes'}
     * (case insensitive) will return {@code true}.
     * {@code 'false'}, {@code 'off'}, {@code 'n'}, {@code 'f'} or {@code 'no'}
     * (case insensitive) will return {@code false}.
     * Otherwise, {@code null} is returned.</p>
     * <p>
     * <p>NOTE: This returns null and will throw a NullPointerException if unboxed to a boolean. </p>
     * <p>
     * <pre>
     *   // N.B. case is not significant
     *   BooleanUtils.toBooleanObject(null)    = null
     *   BooleanUtils.toBooleanObject("true")  = Boolean.TRUE
     *   BooleanUtils.toBooleanObject("T")     = Boolean.TRUE // i.e. T[RUE]
     *   BooleanUtils.toBooleanObject("false") = Boolean.FALSE
     *   BooleanUtils.toBooleanObject("f")     = Boolean.FALSE // i.e. f[alse]
     *   BooleanUtils.toBooleanObject("No")    = Boolean.FALSE
     *   BooleanUtils.toBooleanObject("n")     = Boolean.FALSE // i.e. n[o]
     *   BooleanUtils.toBooleanObject("on")    = Boolean.TRUE
     *   BooleanUtils.toBooleanObject("ON")    = Boolean.TRUE
     *   BooleanUtils.toBooleanObject("off")   = Boolean.FALSE
     *   BooleanUtils.toBooleanObject("oFf")   = Boolean.FALSE
     *   BooleanUtils.toBooleanObject("yes")   = Boolean.TRUE
     *   BooleanUtils.toBooleanObject("Y")     = Boolean.TRUE // i.e. Y[ES]
     *   BooleanUtils.toBooleanObject("blue")  = null
     *   BooleanUtils.toBooleanObject("true ") = null // trailing space (too long)
     *   BooleanUtils.toBooleanObject("ono")   = null // does not match on or no
     * </pre>
     *
     * @param str the String to check; upper and lower case are treated as the same
     * @return the Boolean value of the string, {@code null} if no match or {@code null} input
     */
    public static Boolean toBooleanObject(final String str) {
        // Previously used equalsIgnoreCase, which was fast for interned 'true'.
        // Non interned 'true' matched 15 times slower.
        //
        // Optimisation provides same performance as before for interned 'true'.
        // Similar performance for null, 'false', and other strings not length 2/3/4.
        // 'true'/'TRUE' match 4 times slower, 'tRUE'/'True' 7 times slower.
        if (str == "true") {
            return Boolean.TRUE;
        }
        if (str == null) {
            return null;
        }
        switch (str.length()) {
            case 1: {
                final char ch0 = str.charAt(0);
                if (ch0 == 'y' || ch0 == 'Y' ||
                        ch0 == 't' || ch0 == 'T') {
                    return Boolean.TRUE;
                }
                if (ch0 == 'n' || ch0 == 'N' ||
                        ch0 == 'f' || ch0 == 'F') {
                    return Boolean.FALSE;
                }
                break;
            }
            case 2: {
                final char ch0 = str.charAt(0);
                final char ch1 = str.charAt(1);
                if ((ch0 == 'o' || ch0 == 'O') &&
                        (ch1 == 'n' || ch1 == 'N')) {
                    return Boolean.TRUE;
                }
                if ((ch0 == 'n' || ch0 == 'N') &&
                        (ch1 == 'o' || ch1 == 'O')) {
                    return Boolean.FALSE;
                }
                break;
            }
            case 3: {
                final char ch0 = str.charAt(0);
                final char ch1 = str.charAt(1);
                final char ch2 = str.charAt(2);
                if ((ch0 == 'y' || ch0 == 'Y') &&
                        (ch1 == 'e' || ch1 == 'E') &&
                        (ch2 == 's' || ch2 == 'S')) {
                    return Boolean.TRUE;
                }
                if ((ch0 == 'o' || ch0 == 'O') &&
                        (ch1 == 'f' || ch1 == 'F') &&
                        (ch2 == 'f' || ch2 == 'F')) {
                    return Boolean.FALSE;
                }
                break;
            }
            case 4: {
                final char ch0 = str.charAt(0);
                final char ch1 = str.charAt(1);
                final char ch2 = str.charAt(2);
                final char ch3 = str.charAt(3);
                if ((ch0 == 't' || ch0 == 'T') &&
                        (ch1 == 'r' || ch1 == 'R') &&
                        (ch2 == 'u' || ch2 == 'U') &&
                        (ch3 == 'e' || ch3 == 'E')) {
                    return Boolean.TRUE;
                }
                break;
            }
            case 5: {
                final char ch0 = str.charAt(0);
                final char ch1 = str.charAt(1);
                final char ch2 = str.charAt(2);
                final char ch3 = str.charAt(3);
                final char ch4 = str.charAt(4);
                if ((ch0 == 'f' || ch0 == 'F') &&
                        (ch1 == 'a' || ch1 == 'A') &&
                        (ch2 == 'l' || ch2 == 'L') &&
                        (ch3 == 's' || ch3 == 'S') &&
                        (ch4 == 'e' || ch4 == 'E')) {
                    return Boolean.FALSE;
                }
                break;
            }
            default:
                break;
        }

        return null;
    }

    // String to boolean methods
    //-----------------------------------------------------------------------

    /**
     * <p>Converts a String to a boolean (optimised for performance).</p>
     * <p>
     * <p>{@code 'true'}, {@code 'on'}, {@code 'y'}, {@code 't'} or {@code 'yes'}
     * (case insensitive) will return {@code true}. Otherwise,
     * {@code false} is returned.</p>
     * <p>
     * <p>This method performs 4 times faster (JDK1.4) than
     * {@code Boolean.valueOf(String)}. However, this method accepts
     * 'on' and 'yes', 't', 'y' as true values.
     * <p>
     * <pre>
     *   BooleanUtils.toBoolean(null)    = false
     *   BooleanUtils.toBoolean("true")  = true
     *   BooleanUtils.toBoolean("TRUE")  = true
     *   BooleanUtils.toBoolean("tRUe")  = true
     *   BooleanUtils.toBoolean("on")    = true
     *   BooleanUtils.toBoolean("yes")   = true
     *   BooleanUtils.toBoolean("false") = false
     *   BooleanUtils.toBoolean("x gti") = false
     *   BooleanUtils.toBooleanObject("y") = true
     *   BooleanUtils.toBooleanObject("n") = false
     *   BooleanUtils.toBooleanObject("t") = true
     *   BooleanUtils.toBooleanObject("f") = false
     * </pre>
     *
     * @param str the String to check
     * @return the boolean value of the string, {@code false} if no match or the String is null
     */
    public static boolean toBoolean(final String str) {
        return toBooleanObject(str) == Boolean.TRUE;
    }
}
