package GSApp;

import GSApp.Data.BaseDatos;
import GSApp.Data.DateConversion;
import GSApp.Estetica.Colors;
import GSApp.Estetica.Fonts;
import processing.core.PApplet;

public class GoldenSoulApp extends PApplet {

    Colors c;
    Fonts fontsApp;
    GUI gui;
    BaseDatos db;
    boolean loginWrong = false;
    boolean signupError = false;

    public static void main(String[] args) {
        PApplet.main("GSApp.GoldenSoulApp");
    }

    public void settings(){
        fullScreen();
    }

    public void setup(){
        c = new Colors(this);
        fontsApp = new Fonts(this);
        db = new BaseDatos("admin", "12345", "GoldenSoulApp");
        db.connect();
        gui = new GUI(this, db);
    }

    public void draw(){
        background(230);

        switch(gui.pantallaActual){
            case LOGIN: gui.dibujaPantallaLogIn(this);
                break;

            case SIGNUP: gui.dibujaPantallaSignUp(this);
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
                    text("Usuario o contraseña incorrectos", width / 2 + 170, 575);
                popStyle();
            }
        }
        else if(gui.pantallaActual == GUI.PANTALLA.SIGNUP){
            if(signupError){
                pushStyle();
                textSize(15);
                fill(c.getRedColor(this, 1));
                text("Este nombre de usuario no está disponible", width / 2 + 170, 230);
                popStyle();
            }
        }

        updateCursor(this);
    }

    public void keyPressed(){
        if(gui.pantallaActual == GUI.PANTALLA.LOGIN) {
            gui.textFields[0].keyPressed(keyCode);
            gui.textFields[1].keyPressed(keyCode);
        }
        else if(gui.pantallaActual == GUI.PANTALLA.SIGNUP) {
            gui.textFields[2].keyPressed(keyCode); gui.textFields[3].keyPressed(keyCode);
            gui.textFields[4].keyPressed(keyCode); gui.textFields[5].keyPressed(keyCode);
            gui.textFields[6].keyPressed(keyCode); gui.textFields[7].keyPressed(keyCode);
            gui.textFields[8].keyPressed(keyCode);

            if(gui.tList.getTextField().mouseOverTextField(this)){
                gui.tList.getTextField().keyPressed(keyCode);
                gui.tList.update(this);
            }
        }
        else if(gui.pantallaActual == GUI.PANTALLA.HORAS){
            gui.txtFieldInfoClase[0].keyPressed(keyCode); gui.txtFieldInfoClase[1].keyPressed(keyCode);
        }

    }

    public void keyTyped() {
        if (gui.pantallaActual == GUI.PANTALLA.LOGIN) {
            gui.textFields[0].keyTyped(key);
            gui.textFields[1].keyTyped(key);
        } else if (gui.pantallaActual == GUI.PANTALLA.SIGNUP) {
            gui.textFields[2].keyTyped(key);
            gui.textFields[3].keyTyped(key);
            gui.textFields[4].keyTyped(key);
            gui.textFields[5].keyTyped(key);
            gui.textFields[6].keyTyped(key);
            gui.textFields[7].keyTyped(key);
            gui.textFields[8].keyTyped(key);
        } else if (gui.pantallaActual == GUI.PANTALLA.HORAS) {
            gui.txtFieldInfoClase[0].keyTyped(key);
            gui.txtFieldInfoClase[1].keyTyped(key);
        }
    }

    public void mousePressed() {

        if (gui.pantallaActual == GUI.PANTALLA.LOGIN) {
            if (gui.b1.mouseOverButton(this) && gui.b1.isEnabled()) {
                String nom = gui.textFields[0].getText();
                String password = gui.textFields[1].getText();
                if(db.loginCorrecte(nom, password)){
                    db.guardarSesion(nom);
                    System.out.println("LOGIN CLICKED");
                    System.out.println("USER: " + nom);
                    System.out.println("PASS: " + password);
                    gui.pantallaActual = GUI.PANTALLA.PRINCIPAL;
                } else {
                    println("LOGIN WRONG");
                    loginWrong = true;
                }
            } else if (gui.b4.mouseOverButton(this) && gui.b4.isEnabled()) {
                gui.pantallaActual = GUI.PANTALLA.SIGNUP;
            }
            gui.textFields[0].isPressed(this);
            gui.textFields[1].isPressed(this);
        }

        else if (gui.pantallaActual == GUI.PANTALLA.SIGNUP) {
            if (gui.b2.mouseOverButton(this) && gui.b2.isEnabled()) {
                String usuario = gui.textFields[2].getText();
                String nombre = gui.textFields[3].getText();
                String apellido = gui.textFields[4].getText();
                String fecha = gui.textFields[5].getText();
                String pais = gui.textFields[6].getText();
                String provincia = gui.tList.getSelectedValue();
                String domicilio = gui.textFields[7].getText();
                String password = gui.textFields[8].getText();

                if(db.usuarioExiste(usuario)){
                    signupError = true;
                } else {
                    db.insertarUsuario(usuario, nombre, apellido, fecha, pais, provincia, domicilio, password);
                    signupError = false;
                    gui.pantallaActual = GUI.PANTALLA.PRINCIPAL;
                }
                //db.insertarUsuario(usuario, nombre, apellido, fecha, pais, provincia, domicilio, password);
            }

            gui.textFields[2].isPressed(this);
            gui.textFields[3].isPressed(this);
            gui.textFields[4].isPressed(this);
            gui.textFields[5].isPressed(this);
            gui.textFields[6].isPressed(this);
            gui.textFields[7].isPressed(this);
            gui.textFields[8].isPressed(this);

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

                gui.tablaToDo.setData(gui.bd.getToDos());
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
            if(gui.calendario.bOK.mouseOverButton(this) && gui.calendario.bOK.isEnabled()){
                gui.calendario.resetSelection();
            }
            else if(gui.calendario.isDateSelected()){
                String fechaOg = gui.calendario.getSelectedDate();
                String fechaSQL = DateConversion.formataFechaEng(fechaOg);
                gui.cargarClasesDelDia(fechaSQL, db);

                gui.pantallaActual = GUI.PANTALLA.HORAS;
            }
        }

        else if(gui.pantallaActual == GUI.PANTALLA.HORAS){

            if (gui.b3.mouseOverButton(this) && gui.b3.isEnabled()) {
                gui.pantallaActual = GUI.PANTALLA.PRINCIPAL;
            }
            else if(gui.b5.mouseOverButton(this) && gui.b5.isEnabled()){
                String nombre = gui.txtFieldInfoClase[0].getText();
                String tipo = gui.txtFieldInfoClase[1].getText();
                String fechaOg = gui.calendario.getSelectedDate();
                String fechaSQL = DateConversion.formataFechaEng(fechaOg);

                String hora = "";

                if(gui.horas[0].isChecked()){hora = "09:00:00";}
                else if(gui.horas[1].isChecked()){hora = "09:45:00";}
                else if(gui.horas[2].isChecked()){hora = "10:30:00";}
                else if(gui.horas[3].isChecked()){hora = "17:30:00";}
                else if(gui.horas[4].isChecked()){hora = "18:15:00";}
                else if(gui.horas[5].isChecked()){hora = "19:00:00";}
                else if(gui.horas[6].isChecked()){hora = "19:45:00";}

                if(hora.equals("")){
                    println("Selecciona una hora");
                    return;
                }
                db.reservaClase(nombre, fechaSQL, hora, tipo);

                gui.pantallaActual = GUI.PANTALLA.PRINCIPAL;
            }
            else if(gui.horas[0].onMouseOver(this)){
                gui.horas[0].toggle();
            }
            else if(gui.horas[1].onMouseOver(this)){
                gui.horas[1].toggle();
            }
            else if(gui.horas[2].onMouseOver(this)){
                gui.horas[2].toggle();
            }
            else if(gui.horas[3].onMouseOver(this)){
                gui.horas[3].toggle();
            }
            else if(gui.horas[4].onMouseOver(this)){
                gui.horas[4].toggle();
            }
            else if(gui.horas[5].onMouseOver(this)){
                gui.horas[5].toggle();
            }
            else if(gui.horas[6].onMouseOver(this)){
                gui.horas[6].toggle();
            }
            gui.txtFieldInfoClase[0].isPressed(this);
            gui.txtFieldInfoClase[1].isPressed(this);
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
            else if (gui.calendario.bNext.mouseOverButton(this) && gui.calendario.bNext.isEnabled()) {
                gui.calendario.nextMonth();
            }
            else if (gui.calendario.bPrev.mouseOverButton(this) && gui.calendario.bPrev.isEnabled()) {
                gui.calendario.prevMonth();
            }
            gui.calendario.checkButtons(this);
            if(gui.calendario.bOK.mouseOverButton(this) && gui.calendario.bOK.isEnabled()){
                gui.calendario.resetSelection();
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
            gui.videoExplica[3].clickMouseOnCardItems(this);
            gui.videoExplica[4].clickMouseOnCardItems(this);
            gui.videoExplica[5].clickMouseOnCardItems(this);
        }

        else if (gui.pantallaActual == GUI.PANTALLA.COORDINACION) {
            if (gui.b3.mouseOverButton(this) && gui.b3.isEnabled()) {
                gui.pantallaActual = GUI.PANTALLA.PRINCIPAL;
            }
            gui.videoExplica[6].clickMouseOnCardItems(this);
            gui.videoExplica[7].clickMouseOnCardItems(this);
            gui.videoExplica[8].clickMouseOnCardItems(this);
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
        boolean over = false;
        if(gui.pantallaActual == GUI.PANTALLA.LOGIN){
            over = gui.b1.updateHandCursor(this) || gui.b2.updateHandCursor(this) || gui.b4.updateHandCursor(this);
        }

        else if(gui.pantallaActual == GUI.PANTALLA.SIGNUP){
            over = gui.b2.updateHandCursor(this);
        }

        else if(gui.pantallaActual == GUI.PANTALLA.PRINCIPAL){
            over = gui.bMenu[0].updateHandCursor(this) || gui.bMenu[1].updateHandCursor(this)
                    || gui.bMenu[2].updateHandCursor(this) || gui.bMenu[3].updateHandCursor(this)
                    || gui.bMenu[4].updateHandCursor(this) || gui.bMenu[5].updateHandCursor(this)
                    || gui.calendario.bPrev.updateHandCursor(this) || gui.calendario.bNext.updateHandCursor(this)
                    || gui.calendario.bOK.updateHandCursor(this);
        }

        else if(gui.pantallaActual == GUI.PANTALLA.HORAS){
            over = gui.b3.updateHandCursor(this) || gui.b5.updateHandCursor(this);
        }

        else if(gui.pantallaActual == GUI.PANTALLA.COMPETICIONES){
            over = gui.bMenu[0].updateHandCursor(this) || gui.bMenu[1].updateHandCursor(this)
                    || gui.bMenu[2].updateHandCursor(this) || gui.bMenu[3].updateHandCursor(this)
                    || gui.bMenu[4].updateHandCursor(this) || gui.bMenu[5].updateHandCursor(this)
                    || gui.b3.updateHandCursor(this) ||gui.calendario.bPrev.updateHandCursor(this)
                    || gui.calendario.bNext.updateHandCursor(this) || gui.calendario.bOK.updateHandCursor(this);
        }

        else if(gui.pantallaActual == GUI.PANTALLA.TECNICA){
            over = gui.b3.updateHandCursor(this);
        }

        else if(gui.pantallaActual == GUI.PANTALLA.ELASTICIDAD){
            over = gui.b3.updateHandCursor(this);
        }

        else if(gui.pantallaActual == GUI.PANTALLA.COORDINACION){
            over = gui.b3.updateHandCursor(this);
        }

        else if(gui.pantallaActual == GUI.PANTALLA.TODO){
            over = gui.b3.updateHandCursor(this);
        }

        if(over){
            cursor(HAND);
        } else{
            cursor(ARROW);
        }
    }

}
