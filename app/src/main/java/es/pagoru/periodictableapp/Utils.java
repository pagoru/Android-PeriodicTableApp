package es.pagoru.periodictableapp;

/**
 * Created by Pablo on 20/11/2016.
 */

public class Utils {

    public static int getRandom(int min, int max){
        return (min + (int)(Math.random() * ((max - min))));
    }
}

