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
        fullScreen();
    }

    public void setup(){
        c = new Colors(this);
        fontsApp = new Fonts(this);
        gui = new GUI(this);
    }

    public void draw(){
        background(230);

        switch(gui.pantallaActual){
            case LOGIN: gui.dibujaPantallaLogIn(this);
                break;

            case SIGNIN: gui.dibujaPantallaSignIn(this);
                break;

            case PRINCIPAL: gui.dibujaPantallaPrincipal(this);
                break;

            case COMPETICIONES: gui.dibujaPantallaCalendComps(this);
                break;

            case TECNICA: gui.dibujaPantallaTecnica(this);
                break;

            case ELASTICIDAD: gui.dibujaPantallaElasticidad(this);
                break;

            case COORDINACION: gui.dibujaPantallaCoordinacion(this);
                break;

            case TODO: gui.dibujaPantalllaToDo(this);
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
        else if(key == '3'){
            gui.pantallaActual = GUI.PANTALLA.COMPETICIONES;
        }
        else if(key == '4'){
            gui.pantallaActual = GUI.PANTALLA.TECNICA;
        }
        else if(key == '5'){
            gui.pantallaActual = GUI.PANTALLA.ELASTICIDAD;
        }
        else if(key == '6'){
            gui.pantallaActual = GUI.PANTALLA.COORDINACION;
        }
        else if(key == '7'){
            gui.pantallaActual = GUI.PANTALLA.TODO;
        }
       gui.textFields[0].keyPressed(key, keyCode); gui.textFields[1].keyPressed(key, keyCode);
       gui.textFields[2].keyPressed(key, keyCode); gui.textFields[3].keyPressed(key, keyCode);
       gui.textFields[4].keyPressed(key, keyCode); gui.textFields[5].keyPressed(key, keyCode);
       gui.textFields[6].keyPressed(key, keyCode); gui.textFields[7].keyPressed(key, keyCode);

        if(gui.tList.getTextField().mouseOverTextField(this)){
            gui.tList.getTextField().keyPressed(key, keyCode);
            gui.tList.update(this);
        }

        gui.videoExplica[0].typeOnCardItems(this);
        gui.videoExplica[1].typeOnCardItems(this);
        gui.videoExplica[2].typeOnCardItems(this);

    }

    public void mousePressed() {

        if (gui.pantallaActual == GUI.PANTALLA.LOGIN) {
            if (gui.b1.mouseOverButton(this) && gui.b1.isEnabled()) {
                gui.pantallaActual = GUI.PANTALLA.PRINCIPAL;
            } else if (gui.b4.mouseOverButton(this) && gui.b4.isEnabled()) {
                gui.pantallaActual = GUI.PANTALLA.SIGNIN;
            }
            gui.textFields[0].isPressed(this);
            gui.textFields[1].isPressed(this);
        }
        else if (gui.pantallaActual == GUI.PANTALLA.SIGNIN) {
            if (gui.b2.mouseOverButton(this) && gui.b2.isEnabled()) {
                gui.pantallaActual = GUI.PANTALLA.PRINCIPAL;
            }
            gui.textFields[2].isPressed(this);
            gui.textFields[3].isPressed(this);
            gui.textFields[4].isPressed(this);
            gui.textFields[5].isPressed(this);
            gui.textFields[6].isPressed(this);
            gui.textFields[7].isPressed(this);

            gui.tList.getTextField().isPressed(this);
            gui.tList.buttonPressed(this);
        }

        else if (gui.pantallaActual == GUI.PANTALLA.PRINCIPAL) {
            if (gui.bMenu[0].mouseOverButton(this) && gui.bMenu[0].isEnabled()) {
                gui.pantallaActual = GUI.PANTALLA.COMPETICIONES;
            } else if (gui.bMenu[1].mouseOverButton(this) && gui.bMenu[1].isEnabled()) {
                gui.pantallaActual = GUI.PANTALLA.TECNICA;
            } else if (gui.bMenu[2].mouseOverButton(this) && gui.bMenu[2].isEnabled()) {
                gui.pantallaActual = GUI.PANTALLA.ELASTICIDAD;
            } else if (gui.bMenu[3].mouseOverButton(this) && gui.bMenu[3].isEnabled()) {
                gui.pantallaActual = GUI.PANTALLA.COORDINACION;
            } else if (gui.bMenu[4].mouseOverButton(this) && gui.bMenu[4].isEnabled()) {
                gui.pantallaActual = GUI.PANTALLA.TODO;
            } else if (gui.bMenu[5].mouseOverButton(this) && gui.bMenu[5].isEnabled()) {
                gui.pantallaActual = GUI.PANTALLA.PRINCIPAL;
            } else if (gui.calendario.bNext.mouseOverButton(this) && gui.calendario.bNext.isEnabled()) {
                gui.calendario.nextMonth();
            } else if (gui.calendario.bPrev.mouseOverButton(this) && gui.calendario.bPrev.isEnabled()) {
                gui.calendario.prevMonth();
            }
        }

        else if (gui.pantallaActual == GUI.PANTALLA.COMPETICIONES) {
            if (gui.bMenu[0].mouseOverButton(this) && gui.bMenu[0].isEnabled()) {
                gui.pantallaActual = GUI.PANTALLA.COMPETICIONES;
            } else if (gui.bMenu[1].mouseOverButton(this) && gui.bMenu[1].isEnabled()) {
                gui.pantallaActual = GUI.PANTALLA.TECNICA;
            } else if (gui.bMenu[2].mouseOverButton(this) && gui.bMenu[2].isEnabled()) {
                gui.pantallaActual = GUI.PANTALLA.ELASTICIDAD;
            } else if (gui.bMenu[3].mouseOverButton(this) && gui.bMenu[3].isEnabled()) {
                gui.pantallaActual = GUI.PANTALLA.COORDINACION;
            } else if (gui.bMenu[4].mouseOverButton(this) && gui.bMenu[4].isEnabled()) {
                gui.pantallaActual = GUI.PANTALLA.TODO;
            } else if (gui.bMenu[5].mouseOverButton(this) && gui.bMenu[5].isEnabled()) {
                gui.pantallaActual = GUI.PANTALLA.PRINCIPAL;
            } else if (gui.b3.mouseOverButton(this) && gui.b3.isEnabled()) {
                gui.pantallaActual = GUI.PANTALLA.PRINCIPAL;
            }
        }

        else if (gui.pantallaActual == GUI.PANTALLA.TECNICA) {
            if (gui.b3.mouseOverButton(this) && gui.b3.isEnabled()) {
                gui.pantallaActual = GUI.PANTALLA.PRINCIPAL;
            }
            gui.videoExplica[0].clickMouseOnCardItems(this);
            gui.videoExplica[1].clickMouseOnCardItems(this);
            gui.videoExplica[2].clickMouseOnCardItems(this);
        }

        else if (gui.pantallaActual == GUI.PANTALLA.ELASTICIDAD) {
            if (gui.b3.mouseOverButton(this) && gui.b3.isEnabled()) {
                gui.pantallaActual = GUI.PANTALLA.PRINCIPAL;
            }
            gui.videoExplica[0].clickMouseOnCardItems(this);
            gui.videoExplica[1].clickMouseOnCardItems(this);
            gui.videoExplica[2].clickMouseOnCardItems(this);
        }

        else if (gui.pantallaActual == GUI.PANTALLA.COORDINACION) {
            if (gui.b3.mouseOverButton(this) && gui.b3.isEnabled()) {
                gui.pantallaActual = GUI.PANTALLA.PRINCIPAL;
            }
            gui.videoExplica[0].clickMouseOnCardItems(this);
            gui.videoExplica[1].clickMouseOnCardItems(this);
            gui.videoExplica[2].clickMouseOnCardItems(this);
        }

        else if (gui.pantallaActual == GUI.PANTALLA.TODO) {
            if (gui.b3.mouseOverButton(this) && gui.b3.isEnabled()) {
                gui.pantallaActual = GUI.PANTALLA.PRINCIPAL;
            }
            // NO FUNCIONEN
            else if(gui.bPrev.mouseOverButton(this) && gui.bPrev.isEnabled()){
                gui.tablaToDo.prevPage();
            }
            else if(gui.bNext.mouseOverButton(this) && gui.bNext.isEnabled()){
                gui.tablaToDo.nextPage();
            }
        }
    }

    public void updateCursor(PApplet p5){
        if(gui.b1.updateHandCursor(this) || gui.b2.updateHandCursor(this) || gui.b3.updateHandCursor(this)
                || gui.b4.updateHandCursor(this) || gui.bMenu[0].updateHandCursor(this) || gui.bMenu[1].updateHandCursor(this)
                || gui.bMenu[2].updateHandCursor(this) || gui.bMenu[3].updateHandCursor(this)
                || gui.bMenu[4].updateHandCursor(this) || gui.bMenu[5].updateHandCursor(this)
                || gui.bNext.updateHandCursor(this) || gui.bPrev.updateHandCursor(this) || gui.calendario.bPrev.updateHandCursor(this)
                || gui.calendario.bNext.updateHandCursor(this) || gui.calendario.bOK.updateHandCursor(this)){

            cursor(HAND);
        }
        else{
            cursor(ARROW);
        }
    }
}
