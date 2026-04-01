package GSApp;

import GSApp.Data.BaseDatos;
import GSApp.Estetica.Colors;
import GSApp.Estetica.Fonts;
import processing.core.PApplet;

public class GoldenSoulApp extends PApplet {

    Colors c;
    Fonts fontsApp;
    GUI gui;
    BaseDatos db;
    boolean loginWrong = false;

    public static void main(String[] args) {
        PApplet.main("GSApp.GoldenSoulApp");
    }

    public void settings(){
        fullScreen();
    }

    public void setup(){
        c = new Colors(this);
        fontsApp = new Fonts(this);
        db = new BaseDatos("admin", "12345", "todos");
        db.connect();
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

            case HORAS: gui.dibujaHorasClases(this);
                break;
        }

        if(gui.pantallaActual == GUI.PANTALLA.LOGIN) {
            if (loginWrong) {
                pushStyle();
                textSize(15);
                fill(c.getRedColor(this, 1));
                text("Usuario o contrseña incorrectos", width / 2 + 170, 575);
                popStyle();
            }
        }

        updateCursor(this);
    }

    public void keyPressed(){
        if(gui.pantallaActual == GUI.PANTALLA.LOGIN) {
            gui.textFields[0].keyTyped(key);
            gui.textFields[1].keyTyped(key);

            gui.textFields[0].keyPressed(keyCode);
            gui.textFields[1].keyPressed(keyCode);
        }
        else if(gui.pantallaActual == GUI.PANTALLA.SIGNIN) {
            gui.textFields[2].keyTyped(key); gui.textFields[3].keyTyped(key);
            gui.textFields[4].keyTyped(key); gui.textFields[5].keyTyped(key);
            gui.textFields[6].keyTyped(key); gui.textFields[7].keyTyped(key);

            gui.textFields[2].keyPressed(keyCode); gui.textFields[3].keyPressed(keyCode);
            gui.textFields[4].keyPressed(keyCode); gui.textFields[5].keyPressed(keyCode);
            gui.textFields[6].keyPressed(keyCode); gui.textFields[7].keyPressed(keyCode);

            if(gui.tList.getTextField().mouseOverTextField(this)){
                gui.tList.getTextField().keyPressed(key, keyCode);
                gui.tList.update(this);
            }
        }

    }

    public void mousePressed() {

        if (gui.pantallaActual == GUI.PANTALLA.LOGIN) {
            if (gui.b1.mouseOverButton(this) && gui.b1.isEnabled()) {
                String nom = gui.textFields[0].getText();
                String password = gui.textFields[1].getText();
                if(db.loginCorrecte(nom, password)){
                    gui.pantallaActual = GUI.PANTALLA.PRINCIPAL;
                } else {
                    println("LOGIN WRONG");
                    loginWrong = true;
                }
            } else if (gui.b4.mouseOverButton(this) && gui.b4.isEnabled()) {
                gui.pantallaActual = GUI.PANTALLA.SIGNIN;
            }
            if(gui.textFields[0].mouseOverTextField(this)) {
                gui.textFields[0].isPressed(this);
            }
            else if(gui.textFields[1].mouseOverTextField(this)){
                gui.textFields[1].isPressed(this);
            }
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
            }
            else if (gui.bMenu[1].mouseOverButton(this) && gui.bMenu[1].isEnabled()) {
                gui.pantallaActual = GUI.PANTALLA.TECNICA;
            }
            else if (gui.bMenu[2].mouseOverButton(this) && gui.bMenu[2].isEnabled()) {
                gui.pantallaActual = GUI.PANTALLA.ELASTICIDAD;
            }
            else if (gui.bMenu[3].mouseOverButton(this) && gui.bMenu[3].isEnabled()) {
                gui.pantallaActual = GUI.PANTALLA.COORDINACION;
            }
            else if (gui.bMenu[4].mouseOverButton(this) && gui.bMenu[4].isEnabled()) {
                gui.pantallaActual = GUI.PANTALLA.TODO;
            }
            else if (gui.bMenu[5].mouseOverButton(this) && gui.bMenu[5].isEnabled()) {
                gui.pantallaActual = GUI.PANTALLA.PRINCIPAL;
            }
            else if (gui.calendario.bNext.mouseOverButton(this) && gui.calendario.bNext.isEnabled()) {
                gui.calendario.nextMonth();
            }
            else if (gui.calendario.bPrev.mouseOverButton(this) && gui.calendario.bPrev.isEnabled()) {
                gui.calendario.prevMonth();
            }
            gui.calendario.checkButtons(this);
        }

        else if (gui.pantallaActual == GUI.PANTALLA.COMPETICIONES) {
            if (gui.bMenu[0].mouseOverButton(this) && gui.bMenu[0].isEnabled()) {
                gui.pantallaActual = GUI.PANTALLA.COMPETICIONES;
            }
            else if (gui.bMenu[1].mouseOverButton(this) && gui.bMenu[1].isEnabled()) {
                gui.pantallaActual = GUI.PANTALLA.TECNICA;
            }
            else if (gui.bMenu[2].mouseOverButton(this) && gui.bMenu[2].isEnabled()) {
                gui.pantallaActual = GUI.PANTALLA.ELASTICIDAD;
            }
            else if (gui.bMenu[3].mouseOverButton(this) && gui.bMenu[3].isEnabled()) {
                gui.pantallaActual = GUI.PANTALLA.COORDINACION;
            }
            else if (gui.bMenu[4].mouseOverButton(this) && gui.bMenu[4].isEnabled()) {
                gui.pantallaActual = GUI.PANTALLA.TODO;
            }
            else if (gui.bMenu[5].mouseOverButton(this) && gui.bMenu[5].isEnabled()) {
                gui.pantallaActual = GUI.PANTALLA.PRINCIPAL;
            }
            else if (gui.b3.mouseOverButton(this) && gui.b3.isEnabled()) {
                gui.pantallaActual = GUI.PANTALLA.PRINCIPAL;
            }

            gui.calendario.checkButtons(this);
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
