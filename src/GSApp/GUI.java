package GSApp;

import processing.core.PApplet;

public class GUI {

    public enum PANTALLA {INICIAL, DETALLS, ABOUT};

    // Botones: entrar
    Button b1;

    Colors c;
    Fonts fontsApp;

    //Text field; introducir usuario y contraseña
    TextField[] textFields;

    public PANTALLA pantallaActual;

    public GUI(PApplet p5) {
        b1 = new Button(p5, "ENTRAR", p5.width/2+250, 550, 250, 90);
        c = new Colors(p5);
        fontsApp = new Fonts(p5);
        this.setTextField(p5);
        pantallaActual = PANTALLA.INICIAL;
    }

    public void dibujaPantallaLogIn(PApplet p5){
        p5.background(255);

        p5.pushStyle();
            p5.fill(c.getRedColor(p5, 1));
            p5.rect(p5.width/2+170, 150, 400, 80);
            p5.textAlign(p5.CENTER); p5.fill(c.getGoldColor(p5, 1)); p5.textFont(fontsApp.getFirstFont());
            p5.text("LOG IN", 1110, 210);
        p5.popStyle();

        p5.rect(0, 0, p5.width/2, p5.height);

        p5.pushMatrix();
            p5.translate(p5.width/2, p5.height/2);
            p5.triangle(0, -100, 80, 0, 0, 100);
        p5.popMatrix();

        textFields[0].display(p5); textFields[1].display(p5);

        b1.display(p5);

        p5.circle(350, p5.height/2, 300);
    }

    public void dibuixaPantallaAbout(PApplet p5){
        p5.background(0);
    }

    public void dibuixaPantallaDetalls(PApplet p5){
        p5.background(55);
    }

    public void dibuixaBotonsMenu(PApplet p5){
        //b1.display(p5);
    }

    public void setTextField(PApplet p5){
        textFields = new TextField[5];
        textFields[0] = new TextField(p5, p5.width/2+170, 310, 400, 60);
        textFields[1] = new TextField(p5, p5.width/2+170, 410, 400, 60);
    }

}
