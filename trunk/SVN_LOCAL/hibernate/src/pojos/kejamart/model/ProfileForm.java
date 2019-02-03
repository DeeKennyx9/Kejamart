package kejamart.model;

public class  ProfileForm{
	
	private String email;

	private String password;

	private String firstName;

	private String lastName;

	private String category;
	
	private String mailing;


	public String getMailing() {
		return mailing;
	}

	public void setMailing(String mailing) {
		this.mailing = mailing;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}



}