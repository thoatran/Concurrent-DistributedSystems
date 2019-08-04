

class passCrack implements Runnable{
    // private char list[];

    private int idnum;
    private int low, high;
    private String pwd;
    private String setVar = "0123456789abcdefjhjklmnopqrsrtuvwxyz";

    public passCrack(int id) {
        this.idnum = id;
    }
    public boolean CrackingPassword(String pwd){
        boolean check = false;
        check = w02_fileCrypto.Decrypt("w02_mobydick.encrypted", "password.txt", pwd);
        return check;
    }
    public void run(){
        if (idnum == 1 ){
            low = 0;
            high = 8;
        }
        if (idnum == 2 ){
            low = 9;
            high = 17;
        }
        if (idnum == 3 ){
            low = 18;
            high = 26;
        }
        if (idnum == 4 ){
            low = 27;
            high = 35;
        }

        int lengthOfSet = setVar.length();
        for(int i = low; i < high + 1; i++){

            for(int j = 0; j < lengthOfSet; j++){

                for(int m = 0; m < lengthOfSet; m++){

                    for(int n = 0; n < lengthOfSet; n++){

                        pwd = Character.toString(setVar.charAt(i)) + Character.toString(setVar.charAt(j)) + Character.toString(setVar.charAt(m)) + Character.toString(setVar.charAt(n));
                        //System.out.println(pwd);

                        if (CrackingPassword(pwd) == true){
                            // System.out.println("a");

                            System.out.println("Needing password:" + pwd);

                            System.exit(0);
                        }
                        // else System.out.println("a");
                    }
                }
            }
        }
    }

}