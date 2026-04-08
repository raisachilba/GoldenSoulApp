package GSApp.Data;

import java.sql.*;
import java.util.ArrayList;

public class BaseDatos {

    Connection c;
    Statement query;

    String usuario, contrasena, baseDatosNombre;

    String usuarioActual = "";
    boolean esAdmin = false;

    boolean conectado;

    public BaseDatos(String usuario, String contrasena, String baseDatosNombre){
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.baseDatosNombre = baseDatosNombre;
    }
    public void connect(){
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:8889/"+baseDatosNombre, usuario, contrasena);
            query = c.createStatement();
            System.out.println("Connectat a la BBDD! :) ");
            conectado = true;
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public int getNumFilasTabla(String tabla){
        String q = "SELECT COUNT(*) AS num FROM "+ tabla;
        try{
            ResultSet rs = query.executeQuery(q);
            rs.next();
            return rs.getInt("num");
        }
        catch(Exception e){
            System.out.println(e);
        }
        return 0;
    }

    public boolean loginCorrecte(String usuario, String password){
        String q = "SELECT COUNT(*) AS N "+
                "FROM Usuario "+
                "WHERE Usuario = '"+usuario+"' AND Contraseña ='"+password+"'";
        System.out.println(q);
        try{
            ResultSet rs = query.executeQuery(q);
            rs.next();
            int n = rs.getInt("N");
            return (n==1);
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public void guardarSesion(String usuario){

        System.out.println(">>> guardarSesion RECIBIDO: " + usuario);

        this.usuarioActual = usuario;

        System.out.println(">>> usuarioActual GUARDADO: " + this.usuarioActual);

        if(usuario.equals("admin")){
            esAdmin = true;
        } else {
            esAdmin = false;
        }

        System.out.println("Usuario actual: " + usuarioActual);
        System.out.println("Es admin: " + esAdmin);
    }

    public void insertarUsuario(String u, String n, String a, String f, String pais, String prov, String d, String c){
        String q = "INSERT INTO Usuario (Usuario, Nombre, Apellido, FechaNacimiento, Pais, Provincia, Domicilio, Contraseña) " +
                "VALUES ('"+u+"', '"+n+"', '"+a+"', '"+f+"', '"+pais+"', '"+prov+"', '"+d+"', '"+c+"')";
        System.out.println(q);
        try{
            query.execute(q);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public boolean usuarioExiste(String usuario){

        String q = "SELECT COUNT(*) AS n FROM Usuario WHERE Usuario = '"+usuario+"'";

        try{
            ResultSet rs = query.executeQuery(q);
            rs.next();

            return rs.getInt("n") > 0;

        } catch(Exception e){
            System.out.println(e);
        }

        return false;
    }

    public void reservaClase(String n, String d, String h){
        String q = "INSERT INTO Clase (Nombre, Dia, Hora) " +
                "VALUES ('"+n+"', '"+d+"', '"+h+"')";
        System.out.println(q);
        try{
            query.execute(q);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ResultSet getClasesPorDia(String fecha){
        String q = "SELECT Nombre, Hora FROM Clase WHERE Dia = '"+fecha+"'";
        try{
            return query.executeQuery(q);
        } catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    public ResultSet getCompeticionPorFecha(String fecha){
        String q = "SELECT * FROM Competicion " +
                "WHERE FechaInicio <= '"+fecha+"' AND FechaFin >= '"+fecha+"'";

        System.out.println(q);

        try{
            return query.executeQuery(q);
        } catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<Video> getVideosPorTipo(String tipo) {
        ArrayList<Video> lista = new ArrayList<>();
        String q = "SELECT Titulo, Descripcion, URL FROM Video WHERE TipoVideo_Nombre = '" + tipo + "' LIMIT 3";
        try {
            ResultSet rs = query.executeQuery(q);
            while (rs.next()) {
                String titulo = rs.getString("Titulo");
                String descripcion = rs.getString("Descripcion");
                String url = rs.getString("URL");
                lista.add(new Video(titulo, descripcion, url, tipo));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }

    public String[][] getToDos() {

        ArrayList<String[]> lista = new ArrayList<>();

        String q;

        if(esAdmin){
            q = "SELECT Usuario_Usuario, Baile, TipoToDo_Nombre, Explicacion, Estado FROM ToDo";
        } else {
            q = "SELECT Usuario_Usuario, Baile, TipoToDo_Nombre, Explicacion, Estado FROM ToDo WHERE Usuario_Usuario = '"+usuarioActual+"' ";
        }

        System.out.println("Usuario actual: " + usuarioActual);
        System.out.println("ADMIN: " + esAdmin);

        System.out.println(q);
        try{
            ResultSet rs = query.executeQuery(q);

            int count = 0;

            while(rs.next()){
                count++;

                String[] fila = new String[5];

                fila[0] = rs.getString("Usuario_Usuario");   // Nombre en Java
                fila[1] = rs.getString("Baile");
                fila[2] = rs.getString("TipoToDo_Nombre");   // Objetivo
                fila[3] = rs.getString("Explicacion");

                int estado = rs.getInt("Estado");
                if(estado == 1){
                    fila[4] = "done";
                } else {
                    fila[4] = "pending";
                }

                lista.add(fila);
            }
            System.out.println("Filas encontradas: " + count);

        } catch(Exception e){
            System.out.println(e);
        }

        String[][] datos = new String[lista.size()][5];

        for(int i=0; i<lista.size(); i++){
            datos[i] = lista.get(i);
        }

        return datos;
    }
}
