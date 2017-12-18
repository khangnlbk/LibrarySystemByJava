package UserManager.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * @author anonymous588
 */
public class User {
    
    private final IntegerProperty user_id = new SimpleIntegerProperty();

    /**
     *
     * @return
     */
    public IntegerProperty user_id_property() {
        return user_id;
    }

    /**
     *
     * @return
     */
    public final int getUser_id() {
        return user_id_property().get();
    }

    /**
     *
     * @param id
     */
    public final void setUser_id(int id) {
    	user_id_property().set(id);
    }
    
    private final StringProperty role = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public StringProperty role_property() {
        return role;
    }

    /**
     *
     * @return
     */
    public final String getRole() {
        return role_property().get();
    }

    /**
     *
     * @param id
     */
    public final void setRole(String id) {
    	role_property().set(id);
    }
    
    private final StringProperty username = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public StringProperty username_property() {
        return username;
    }

    /**
     *
     * @return
     */
    public final String getUsername() {
        return username_property().get();
    }

    /**
     *
     * @param id
     */
    public final void setUsername(String id) {
    	username_property().set(id);
    }
    
    private final StringProperty password = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public StringProperty password_property() {
        return password;
    }

    /**
     *
     * @return
     */
    public final String getPassword() {
        return password_property().get();
    }

    /**
     *
     * @param id
     */
    public final void setPassword(String id) {
    	password_property().set(id);
    }
    
    private final StringProperty full_name = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public StringProperty full_name_property() {
        return full_name;
    }

    /**
     *
     * @return
     */
    public final String getFull_name() {
        return full_name_property().get();
    }

    /**
     *
     * @param id
     */
    public final void setFull_name(String id) {
    	full_name_property().set(id);
    }
    
    private final StringProperty email = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public StringProperty email_property() {
        return email;
    }

    /**
     *
     * @return
     */
    public final String getEmail() {
        return email_property().get();
    }

    /**
     *
     * @param id
     */
    public final void setEmail(String id) {
    	email_property().set(id);
    }
    
    private final IntegerProperty age = new SimpleIntegerProperty();

    /**
     *
     * @return
     */
    public IntegerProperty age_property() {
        return age;
    }

    /**
     *
     * @return
     */
    public final int getAge() {
        return age_property().get();
    }

    /**
     *
     * @param age
     */
    public final void setAge(int age) {
    	age_property().set(age);
    }
    
    private final StringProperty addresss = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public StringProperty addresss_property() {
        return addresss;
    }

    /**
     *
     * @return
     */
    public final String getAddresss() {
        return addresss_property().get();
    }

    /**
     *
     * @param id
     */
    public final void setAddresss(String id) {
    	addresss_property().set(id);
    }
    
    private final StringProperty gender = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public StringProperty gender_property() {
        return gender;
    }

    /**
     *
     * @return
     */
    public final String getGender() {
        return gender_property().get();
    }

    /**
     *
     * @param id
     */
    public final void setGender(String id) {
    	gender_property().set(id);
    }
    
    private final IntegerProperty student_id = new SimpleIntegerProperty();

    /**
     *
     * @return
     */
    public IntegerProperty student_id_property() {
        return student_id;
    }

    /**
     *
     * @return
     */
    public final int getStudent_id() {
        return student_id_property().get();
    }

    /**
     *
     * @param id
     */
    public final void setStudent_id(int id) {
    	student_id_property().set(id);
    }
    
    private final IntegerProperty deposit = new SimpleIntegerProperty();

    /**
     *
     * @return
     */
    public IntegerProperty deposit_property() {
        return deposit;
    }

    /**
     *
     * @return
     */
    public final int getDeposit() {
        return deposit_property().get();
    }

    /**
     *
     * @param deposit
     */
    public final void setDeposit(int deposit) {
    	deposit_property().set(deposit);
    }
    
    /**
     *
     * @param user_id
     * @param role
     * @param username
     * @param password
     * @param full_name
     * @param email
     * @param age
     * @param address
     * @param gender
     * @param student_id
     * @param deposit
     */
    public User(int user_id, String role, String username, String password, String full_name, String email, int age,
    					String address, String gender, int student_id, int deposit) {
    	this.setUser_id(user_id);
    	this.setRole(role);
    	this.setUsername(username);
    	this.setPassword(password);
    	this.setFull_name(full_name);
    	this.setEmail(email);
    	this.setAge(age);
    	this.setAddresss(address);
    	this.setGender(gender);
    	this.setStudent_id(student_id);
    	this.setDeposit(deposit);
    }
    
    /**
     *
     * @param rs
     * @throws SQLException
     */
    public User(ResultSet rs) throws SQLException {
        this.setUser_id(rs.getInt("user_id"));
    	this.setRole(rs.getString("role"));
    	this.setUsername(rs.getString("username"));
    	this.setPassword(rs.getString("password"));
    	this.setFull_name(rs.getString("full_name"));
    	this.setEmail(rs.getString("email"));
    	this.setAge(rs.getInt("age"));
    	this.setAddresss(rs.getString("address"));
    	this.setGender(rs.getString("gender"));
    	this.setStudent_id(rs.getInt("student_id"));
    	this.setDeposit(rs.getInt("deposit"));
    }
}
