package model.account;

public interface Account {
	
    public int getId();
    
    public void setId(int id);
    
    public double getBalance();
    
    public void setBalance(double balance);

    public Customer getCustomer();
    
    public void setCustomer(Customer customer);

}
