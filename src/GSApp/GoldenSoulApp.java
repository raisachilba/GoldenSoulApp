package GSApp;

import processing.core.PApplet;

public class GoldenSoulApp extends PApplet {

    Colors c;
    Fonts fontsApp;

    public static void main(String[] args) {
        PApplet.main("GSApp.GoldenSoulApp");
    }

    public void settings(){
        size(1470, 810);
    }

    public void setup(){
        c = new Colors(this);
        fontsApp = new Fonts(this);
    }

    public void draw(){
        background(255);
        c.display(this, 100, 100, width - 200);

        fill(50);
        textFont(fontsApp.getFirstFont());
        text("Títol de l'app", 50, 410);

        fill(50, 0, 0);
        textFont(fontsApp.getSecondFont());
        text("Subtítol de l'app", 50,   490);

        fill(0, 50, 0);
        textFont(fontsApp.getFirstFont());
        text("Paràgraf de l'app", 50, 580);
    }
}
