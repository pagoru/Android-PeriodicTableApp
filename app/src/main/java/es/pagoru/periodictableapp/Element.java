package es.pagoru.periodictableapp;

import android.content.res.Resources;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Pablo on 06/11/2016.
 */

public class Element {

    private static List<Element> elements = new ArrayList<>();

    public static List<Element> getElements(){
        return elements;
    }

    public static Element getElement(String name){
        //Lambda power :(
        for (Element element :
                getElements()) {
            if(element.getName().equalsIgnoreCase(name)){
                return element;
            }
        }
        return null;
    }

    public static void loadElements(Resources resources){
        try {
            JSONArray arr = new JSONArray(convertStreamToString(resources.openRawResource(R.raw.elements)));

            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                elements.add(new Element(
                        obj.getString("Number"),
                        obj.getString("Name"),
                        obj.getString("Symbol"),
                        obj.getString("Category"),
                        obj.getString("Group"),
                        obj.getString("Period"),
                        obj.getString("Block"),
                        obj.getString("Weight"))
                );
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static String convertStreamToString(InputStream is) {
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

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

    public int getColor(){
        switch (getCategory()){
            case "No metalls":
                return R.color.element_OtrosNoMetales;
            case "Gasos nobles":
                return R.color.element_GasesNobles;
            case "Metalls alcalins":
                return R.color.element_Alcalino;
            case "Metalls alcalinoterris":
                return R.color.element_AlcalinoTerreos;
            case "Metal·loides":
                return R.color.element_Metaloides;
            case "Halògens":
                return R.color.element_Halogenos;
            default:
                return 0;
        }
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
