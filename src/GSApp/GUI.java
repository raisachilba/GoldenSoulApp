package GSApp;

import processing.core.PApplet;

public class GUI {

    public enum PANTALLA {INICIAL, DETALLS, ABOUT};
    Button b1;
    Colors c;
    TextField tUsuari;

    public PANTALLA pantallaActual;

    public GUI(PApplet p5) {
        b1 = new Button(p5, "RED", 40, 400, 250, 100);
        tUsuari = new TextField(p5, 200, 300, 250, 100);
        pantallaActual = PANTALLA.INICIAL;
    }

    public void dibuixaPantallaInicial(PApplet p5){
        p5.background(255);
        p5.fill(255);
        p5.rect(0, 0, p5.width/2, p5.height);
        tUsuari.display(p5);
        dibuixaBotonsMenu(p5);
    }

    public void dibuixaPantallaAbout(PApplet p5){
        p5.background(55);
        p5.rect(p5.width/2, p5.height/2, 100, 100);
    }

    public void dibuixaPantallaDetalls(PApplet p5){
        p5.background(55);
        p5.rect(p5.width/2, p5.height/2, 200, 400);
    }

    public void dibuixaBotonsMenu(PApplet p5){
        b1.display(p5);
    }
}
