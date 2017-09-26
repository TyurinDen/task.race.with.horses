package Khasang.Java.Level0.lesson6;

import Khasang.Java.Level0.DB.DataBaseSQLite;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class App {
    private static final String DATA_BASE = "jdbc:sqlite:DB//Horses.db";
    private static final String DB_USER = "user";
    private static final String DB_PASSWORD = "password";

    private static Connection connection;
    private static Statement statement = null;
    private static ResultSet resultSet;
    private static List<Animal> horseList = new ArrayList();
    private static Horse horse;

    public static void main(String[] args) throws SQLException {
        DataBaseSQLite horsesDB = new DataBaseSQLite(DATA_BASE, DB_USER, DB_PASSWORD);
        connection = horsesDB.getDBConnection();
        statement = horsesDB.getStatement(connection);
        resultSet = statement.executeQuery("SELECT * FROM Horses");

        while (resultSet.next()) {
            horse = new Horse(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                    resultSet.getFloat(5), resultSet.getInt(6), resultSet.getInt(7),
                    resultSet.getFloat(8));
            horseList.add(horse);
        }
        statement.close();
        connection.close();

        System.out.println();
        HorseRaceTrack horseRaceTrack1 = new HorseRaceTrack(7258, 32);
        HorsesInRace Team1 = new HorsesInRace("Team 1", horseList);
        TheChallenge grandLiverpoolSteeplechase = new TheChallenge(horseRaceTrack1, Team1);
        grandLiverpoolSteeplechase.launch();
        grandLiverpoolSteeplechase.arrangeResults();
        grandLiverpoolSteeplechase.printResults();
        grandLiverpoolSteeplechase.printWinner();
    }
}
