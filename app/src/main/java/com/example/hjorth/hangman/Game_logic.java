package com.example.hjorth.hangman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

//THIS CLASS IS BORROWED FROM https://github.com/nordfalk/Galgeleg/blob/master/src/galgeleg/Galgelogik.java
public class Game_logic {

    /** AHT afprøvning er muligeOrd synlig på pakkeniveau */
    private ArrayList<String> muligeOrd = new ArrayList<>();
    private String ordet;
    private ArrayList<String> brugteBogstaver = new ArrayList<>();
    private String synligtOrd;
    private int antalForkerteBogstaver;
    private boolean sidsteBogstavVarKorrekt;
    private boolean spilletErVundet;
    private boolean spilletErTabt;

    private final String[] words = {"Ø", "To", "bil", "Æble", "computer", "programmering", "motorvej", "busrute", "gangsti", "skovsnegl", "solsort", "seksten", "sytten", "atten"};


    public ArrayList<String> getBrugteBogstaver() {
        return brugteBogstaver;
    }

    public String getSynligtOrd() {
        return synligtOrd;
    }

    public String getOrdet() {
        return ordet;
    }

    public int getAntalForkerteBogstaver() {
        return antalForkerteBogstaver;
    }

    public boolean erSidsteBogstavKorrekt() {
        return sidsteBogstavVarKorrekt;
    }

    public boolean erSpilletVundet() {
        return spilletErVundet;
    }

    public boolean erSpilletTabt() {
        return spilletErTabt;
    }

    public boolean erSpilletSlut() {
        return spilletErTabt || spilletErVundet;
    }

    //modified
    public Game_logic(String level) {
        getGameLevel(level);
        nulstil();
    }

    private void getGameLevel(String level){
        int minLength = 1;
        int maxLength = 12;

        if(level.equals("Beginner")){
            minLength = 1;
            maxLength = 4;
        }
        if(level.equals("Intermediate")){
            minLength = 5;
            maxLength = 7;
        }
        if(level.equals("Pro")){
            minLength = 8;
            maxLength = 12;
        }

        for(String word : words){
            if(word.length() >= minLength && word.length() <= maxLength){
                muligeOrd.add(word);
            }
        }
    }
    private void nulstil() {
        brugteBogstaver.clear();
        antalForkerteBogstaver = 0;
        spilletErVundet = false;
        spilletErTabt = false;
        ordet = muligeOrd.get(new Random().nextInt(muligeOrd.size()));
        opdaterSynligtOrd();
    }


    private void opdaterSynligtOrd() {
        synligtOrd = "";
        spilletErVundet = true;
        for (int n = 0; n < ordet.length(); n++) {
            String bogstav = ordet.substring(n, n + 1);
            if (brugteBogstaver.contains(bogstav)) {
                synligtOrd = synligtOrd.concat(bogstav);
            } else {
                synligtOrd = synligtOrd.concat("*");
                spilletErVundet = false;
            }
        }
    }

    public boolean gætBogstav(String bogstav) {

        System.out.println("Der gættes på bogstavet: " + bogstav);

        if (brugteBogstaver.contains(bogstav)) {
            return true;
        }
        if (spilletErVundet || spilletErTabt) {
            return true;
        }

        brugteBogstaver.add(bogstav);

        if (ordet.contains(bogstav)) {
            sidsteBogstavVarKorrekt = true;
            System.out.println("Bogstavet var korrekt: " + bogstav);
        } else {
            // Vi gættede på et bogstav der ikke var i ordet.
            sidsteBogstavVarKorrekt = false;
            System.out.println("Bogstavet var IKKE korrekt: " + bogstav);
            antalForkerteBogstaver = antalForkerteBogstaver + 1;
            if (antalForkerteBogstaver > 6) {
                spilletErTabt = true;
            }
        }
        opdaterSynligtOrd();
        return true;
    }

    public void logStatus() {
        System.out.println("---------- ");
        System.out.println("- ordet (skult) = " + ordet);
        System.out.println("- synligtOrd = " + synligtOrd);
        System.out.println("- forkerteBogstaver = " + antalForkerteBogstaver);
        System.out.println("- brugeBogstaver = " + brugteBogstaver);
        if (spilletErTabt) System.out.println("- SPILLET ER TABT");
        if (spilletErVundet) System.out.println("- SPILLET ER VUNDET");
        System.out.println("---------- ");
    }


    public static String hentUrl(String url) throws IOException {
        System.out.println("Henter data fra " + url);
        BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
        StringBuilder sb = new StringBuilder();
        String linje = br.readLine();
        while (linje != null) {
            sb.append(linje.concat("\n"));
            linje = br.readLine();
        }
        return sb.toString();
    }


    public void hentOrdFraDr() throws Exception {
        String data = hentUrl("https://dr.dk");
        //System.out.println("data = " + data);

        data = data.substring(data.indexOf("<body")). // fjern headere
                replaceAll("<.+?>", " ").toLowerCase(). // fjern tags
                replaceAll("&#198;", "æ"). // erstat HTML-tegn
                replaceAll("&#230;", "æ"). // erstat HTML-tegn
                replaceAll("&#216;", "ø"). // erstat HTML-tegn
                replaceAll("&#248;", "ø"). // erstat HTML-tegn
                replaceAll("&oslash;", "ø"). // erstat HTML-tegn
                replaceAll("&#229;", "å"). // erstat HTML-tegn
                replaceAll("[^a-zæøå]", " "). // fjern tegn der ikke er bogstaver
                replaceAll(" [a-zæøå] "," "). // fjern 1-bogstavsord
                replaceAll(" [a-zæøå][a-zæøå] "," "); // fjern 2-bogstavsord

        System.out.println("data = " + data);
        System.out.println("data = " + Arrays.asList(data.split("\\s+")));
        muligeOrd.clear();
        muligeOrd.addAll(new HashSet<>(Arrays.asList(data.split(" "))));

        System.out.println("muligeOrd = " + muligeOrd);
        nulstil();
    }
}
