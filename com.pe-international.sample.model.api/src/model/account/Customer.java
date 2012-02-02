package model.account;

public interface Customer {
	
    public int getId();
    
    public String getLastName();
    
    public void setLastName(String lastName);

    public String getFirstName();
    
    public void setFirstName(String firstName);

    public String getAddress();
    
    public void setAddress(String address);

    public Account getAccount();
    
    public void setAccount(Account account);
}
