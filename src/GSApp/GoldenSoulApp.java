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

        switch(gui.pantallaActual){
            case INICIAL: gui.dibujaPantallaLogIn(this);
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
       gui.textFields[0].keyPressed(key, keyCode); gui.textFields[1].keyPressed(key, keyCode);
    }

    public void mousePressed(){
       /*if(gui.b1.mouseOverButton(this)){
           System.out.println("B1 has been pressed!!!");
       }
        */
       gui.textFields[0].isPressed(this);
       gui.textFields[1].isPressed(this);
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
