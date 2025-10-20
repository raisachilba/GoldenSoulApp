package GSApp;

import processing.core.PApplet;

public class GUI {

    public enum PANTALLA {INICIAL, DETALLS, ABOUT};
    Button b1;
    Colors c;
    TextField tUsuari;
    Mides m;

    public PANTALLA pantallaActual;

    public GUI(PApplet p5) {
        b1 = new Button(p5, "RED", 40, 400, 250, 100);
        tUsuari = new TextField(p5, 200, 300, 250, 100);
        c = new Colors(p5);
        m = new Mides();
        pantallaActual = PANTALLA.INICIAL;
    }

    /*public void dibuixaPantallaInicial(PApplet p5){
        p5.background(255);
        p5.fill(255);
        p5.rect(0, 0, p5.width/2, p5.height);
        tUsuari.display(p5);
        dibuixaBotonsMenu(p5);
    }
     */

    public void dibujaPantallaLogIn(PApplet p5){
        p5.background(255);
        p5.fill(c.getRedColor());
        p5.rect(0, 0, p5.width/2, p5.height);
        p5.pushMatrix();
            p5.translate(p5.width/2, p5.height/2);
            p5.triangle(0, -100, 80, 0, 0, 100);

        p5.popMatrix();
    }

    public void dibuixaPantallaAbout(PApplet p5){
        p5.background(55);
    }

    public void dibuixaPantallaDetalls(PApplet p5){
        p5.background(55);
    }

    public void dibuixaBotonsMenu(PApplet p5){
        b1.display(p5);
    }
}
