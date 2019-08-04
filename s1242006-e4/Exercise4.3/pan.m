#define rand	pan_rand
#define pthread_equal(a,b)	((a)==(b))
#if defined(HAS_CODE) && defined(VERBOSE)
	#ifdef BFS_PAR
		bfs_printf("Pr: %d Tr: %d\n", II, t->forw);
	#else
		cpu_printf("Pr: %d Tr: %d\n", II, t->forw);
	#endif
#endif
	switch (t->forw) {
	default: Uerror("bad forward move");
	case 0:	/* if without executable clauses */
		continue;
	case 1: /* generic 'goto' or 'skip' */
		IfNotBlocked
		_m = 3; goto P999;
	case 2: /* generic 'else' */
		IfNotBlocked
		if (trpt->o_pm&1) continue;
		_m = 3; goto P999;

		 /* PROC main */
	case 3: // STATE 1 - ex43.pml:25 - [((semaphore==0))] (0:0:0 - 1)
		IfNotBlocked
		reached[2][1] = 1;
		if (!((now.semaphore==0)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 4: // STATE 2 - ex43.pml:27 - [(((((choice1==1)&&(choice2==2))||((choice1==2)&&(choice2==3)))||((choice1==3)&&(choice2==1))))] (0:0:0 - 1)
		IfNotBlocked
		reached[2][2] = 1;
		if (!(((((now.choice1==1)&&(now.choice2==2))||((now.choice1==2)&&(now.choice2==3)))||((now.choice1==3)&&(now.choice2==1)))))
			continue;
		_m = 3; goto P999; /* 0 */
	case 5: // STATE 3 - ex43.pml:27 - [printf('Second won\\n')] (0:0:0 - 1)
		IfNotBlocked
		reached[2][3] = 1;
		Printf("Second won\n");
		_m = 3; goto P999; /* 0 */
	case 6: // STATE 4 - ex43.pml:28 - [(((((choice2==1)&&(choice1==2))||((choice2==2)&&(choice1==3)))||((choice2==3)&&(choice1==1))))] (0:0:0 - 1)
		IfNotBlocked
		reached[2][4] = 1;
		if (!(((((now.choice2==1)&&(now.choice1==2))||((now.choice2==2)&&(now.choice1==3)))||((now.choice2==3)&&(now.choice1==1)))))
			continue;
		_m = 3; goto P999; /* 0 */
	case 7: // STATE 5 - ex43.pml:28 - [printf('First won\\n')] (0:0:0 - 1)
		IfNotBlocked
		reached[2][5] = 1;
		Printf("First won\n");
		_m = 3; goto P999; /* 0 */
	case 8: // STATE 7 - ex43.pml:29 - [printf('Draw\\n')] (0:0:0 - 1)
		IfNotBlocked
		reached[2][7] = 1;
		Printf("Draw\n");
		_m = 3; goto P999; /* 0 */
	case 9: // STATE 10 - ex43.pml:31 - [-end-] (0:0:0 - 4)
		IfNotBlocked
		reached[2][10] = 1;
		if (!delproc(1, II)) continue;
		_m = 3; goto P999; /* 0 */

		 /* PROC P2 */
	case 10: // STATE 1 - ex43.pml:17 - [((1==1))] (0:0:0 - 1)
		IfNotBlocked
		reached[1][1] = 1;
		if (!((1==1)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 11: // STATE 2 - ex43.pml:17 - [choice2 = 1] (0:0:1 - 1)
		IfNotBlocked
		reached[1][2] = 1;
		(trpt+1)->bup.oval = now.choice2;
		now.choice2 = 1;
#ifdef VAR_RANGES
		logval("choice2", now.choice2);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 12: // STATE 3 - ex43.pml:18 - [((1==1))] (0:0:0 - 1)
		IfNotBlocked
		reached[1][3] = 1;
		if (!((1==1)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 13: // STATE 4 - ex43.pml:18 - [choice2 = 2] (0:0:1 - 1)
		IfNotBlocked
		reached[1][4] = 1;
		(trpt+1)->bup.oval = now.choice2;
		now.choice2 = 2;
#ifdef VAR_RANGES
		logval("choice2", now.choice2);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 14: // STATE 5 - ex43.pml:19 - [((1==1))] (0:0:0 - 1)
		IfNotBlocked
		reached[1][5] = 1;
		if (!((1==1)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 15: // STATE 6 - ex43.pml:19 - [choice2 = 3] (0:0:1 - 1)
		IfNotBlocked
		reached[1][6] = 1;
		(trpt+1)->bup.oval = now.choice2;
		now.choice2 = 3;
#ifdef VAR_RANGES
		logval("choice2", now.choice2);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 16: // STATE 9 - ex43.pml:21 - [semaphore = (semaphore-1)] (0:0:1 - 4)
		IfNotBlocked
		reached[1][9] = 1;
		(trpt+1)->bup.oval = now.semaphore;
		now.semaphore = (now.semaphore-1);
#ifdef VAR_RANGES
		logval("semaphore", now.semaphore);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 17: // STATE 10 - ex43.pml:22 - [-end-] (0:0:0 - 1)
		IfNotBlocked
		reached[1][10] = 1;
		if (!delproc(1, II)) continue;
		_m = 3; goto P999; /* 0 */

		 /* PROC P1 */
	case 18: // STATE 1 - ex43.pml:8 - [((1==1))] (0:0:0 - 1)
		IfNotBlocked
		reached[0][1] = 1;
		if (!((1==1)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 19: // STATE 2 - ex43.pml:8 - [choice1 = 1] (0:0:1 - 1)
		IfNotBlocked
		reached[0][2] = 1;
		(trpt+1)->bup.oval = now.choice1;
		now.choice1 = 1;
#ifdef VAR_RANGES
		logval("choice1", now.choice1);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 20: // STATE 3 - ex43.pml:9 - [((1==1))] (0:0:0 - 1)
		IfNotBlocked
		reached[0][3] = 1;
		if (!((1==1)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 21: // STATE 4 - ex43.pml:9 - [choice1 = 2] (0:0:1 - 1)
		IfNotBlocked
		reached[0][4] = 1;
		(trpt+1)->bup.oval = now.choice1;
		now.choice1 = 2;
#ifdef VAR_RANGES
		logval("choice1", now.choice1);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 22: // STATE 5 - ex43.pml:10 - [((1==1))] (0:0:0 - 1)
		IfNotBlocked
		reached[0][5] = 1;
		if (!((1==1)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 23: // STATE 6 - ex43.pml:10 - [choice1 = 3] (0:0:1 - 1)
		IfNotBlocked
		reached[0][6] = 1;
		(trpt+1)->bup.oval = now.choice1;
		now.choice1 = 3;
#ifdef VAR_RANGES
		logval("choice1", now.choice1);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 24: // STATE 9 - ex43.pml:12 - [semaphore = (semaphore-1)] (0:0:1 - 4)
		IfNotBlocked
		reached[0][9] = 1;
		(trpt+1)->bup.oval = now.semaphore;
		now.semaphore = (now.semaphore-1);
#ifdef VAR_RANGES
		logval("semaphore", now.semaphore);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 25: // STATE 10 - ex43.pml:13 - [-end-] (0:0:0 - 1)
		IfNotBlocked
		reached[0][10] = 1;
		if (!delproc(1, II)) continue;
		_m = 3; goto P999; /* 0 */
	case  _T5:	/* np_ */
		if (!((!(trpt->o_pm&4) && !(trpt->tau&128))))
			continue;
		/* else fall through */
	case  _T2:	/* true */
		_m = 3; goto P999;
#undef rand
	}

