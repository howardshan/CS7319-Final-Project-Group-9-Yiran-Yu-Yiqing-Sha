package Unselected.Subroutine.DataBase;

/**
 * Create a User object to store user information and access and modify that information through getter and setter methods.
 *  Create a User object to hold the player's name and score, and then update the score and print out the user information at the end of the game.
 */
public class User {
    private int id;
    private String username;
    private int score;

    public User(int id, String username, int score) {
        this.id = id;
        this.username = username;
        this.score = score;
    }

    // Getter for id
    public int getId() {
        return id;
    }

    // Setter for id
    public void setId(int id) {
        this.id = id;
    }

    // Getter for username
    public String getUsername() {
        return username;
    }

    // Setter for username
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter for score
    public int getScore() {
        return score;
    }

    // Setter for score
    public void setScore(int score) {
        this.score = score;
    }

    // Optionally, you may want to override toString() method for better readability when printing User objects
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", score=" + score +
                '}';
    }

}
