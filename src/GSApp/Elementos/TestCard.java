package GSApp.Elementos;

import processing.core.PApplet;

public class TestCard extends PApplet{

    Card c1;

    public static void main(String[] args) {
        PApplet.main("GSApp.Elementos.TestCard");
    }

    public void settings(){
        size(800, 800);
    }

    public void setup(){
        c1 = new Card(this, "Hello card", 100, 100, 300, 400);
    }

    public void draw(){
        background(255);
        c1.display(this);
    }

    public void mousePressed(){
        c1.clickMouseOnCardItems(this);
    }

    public void keyPressed(){
        c1.typeOnCardItems(this);
    }
}
