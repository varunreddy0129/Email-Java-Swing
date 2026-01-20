//package emailapp;

public class Email {
	
	private String firstName;
	private String lastName;
	private String password;
	private String department;
	private String email;
	private int mailboxCapacity = 500;
	private int defaultPasswordLength;
	private String alternateEmail;
	private String companySuffix = "email.com";
	
	//constructor to receive the firstName and lastName
	public Email(String firstName,String lastName,String department) {
		this.firstName = firstName;
		this.lastName = lastName;
		//System.out.println("Email Created : " + this.firstName + " " + this.lastName);
		
		
		//call a method asking for the department- return the department
		
		this.department = department;
		//System.out.println("Department: " + this.department);
		
		//call a method that returns a random password
		
		this.password = randomPassword(8);
		//System.out.println("Your password is: "+ this.password);
		
		//Combine elements to generate email
		this.email = firstName.toLowerCase() +"." + lastName.toLowerCase() + "@" + department + "." + companySuffix;
		//System.out.println("YOur email is :" + email);
	}
	//Ask for the department
	//private String setDepartment() {
		//System.out.print("New Worker: " +firstName + ".Department Codes\n1 for sales\n2 for Development\n3 for Accounting\n0 for none\n Enter department code: ");
		//Scanner in  = new Scanner(System.in);
		//int depChoice = in.nextInt();
		//if(depChoice == 1) { return "sales"; }
		//else if(depChoice == 2) {return "dev";}
		//else if(depChoice == 3) {return "acct";}
		//else { return "";}
	//}
	//generate a random password
	private String randomPassword(int length) {
		String passwordSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%";
		char[] password = new char[length];
		for(int i = 0;i<length;i++) {
			int rand = (int)(Math.random() * passwordSet.length());
			password[i] = passwordSet.charAt(rand);
			
		}
		return new String(password);
	}
	//Set the mailbox capacity
	public void setMailCapacity(int capacity) {
		this.mailboxCapacity = capacity;
	}
	
	//Generate the alternate email
	public void setAlternateEmail(String altEmail) {
		this.alternateEmail = altEmail;
		
	}
	
	//Change the passwords
	
	public int getMailCapacity() {return mailboxCapacity;}
	public String getAlternateEmail() {return alternateEmail;}
	
	public void setMailboxCapacity(int capacity) {
		this.mailboxCapacity = capacity;
	}
	public String getPassword() {return password;}
	public String getMaskedPassword() {return "*".repeat(password.length());}
	public String getEmail() {return email;}
	public void changePassword(String newPassword) {
	    this.password = newPassword;
	}

	public String showInfo() {
		return "DISPLAY NAME: " + firstName +" "+lastName + 
				"\nCOMPANY EMAIL: " + email +
				"\nMAILBOX CAPACITY: " + mailboxCapacity + "MB";
 	}
	
}