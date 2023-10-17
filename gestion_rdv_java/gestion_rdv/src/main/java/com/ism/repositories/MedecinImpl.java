package com.ism.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ism.entities.Medecin;

public class MedecinImpl implements IMedecin<Medecin> {
private  DatabaseImpl database;

public MedecinImpl(DatabaseImpl database) {
    this.database = database;
}

    @Override
    public int insert(Medecin medecin) {
        try {
            database.connect();
            String insertQuery = "INSERT INTO personne (nomComplet, type, antecedent, specialite) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = database.getPs(insertQuery);

            preparedStatement.setString(1, medecin.getNomComplet());
            preparedStatement.setInt(2, medecin.getType());
            preparedStatement.setString(3,null);
            preparedStatement.setString(4, medecin.getSpecialite());

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
public String findMedecin(String nomComplet) throws ClassNotFoundException {

    try {
        database.connect();
    String query = "SELECT * FROM personne WHERE nomComplet = ?";
         PreparedStatement preparedStatement = database.getPs(query); {
        preparedStatement.setString(1, nomComplet);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                // Médecin trouvé, retourner le nom complet
                return resultSet.getString("nomComplet");
            }
        }
    }} catch (SQLException e) {
        e.printStackTrace();
    }

    // Si aucun médecin n'a été trouvé, retourner null
    return null;
}
}


