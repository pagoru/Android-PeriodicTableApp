package es.pagoru.periodictableapp;

/**
 * Created by Pablo on 06/11/2016.
 */

public class Element {

    private String Number;
    private String Name;
    private String Symbol;

    private String Category;
    private String Group;
    private String Period;
    private String Block;
    private String Weight;

    public Element(String number, String name, String symbol, String category, String group, String period, String block, String weight) {
        Number = number;
        Name = name;
        Symbol = symbol;
        Category = category;
        Group = group;
        Period = period;
        Block = block;
        Weight = weight;
    }

    public String getNumber(){
        return Number;
    }

    public String getName() {
        return Name;
    }

    public String getSymbol() {
        return Symbol;
    }

    public String getCategory() {
        return Category;
    }

    public String getGroup() {
        return Group;
    }

    public String getPeriod() {
        return Period;
    }

    public String getBlock() {
        return Block;
    }

    public String getWeight() {
        return Weight;
    }
}
