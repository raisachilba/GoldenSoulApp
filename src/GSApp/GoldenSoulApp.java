package GSApp;

import processing.core.PApplet;

public class GoldenSoulApp extends PApplet {

    Colors c;
    Fonts fontsApp;
    GUI gui;

    public static void main(String[] args) {
        PApplet.main("GSApp.GoldenSoulApp");
    }

    public void settings(){
        size(1470, 810);
    }

    public void setup(){
        c = new Colors(this);
        fontsApp = new Fonts(this);
        gui = new GUI(this);
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

        switch(gui.pantallaActual){
            case INICIAL: gui.dibuixaPantallaInicial(this);
            break;

            case ABOUT: gui.dibuixaPantallaAbout(this);
            break;

            case DETALLS: gui.dibuixaPantallaDetalls(this);
            break;
        }

        updateCursor(this);
    }

    public void keyPressed(){
        if(key == '0'){
            gui.pantallaActual = GUI.PANTALLA.INICIAL;
        }
        else if(key == '1'){
            gui.pantallaActual = GUI.PANTALLA.DETALLS;
        }
        else if(key == '2'){
            gui.pantallaActual = GUI.PANTALLA.ABOUT;
        }
        gui.tUsuari.keyPressed(key, keyCode);
    }

    public void mousePressed(){
       if(gui.b1.mouseOverButton(this)){
           System.out.println("B1 has been pressed!!!");
       }
       gui.tUsuari.isPressed(this);
    }

    public void updateCursor(PApplet p5){
        if(gui.b1.updateHandCursor(p5)){
            cursor(HAND);
        }
        else{
            cursor(ARROW);
        }
    }
}
