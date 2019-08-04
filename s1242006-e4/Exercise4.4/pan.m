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
	case 3: // STATE 1 - ex44.pml:16 - [A[0] = -(5)] (0:0:1 - 1)
		IfNotBlocked
		reached[1][1] = 1;
		(trpt+1)->bup.oval = now.A[0];
		now.A[0] =  -(5);
#ifdef VAR_RANGES
		logval("A[0]", now.A[0]);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 4: // STATE 2 - ex44.pml:17 - [A[1] = -(1)] (0:0:1 - 1)
		IfNotBlocked
		reached[1][2] = 1;
		(trpt+1)->bup.oval = now.A[1];
		now.A[1] =  -(1);
#ifdef VAR_RANGES
		logval("A[1]", now.A[1]);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 5: // STATE 3 - ex44.pml:18 - [A[2] = 5] (0:0:1 - 1)
		IfNotBlocked
		reached[1][3] = 1;
		(trpt+1)->bup.oval = now.A[2];
		now.A[2] = 5;
#ifdef VAR_RANGES
		logval("A[2]", now.A[2]);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 6: // STATE 4 - ex44.pml:19 - [A[3] = 10] (0:0:1 - 1)
		IfNotBlocked
		reached[1][4] = 1;
		(trpt+1)->bup.oval = now.A[3];
		now.A[3] = 10;
#ifdef VAR_RANGES
		logval("A[3]", now.A[3]);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 7: // STATE 5 - ex44.pml:20 - [A[4] = 5] (0:0:1 - 1)
		IfNotBlocked
		reached[1][5] = 1;
		(trpt+1)->bup.oval = now.A[4];
		now.A[4] = 5;
#ifdef VAR_RANGES
		logval("A[4]", now.A[4]);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 8: // STATE 6 - ex44.pml:21 - [max = A[0]] (0:0:1 - 1)
		IfNotBlocked
		reached[1][6] = 1;
		(trpt+1)->bup.oval = now.max;
		now.max = now.A[0];
#ifdef VAR_RANGES
		logval("max", now.max);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 9: // STATE 7 - ex44.pml:22 - [((index==5))] (0:0:0 - 1)
		IfNotBlocked
		reached[1][7] = 1;
		if (!((now.index==5)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 10: // STATE 8 - ex44.pml:23 - [printf('\\n MAX: %d\\n',max)] (0:0:0 - 1)
		IfNotBlocked
		reached[1][8] = 1;
		Printf("\n MAX: %d\n", now.max);
		_m = 3; goto P999; /* 0 */
	case 11: // STATE 9 - ex44.pml:24 - [-end-] (0:0:0 - 1)
		IfNotBlocked
		reached[1][9] = 1;
		if (!delproc(1, II)) continue;
		_m = 3; goto P999; /* 0 */

		 /* PROC P */
	case 12: // STATE 1 - ex44.pml:7 - [((index==_pid))] (0:0:0 - 1)
		IfNotBlocked
		reached[0][1] = 1;
		if (!((now.index==((int)((P0 *)this)->_pid))))
			continue;
		_m = 3; goto P999; /* 0 */
	case 13: // STATE 2 - ex44.pml:9 - [((A[_pid]>max))] (0:0:0 - 1)
		IfNotBlocked
		reached[0][2] = 1;
		if (!((now.A[ Index(((int)((P0 *)this)->_pid), 5) ]>now.max)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 14: // STATE 3 - ex44.pml:9 - [max = A[_pid]] (0:0:1 - 1)
		IfNotBlocked
		reached[0][3] = 1;
		(trpt+1)->bup.oval = now.max;
		now.max = now.A[ Index(((int)((P0 *)this)->_pid), 5) ];
#ifdef VAR_RANGES
		logval("max", now.max);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 15: // STATE 5 - ex44.pml:10 - [max = max] (0:0:1 - 1)
		IfNotBlocked
		reached[0][5] = 1;
		(trpt+1)->bup.oval = now.max;
		now.max = now.max;
#ifdef VAR_RANGES
		logval("max", now.max);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 16: // STATE 8 - ex44.pml:12 - [index = (index+1)] (0:0:1 - 3)
		IfNotBlocked
		reached[0][8] = 1;
		(trpt+1)->bup.oval = now.index;
		now.index = (now.index+1);
#ifdef VAR_RANGES
		logval("index", now.index);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 17: // STATE 9 - ex44.pml:13 - [-end-] (0:0:0 - 1)
		IfNotBlocked
		reached[0][9] = 1;
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

