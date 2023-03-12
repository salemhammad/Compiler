import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TokenScanner {
    public static List<String> tokenizeCode(String code) {
        List<String> tokens = new ArrayList<>();
        Pattern pattern = Pattern.compile("[\\w]+|[^\\w\\s]+");
        Matcher matcher = pattern.matcher(code);
        while (matcher.find()) {
            tokens.add(matcher.group());
        }
        return tokens;
    }

    public static void main(String[] args) {
        //Enter the code
        String input = "1x=10";
        System.out.println(tokenizeCode(input));
        parsing(tokenizeCode(input));
    }

    public static void parsing(List<String> listInputCode) {
        ParsingTable ParsingTable = new ParsingTable();
        HashMap<String,HashMap<String,ProductionRule>>ll1ParsingTable ;
        ArrayList<String> WordsReserved;
        ll1ParsingTable = ParsingTable.getLl1ParsingTable();
        WordsReserved = ParsingTable.getWordsReserved();

        Stack<String> VNTerminals = new Stack<>();
        VNTerminals.push("project-declaration ");


        int n = 0;
        while (!VNTerminals.empty()) {
            if (VNTerminals.peek().equals(listInputCode.get(n))) {
                VNTerminals.pop();
                n++;
            } else {
                if (ll1ParsingTable.get(VNTerminals.peek()).containsKey(listInputCode.get(n))) {
                    int save = ll1ParsingTable.get(VNTerminals.peek()).get(listInputCode.get(n)).getProductionNumber();
                    VNTerminals.pop();
                    ProductionRule productionRule = new ProductionRule();
                    ArrayList<String> listparsing = ll1ParsingTable.get(VNTerminals.peek()).get(productionRule.getProductionValue()).getProductionValue();
                    for (int x = 0; x < listparsing.size(); x++) {
                        VNTerminals.push(listparsing.get(x - 1));
                    }
                } else if (VNTerminals.peek().equals("integer-value") && isNumeric(listInputCode.get(n))) {
                    VNTerminals.pop();
                    n++;

                } else if (!VNTerminals.peek().equals("integer-value") && isNumeric(listInputCode.get(n))) {


                    if (ll1ParsingTable.get(VNTerminals.peek()).containsKey("integer-value")) {
                        int save = ll1ParsingTable.get(VNTerminals.peek()).get("integer-value").getProductionNumber();
                        VNTerminals.pop();
                        ProductionRule productionRule = new ProductionRule();
                        ArrayList<String> listparsing = ll1ParsingTable.get(VNTerminals.peek()).get(productionRule.getProductionValue()).getProductionValue();
                        for (int x = 0; x < listparsing.size(); x++) {
                            VNTerminals.push(listparsing.get(x - 1));
                        }
                    }

                } else if (VNTerminals.peek().equals("const-name") || VNTerminals.peek().equals("var-name") || VNTerminals.peek().equals("name")) {
                    if (WordsReserved.contains(listInputCode.get(n))) {
                        VNTerminals.pop();
                        n++;
                    }
                    if (isValidUsername(listInputCode.get(n))) {
                        VNTerminals.pop();
                        n++;
                    }

                } else if (!VNTerminals.peek().equals("const-name") && !VNTerminals.peek().equals("var-name") && !VNTerminals.peek().equals("name")) {
                    if (ll1ParsingTable.get(VNTerminals.peek()).containsKey("const-name")) {
                        int save = ll1ParsingTable.get(VNTerminals.peek()).get("const-name").getProductionNumber();
                        VNTerminals.pop();
                        ProductionRule productionRule = new ProductionRule();
                        ArrayList<String> listparsing = ll1ParsingTable.get(VNTerminals.peek()).get(productionRule.getProductionValue()).getProductionValue();
                        for (int x = 0; x < listparsing.size(); x++) {
                            VNTerminals.push(listparsing.get(x - 1));
                        }
                    }

                    if (ll1ParsingTable.get(VNTerminals.peek()).containsKey("var-name")) {
                        int save = ll1ParsingTable.get(VNTerminals.peek()).get("var-name").getProductionNumber();
                        VNTerminals.pop();
                        ProductionRule productionRule = new ProductionRule();
                        ArrayList<String> listparsing = ll1ParsingTable.get(VNTerminals.peek()).get(productionRule.getProductionValue()).getProductionValue();
                        for (int x = 0; x < listparsing.size(); x++) {
                            VNTerminals.push(listparsing.get(x - 1));
                        }
                    }
                    if (ll1ParsingTable.get(VNTerminals.peek()).containsKey("name")) {
                        int save = ll1ParsingTable.get(VNTerminals.peek()).get("name").getProductionNumber();
                        VNTerminals.pop();
                        ProductionRule productionRule = new ProductionRule();
                        ArrayList<String> listparsing = ll1ParsingTable.get(VNTerminals.peek()).get(productionRule.getProductionValue()).getProductionValue();
                        for (int x = 0; x < listparsing.size(); x++) {
                            VNTerminals.push(listparsing.get(x - 1));
                        }
                    }
                } else
                    System.out.println("error");

            }


        }


    }


    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isValidUsername(String namex) {
        String reg = "^[A-Za-z]\\w{1,29}$";
        Pattern pattern = Pattern.compile(reg);
        if (namex == null) {
            return false;
        }
        Matcher mathing= pattern.matcher(namex);
        return mathing.matches();
    }
}
