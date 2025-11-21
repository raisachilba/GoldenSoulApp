package GSApp.Elementos;

import GSApp.Estetica.Colors;
import GSApp.Estetica.Fonts;
import processing.core.PApplet;
import processing.core.PImage;

import static GSApp.Estetica.Medidas.midaSubtitol;

public class Card {

    PImage img;
    String titol;
    TextField txtField;
    Button boto;
    float x, y, w, h;

    Fonts fontsCard;
    Colors colorsCard;

    public Card(PApplet p5, String titol, float x, float y, float w, float h){
        this.titol = titol;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.txtField = new TextField(p5, (int)x + 20, (int)(y+350), (int)w-40, 50);
        this.boto = new Button(p5, "VER", (int)x + 20, (int)(y + 420), (int)w-40, 25);

        fontsCard = new Fonts(p5);
        colorsCard = new Colors(p5);
    }

    public void display(PApplet p5){
        p5.pushStyle();
            p5.pushStyle();
                p5.fill(colorsCard.getRedColor(p5,3));
                p5.rect(x, y, w, h, 5);
            p5.popStyle();
            if (img == null){
                p5.rect(x+5, y+5, w-10, h/2);
            }
            else{
                p5.image(img, x+5, y+5, w-10, h/4);
            }

            p5.fill(colorsCard.getGoldColor(p5, 1)); p5.textSize(midaSubtitol); p5.textFont(fontsCard.getFontCards());
            p5.text(titol, x+20, y+h/2 + 70);

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
