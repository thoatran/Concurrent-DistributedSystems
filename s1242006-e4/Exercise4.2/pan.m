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
	case 3: // STATE 1 - ex42.pml:10 - [((i==5))] (0:0:0 - 1)
		IfNotBlocked
		reached[1][1] = 1;
		if (!((now.i==5)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 4: // STATE 2 - ex42.pml:11 - [i = 0] (0:0:1 - 1)
		IfNotBlocked
		reached[1][2] = 1;
		(trpt+1)->bup.oval = now.i;
		now.i = 0;
#ifdef VAR_RANGES
		logval("i", now.i);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 5: // STATE 3 - ex42.pml:13 - [((i>=5))] (0:0:0 - 1)
		IfNotBlocked
		reached[1][3] = 1;
		if (!((now.i>=5)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 6: // STATE 6 - ex42.pml:14 - [printf('%d\\n',A[i])] (0:9:1 - 1)
		IfNotBlocked
		reached[1][6] = 1;
		Printf("%d\n", A[ Index(now.i, 5) ]);
		/* merge: i = (i+1)(9, 7, 9) */
		reached[1][7] = 1;
		(trpt+1)->bup.oval = now.i;
		now.i = (now.i+1);
#ifdef VAR_RANGES
		logval("i", now.i);
#endif
		;
		/* merge: .(goto)(0, 10, 9) */
		reached[1][10] = 1;
		;
		_m = 3; goto P999; /* 2 */
	case 7: // STATE 12 - ex42.pml:16 - [-end-] (0:0:0 - 3)
		IfNotBlocked
		reached[1][12] = 1;
		if (!delproc(1, II)) continue;
		_m = 3; goto P999; /* 0 */

		 /* PROC Assign */
	case 8: // STATE 1 - ex42.pml:5 - [((_pid==i))] (0:0:0 - 1)
		IfNotBlocked
		reached[0][1] = 1;
		if (!((((int)((P0 *)this)->_pid)==now.i)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 9: // STATE 2 - ex42.pml:6 - [A[i] = _pid] (0:0:1 - 1)
		IfNotBlocked
		reached[0][2] = 1;
		(trpt+1)->bup.oval = A[ Index(now.i, 5) ];
		A[ Index(now.i, 5) ] = ((int)((P0 *)this)->_pid);
#ifdef VAR_RANGES
		logval("A[i]", A[ Index(now.i, 5) ]);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 10: // STATE 3 - ex42.pml:7 - [i = (i+1)] (0:0:1 - 1)
		IfNotBlocked
		reached[0][3] = 1;
		(trpt+1)->bup.oval = now.i;
		now.i = (now.i+1);
#ifdef VAR_RANGES
		logval("i", now.i);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 11: // STATE 4 - ex42.pml:8 - [-end-] (0:0:0 - 1)
		IfNotBlocked
		reached[0][4] = 1;
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

