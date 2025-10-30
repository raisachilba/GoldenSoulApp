package GSApp;

import GSApp.Elementos.Button;
import GSApp.Elementos.TextField;
import GSApp.Estetica.Colors;
import GSApp.Estetica.Fonts;
import GSApp.Estetica.Medidas;
import processing.core.PApplet;
import processing.core.PImage;

public class GUI {

    public enum PANTALLA {LOGIN, SIGNIN, PRINCIPAL};

    // Botones: entrar
    Button b1, b2;
    Button[] bMenu;
    PImage perfil;
    //Load image perque es png

    Colors c;
    Fonts fontsApp;

    //Text field; introducir usuario y contraseña
    TextField[] textFields;

    Medidas medida;

    public PANTALLA pantallaActual;

    public GUI(PApplet p5) {
        b1 = new Button(p5, "ENTRAR", p5.width/2+250, 550, 250, 90);
        b2 = new Button(p5, "REGISTRARSE", p5.width/2+250, 700, 250, 70);
        setButtons(p5);
        c = new Colors(p5);
        medida = new Medidas();
        fontsApp = new Fonts(p5);
        perfil = p5.loadImage("data/Icones/FotoPerfil.png");
        this.setTextField(p5);

        pantallaActual = PANTALLA.LOGIN;
    }

    public void setButtons(PApplet p5){
        bMenu = new Button[6];

        bMenu[0] = new Button(p5,"COMPETICIONES", 70, 300, 150, 150);
        bMenu[1] = new Button(p5,"TÉCNICA", 260, 300, 150, 150);
        bMenu[2] = new Button(p5,"ELASTICIDAD", 450, 300, 150, 150);
        bMenu[3] = new Button(p5,"COORDINACIÓN", 70, 530, 150, 150);
        bMenu[4] = new Button(p5,"TO-DO LIST", 260, 530, 150, 150);
        bMenu[5] = new Button(p5,"CALENDARIO", 450, 530, 150, 150);
    }

    public void dibujaPantallaLogIn(PApplet p5){
        p5.background(255);

        p5.pushStyle();
            p5.fill(c.getRedColor(p5, 1));
            p5.rectMode(p5.CORNER);
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

        //Logo
        p5.rect(200, 250, 300, 300);
    }

    public void dibujaPantallaSignIn(PApplet p5){
        p5.background(255);
        p5.pushStyle();
            p5.fill(c.getRedColor(p5, 1));
            p5.rect(p5.width/2+170, 50, 400, 80);
            p5.textAlign(p5.CENTER); p5.fill(c.getGoldColor(p5, 1)); p5.textFont(fontsApp.getFirstFont());
            p5.text("SIGN IN", 1110, 110);
        p5.popStyle();

        p5.rect(0, 0, p5.width/2, p5.height);

        p5.pushMatrix();
            p5.translate(p5.width/2, p5.height/2);
            p5.triangle(0, -100, 80, 0, 0, 100);
        p5.popMatrix();

        textFields[2].display(p5); textFields[3].display(p5); textFields[4].display(p5);

        // Queda afegir el petit panell amb les diferents opcions per escollir la provincia

        b2.display(p5);

        //Logo
        p5.rect(200, 250, 300, 300);
    }

    public void dibujaPantallaPrincipal(PApplet p5){
        p5.background(255);

        dibujaLogoBanner(p5);

        dibujaBotonesMenu(p5);

        p5.pushStyle();
            p5.textAlign(p5.CENTER);
            p5.textSize(medida.midaTitol); p5.fill(c.getGoldColor(p5, 1));
            p5.text("GOLDEN SOUL DANCE", 800, 130);
        p5.popStyle();

        p5.pushMatrix();
        p5.image(perfil, 50, 100);
        p5.scale(0.8f, 0.8f);
        p5.popMatrix();

    }

    public void dibujaBotonesMenu(PApplet p5){
        bMenu[0].display(p5);
        bMenu[1].display(p5);
        bMenu[2].display(p5);
        bMenu[3].display(p5);
        bMenu[4].display(p5);
        bMenu[5].display(p5);
    }

    public void dibujaLogoBanner(PApplet p5){

        p5.pushMatrix();
            p5.rect(0, 0, 220, 220);
            p5.translate(220, 0);
            p5.rect(0, 0, p5.width, 220);
        p5.popMatrix();
    }

    public void setTextField(PApplet p5){
        textFields = new TextField[5];

        //Text field LOG IN
        textFields[0] = new TextField(p5, p5.width/2+170, 310, 400, 60);
        textFields[1] = new TextField(p5, p5.width/2+170, 410, 400, 60);

        //Text field SIGN IN
        textFields[2] = new TextField(p5, p5.width/2+170, 200, 400, 50);
        textFields[3] = new TextField(p5, p5.width/2+170, 300, 400, 50);
        textFields[4] = new TextField(p5, p5.width/2+170, 400, 400, 50);
    }

}
