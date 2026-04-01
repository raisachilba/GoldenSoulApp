package GSApp.Elementos;

import processing.core.PApplet;

public class CheckBoxtest extends PApplet {
    // Elements de la Interfície Gràfica (Checkbox)

    // Variables checkbox
    CheckBox cb1, cb2, cb3;

    // Variables color (RGB)
    float r, g, b;


    public static void main(String[] args) {
        PApplet.main("GSApp.Elementos.CheckBoxtest", args);
    }

    public void settings(){
        size(1200, 600);
        smooth(10);
    }

    public void setup(){

        // Construcció dels checkboxes
        cb1 = new CheckBox(this, 160,50,50);
        cb2 = new CheckBox(this, 160,250,50);
        //b3 = new CheckBox(this, 160,300,50);
    }

    public void draw() {

        // Color de la finestra
        background(r, g, b);

        if(r==g && g==b && r==0){
            fill(255);
        }
        else {
            fill(0);
        }
        textSize(24);
        text("RED", 100, 85);
        text("GREEN", 70, 185);
        text("BLUE", 90, 285);

        // Dibuixam els checkboxes
        cb1.display(this);
        cb2.display(this);
        //cb3.display(this);

        // Actualitza el cursor
        updateCursor(this);
    }

    // Modifica el cursor
    void updateCursor(PApplet p5){
        if(cb1.onMouseOver(p5)|| cb2.onMouseOver(p5)){
            cursor(HAND);
        }
        else {
            cursor(ARROW);
        }
    }

    // ******************* KEYBOARD interaction ***************************** //

    public void keyPressed(){
    }

    // ******************* MOUSE interaction ***************************** //

    // En cas de pitjar el ratolí
    public void mousePressed(){

        // Si pitjam sobre el checboxes
        if(cb1.onMouseOver(this)){
            cb1.toggle();
        }
        else if(cb2.onMouseOver(this)){
            cb2.toggle();
        }
        /*else if(cb3.onMouseOver(this)){
            cb3.toggle();
        }

         */

        // Miram el seu valor, per actualitzar r,g i b
        r = cb1.isChecked() ? 255 : 0;
        g = cb2.isChecked() ? 255 : 0;
        //b = cb3.isChecked() ? 255 : 0;
    }

    public void mouseDragged(){
        println("MOUSE DRAGGED");
    }

    public void mouseReleased() {
        println("MOUSE RELEASED");
    }

}
