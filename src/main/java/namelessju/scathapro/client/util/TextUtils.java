package namelessju.scathapro.client.util;

import java.text.DecimalFormat;

public class TextUtils {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,##0.00");
    private static final DecimalFormat INT_FORMAT = new DecimalFormat("#,##0");

    public static String numberToString(double number) {
        if (number >= 1_000_000) {
            return String.format("%.2fM", number / 1_000_000);
        } else if (number >= 1_000) {
            return String.format("%.2fK", number / 1_000);
        }
        return DECIMAL_FORMAT.format(number);
    }

    public static String numberToString(int number) {
        if (number >= 1_000_000) {
            return (number / 1_000_000) + "M";
        } else if (number >= 1_000) {
            return (number / 1_000) + "K";
        }
        return INT_FORMAT.format(number);
    }

    public static String capitalize(String text) {
        if (text == null || text.length() == 0) return text;
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }

    public static String removeColorCodes(String text) {
        return text.replaceAll("§[0-9a-fk-or]", "");
    }
}
