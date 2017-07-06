//This is account class that acutally have acces to withdraw and deposit the amount 
class Account {
    int balance= 1000;
    
    //method is used to the balance
    public int getBal(){
        return balance;
    }
    
    //mehtod is used to withdraw the amount from the bank
    public void withdraw(int bal){
        balance= balance-bal;
        System.out.println(bal+" Amount is withdrawn new balance is : "+balance);
    }

    //mehthod is used to deposit the amount to the bank
    public void deposit(int bal){
        balance= balance+bal;
        System.out.println(bal+" Amount is desposited new balance is : "+balance);
    }
}

//class that contain trhe main method implement runnable interface for thread funcitonality 
public class EightThree implements Runnable{
	
	//account object creadted
    Account acc = new Account();
    
    
    //main method
    public static void main(String[] args) {
    	
    	//main class object created
        EightThree ts = new EightThree();
        
        //new thread creadted of main object and passed their names
        Thread t1 = new Thread(ts, "person 1");
        Thread t2 = new Thread(ts, "person 2");
        Thread t3 = new Thread(ts, "person 3");
        
        //Threads are started
        t1.start(); 
        t2.start();
        t3.start();
    }

    
    //run mehtod to start the thread
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            makeWithdraw(100);                 //calling syncronized makewithdraw method for withdrawing the amount
            if (acc.getBal() < 0) {				//cheking is the balance is  empty or not
                System.out.println("account is overdrawn!"); 
            }
            deposit(200);                         //amount is deposited to the account
        }
    }

    //makewithdraw method to withdraw amount
    private synchronized void makeWithdraw(int bal){
    	
    	
        if (acc.getBal()>=bal) {
            System.out.println(Thread.currentThread().getName()+" "+ "is try to withdraw");
            try {
                Thread.sleep(100);				//put the current thread to sleep
            } catch (Exception e) {
                e.printStackTrace();
            }
            acc.withdraw(bal);					//withdraw method is called of the account claass
            System.out.println(Thread.currentThread().getName()+" "+ "is complete the withdraw");
        }else{        
            System.out.println(Thread.currentThread().getName()+ " "+"doesn't have enough money for withdraw ");
        }
    }

    //deposit method to deposit the amount to the account
    private synchronized void deposit(int bal){
        if (bal>0) {
            System.out.println(Thread.currentThread().getName()+" "+ " is try to deposit");
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            acc.deposit(bal);			//deposit method of class account is called
            System.out.println(Thread.currentThread().getName()+" "+ "is complete the deposit");
        }else{        
            System.out.println(Thread.currentThread().getName()+ " "+"doesn't have enough money for deposit");
        }
    }
}