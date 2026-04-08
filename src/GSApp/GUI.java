package GSApp;

import GSApp.Data.BaseDatos;
import GSApp.Data.Competicion;
import GSApp.Data.DateConversion;
import GSApp.Data.Video;
import GSApp.Elementos.*;
import GSApp.Estetica.Colors;
import GSApp.Estetica.Fonts;
import GSApp.Estetica.Medidas;
import processing.core.PApplet;
import processing.core.PImage;

import java.sql.ResultSet;
import java.util.ArrayList;

import static GSApp.Estetica.Medidas.*;

public class GUI {

    //Base de datos
    BaseDatos bd;

    public enum PANTALLA {LOGIN, SIGNUP, PRINCIPAL, COMPETICIONES, TECNICA, ELASTICIDAD, COORDINACION, TODO, HORAS};

    // Botones: entrar, registrarse, volver a la pantalla principal, si no tienes cuenta regístrate, paged table, clase
    Button b1, b2, b3, b4, bPrev, bNext, b5;
    Button[] bMenu;

    PImage logoLogIn, logoPrincipal, logoBotonesEntradas, fotoLogIn, fotoSignIn, fotoBanner,
            tecnica1, tecnica2, tecnica3, elasticidad1, elasticidad2, elasticidad3, coordinacion1, coordinacion2, coordinacion3;

    TextList tList;
    String[] provincias = {"Alicante", "Asturias", "Barcelona", "Cádiz", "Girona", "Granada",
            "Guipúzcoa","Islas Baleares","Islas Canarias", "La Coruña", "Madrid", "Málaga", "Murcia",
            "Sevilla", "Toledo", "Valencia", "Valladolid", "Vizcaya", "Zaragoza"};

    Colors c;
    Fonts fontsApp;

    //Text Field log in, sign up
    TextField[] textFields;
    TextField[] txtFieldInfoClase;

    CheckBox[] horas;

    Medidas medida;

    CalendarPlus calendario;

    Card[] videoExplica = new Card[9];

    PagedTable tablaToDo;
    String[] headers = {"Nombre","Baile", "Objetivo", "Explicación", "Estado"};
    float[] colWidth = {20, 15, 20, 35, 10};

    Table clases;
    String[] headerClase = {"Hora", "Nombre"};
    float[] colWidthClase = {40, 60};
    String[][] infoClase = {
            {"9:00", ""},
            {"9:45", ""},
            {"10:30", ""},
            {"17:30", ""},
            {"18:15", ""},
            {"19:00", ""},
            {"19:45", ""},
    };

    Competicion compActual;

    public PANTALLA pantallaActual;

    public GUI(PApplet p5, BaseDatos bd) {
        this.bd = bd;

        p5.scale(0, 2f);
        tecnica1 = p5.loadImage("data/Fotos/Tecnica1.png");
        System.out.println(tecnica1);
        tecnica2 = p5.loadImage("data/Fotos/Tecnica2.png");
        tecnica3 = p5.loadImage("data/Fotos/Tecnica3.png");
        elasticidad1 = p5.loadImage("data/Fotos/Elasticidad1.png");
        elasticidad2 = p5.loadImage("data/Fotos/Elasticidad2.png");
        elasticidad3 = p5.loadImage("data/Fotos/Elasticidad3.png");
        coordinacion1 = p5.loadImage("data/Fotos/Coordinacion1.png");
        coordinacion2 = p5.loadImage("data/Fotos/Coordinacion2.png");
        coordinacion3 = p5.loadImage("data/Fotos/Coordinacion3.png");

        dibujaVideoExplica(p5, bd);

        c = new Colors(p5);

        b1 = new Button(p5, "ENTRAR", p5.width/2+250, 600, 250, 90);
        b2 = new Button(p5, "REGISTRARSE", p5.width/2+250, 860, 250, 70);
        b3 = new Button(p5, "VOLVER ATRÁS", 50, 860, 170, 30);
        b4 = new Button(p5, "No tienes cuenta? Regístrate", p5.width/2+350, 800, 300, 80);
        b5 = new Button(p5, "RESERVA AHORA", p5.width/2, 790, 170, 50);
        bNext = new Button(p5, "NEXT", 200, 250, 70, 30);
        bPrev = new Button(p5, "PREV", 100, 250, 70, 30 );
        setButtons(p5);

        calendario = new CalendarPlus(p5, 800, 350, 600, 600, c);
        setPagedTable(p5);
        setTablaClases(p5);

        medida = new Medidas();
        fontsApp = new Fonts(p5);

        logoLogIn = p5.loadImage("data/Logo/LogoLogInNegro.png");
        logoPrincipal = p5.loadImage("data/Logo/LogoPantallaPrincipalN.png");
        logoBotonesEntradas = p5.loadImage("data/Logo/LogoCompeticionesGranate.png");

        fotoLogIn = p5.loadImage("data/Fotos/LatinoJive.png");
        fotoSignIn = p5.loadImage("data/Fotos/Ballroom1.png");
        fotoBanner = p5.loadImage("data/Fotos/BallroomExtended.png");

        this.setTextField(p5);
        this.setTxtFieldInfoClase(p5);

        tList = new TextList(p5, provincias, p5.width/2+170, 620, 400, 50);

        this.setCheckBoxHoras(p5);

        pantallaActual = PANTALLA.LOGIN;
    }

    public void dibujaPantallaLogIn(PApplet p5){
        p5.background(230);

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

    public void dibujaPantallaSignUp(PApplet p5){
        p5.background(230);
        p5.pushStyle();
            p5.fill(c.getRedColor(p5, 1));
            p5.rect(p5.width/2+170, 40, 400, 80);
            p5.textAlign(p5.CENTER); p5.fill(c.getGoldColor(p5, 1)); p5.textFont(fontsApp.getFontLogIn()); //Ús de fonts
            p5.text("SIGN IN", 1110, 100);
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
            p5.text("Usuario", p5.width/2+170, 160);
            p5.text("Nombre", p5.width/2+170, 250);
            p5.text("Apellidos", p5.width/2+170, 340);
            p5.text("Fecha nacimiento", p5.width/2+170, 430);
            p5.text("País", p5.width/2+170, 520);
            p5.text("Província", p5.width/2+170, 610);
            p5.text("Domicilio", p5.width/2+170, 700);
            p5.text("Contraseña", p5.width/2+170, 790);
        p5.popStyle();

        textFields[2].display(p5); textFields[3].display(p5); textFields[4].display(p5); textFields[5].display(p5);
        textFields[6].display(p5); textFields[7].display(p5); textFields[8].display(p5);

        b2.display(p5);

        //Logo
        p5.image(logoLogIn, 200, 330);

        tList.display(p5);
    }

    public void dibujaPantallaPrincipal(PApplet p5){
        p5.background(230);

        dibujaLogoBanner(p5);

        dibujaBotonesMenu(p5);

        calendario.display(p5);

        p5.pushStyle();
            p5.textAlign(p5.CENTER);
            p5.fill(c.getGoldColor(p5, 1)); p5.textFont(fontsApp.getFontTitulo());
            p5.text("GOLDEN SOUL DANCE", 800, 130);
        p5.popStyle();
    }

    public void dibujaHorasClases(PApplet p5){
        p5.background(230);

        dibujaLogoBanner(p5);

        p5.pushStyle();
            p5.textAlign(p5.CENTER);
            p5.fill(c.getGoldColor(p5, 1)); p5.textFont(fontsApp.getFontTitulo());
            p5.text("RESERVA CLASE", 800, 130);
        p5.popStyle();

        b3.display(p5);
        b5.display(p5);

        clases.display(p5, 100, 300, 400, 500);

        txtFieldInfoClase[0].display(p5);
        txtFieldInfoClase[1].display(p5);

        String fecha = calendario.getSelectedDate();

        p5.pushStyle();
            p5.fill(c.getRedColor(p5, 3));
            p5.rect(p5.width/2, 720, 400, 50, 5);
            p5.pushStyle();
                p5.textSize(midaText); p5.fill(0);
                p5.text(fecha, p5.width/2+10, 750);
            p5.popStyle();
        p5.popStyle();

        p5.pushStyle();
            p5.fill(c.getGoldColor(p5, 1)); p5.textFont(fontsApp.getFontHoras()); p5.strokeWeight(5);
            p5.text("Clases cogidas", 100, 280);
            p5.text("Reserva clase", p5.width/2, 280);

            p5.pushStyle();
                p5.textSize(midaParagraf); p5.strokeWeight(5);
                p5.text(fecha, 410, 280);
            p5.popStyle();

            p5.pushStyle();
                p5.textSize(midaParagraf); p5.strokeWeight(5); p5.fill(0);
                p5.text("Nombre", p5.width/2, 320);
                p5.text("Tipo de clase", p5.width/2, 420);
                p5.text("Hora", p5.width/2, 520);
                p5.text("Fecha", p5.width/2, 710);
            p5.popStyle();
        p5.popStyle();

        horas[0].display(p5); horas[1].display(p5); horas[2].display(p5); horas[3].display(p5);
        horas[4].display(p5); horas[5].display(p5); horas[6].display(p5);

        p5.pushStyle();
            p5.fill(0); p5.textSize(midaHoras);
            p5.text("9:00", p5.width/2+30, 557);
            p5.text("9:45", p5.width/2+30, 597);
            p5.text("10:30", p5.width/2+30, 637);
            p5.text("17:30", p5.width/2+30, 677);
            p5.text("18:15", p5.width/2+270, 557);
            p5.text("19:00", p5.width/2+270, 597);
            p5.text("19:45", p5.width/2+270, 637);
        p5.popStyle();
    }

    public void dibujaPantallaCalendComps(PApplet p5){
        p5.background(230);

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

        if(calendario.isDateSelected()){
            String fechaOg = calendario.getSelectedDate();
            String fechaSQL = DateConversion.formataFechaEng(fechaOg);

            compActual = cargarCompeticionDelDia(fechaSQL, bd);

            p5.pushStyle();
                p5.rectMode(p5.CORNER); p5.fill(c.getRedColor(p5, 1)); p5.stroke(c.getGoldColor(p5, 1)); p5.strokeWeight(5);
                p5.rect(400, 350, 350, 300, 5);
            p5.popStyle();

            if(compActual != null) {
                p5.pushStyle();
                    p5.fill(c.getGoldColor(p5, 1)); p5.textAlign(p5.LEFT, p5.TOP); p5.textFont(fontsApp.getFontComp());
                    p5.text(compActual.nombre, 410, 370, 320, 120);
                p5.popStyle();

                p5.pushStyle();
                    p5.textSize(midaParagraf); p5.fill(c.getGoldColor(p5, 1));
                    if(compActual.fechaInicio.equals(compActual.fechaFin)){
                        p5.text(compActual.fechaInicio, 410, 470);
                    }
                    else{
                        p5.text(compActual.fechaInicio+" - "+compActual.fechaFin, 410, 470);
                    }
                    p5.text("Federación: "+compActual.federacion, 410, 500);
                    p5.text("Organizador: "+compActual.organizador, 410, 530);
                    p5.text(compActual.lugar, 410, 570);
                    p5.pushStyle();
                        p5.textSize(midaText); p5.fill(150);
                        p5.text(compActual.ubicacion, 410, 590);
                    p5.popStyle();
                    p5.text("Fin de registro: "+compActual.finRegistro, 410, 630);
                p5.popStyle();
            }
            else{
                p5.pushStyle();
                    p5.fill(c.getGoldColor(p5, 1)); p5.textAlign(p5.CENTER); p5.textSize(midaParagraf);
                    p5.text("No hay competición disponible", 575, 500);
                p5.popStyle();
            }
        }
    }

    public void dibujaPantallaTecnica(PApplet p5){
        p5.background(230);

        dibujaLogoBanner(p5);

        b3.display(p5);

        for(int i=0; i<3;i++){
            if(videoExplica[i]!=null){
                videoExplica[i].display(p5);
            }
        }
        p5.pushStyle();
            p5.textAlign(p5.CENTER);
            p5.textSize(medida.midaTitol); p5.fill(c.getGoldColor(p5, 1));
            p5.textFont(fontsApp.getFontTitulosPantallaBotones());
            p5.text("TÉCNICA", 800, 130);
        p5.popStyle();
    }

    public void dibujaPantallaElasticidad(PApplet p5){
        p5.background(230);

        dibujaLogoBanner(p5);

        b3.display(p5);

        for(int i = 3; i < 6; i++){
            if(videoExplica[i] != null){
                videoExplica[i].display(p5);
            }
        }

        p5.pushStyle();
            p5.textAlign(p5.CENTER);
            p5.textSize(medida.midaTitol); p5.fill(c.getGoldColor(p5, 1));
            p5.textFont(fontsApp.getFontTitulosPantallaBotones()); //ÚS FONTS
            p5.text("ELASTICIDAD", 800, 130);
        p5.popStyle();
    }

    public void dibujaPantallaCoordinacion(PApplet p5){
        p5.background(230);

        dibujaLogoBanner(p5);

        b3.display(p5);

        for(int i = 6; i < 9; i++){
            if(videoExplica[i] != null){
                videoExplica[i].display(p5);
            }
        }

        p5.pushStyle();
            p5.textAlign(p5.CENTER);
            p5.textSize(medida.midaTitol); p5.fill(c.getGoldColor(p5, 1));
            p5.textFont(fontsApp.getFontTitulosPantallaBotones());
            p5.text("COORDINACIÓN", 800, 130);
        p5.popStyle();
    }

    public void dibujaPantalllaToDo(PApplet p5){

        p5.background(230);

        dibujaLogoBanner(p5);

        b3.display(p5);

        p5.pushStyle();
            p5.textAlign(p5.CENTER);
            p5.textSize(medida.midaTitol); p5.fill(c.getGoldColor(p5, 1));
            p5.textFont(fontsApp.getFontTitulosPantallaBotones());
            p5.text("TO-DO LIST", 800, 130);
        p5.popStyle();

        tablaToDo.display(p5, 100, 300, 1250, 500);

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
        else if(pantallaActual == PANTALLA.HORAS) {
            p5.image(logoPrincipal, 0, 0);
        }
    }

    public void setTextField(PApplet p5){
        textFields = new TextField[9];

        //Text field LOG IN
        textFields[0] = new TextField(p5, p5.width/2+170, 400, 400, 60);
        textFields[1] = new TextField(p5, p5.width/2+170, 500, 400, 60);

        //Text field SIGN IN
        textFields[2] = new TextField(p5, p5.width/2+170, 170, 400, 50);
        textFields[3] = new TextField(p5, p5.width/2+170, 260, 400, 50);
        textFields[4] = new TextField(p5, p5.width/2+170, 350, 400, 50);
        textFields[5] = new TextField(p5, p5.width/2+170, 440, 400, 50);
        textFields[6] = new TextField(p5, p5.width/2+170, 530, 400, 50);
        textFields[7] = new TextField(p5, p5.width/2+170, 710, 400, 50);
        textFields[8] = new TextField(p5, p5.width/2+170, 800, 400, 50);
    }

    public void dibujaVideoExplica(PApplet p5, BaseDatos bd){
        ArrayList<Video> tecnica = bd.getVideosPorTipo("tecnica");
        ArrayList<Video> elasticidad = bd.getVideosPorTipo("elasticidad");
        ArrayList<Video> coordinacion = bd.getVideosPorTipo("coordinacion");

        float w = 400, h = 500;
        float y = 320;

        // Técnica
        for(int i = 0; i < tecnica.size() && i < 3; i++){
            float x = 100 + i*(w + 50);
            Video v = tecnica.get(i);
            if(i==0) {
                videoExplica[i] = new Card(p5, v.titulo, v.descripcion, v.url, x, y, w, h, tecnica1);
            }
            else if(i==1) {
                videoExplica[i] = new Card(p5, v.titulo, v.descripcion, v.url, x, y, w, h, tecnica2);
            }
            else if(i==2) {
                videoExplica[i] = new Card(p5, v.titulo, v.descripcion, v.url, x, y, w, h, tecnica3);
            }
        }

        // Elasticidad
        for(int i = 0; i < elasticidad.size() && i < 3; i++){
            float x = 100 + i*(w + 50);
            Video v = elasticidad.get(i);
            if(i==0){
                videoExplica[i + 3] = new Card(p5, v.titulo, v.descripcion, v.url, x, y, w, h, elasticidad1);
            }
            else if(i==1){
                videoExplica[i + 3] = new Card(p5, v.titulo, v.descripcion, v.url, x, y, w, h, elasticidad2);
            }
            else if(i==2){
                videoExplica[i + 3] = new Card(p5, v.titulo, v.descripcion, v.url, x, y, w, h, elasticidad3);
            }
        }

        // Coordinación
        for(int i = 0; i < coordinacion.size() && i < 3; i++){
            float x = 100 + i*(w + 50);
            Video v = coordinacion.get(i);
            if(i==0){
                videoExplica[i + 6] = new Card(p5, v.titulo, v.descripcion, v.url, x, y, w, h, coordinacion1);
            }
            else if(i==1){
                videoExplica[i + 6] = new Card(p5, v.titulo, v.descripcion, v.url, x, y, w, h, coordinacion2);
            }
            else if(i==2){
                videoExplica[i + 6] = new Card(p5, v.titulo, v.descripcion, v.url, x, y, w, h, coordinacion3);
            }
        }
    }

    public void setPagedTable(PApplet p5){
        tablaToDo = new PagedTable(p5,4, 5);
        tablaToDo.setHeaders(headers);
        tablaToDo.setColumnWidths(colWidth);
        String[][] datos = bd.getToDos();
        tablaToDo.setData(datos);
    }

    public void setTablaClases(PApplet p5){
        clases = new Table(p5,8, 2);
        clases.setColumnWidths(colWidthClase);
        clases.setHeaders(headerClase);
        clases.setData(infoClase);
    }

    public void setTxtFieldInfoClase(PApplet p5) {
        txtFieldInfoClase = new TextField[2];

        txtFieldInfoClase[0] = new TextField(p5, p5.width/2, 330, 400, 50);
        txtFieldInfoClase[1] = new TextField(p5, p5.width/2, 430, 400, 50);
    }

    public void setCheckBoxHoras(PApplet p5){
        horas = new CheckBox[7];

        horas[0] = new CheckBox(p5, p5.width/2+10, 550, 20);
        horas[1] = new CheckBox(p5, p5.width/2+10, 590, 20);
        horas[2] = new CheckBox(p5, p5.width/2+10, 630, 20);
        horas[3] = new CheckBox(p5, p5.width/2+10, 670, 20);

        horas[4] = new CheckBox(p5, p5.width/2+250, 550, 20);
        horas[5] = new CheckBox(p5, p5.width/2+250, 590, 20);
        horas[6] = new CheckBox(p5, p5.width/2+250, 630, 20);
    }

    public void cargarClasesDelDia(String fecha, BaseDatos bd){
        for(int i = 0; i < infoClase.length; i++){
            infoClase[i][1] = "";
        }

        try {
            ResultSet rs = bd.getClasesPorDia(fecha);

            while(rs.next()){
                String nombre = rs.getString("Nombre");
                String hora = rs.getString("Hora");

                hora = hora.substring(0,5);
                if(hora.startsWith("0")){
                    hora = hora.substring(1);
                }

                for(int i = 0; i < infoClase.length; i++){
                    if(infoClase[i][0].equals(hora)){
                        infoClase[i][1] = nombre;
                    }
                }
            }
            clases.setData(infoClase);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public Competicion cargarCompeticionDelDia(String fechaSQL, BaseDatos db){
        try{
            ResultSet rs = db.getCompeticionPorFecha(fechaSQL);
            if(rs.next()){
                System.out.println("SI hay competición");

                return new Competicion(
                        rs.getString("Nombre"),
                        DateConversion.formataFechaEsp(rs.getString("FechaInicio")),
                        DateConversion.formataFechaEsp(rs.getString("FechaFin")),
                        rs.getString("Federacion"),
                        rs.getString("Organizador"),
                        rs.getString("Lugar"),
                        rs.getString("Ubicacion"),
                        DateConversion.formataFechaEsp(rs.getString("FinRegistro"))
                );
            }
            else{
                System.out.println("NO hay competición");
            }
        } catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
}
