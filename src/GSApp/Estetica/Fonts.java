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
        this.fonts = new PFont[4];
        this.fonts[0] = p5.createFont("data/Fonts/Modum.ttf", midaTitol);
        this.fonts[1] = p5.createFont("data/Fonts/GingerBrand.ttf", midaTitol);
        this.fonts[2] = p5.createFont("data/Fonts/MonoSpatial.ttf", midaTitol);
        this.fonts[3] = p5.createFont("data/Fonts/Modum.ttf", midaSubtitol);
    }

    public int getNumFonts() {return this.fonts.length; }

    public PFont getFontLogIn() {return this.fonts[0];}
    public PFont getFontTitulo() {return this.fonts[1];}
    public PFont getFontTitulosPantallaBotones() {return this.fonts[2];}
    public PFont getFontCards() {return this.fonts[3];}
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
