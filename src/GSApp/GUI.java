package GSApp;

import processing.core.PApplet;

public class GUI {

    public enum PANTALLA {INICIAL, DETALLS, ABOUT};
    Button b1;
    Colors c;

    //Text field introducir usuario y contraseña
    TextField t1;

    public PANTALLA pantallaActual;

    public GUI(PApplet p5) {
        b1 = new Button(p5, "RED", 40, 400, 250, 100);
        c = new Colors(p5);
        t1 = new TextField(p5, p5.width/2 + 50, 200, 300, 100);
        pantallaActual = PANTALLA.INICIAL;
    }

    public void dibujaPantallaLogIn(PApplet p5){
        p5.background(255);
        p5.pushStyle();
            p5.fill(c.getRedColor(p5, 1));
            p5.rect(p5.width/2+170, 100, 400, 80);
        p5.popStyle();
        p5.rect(0, 0, p5.width/2, p5.height);
        p5.pushMatrix();
            p5.translate(p5.width/2, p5.height/2);
            p5.triangle(0, -100, 80, 0, 0, 100);
        p5.popMatrix();
        t1.display(p5);
    }

    public void dibuixaPantallaAbout(PApplet p5){
        p5.background(0);
    }

    public void dibuixaPantallaDetalls(PApplet p5){
        p5.background(55);
    }

    public void dibuixaBotonsMenu(PApplet p5){
        b1.display(p5);
    }

}
