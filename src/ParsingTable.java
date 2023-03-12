import java.util.ArrayList;
import java.util.HashMap;

public class ParsingTable {
    //exp:
    // stat-list -> name ->  1-> =
    // project -> im -> 2 -> print
    HashMap<String,HashMap<String,ProductionRule>>ll1ParsingTable ;

    ArrayList<String>WordsReserved;

    public ParsingTable() {
        this.ll1ParsingTable = new HashMap<>();
        this.WordsReserved= new ArrayList<>();
        TableFilling();
        WordsReserved();
    }

    public ArrayList<String> getWordsReserved() {
        return WordsReserved;
    }

    private void WordsReserved(){
        WordsReserved.add("project");
        WordsReserved.add("const");
        WordsReserved.add("var");
        WordsReserved.add("int");
        WordsReserved.add("subroutine");
        WordsReserved.add("begin");
        WordsReserved.add("end");
        WordsReserved.add("print");
        WordsReserved.add("scan");
        WordsReserved.add("if");
        WordsReserved.add("then");
        WordsReserved.add("endif");
        WordsReserved.add("else");
        WordsReserved.add("do");
    }
    private void TableFilling() {
        HashMap<String,ProductionRule> declarationMap = new HashMap<>();
        ArrayList <String>  production = new ArrayList<>();
        production.add("project-def");
        production.add(".");
        declarationMap.put("project",new ProductionRule(1,production));
        ll1ParsingTable.put("project-declaration",declarationMap);

        HashMap<String,ProductionRule> projectdefMap = new HashMap<>();
        production = new ArrayList<>();
        production.add("project-heading");
        production.add("declarations");
        production.add("compound-stmt");
        projectdefMap.put("project",new ProductionRule(2,production));
        ll1ParsingTable.put("project-def",projectdefMap);

        HashMap<String,ProductionRule> PheadingMap = new HashMap<>();
        production = new ArrayList<>();
        production.add("project name");
        production.add(";");
        PheadingMap.put("project",new ProductionRule(3,production));
        ll1ParsingTable.put("project-heading",PheadingMap);

        HashMap<String,ProductionRule> declarationsMap= new HashMap<>();
        production = new ArrayList<>();
        production.add("const-decl");
        production.add("var-decl");
        production.add("subroutine-decl");
        declarationsMap.put("const",new ProductionRule(4,production));
        declarationsMap.put("var",new ProductionRule(4,production));
        declarationsMap.put("subroutine",new ProductionRule(4,production));
        declarationsMap.put("begin",new ProductionRule(4,production));
        ll1ParsingTable.put("declarations",declarationsMap);

        HashMap<String,ProductionRule> constdeclMap= new HashMap<>();
        production = new ArrayList<>();
        production.add("const");
        production.add("const-list");
        constdeclMap.put("const",new ProductionRule(5,production));
        constdeclMap.put("var",new ProductionRule(6,""));
        constdeclMap.put("subroutine",new ProductionRule(6,""));
        constdeclMap.put("begin",new ProductionRule(6,""));
        ll1ParsingTable.put("const-decl",constdeclMap);

        HashMap<String,ProductionRule> constlistMap= new HashMap<>();
        production = new ArrayList<>();
        production.add("const-name");
        production.add("=");
        production.add("integer-value");
        production.add(";");
        production.add("const-list");
        constlistMap.put("const-name",new ProductionRule(7,production));
        constlistMap.put("var",new ProductionRule(8,""));
        constlistMap.put("subroutine",new ProductionRule(8,""));
        constlistMap.put("begin",new ProductionRule(8,""));
        ll1ParsingTable.put("const-list",constlistMap);

        HashMap<String,ProductionRule> vardeclMap= new HashMap<>();
        production = new ArrayList<>();
        production.add("const-name");
        production.add("=");
        production.add("integer-value");
        production.add(";");
        production.add("const-list");
        vardeclMap.put("var",new ProductionRule(9,production));
        vardeclMap.put("subroutine",new ProductionRule(10,""));
        vardeclMap.put("begin",new ProductionRule(10,""));
        ll1ParsingTable.put("var-decl",vardeclMap);

        HashMap<String,ProductionRule> varlistMap= new HashMap<>();
        production = new ArrayList<>();
        production.add("var-item");
        production.add(";");
        production.add("var-list");
        varlistMap.put("var-name",new ProductionRule(11,production));
        varlistMap.put("subroutine",new ProductionRule(12,""));
        varlistMap.put("begin",new ProductionRule(12,""));
        ll1ParsingTable.put("var-list",varlistMap);

        HashMap<String,ProductionRule> varitemMap= new HashMap<>();
        production = new ArrayList<>();
        production.add("name-list");
        production.add(":");
        production.add("int");
        varitemMap.put("var-name",new ProductionRule(13,production));
        ll1ParsingTable.put("var-item",varitemMap);

        HashMap<String,ProductionRule> namelistMap= new HashMap<>();
        production = new ArrayList<>();
        production.add("var-name");
        production.add("more-names");
        namelistMap.put("var-name",new ProductionRule(14,production));
        ll1ParsingTable.put("name-list",namelistMap);

        HashMap<String,ProductionRule> morenamesMap= new HashMap<>();
        production = new ArrayList<>();
        production.add(",");
        production.add("name-list");
        morenamesMap.put(",",new ProductionRule(15,production));
        morenamesMap.put(":",new ProductionRule(16,""));
        ll1ParsingTable.put("more-names",morenamesMap);

        HashMap<String,ProductionRule> subroutinedeclMap= new HashMap<>();
        production = new ArrayList<>();
        production.add("subroutine-heading");
        production.add("declarations");
        production.add("compound-stmt");
        production.add(";");
        subroutinedeclMap.put("subroutine",new ProductionRule(17,production));
        subroutinedeclMap.put("begin",new ProductionRule(18,""));
        ll1ParsingTable.put("subroutine-decl",subroutinedeclMap);

        HashMap<String,ProductionRule> subroutineheadingMap= new HashMap<>();
        production = new ArrayList<>();
        production.add("subroutine");
        production.add("name");
        production.add(";");
        subroutineheadingMap.put("subroutine",new ProductionRule(19,production));
        ll1ParsingTable.put("subroutine-heading",subroutineheadingMap);

        HashMap<String,ProductionRule> compundstmtMap= new HashMap<>();
        production = new ArrayList<>();
        production.add("begin");
        production.add("stmt-list");
        production.add("end");
        compundstmtMap.put("begin",new ProductionRule(20,production));
        ll1ParsingTable.put("compund-stmt",compundstmtMap);

        HashMap<String,ProductionRule> stmtlistMap= new HashMap<>();
        production = new ArrayList<>();
        production.add("statement");
        production.add(";");
        production.add("stmt-list");
        stmtlistMap.put("name",new ProductionRule(21,production));
        stmtlistMap.put(";",new ProductionRule(21,production));
        stmtlistMap.put("begin",new ProductionRule(21,production));
        stmtlistMap.put("scan",new ProductionRule(21,production));
        stmtlistMap.put("print",new ProductionRule(21,production));
        stmtlistMap.put("if",new ProductionRule(21,production));
        stmtlistMap.put("endif",new ProductionRule(21,production));
        stmtlistMap.put("else",new ProductionRule(21,production));
        stmtlistMap.put("while",new ProductionRule(21,production));
        stmtlistMap.put("end",new ProductionRule(22,""));
        ll1ParsingTable.put("stmt-list",stmtlistMap);

        HashMap<String,ProductionRule> statementMap= new HashMap<>();
        statementMap.put("name",new ProductionRule(23,"ass-stmt"));
        statementMap.put("print",new ProductionRule(24,"inout-stmt"));
        statementMap.put("scan",new ProductionRule(24,"inout-stmt"));
        statementMap.put("if",new ProductionRule(25,"if-stmt"));
        statementMap.put("while",new ProductionRule(26,"while-stmt"));
        statementMap.put("begin",new ProductionRule(27,"compound-stmt"));
        statementMap.put(";",new ProductionRule(28,""));
        statementMap.put("endif",new ProductionRule(28,""));
        statementMap.put("else",new ProductionRule(28,""));
        ll1ParsingTable.put("statement",statementMap);

        HashMap<String,ProductionRule> assstmtMap= new HashMap<>();
        production = new ArrayList<>();
        production.add("name");
        production.add(":=");
        production.add("arith-exp");
        assstmtMap.put("name",new ProductionRule(29,production));
        ll1ParsingTable.put("ass-stmt",assstmtMap);

        HashMap<String,ProductionRule> arithexpMap= new HashMap<>();
        production = new ArrayList<>();
        production.add("term");
        production.add("arith-exp-prime");
        arithexpMap.put("name",new ProductionRule(30,production));
        arithexpMap.put("integer-value",new ProductionRule(30,production));
        arithexpMap.put("(",new ProductionRule(30,production));
        ll1ParsingTable.put("arith-exp",arithexpMap);

        HashMap<String,ProductionRule> arithexpprimeMap = new HashMap<>();
        production = new ArrayList<>();
        production.add("add-sign");
        production.add("term");
        production.add("arith-exp-prime");
        arithexpprimeMap.put("+",new ProductionRule(31,production));
        arithexpprimeMap.put("-",new ProductionRule(31,production));
        arithexpprimeMap.put(";",new ProductionRule(32,""));
        arithexpprimeMap.put(")",new ProductionRule(32,""));
        arithexpprimeMap.put("endif",new ProductionRule(32,""));
        arithexpprimeMap.put("else",new ProductionRule(32,""));
        ll1ParsingTable.put("arith-exp-prime ",arithexpprimeMap);

        HashMap<String,ProductionRule> termMap= new HashMap<>();
        production = new ArrayList<>();
        production.add("factor");
        production.add("term-prime");
        termMap.put("name",new ProductionRule(33,production));
        termMap.put("integer-value",new ProductionRule(33,production));
        termMap.put("(",new ProductionRule(33,production));
        ll1ParsingTable.put("term",termMap);

        HashMap<String,ProductionRule> termprimeMap= new HashMap<>();
        production = new ArrayList<>();
        production.add("mul-sign");
        production.add("factor");
        production.add("term-prime");
        termprimeMap.put("*",new ProductionRule(34,production));
        termprimeMap.put("/",new ProductionRule(34,production));
        termprimeMap.put("%",new ProductionRule(34,production));
        termprimeMap.put(";",new ProductionRule(35,""));
        termprimeMap.put(")",new ProductionRule(35,""));
        termprimeMap.put("+",new ProductionRule(35,""));
        termprimeMap.put("-",new ProductionRule(35,""));
        termprimeMap.put("endif",new ProductionRule(35,""));
        termprimeMap.put("else",new ProductionRule(35,""));
        ll1ParsingTable.put("term-prime",termprimeMap);

        HashMap<String,ProductionRule> factorMap= new HashMap<>();
        production = new ArrayList<>();
        production.add("(");
        production.add(" arith-exp");
        production.add(")");
        factorMap.put("(",new ProductionRule(36,production));
        factorMap.put("name",new ProductionRule(37,"name-value"));
        factorMap.put("integer-value",new ProductionRule(37,"name-value"));
        ll1ParsingTable.put("factor",factorMap);

        HashMap<String,ProductionRule> namevalueMap= new HashMap<>();
        namevalueMap.put("name",new ProductionRule(38,"name"));
        namevalueMap.put("integer-value",new ProductionRule(39,"integer-value"));
        ll1ParsingTable.put("name-value",namevalueMap);

        HashMap<String,ProductionRule> addsignMap= new HashMap<>();
        addsignMap.put("+",new ProductionRule(40,"+"));
        addsignMap.put("-",new ProductionRule(41,"-"));
        ll1ParsingTable.put("add-sign",addsignMap);

        HashMap<String,ProductionRule> mulsignMap= new HashMap<>();
        mulsignMap.put("*",new ProductionRule(42,"*"));
        mulsignMap.put("/",new ProductionRule(43,"/"));
        mulsignMap.put("%",new ProductionRule(44,"%"));
        ll1ParsingTable.put("mul-sign",mulsignMap);

        HashMap<String,ProductionRule> inoutstmtMap= new HashMap<>();
        production = new ArrayList<>();
        production.add("scan");
        production.add("(");
        production.add("name");
        production.add(")");
        inoutstmtMap.put("scan",new ProductionRule(45,production));
        production = new ArrayList<>();
        production.add("print");
        production.add("(");
        production.add("name-value");
        production.add(")");
        inoutstmtMap.put("print",new ProductionRule(46,production));
        ll1ParsingTable.put("inout-stmt",inoutstmtMap);

        HashMap<String,ProductionRule> ifstmtMap= new HashMap<>();
        production = new ArrayList<>();
        production.add("if");
        production.add("bool-exp");
        production.add("then");
        production.add("statement");
        production.add("else-part");
        production.add("endif");
        ifstmtMap.put("if",new ProductionRule(47,production));
        ll1ParsingTable.put("if-stmt",ifstmtMap);

        HashMap<String,ProductionRule> elsepartMap= new HashMap<>();
        production = new ArrayList<>();
        production.add("else");
        production.add("statement");
        elsepartMap.put("else",new ProductionRule(48,production));
        elsepartMap.put("endif",new ProductionRule(49,""));
        ll1ParsingTable.put("else-part",elsepartMap);

        HashMap<String,ProductionRule> whilestmtMap= new HashMap<>();
        production = new ArrayList<>();
        production.add("while");
        production.add("bool-exp");
        production.add("do");
        production.add("statement");
        whilestmtMap.put("while",new ProductionRule(50,"while bool-exp do statement"));
        ll1ParsingTable.put("while-stmt",whilestmtMap);

        HashMap<String,ProductionRule> boolexpMap= new HashMap<>();
        production = new ArrayList<>();
        production.add("name-value");
        production.add("relational-oper");
        production.add("name-value");
        boolexpMap.put("name",new ProductionRule(51,production));
        boolexpMap.put("integer-value",new ProductionRule(51,production));
        ll1ParsingTable.put("bool-exp",boolexpMap);

        HashMap<String,ProductionRule> relationaloperMap= new HashMap<>();
        relationaloperMap.put("=",new ProductionRule(52,"="));
        relationaloperMap.put("|=",new ProductionRule(53,"|="));
        relationaloperMap.put("<",new ProductionRule(54,"<"));
        relationaloperMap.put("=<",new ProductionRule(55,"=<"));
        relationaloperMap.put(">",new ProductionRule(56,">"));
        relationaloperMap.put("=>",new ProductionRule(57,"=>"));
        ll1ParsingTable.put("relational-oper",relationaloperMap);
    }


    public HashMap<String, HashMap<String, ProductionRule>> getLl1ParsingTable() {
        return ll1ParsingTable;
    }
}
