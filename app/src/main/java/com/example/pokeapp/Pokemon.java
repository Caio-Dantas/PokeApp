package com.example.pokeapp;

import android.graphics.drawable.Drawable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;

public class Pokemon {

    private String name;
    private String height;
    private String weight;
    private String baseXP;
    private String type;
    private int color;
    private Drawable sprite;

    public Pokemon(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            String sprites = jsonObject.getString("sprites");
            JSONObject jsonSprites = new JSONObject(sprites);

            String decimetersHeight = jsonObject.getString("height");
            this.height = String.valueOf(Double.parseDouble(decimetersHeight) * 10);
            String hectogramWeight = jsonObject.getString("weight");
            this.weight = String.valueOf(Double.parseDouble(hectogramWeight) / 10);
            this.baseXP = jsonObject.getString("base_experience");
            this.name = jsonObject.getString("name");
            this.sprite = loadImageFromUrl(jsonSprites.getString("front_default"));

            String firstSlot = jsonObject.getJSONArray("types").getString(0);
            String mainType = new JSONObject(firstSlot).getString("type");
            this.type = new JSONObject(mainType).getString("name");

            this.color = getTypeColor(type);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean isOk(){
        return name != null && height != null && weight != null && baseXP != null && type != null && color != 0 && sprite != null;
    }

    public String getName() {
        return capitalize(name);
    }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    public String getBaseXP() {
        return baseXP;
    }

    public Drawable getSprite() {
        return sprite;
    }

    public String getType() {
        return capitalize(type);
    }

    public int getColor(){
        return color;
    }

    private static Drawable loadImageFromUrl(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            return  Drawable.createFromStream(is, "src");
        } catch (Exception e) {
            return null;
        }
    }

    private int getTypeColor(String type){
        switch (type){
            case "normal": return Colors.NORMAL.getColor();
            case "fire": return Colors.FIRE.getColor();
            case "water": return Colors.WATER.getColor();
            case "grass": return Colors.GRASS.getColor();
            case "electric": return Colors.ELECTRIC.getColor();
            case "ice": return Colors.ICE.getColor();
            case "fighting": return Colors.FIGHTING.getColor();
            case "poison": return Colors.POISON.getColor();
            case "ground": return Colors.GROUND.getColor();
            case "flying": return Colors.FLYING.getColor();
            case "psychic": return Colors.PSYCHIC.getColor();
            case "bug": return Colors.BUG.getColor();
            case "rock": return Colors.ROCK.getColor();
            case "ghost": return Colors.GHOST.getColor();
            case "dark": return Colors.DARK.getColor();
            case "dragon": return Colors.DRAGON.getColor();
            case "steel": return Colors.STEEL.getColor();
            case "fairy": return Colors.FAIRY.getColor();
            default: return Colors.NORMAL.getColor();
        }
    }

    private String capitalize(String word){
        return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
    }
}
