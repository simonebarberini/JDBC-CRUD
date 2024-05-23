package com.test.dbservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.test.model.User;

public class UserDao {
    

    public void createUser(User user){
        String sql = "INSERT INTO prova (nome,email) VALUES (?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, user.getNome());
            pstmt.setString(2, user.getEmail());
            pstmt.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    //READ USER
    public User getUserById(int id){
        String sql = "SELECT * FROM prova WHERE id = ?";
        User user = null;
        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("nome"), rs.getString("email"));
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }    
        return user;
    }

    //LIST ALL USER
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM prova";
        List<User> utenti = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) { // Loop through the result set
                User user = new User(rs.getInt("id"), rs.getString("nome"), rs.getString("email"));
                utenti.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utenti;
    }

    //UPDATE USER
    public void updateUser(User user){
        String sql = "UPDATE prova SET nome = ?, email =? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getNome());
            pstmt.setString(2, user.getEmail());
            pstmt.setInt(3, user.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // DELETE USER
    public void deleteUser(int id) {
        String sql = "DELETE FROM prova WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
