package com.ism.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ism.entities.Rdv;

public class RdvImpl implements IRdv<Rdv> {
    private  DatabaseImpl database;

    public RdvImpl(DatabaseImpl database) {
        this.database = database;
    }

    @Override
public int insert(Rdv data) {
    try {
        database.connect();
        String query = "INSERT INTO rdv (date, statut, medecin, patient) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = database.getPs(query);
        preparedStatement.setString(1, data.getDate()); 
        preparedStatement.setInt(2, data.getStatut());
        preparedStatement.setString(3, data.getMedecin());
        preparedStatement.setString(4, data.getPatient());
        int rowsInserted = database.executeUpdate(preparedStatement);
            database.disconnect();

            return rowsInserted;
        } catch (SQLException e) {
            e.printStackTrace();

            return 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
}


@Override
public ArrayList<Rdv> findAll() throws ClassNotFoundException {
    ArrayList<Rdv> rdvList = new ArrayList<>();
    try {
        database.connect();
        String query ="SELECT * FROM rdv";
        PreparedStatement preparedStatement = database.getPs(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
            Rdv rdv = new Rdv(resultSet.getInt("id"), resultSet.getString("date"), resultSet.getInt("statut"), resultSet.getString("medecin"), resultSet.getString("patient"));
            rdvList.add(rdv);
        }
        
        resultSet.close();
        preparedStatement.close();
        database.disconnect(); 
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return rdvList;
}


@Override
public int cancel(int id) throws ClassNotFoundException {
    try {
        database.connect();
        String query = "DELETE FROM rdv WHERE id = ?";
        PreparedStatement preparedStatement =database.getPs(query);
        preparedStatement.setInt(1, id);
        int rowsAffected = preparedStatement.executeUpdate();
        preparedStatement.close();
        database.disconnect();
        return rowsAffected;
    } catch (SQLException e) {
        e.printStackTrace();
        return 0;
    }
}

@Override
public ArrayList<Rdv> findByMedecinAndDate(String nomComplet, String date) throws ClassNotFoundException {
    ArrayList<Rdv> rdvList = new ArrayList<>();

    try {
        database.connect();
        String query = "SELECT * FROM rdv WHERE medecin = ? AND date = ?";
        PreparedStatement preparedStatement = database.getPs(query);
        preparedStatement.setString(1, nomComplet);
        preparedStatement.setString(2, date);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Rdv rdv = new Rdv(resultSet.getInt("id"), resultSet.getString("date"), resultSet.getInt("statut"), resultSet.getString("medecin"), resultSet.getString("patient"));
            rdvList.add(rdv);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return rdvList;
}

@Override
public ArrayList<Rdv> findRdvByDate(String date) throws ClassNotFoundException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findRdvByDate'");
}

@Override
public int updateStatut(Rdv rdv) throws ClassNotFoundException {
    try {
        database.connect();
        String query = "UPDATE rdv SET statut = ? WHERE id = ?";
        PreparedStatement preparedStatement = database.getPs(query);
        preparedStatement.setInt(1, rdv.getStatut());
        preparedStatement.setInt(2, rdv.getId());

        return database.executeUpdate(preparedStatement);
    } catch (SQLException e) {
        e.printStackTrace();
        return 0; // Échec de la mise à jour
    }
}



}
