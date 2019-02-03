package squirrel.kejani.controller.main;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

public class LoginForm {
	@NotEmpty
	@Size(min = 1, max = 50)
	private String email;
	@NotEmpty
	@Size(min = 1, max = 20)
	private String password;

	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
}
