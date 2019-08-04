#define N 5
int A[N];
int index = 0
int max;

active [N] proctype P() {
	index == _pid;
	if
	:: A[_pid] > max -> max = A[_pid];
	:: else -> max = max;
	fi;
	index ++;
}

active proctype main() {
	A[0] = -5;
	A[1] = -1;
	A[2] = 5;
	A[3] = 10;
	A[4] = 5;
	max = A[0]
	index == N;
	printf("\n MAX: %d\n", max);
}