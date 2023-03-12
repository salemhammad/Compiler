import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        ParsingTable table=  new ParsingTable();
        HashMap<String, HashMap<String,ProductionRule>>ll1ParsingTable ;
        ll1ParsingTable=table.getLl1ParsingTable();
        ll1ParsingTable.size();
    }
}