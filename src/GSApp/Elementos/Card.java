package GSApp.Elementos;

import GSApp.Estetica.Colors;
import GSApp.Estetica.Fonts;
import processing.core.PApplet;
import processing.core.PImage;

import java.awt.*;
import java.net.URI;

public class Card {

    PImage img;
    String titol;
    String descripcion;
    Button boto;
    String url;
    float x, y, w, h;

    Fonts fontsCard;
    Colors colorsCard;

    public Card(PApplet p5, String titol, String descripcion, String url, float x, float y, float w, float h, PImage img){
        this.titol = titol;
        this.descripcion = descripcion;
        this.url = url;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.img = img;
        this.boto = new Button(p5, "VER", (int)x + 20, (int)(y + 450), (int)w-40, 40);


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

            p5.fill(0); p5.textFont(fontsCard.getFontCards());

            p5.textSize(30);
            float marginX = x + 20;
            float maxWidth = w - 40;
            float titleY = y + h/4 + 160;
            float lineHeightTitle = 28;

            drawTextWrapped(p5, titol, marginX, titleY, maxWidth, lineHeightTitle);

            float descRectY = titleY + 3*lineHeightTitle-75;
            float descRectHeight = 150;

            p5.textSize(22); p5.fill(0);
            float descX = x + 20;
            float descY = descRectY + 20;
            float lineHeightDesc = 20;
            drawTextWrappedInRect(p5, descripcion, descX, descY, w - 40, descRectHeight - 20, lineHeightDesc);

            p5.pushStyle();
                p5.fill(colorsCard.getRedColor(p5, 3));
                p5.rect(x + 10, descRectY, w - 20, descRectHeight, 5);
            p5.popStyle();

            boto.display(p5);
        p5.popStyle();
    }

    public void clickMouseOnCardItems(PApplet p5){
        if(boto.mouseOverButton(p5)){
            System.out.println("Card Button clicked!!");
            try{
                Desktop.getDesktop().browse(new URI(url));
            }
            catch (Exception e){
                System.out.println(e);
            }
        }
    }

    private void drawTextWrapped(PApplet p5, String text, float x, float y, float maxWidth, float lineHeight){
        String[] palabras = text.split(" ");
        String linea = "";
        for(String palabra : palabras){
            String prueba = linea + palabra + " ";
            if(p5.textWidth(prueba) > maxWidth){
                p5.text(linea, x, y);
                linea = palabra + " ";
                y += lineHeight;
            } else {
                linea = prueba;
            }
        }
        if(linea.length() > 0){
            p5.text(linea, x, y);
        }
    }

    private void drawTextWrappedInRect(PApplet p5, String text, float x, float y, float maxWidth, float maxHeight, float lineHeight){
        String[] palabras = text.split(" ");
        String linea = "";
        float startY = y;
        for(String palabra : palabras){
            String prueba = linea + palabra + " ";
            if(p5.textWidth(prueba) > maxWidth){
                if(y + lineHeight > startY + maxHeight) break; // no se sale del rectángulo
                p5.text(linea, x, y);
                linea = palabra + " ";
                y += lineHeight;
            } else {
                linea = prueba;
            }
        }
        if(linea.length() > 0 && y + lineHeight <= startY + maxHeight){
            p5.text(linea, x, y);
        }
    }
}

