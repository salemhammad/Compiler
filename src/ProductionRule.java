import java.util.ArrayList;

public class ProductionRule {

    private int productionNumber;
    private ArrayList<String> productionValue;
    private  String lambda;

    public ProductionRule(int productionNumber, String lambda) {
        this.productionNumber = productionNumber;
        this.lambda = lambda;
    }

    public ProductionRule() {
    }

    public ArrayList<String> getProductionValue() {
        return productionValue;
    }

    public void setProductionValue(ArrayList<String> productionValue) {
        this.productionValue = productionValue;
    }

    public ProductionRule(int productionNumber, ArrayList<String> productionValue) {
        this.productionNumber = productionNumber;
        this.productionValue = productionValue;
    }

    public int getProductionNumber() {
        return productionNumber;
    }

    public void setProductionNumber(int productionNumber) {
        this.productionNumber = productionNumber;
    }


}
