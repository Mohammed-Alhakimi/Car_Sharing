import java.util.*;

class BankCard {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String numbers = scn.nextLine();
        String card = numbers.replaceAll("\\s", "");
        String visaRegex = "^4\\d{15}$";
        String rangeOf51To55Regex = "^5[1-5]\\d{14}$";
        String rangeOf2221To2229 = "^222[1-9]\\d{12}$";
        String rangeOf2230To2299 = "^22[3-9][0-9]\\d{12}$";
        String rangeOf2300To2699 = "^22[3-6][0-9]\\d{12}$";
        String rangeOf2700To2719 = "^27[0-1][0-9]\\d{12}$";
        String rangeOf2720 = "^2720\\d{12}$";
        String masterCardRegex = rangeOf51To55Regex + "|" +
                rangeOf2720 + "|" +
                rangeOf2700To2719 + "|" +
                rangeOf2300To2699 + "|" +
                rangeOf2230To2299 + "|" +
                rangeOf2221To2229;
        String americanExpressRegex = "^3[4-7]\\d{13}$";

        if (card.matches(visaRegex)) {
            System.out.println("Visa");
        } else if (card.matches(masterCardRegex)) {
            System.out.println("MasterCard");
        } else if (card.matches(americanExpressRegex)) {
            System.out.println("AmericanExpress");
        } else {
            System.out.println("Card number does not exist");
        }
    }
}