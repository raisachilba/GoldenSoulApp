package GSApp;

import GSApp.Estetica.Colors;
import GSApp.Estetica.Fonts;
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

        switch(gui.pantallaActual){
            case LOGIN: gui.dibujaPantallaLogIn(this);
            break;

            case SIGNIN: gui.dibujaPantallaSignIn(this);
            break;

            case PRINCIPAL: gui.dibujaPantallaPrincipal(this);
            break;
        }

        updateCursor(this);
    }

    public void keyPressed(){
        if(key == '0'){
            gui.pantallaActual = GUI.PANTALLA.LOGIN;
        }
        else if(key == '1'){
            gui.pantallaActual = GUI.PANTALLA.SIGNIN;
        }
        else if(key == '2'){
            gui.pantallaActual = GUI.PANTALLA.PRINCIPAL;
        }
       gui.textFields[0].keyPressed(key, keyCode); gui.textFields[1].keyPressed(key, keyCode);
       gui.textFields[2].keyPressed(key, keyCode); gui.textFields[3].keyPressed(key, keyCode);
       gui.textFields[4].keyPressed(key, keyCode);

        if(gui.tList.getTextField().mouseOverTextField(this)){
            gui.tList.getTextField().keyPressed(key, (int)keyCode);
            gui.tList.update(this);
        }
    }

    public void mousePressed(){

       gui.textFields[0].isPressed(this);
       gui.textFields[1].isPressed(this);
       gui.textFields[2].isPressed(this);
       gui.textFields[3].isPressed(this);
       gui.textFields[4].isPressed(this);

        gui.tList.getTextField().isPressed(this);
        gui.tList.buttonPressed(this);

    }

    public void updateCursor(PApplet p5){
        if(gui.b1.updateHandCursor(p5) || gui.bMenu[0].updateHandCursor(p5) || gui.bMenu[1].updateHandCursor(p5)
                || gui.bMenu[2].updateHandCursor(p5) || gui.bMenu[3].updateHandCursor(p5) || gui.bMenu[4].updateHandCursor(p5)
                || gui.bMenu[5].updateHandCursor(p5)){

            cursor(HAND);
        }
        else{
            cursor(ARROW);
        }
    }
}
