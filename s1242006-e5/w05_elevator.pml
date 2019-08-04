bool doors_open = true;
int state = 1;

// 1 = down, 2 = moving up, 3 = up, 4 = moving down

active proctype main() {
//4.1
//ltl { [](!doors_open U (state == 1 || state==3)) }
//4.2
//ltl {<>(state == 3 && doors_open)}
//4.3
//ltl {(state == 3) -> <>(state == 1 && doors_open)}

ltl{ ([](!doors_open U (state == 1 || state==3))) && (<>(state == 3 && doors_open)) && ((state == 3) -> <>(state == 1 && doors_open)) }


do
:: state == 1 && doors_open -> doors_open = false; state = 2;
:: state == 2 -> state = 3;
:: state == 3 && !doors_open -> doors_open = true;
:: state == 3 && doors_open -> doors_open = false; state = 4;
:: state == 4 -> state = 1;
:: state == 1 && !doors_open -> doors_open = true;
od;
} 
