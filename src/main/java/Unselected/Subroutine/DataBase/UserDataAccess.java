package Unselected.Subroutine.DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Performs CRUD (Create, Read, Update, Delete) operations related to the UsersInformation table in the database.
 *  * Use the getConnection() method of the GameData class to establish a connection to the database and execute SQL statements.
 */
public class UserDataAccess {

    // Create operation: 添加一个新用户
    public boolean insertUser(String username) {
        String sql = "INSERT INTO UsersInformation (username) VALUES (?)";
        try (Connection conn = GameData.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Read operation: Read user information based on user name
    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM UsersInformation WHERE username = ?";
        try (Connection conn = GameData.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                // Assuming there's a User class to hold user data
                return new User(rs.getInt("id"), rs.getString("username"), rs.getInt("score"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update operation: Updating user scores
    public void updateUserScore(String username, int score) {
        String sql = "UPDATE UsersInformation SET score = ? WHERE score = ?";
        try (Connection conn = GameData.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, score);
            pstmt.setString(2, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete operation:
    public void deleteUser(String username) {
        String sql = "DELETE FROM UsersInformation WHERE username = ?";
        try (Connection conn = GameData.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}


