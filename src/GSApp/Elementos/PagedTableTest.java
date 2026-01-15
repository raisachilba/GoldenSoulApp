package GSApp.Elementos;

import processing.core.PApplet;

public class PagedTableTest extends PApplet {

    // Elements de la Interfície Gràfica (Table)
    // Botons
    Button b1, b2;

    // Dimensions dels botons
    float buttonW = 60, buttonH = 60;

    // Taula Paginada
    PagedTable t;

    // Dimensions de la taula
    float tableW = 800, tableH = 300;

    // Número de files (capçalera inclosa) i columnes de la taula
    int files = 5, columnes = 5;

    // Títols de les columnes
    String[] headers = {"Id", "Nom", "Llinatges", "Edat", "Sexe"};

    // Amplades de les columnes
    int[] colWidths = {10, 20, 40, 10, 20};

    // Dades de la taula
    String[][] info = {
            {"1", "Pere", "Soler Miralles", "33", "Home"},
            {"2", "Maria", "Garcia Lopez", "25", "Dona"},
            {"3", "Joan", "Melis Cabrer", "47", "Home"},
            {"4", "Bel", "Riera Mates", "52", "Dona"},
            {"5", "Jose", "Perez Galdós", "37", "Home"},
            {"6", "Pere", "Soler Miralles", "33", "Home"},
            {"7", "Maria", "Garcia Lopez", "25", "Dona"},
            {"8", "Joan", "Melis Cabrer", "47", "Home"},
            {"9", "Bel", "Riera Mates", "52", "Dona"},
            {"10", "Jose", "Perez Galdós", "37", "Home"},
            {"11", "Pere", "Soler Miralles", "33", "Home"},
            {"12", "Maria", "Garcia Lopez", "25", "Dona"},
            {"13", "Joan", "Melis Cabrer", "47", "Home"},
            {"14", "Bel", "Riera Mates", "52", "Dona"},
            {"15", "Jose", "Perez Galdós", "37", "Home"},
            {"16", "Pepe", "Viyuela Lopez", "42", "Home"},
    };


    public static void main(String[] args) {
        PApplet.main("GSApp.Elementos.PagedTableTest", args);
    }

    public void settings(){
        size(1200, 600);
        smooth(10);
    }

    public void setup(){
        // Creació de la taula
        t = new PagedTable(files, columnes);
        t.setHeaders(headers);
        t.setData(info);
        t.setColumnWidths(colWidths);

        // Creació dels botons
        b1 = new Button(this, "NEXT", 25 + tableW/2 + buttonW/1.5f, tableH + 80, buttonW, buttonH);
        b2 = new Button(this, "PREV", 25 + tableW/2 - buttonW/1.5f, tableH + 80, buttonW, buttonH);

    }

    public void draw(){
        background(255);
        fill(0); textSize(28);

        // Dibuixa la Table
        t.display(this, 50, 50, tableW, tableH);

        // Dibuixa els botons
        b1.display(this);
        b2.display(this);

        // Actualitza forma del cursor
        updateCursor();
    }

    // Modifica el cursor
    void updateCursor(){

        if((b1.mouseOverButton(this) && b1.isEnabled())||
                (b2.mouseOverButton(this) && b2.isEnabled())){
            cursor(HAND);
        }
        else {
            cursor(ARROW);
        }

    }

    // ******************* KEYBOARD interaction ***************************** //

    public void keyPressed(){
        if(keyCode==LEFT){
            t.prevPage();
        }
        else if(keyCode==RIGHT){
            t.nextPage();
        }
    }

    // ******************* MOUSE interaction ***************************** //

    public void mousePressed(){
        if(b1.mouseOverButton(this) && b1.isEnabled()){
            t.nextPage();
        }
        else if(b2.mouseOverButton(this) && b2.isEnabled()){
            t.prevPage();
        }
    }

    public void mouseDragged(){
        println("MOUSE DRAGGED");
    }

    public void mouseReleased() {
        println("MOUSE RELEASED");
    }
}
