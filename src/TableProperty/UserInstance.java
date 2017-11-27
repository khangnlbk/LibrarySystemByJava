package TableProperty;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * @author anonymous588
 */
public class UserInstance {
	private final StringProperty user_id = new SimpleStringProperty();
	public StringProperty user_id_property() {
        return user_id;
    }
    public final String getUser_id() {
        return user_id_property().get();
    }
    public final void setUser_id(String id) {
    	user_id_property().set(id);
    }
    
    private final StringProperty role = new SimpleStringProperty();
	public StringProperty role_property() {
        return role;
    }
    public final String getRole() {
        return role_property().get();
    }
    public final void setRole(String id) {
    	role_property().set(id);
    }
    
    private final StringProperty username = new SimpleStringProperty();
	public StringProperty username_property() {
        return username;
    }
    public final String getUsername() {
        return username_property().get();
    }
    public final void setUsername(String id) {
    	username_property().set(id);
    }
    
    private final StringProperty password = new SimpleStringProperty();
	public StringProperty password_property() {
        return password;
    }
    public final String getPassword() {
        return password_property().get();
    }
    public final void setPassword(String id) {
    	password_property().set(id);
    }
    
    private final StringProperty full_name = new SimpleStringProperty();
	public StringProperty full_name_property() {
        return full_name;
    }
    public final String getFull_name() {
        return full_name_property().get();
    }
    public final void setFull_name(String id) {
    	full_name_property().set(id);
    }
    
    private final StringProperty email = new SimpleStringProperty();
	public StringProperty email_property() {
        return email;
    }
    public final String getEmail() {
        return email_property().get();
    }
    public final void setEmail(String id) {
    	email_property().set(id);
    }
    
    private final StringProperty age = new SimpleStringProperty();
	public StringProperty age_property() {
        return age;
    }
    public final String getAge() {
        return age_property().get();
    }
    public final void setAge(String id) {
    	age_property().set(id);
    }
    
    private final StringProperty addresss = new SimpleStringProperty();
	public StringProperty addresss_property() {
        return addresss;
    }
    public final String getAddresss() {
        return addresss_property().get();
    }
    public final void setAddresss(String id) {
    	addresss_property().set(id);
    }
    
    private final StringProperty gender = new SimpleStringProperty();
	public StringProperty gender_property() {
        return gender;
    }
    public final String getGender() {
        return gender_property().get();
    }
    public final void setGender(String id) {
    	gender_property().set(id);
    }
    
    private final StringProperty student_id = new SimpleStringProperty();
	public StringProperty student_id_property() {
        return student_id;
    }
    public final String getStudent_id() {
        return student_id_property().get();
    }
    public final void setStudent_id(String id) {
    	student_id_property().set(id);
    }
    
    private final StringProperty deposit = new SimpleStringProperty();
	public StringProperty deposit_property() {
        return deposit;
    }
    public final String getDeposit() {
        return deposit_property().get();
    }
    public final void setDeposit(String id) {
    	deposit_property().set(id);
    }
    
    
    public UserInstance(String user_id, String role, String username, String password, String full_name, String email, String age,
    					String address, String gender, String student_id, String deposit) {
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
}
