package GSApp;

import GSApp.Elementos.*;
import GSApp.Estetica.Colors;
import GSApp.Estetica.Fonts;
import GSApp.Estetica.Medidas;
import processing.core.PApplet;
import processing.core.PImage;

import static GSApp.Estetica.Medidas.midaParagraf;

public class GUI {

    public enum PANTALLA {LOGIN, SIGNIN, PRINCIPAL, COMPETICIONES, TECNICA, ELASTICIDAD, COORDINACION,TODO};

    // Botones: entrar, registrarse, volver a la pantalla principal, si no tienes cuenta regístrate, paged table
    Button b1, b2, b3, b4, bPrev, bNext;
    Button[] bMenu;

    PImage perfil, logoLogIn, logoPrincipal, logoBotonesEntradas, fotoLogIn, fotoSignIn, fotoBanner; //Load image perque es png

    TextList tList;
    String[] provincias = { "Alicante", "Asturias", "Barcelona", "Cádiz", "Granada",
            "Guipúzcoa","Islas Baleares","Islas Canarias", "La Coruña", "Madrid", "Málaga", "Murcia",
            "Sevilla", "Toledo", "Valencia", "Valladolid", "Vizcaya", "Zaragoza"};

    Colors c;
    Fonts fontsApp;

    //Text field; introducir usuario y contraseña
    TextField[] textFields;

    Medidas medida;

    CalendarPlus calendario;

    Card[] videoExplica;

    PagedTable tablaToDo;
    String[] headers = {"Nombre","Baile", "Objetivo", "Explicación", "Estado"};
    int[] colWidth = {50, 30, 40, 100, 30};
    String[][] info = {
            {"Pere Soler", "Rumba", "Personal", "...", "Done"},
            {"Pere Soler", "Rumba", "Personal", "...", "Done"},
            {"Pere Soler", "Rumba", "Personal", "...", "Done"},
            {"Pere Soler", "Rumba", "Personal", "...", "Done"},
            {"Pere Soler", "Rumba", "Personal", "...", "Done"},
            {"Pere Soler", "Rumba", "Personal", "...", "Done"},
            {"Pere Soler", "Rumba", "Personal", "...", "Done"},
            {"Pere Soler", "Rumba", "Personal", "...", "Done"},
    };

    public PANTALLA pantallaActual;

    public GUI(PApplet p5) {
        b1 = new Button(p5, "ENTRAR", p5.width/2+250, 600, 250, 90);
        b2 = new Button(p5, "REGISTRARSE", p5.width/2+250, 860, 250, 70);
        b3 = new Button(p5, "VOLVER ATRÁS", 50, 860, 170, 30);
        b4 = new Button(p5, "No tienes cuenta? Regístrate", p5.width/2+350, 800, 300, 80);
        bNext = new Button(p5, "NEXT", 200, 250, 70, 30);
        bPrev = new Button(p5, "PREV", 100, 250, 70, 30 );
        setButtons(p5);
        calendario = new CalendarPlus(p5, 800, 350, 600, 600);
        setPagedTable(p5);

        this.dibujaVideoExplica(p5);

        c = new Colors(p5);
        medida = new Medidas();
        fontsApp = new Fonts(p5);

        perfil = p5.loadImage("data/Icones/FotoPerfil.png");
        logoLogIn = p5.loadImage("data/Logo/LogoLogInNegro.png");
        logoPrincipal = p5.loadImage("data/Logo/LogoPantallaPrincipalN.png");
        logoBotonesEntradas = p5.loadImage("data/Logo/LogoCompeticionesGranate.png");

        fotoLogIn = p5.loadImage("data/Fotos/LatinoJive.png");
        fotoSignIn = p5.loadImage("data/Fotos/Ballroom1.png");
        fotoBanner = p5.loadImage("data/Fotos/BallroomExtended.png");

        this.setTextField(p5);

        tList = new TextList(p5, provincias, p5.width/2+170, 580, 400, 50);

        pantallaActual = PANTALLA.LOGIN;
    }

    //Fet, posar que si es pitja el boto d'entrar vagi a la pantalla principal.
    public void dibujaPantallaLogIn(PApplet p5){
        p5.background(255);

        p5.pushStyle();
            p5.fill(c.getRedColor(p5, 1));
            p5.rectMode(p5.CORNER);
            p5.rect(p5.width/2+170, 260, 400, 80);
            p5.textAlign(p5.CENTER); p5.fill(c.getGoldColor(p5, 1)); p5.textFont(fontsApp.getFontLogIn()); //Ús de fonts
            p5.text("LOG IN", 1110, 320);
        p5.popStyle();

        //p5.rect(0, 0, p5.width/2, p5.height);
        p5.pushMatrix();
            p5.scale(1.49f,1.5f);
            p5.image(fotoLogIn, 0,0);
        p5.popMatrix();

        p5.pushMatrix();
            p5.translate(p5.width/2, p5.height/2);
            p5.pushStyle();
                p5.fill(10);
                p5.triangle(0, -100, 80, 0, 0, 100);
            p5.popStyle();
        p5.popMatrix();

        p5.pushStyle();
            p5.fill(0); p5.textSize(midaParagraf);
            p5.text("Usuario", p5.width/2+170, 390);
            p5.text("Contraseña", p5.width/2+170, 490);
        p5.popStyle();

        textFields[0].display(p5); textFields[1].display(p5);

        b1.display(p5); b4.display(p5);

        //Logo
        p5.image(logoLogIn, 200, 330);
    }

    //Fet, posar que si es pitja el boto d'entrar vagi a la pantalla principal.
    public void dibujaPantallaSignIn(PApplet p5){
        p5.background(255);
        p5.pushStyle();
            p5.fill(c.getRedColor(p5, 1));
            p5.rect(p5.width/2+170, 50, 400, 80);
            p5.textAlign(p5.CENTER); p5.fill(c.getGoldColor(p5, 1)); p5.textFont(fontsApp.getFontLogIn()); //Ús de fonts
            p5.text("SIGN IN", 1110, 110);
        p5.popStyle();

        p5.pushMatrix();
            p5.scale(0.618f,0.71f);
            p5.image(fotoSignIn,-10,0);
        p5.popMatrix();

        p5.pushMatrix();
            p5.translate(p5.width/2, p5.height/2);
            p5.pushStyle();
                p5.fill(10);
                p5.triangle(0, -100, 80, 0, 0, 100);
            p5.popStyle();
        p5.popMatrix();

        p5.pushStyle();
            p5.fill(0); p5.textSize(midaParagraf);
            p5.text("Nombre", p5.width/2+170, 170);
            p5.text("Apellidos", p5.width/2+170, 270);
            p5.text("Fecha nacimiento", p5.width/2+170, 370);
            p5.text("País", p5.width/2+170, 470);
            p5.text("Província", p5.width/2+170, 570);
            p5.text("Domicilio", p5.width/2+170, 670);
            p5.text("Contraseña", p5.width/2+170, 770);
        p5.popStyle();

        textFields[2].display(p5); textFields[3].display(p5); textFields[4].display(p5); textFields[5].display(p5);
        textFields[6].display(p5); textFields[7].display(p5);

        b2.display(p5);

        //Logo
        p5.image(logoLogIn, 200, 330);

        tList.display(p5);
    }

    public void dibujaPantallaPrincipal(PApplet p5){
        p5.background(240);

        dibujaLogoBanner(p5);

        dibujaBotonesMenu(p5);

        calendario.display(p5);

        p5.pushStyle();
            p5.textAlign(p5.CENTER);
            p5.textSize(medida.midaTitol); p5.fill(c.getGoldColor(p5, 1)); p5.textFont(fontsApp.getFontTitulo()); //ÚS FONTS
            p5.text("GOLDEN SOUL DANCE", 800, 130);
        p5.popStyle();

        p5.pushMatrix();
            p5.scale(0.1f, 0.1f);
            p5.image(perfil, 600, 100);
        p5.popMatrix();
    }

    public void dibujaPantallaCalendComps(PApplet p5){
        p5.background(240);

        dibujaLogoBanner(p5);

        calendario.display(p5);

        p5.pushStyle();
            p5.textAlign(p5.CENTER);
            p5.textSize(medida.midaTitol); p5.fill(c.getGoldColor(p5, 1));
            p5.textFont(fontsApp.getFontTitulosPantallaBotones()); //ÚS FONTS
            p5.text("CALENDARIO COMPETICIONES", 800, 130);
        p5.popStyle();

        dibujaBotonesMenu(p5);
        b3.display(p5);
    }

    public void dibujaPantallaTecnica(PApplet p5){
        p5.background(240);

        dibujaLogoBanner(p5);

        b3.display(p5);

        dibujaVideoExplica(p5);

        p5.pushStyle();
            p5.textAlign(p5.CENTER);
            p5.textSize(medida.midaTitol); p5.fill(c.getGoldColor(p5, 1));
            p5.textFont(fontsApp.getFontTitulosPantallaBotones()); //ÚS FONTS
            p5.text("TÉCNICA", 800, 130);
        p5.popStyle();
    }

    public void dibujaPantallaElasticidad(PApplet p5){
        p5.background(240);

        dibujaLogoBanner(p5);

        b3.display(p5);

        dibujaVideoExplica(p5);

        p5.pushStyle();
            p5.textAlign(p5.CENTER);
            p5.textSize(medida.midaTitol); p5.fill(c.getGoldColor(p5, 1));
            p5.textFont(fontsApp.getFontTitulosPantallaBotones()); //ÚS FONTS
            p5.text("ELASTICIDAD", 800, 130);
        p5.popStyle();
    }

    public void dibujaPantallaCoordinacion(PApplet p5){
        p5.background(240);

        dibujaLogoBanner(p5);

        b3.display(p5);

        dibujaVideoExplica(p5);

        p5.pushStyle();
            p5.textAlign(p5.CENTER);
            p5.textSize(medida.midaTitol); p5.fill(c.getGoldColor(p5, 1));
            p5.textFont(fontsApp.getFontTitulosPantallaBotones()); //ÚS FONTS
            p5.text("COORDINACIÓN", 800, 130);
        p5.popStyle();
    }

    //LA PAGED TABLE NO ES DIBUIXA TAL COM TOCA
    public void dibujaPantalllaToDo(PApplet p5){

        p5.background(240);

        dibujaLogoBanner(p5);

        b3.display(p5);

        p5.pushStyle();
            p5.textAlign(p5.CENTER);
            p5.textSize(medida.midaTitol); p5.fill(c.getGoldColor(p5, 1));
            p5.textFont(fontsApp.getFontTitulosPantallaBotones()); //ÚS FONTS
            p5.text("TO-DO LIST", 800, 130);
        p5.popStyle();

        setPagedTable(p5);
        bNext.display(p5);
        bPrev.display(p5);
    }

    public void setButtons(PApplet p5){
        bMenu = new Button[7];

        bMenu[0] = new Button(p5,"COMPETICIONES", 70, 360, 180, 160);
        bMenu[1] = new Button(p5,"TÉCNICA", 260, 360, 180, 160);
        bMenu[2] = new Button(p5,"ELASTICIDAD", 450, 360, 180, 160);
        bMenu[3] = new Button(p5,"COORDINACIÓN", 70, 590, 180, 160);
        bMenu[4] = new Button(p5,"TO-DO LIST", 260, 590, 180, 160);
        bMenu[5] = new Button(p5,"CALENDARIO", 450, 590, 180, 160);
    }

    public void dibujaBotonesMenu(PApplet p5){
        bMenu[0].display(p5);
        bMenu[1].display(p5);
        bMenu[2].display(p5);
        bMenu[3].display(p5);
        bMenu[4].display(p5);
        bMenu[5].display(p5);

    }

    public void dibujaLogoBanner(PApplet p5){

        p5.image(fotoBanner, 220, 0);

        if(pantallaActual == PANTALLA.PRINCIPAL) {
            p5.image(logoPrincipal, 0, 0);
        }
        else if (pantallaActual == PANTALLA.COMPETICIONES){
            p5.image(logoBotonesEntradas, 0, 0);
        }
        else if (pantallaActual == PANTALLA.TECNICA){
            p5.image(logoBotonesEntradas, 0, 0);
        }
        else if (pantallaActual == PANTALLA.ELASTICIDAD){
            p5.image(logoBotonesEntradas, 0, 0);
        }
        else if (pantallaActual == PANTALLA.COORDINACION){
            p5.image(logoBotonesEntradas, 0, 0);
        }
        else if (pantallaActual == PANTALLA.TODO){
            p5.image(logoBotonesEntradas, 0, 0);
        }

    }

    public void setTextField(PApplet p5){
        textFields = new TextField[8];

        //Text field LOG IN
        textFields[0] = new TextField(p5, p5.width/2+170, 400, 400, 60);
        textFields[1] = new TextField(p5, p5.width/2+170, 500, 400, 60);

        //Text field SIGN IN
        textFields[2] = new TextField(p5, p5.width/2+170, 180, 400, 50);
        textFields[3] = new TextField(p5, p5.width/2+170, 280, 400, 50);
        textFields[4] = new TextField(p5, p5.width/2+170, 380, 400, 50);
        textFields[5] = new TextField(p5, p5.width/2+170, 480, 400, 50);
        textFields[6] = new TextField(p5, p5.width/2+170, 680, 400, 50);
        textFields[7] = new TextField(p5, p5.width/2+170, 780, 400, 50);
    }

    public void dibujaVideoExplica(PApplet p5){

        p5.pushStyle();
            videoExplica = new Card[3];
            videoExplica[0] = new Card(p5, "VÍDEO 1", 100, 320, 400, 500);
            videoExplica[1] = new Card(p5, "VÍDEO 2", 550, 320, 400, 500);
            videoExplica[2] = new Card(p5, "VÍDEO 3", 1000, 320, 400, 500);

            videoExplica[0].display(p5); videoExplica[1].display(p5); videoExplica[2].display(p5);
        p5.popStyle();
    }

    public void setPagedTable(PApplet p5){
        tablaToDo = new PagedTable(5, 4);
        tablaToDo.setHeaders(headers);
        tablaToDo.setColumnWidths(colWidth);
        tablaToDo.setData(info);
        tablaToDo.display(p5, 100, 300, 700, 500);
    }

}
