package GSApp.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BaseDatos {

    Connection c;
    Statement query;

    String usuario, contrasena, baseDatosNombre;

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

    /*public String[][] getInfoTablaToDo(String[] info){
        int nf = 5;
        String q = "SELECT Usuario, Titulo, Objetivo, Estado"+
                "FROM Usuario u, ToDo td"+
                "WHERE u.Usuario=td.usuario";
        System.out.println(q);
        try{
            ResultSet rs = query.executeQuery(q);
            int f = 0;
            while(rs.next()){
                info[f][0] = rs.getString("Usuario");
                f++;
            }
        }
        catch(Exception e){

        }
    }

     */

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
}
