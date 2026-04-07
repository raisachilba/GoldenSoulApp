package GSApp.Elementos;

import processing.core.PApplet;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HyperLink {

    Desktop desktop;

    String text;
    String url;

    float x, y, w;
    float textSize;

    public HyperLink(Desktop d, String text, String url, float x, float y, float textSize){

        this.desktop = d;
        this.text = text;
        this.url = url;

        this.x = x; this.y = y;
        this.textSize = textSize;
        this.w = text.length() * textSize/1.75f;  // Adaptar segons el tipus de lletra
    }

    public void display(PApplet p5){
        p5.pushMatrix();
        p5.strokeWeight(2);
        if(mouseOverText(p5)){
            p5.fill(255, 0, 0); p5.stroke(255, 0, 0);
        }
        else {
            p5.fill(0, 0, 255); p5.stroke(0, 0, 255);
        }
        p5.textSize(textSize);
        p5.text(this.text, this.x, this.y);
        p5.line(this.x, this.y+2, this.x + this.w, this.y+2);
        p5.popMatrix();
    }

    public boolean mouseOverText(PApplet p5){
        return (p5.mouseX>= this.x) && (p5.mouseX < (this.x + this.w)) && (p5.mouseY>= (this.y - this.textSize)) && (p5.mouseY<= this.y);
    }

    public void openWebPage() {
        try {
            URI site = new URI(url);
            if (Desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.BROWSE)) {
                desktop.browse(site);
            } else {
                System.out.println("App no suporta el navegador");
            }
        } catch(URISyntaxException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
