package GSApp;

import processing.core.PApplet;

public class Colors {

    int[] colors;

    public Colors(PApplet p5){ this.setColors(p5); }

    void setColors(PApplet p5){
        this.colors = new int[5];
        this.colors[0] = p5.color(0xFF7A2A3A);
        this.colors[1] = p5.color(0x7A2A3AAA);
        this.colors[2] = p5.color(0x7A2A3AAA);
        this.colors[3] = p5.color(0x7A2A3AAA);
        this.colors[4] = p5.color(0x7A2A3AAA);
    }

    // Getter del número de colors
    int getNumColors(){
        return this.colors.length;
    }

    // Getter del color primari
    int getRedColor(PApplet p5, int i){
        int red1 = p5.color(0xFF7A2A3A); //Granate, opacidad al máximo
        int red2 = p5.color(0xAA7A2A3A); //Granate, menor opacidad
        if(i==1) {
            return red1;
        }
        else if(i==2){
            return red2;
        }
        return (255);
    }

    // Getter del color secundari
    int getSecondColor(){
        return  this.colors[1];
    }

    // Getter del color terciari
    int getThirdColor(){
        return  this.colors[2];
    }

    // Getter del color i-èssim
    int getColorAt(int i){
        return this.colors[i];
    }

    public void display(PApplet p5, float x, float y, float w){
        p5.pushStyle();
        p5.fill(0); p5.textAlign(p5.LEFT); p5.textSize(36);
        float wc = w /getNumColors();
        for(int i=0;i<getNumColors();i++){
            p5.fill(getColorAt(i));p5.stroke(0); p5.strokeWeight(3);
            p5.rect(x + i*wc, y, wc, wc);
        }
        p5.popStyle();
    }
}
