public class BankAccount {
    private int accountNumber;
    private String accountHolderName;
    private int balance;
    public int getAccountNumber(){
        return accountNumber;
    }
    public String getAccountHolderName(){
        return accountHolderName;
    }
    public int getBalance(){
        return balance;
    }
    public void deposit(int amount){
        balance+=amount;
    }
    public void withdraw(int amount){
        balance=balance>amount?balance-amount:balance;
    }
    public BankAccount(int accountNumber,String accountHolderName,int balance){
        this.accountNumber=accountNumber;
        this.accountHolderName=accountHolderName;
        this.balance=balance;
    }
}
