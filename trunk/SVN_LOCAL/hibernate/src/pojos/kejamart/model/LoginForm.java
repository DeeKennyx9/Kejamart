package kejamart.model;

import org.hibernate.validator.constraints.NotEmpty;

public class LoginForm {
	 
	private int id;
	
	@NotEmpty
    private String email;
	
	@NotEmpty
    private String password;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
}