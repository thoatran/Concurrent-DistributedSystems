int Semaphore = 2; int Account = 0;

//5.3 The value of semaphore variable decreases as time goes on
ltl { [](Semaphore==0 U Semaphore==1) U Semaphore==2 }

active [2] proctype Spouse(){ 
    int t = 0; int i = 0;
    do
    :: i >= 10 -> break;
    :: else -> Account++; i++;
    od;
    Semaphore--;
    ltl { <> (Semaphore--)}
}

active proctype main(){ 
    Semaphore == 0;
    printf("Account = %d\n", Account);
    //5.1 account contains 20 money units in the end
    assert(Account == 20);
}
