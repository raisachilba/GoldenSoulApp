package GSApp;

import processing.core.PApplet;

public class GoldenSoulApp extends PApplet {

    Colors c;

    public static void main(String[] args) {
        PApplet.main("GSApp.GoldenSoulApp");
    }

    public void settings(){
        size(1470, 810);
    }

    public void setup(){
        c = new Colors(this);
    }

    public void draw(){
        background(255);
        c.display(this, 100, 100, width - 200);
    }
}
