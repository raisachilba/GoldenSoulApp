package GSApp.Elementos;

import processing.core.PApplet;
import processing.core.PImage;

public class Card {

    PImage img;
    String titol;
    TextField txtField;
    Button boto;
    float x, y, w, h;

    public Card(PApplet p5, String titol, float x, float y, float w, float h){
        this.titol = titol;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.txtField = new TextField(p5, (int)x + 20, (int)(y + h/2), (int)w-40, 25);
        this.boto = new Button(p5, "VER", (int)x + 20, (int)(y + h/2 + 30), (int)w-40, 25);
    }

    public void display(PApplet p5){
        p5.pushStyle();
            p5.rect(x, y, w, h, 5);
            if (img == null){
                p5.rect(x+5, y+5, w-10, h/4);
            }
            else{
                p5.image(img, x+5, y+5, w-10, h/4);
            }

            p5.fill(0);
            p5.text(titol, x+5, h/2 + 25);

            txtField.display(p5);
            boto.display(p5);
        p5.popStyle();
    }

    public void clickMouseOnCardItems(PApplet p5){
        txtField.isPressed(p5);

        if(boto.mouseOverButton(p5)){
            System.out.println("Card Button clicked!!");
        }
    }

    public void typeOnCardItems(PApplet p5){
        txtField.keyPressed(p5.key, p5.keyCode);
    }
}
