package org.example.dao;

import org.example.config.JediConfiguration;
import org.example.model.Jedi;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JediDAOImpl implements JediDAO{
    // SQL Statements
    private static final String SELECT_ALL_JEDI = "SELECT * FROM JEDI";
    private static final String INSERT_JEDI = "INSERT INTO JEDI (name, rank, gender, councilmember) VALUES (?,?,?,?)";
    private static final String UPDATE_JEDI = "UPDATE JEDI SET name=?, rank = ?, gender = ?, councilmember = ? WHERE id=?";
    private static final String DELETE_JEDI = "DELETE FROM JEDI WHERE id = ?";
    private String connectionURL;

    public JediDAOImpl() {
        connectionURL = JediConfiguration.getValue("db.url"); // obtaining DB URL
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<Jedi> findAll() {
        List<Jedi> result = new ArrayList<>();

        try(Connection c = DriverManager.getConnection(connectionURL);
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ALL_JEDI)
        ){

            while(rs.next()){
                Jedi jedi = new Jedi();
                jedi.setId(rs.getInt("id"));
                jedi.setName(rs.getString("name"));
                jedi.setRank(rs.getString("rank"));
                jedi.setGender(rs.getString("gender"));
                jedi.setCouncilmember(rs.getString("councilmember"));


                result.add(jedi);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;

    }

    @Override
    public Jedi save(Jedi jedi) {
        if (jedi.getId() == null) {
            jedi.setId(-1);
        }
        try(Connection c = DriverManager.getConnection(connectionURL);
            PreparedStatement stmt = jedi.getId() <= 0 ? c.prepareStatement(INSERT_JEDI, Statement.RETURN_GENERATED_KEYS) : c.prepareStatement(UPDATE_JEDI)
        ){
            if(jedi.getId() > 0){ // UPDATE
                stmt.setInt(5, jedi.getId());
            }

            stmt.setString(1, jedi.getName());
            stmt.setString(2, jedi.getRank());
            stmt.setString(3, jedi.getGender());
            stmt.setString(4, jedi.getCouncilmember());

            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0){
                return null;
            }

            if(jedi.getId() <= 0){ // INSERT
                ResultSet genKeys = stmt.getGeneratedKeys();
                if(genKeys.next()){
                    jedi.setId(genKeys.getInt(1));
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

        return jedi;
    }


    @Override
    public void delete(Jedi jedi) {
        try(Connection c = DriverManager.getConnection(connectionURL);
            PreparedStatement stmt = c.prepareStatement(DELETE_JEDI);
        ) {
            stmt.setInt(1, jedi.getId());
            stmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(int jediId) {
        try(Connection c = DriverManager.getConnection(connectionURL);
            PreparedStatement stmt = c.prepareStatement(DELETE_JEDI);
        ) {
            stmt.setInt(1, jediId);
            stmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Jedi findById(int jediId) {
        try (Connection conn = DriverManager.getConnection(connectionURL);
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM JEDI WHERE id = ?")
        ) {
            pst.setInt(1, jediId);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return extractJediFromResultSet(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    Jedi extractJediFromResultSet(ResultSet rs){
        try {
            Jedi jedi = new Jedi();
            jedi.setId(rs.getInt("id"));
            jedi.setName(rs.getString("name"));
            jedi.setRank(rs.getString("rank"));
            jedi.setGender(rs.getString("gender"));
            jedi.setCouncilmember(rs.getString("councilmember"));
            return jedi;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
