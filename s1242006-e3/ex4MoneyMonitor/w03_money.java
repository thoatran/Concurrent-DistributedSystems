import java.util.concurrent.Semaphore;

class w03_money
{
    static int Account = 0;
    static Semaphore S = new Semaphore(0);
    static Object M = new Object();

    public static class AccountType{
        private int id;
        private int value;
        public AccountType(int id) {
            this.id = id;
            this.value = 0;
        }

        public synchronized int getValue() {
            return value;
        }

        public synchronized void addOneUnit() {
            this.value ++;
        }
    }

    static class Spouse implements Runnable
    {
        private int Sum;
        private AccountType acc;

        public Spouse(int sum, AccountType acc) {
            this.Sum = sum;
            this.acc = acc;
        }

        public void run()
        {
            for (int i = 0; i < Sum; i++)
            {
                synchronized (M){
                    acc.addOneUnit();
                    Account ++;
                }
            }

            S.release();
        }
    }

    static public void main(String args[])
    {
        AccountType acc1 = new AccountType(1);
        AccountType acc2 = new AccountType(2);
        AccountType acc3 = new AccountType(3);
        Spouse husband = new Spouse(400000, acc1);
        Spouse wife = new Spouse(500000, acc2);
        Spouse son = new Spouse(300000, acc3);

        new Thread(husband).start();
        new Thread(wife).start();
        new Thread(son).start();

        try
        {
            S.acquire();
            S.acquire();
            S.acquire();
        }
        catch(Exception e){}

        System.out.println("The wife's account has the current amount of money: " + wife.acc.getValue());
        System.out.println(" The husband's account has the amount of money: " + husband.acc.getValue());
        System.out.println("The son's account has the amount of money: " + son.acc.getValue());

        System.out.println("The total money: " + Account);
    }
}