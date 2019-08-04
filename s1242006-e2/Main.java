
class Main
{
    static public void main(String args[])
    {
        for(int i = 1; i <= 4; i++){
            new Thread(new passCrack(i)).start();
        }        
    }
}