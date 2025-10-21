package GSApp.Estetica;

import processing.core.PApplet;
import processing.core.PFont;

import static GSApp.Estetica.Medidas.*;

public class Fonts {

    PFont[] fonts;

    public Fonts(PApplet p5){
        this.setFonts(p5);
    }

    public void setFonts(PApplet p5){
        this.fonts = new PFont[3];
        this.fonts[0] = p5.createFont("data/Modum.ttf", midaTitol);
        this.fonts[1] = p5.createFont("data/MonoSpatial.ttf", midaSubtitol);
    }

    public int getNumFonts() {return this.fonts.length; }

    public PFont getFirstFont() {return this.fonts[0];}
    public PFont getSecondFont() {return this.fonts[1];}
    public PFont getFontAt(int i){return this.fonts[i];}

    public void displayFonts(PApplet p5, float x, float y, float h){
        p5.pushStyle();
        for(int i=0; i<getNumFonts(); i++){
            p5.fill(0); p5.stroke(0); p5.strokeWeight(3);
            p5.textFont(getFontAt(i));
            p5.text("Tipografia "+i, x, y + i*h);
        }
        p5.popStyle();
    }
}
