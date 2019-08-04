#define N 5
int A[N];
int i = 0;
active [N] proctype Assign() {
	_pid == i;
	A[i] = _pid;
	i++;
}
active proctype main() {
	i == N ;
	i = 0;
	do
	:: i >= N ->break;
	:: else ->atomic {printf("%d\n", A[i]);i++;}
	od;
}