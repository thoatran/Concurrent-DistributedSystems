int choice1;
int choice2;
int semaphore = 2;

// 1-rock 2-paper 3-scissors
active proctype P1() {
	if
	:: true == true -> choice1 = 1;
	:: true == true -> choice1 = 2;
	:: true == true -> choice1 = 3;
	fi;
	semaphore --;
}

active proctype P2() {
	if
	:: true == true -> choice2 = 1;
	:: true == true -> choice2 = 2;
	:: true == true -> choice2= 3;
	fi;
	semaphore --;
}

active proctype main() {
	semaphore == 0;
	if 
	:: (choice1 == 1 && choice2 == 2) || (choice1 == 2 && choice2 == 3) || (choice1 == 3 && choice2 == 1) -> printf("Second won\n");
	:: (choice2 == 1 && choice1 == 2) || (choice2 == 2 && choice1 == 3) || (choice2 == 3 && choice1 == 1) -> printf("First won\n");
	:: else -> printf("Draw\n");
	fi;
}