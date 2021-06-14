package ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import io.*;

public class Crud {

    private static Connection con;
    private static PreparedStatement ps;

    public static ArrayList<ScoreData> getData(){

        ArrayList<ScoreData> data = new ArrayList<>();

        try{

            con = ConnectionDB.getDBConnection();
            ps = con.prepareStatement("SELECT * FROM TablaResultados, Equipo");
            ResultSet resultSet = ps.executeQuery();

            while(resultSet.next()){

                ScoreData score = new ScoreData();
                score.setNombre(resultSet.getString("Nombre"));
                score.setScore(resultSet.getInt("Score"));
                score.setMuertes(resultSet.getInt("Muertes"));
                score.setDate(resultSet.getString("Fecha"));
                data.add(score);
            }

            con.close();
        }catch (Exception e){
            System.out.println(e);
        }


        return data;
    }


    public static void insertResultados(int id, String fecha, int score){

        try{

            con = ConnectionDB.getDBConnection();
            ps = con.prepareStatement("INSERT INTO TablaResultados(idScore, Fecha, Score) VALUES(?,?,?)");
            ps.setInt(1,id);
            ps.setString(2,fecha);
            ps.setInt(3,score);
            ps.executeUpdate();
            con.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void insertEquipo(int id, String nombre, int muertes){

        try{

            con = ConnectionDB.getDBConnection();
            ps = con.prepareStatement("INSERT INTO Equipo(idEquipo, Nombre, Muertes) VALUES(?,?,?)");
            ps.setInt(1,id);
            ps.setString(2,nombre);
            ps.setInt(3,muertes);
            ps.executeUpdate();
            con.close();
        }catch (Exception e){
            System.out.println(e);
        }

    }

    public static void deleteEquipo(String nombre){

        try{

            con = ConnectionDB.getDBConnection();
            ps = con.prepareStatement("DELETE * FROM Equipo WHERE Nombre=?");
            ps.setString(1,nombre);
            ps.executeUpdate();
            con.close();
        }catch (Exception e){
            System.out.println(e);
        }

    }

    public static void updateEquipo(String nombre, String nuevoNombre){

        try{

            con = ConnectionDB.getDBConnection();
            ps = con.prepareStatement("UPDATE Equipo SET=? WHERE Nombre=?");
            ps.setString(1,nuevoNombre);
            ps.setString(2,nombre);
            ps.executeUpdate();
            con.close();
        }catch (Exception e){
            System.out.println(e);
        }

    }

}
