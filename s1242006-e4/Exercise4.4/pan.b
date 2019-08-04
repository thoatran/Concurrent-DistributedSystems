	switch (t->back) {
	default: Uerror("bad return move");
	case  0: goto R999; /* nothing to undo */

		 /* PROC main */

	case 3: // STATE 1
		;
		now.A[0] = trpt->bup.oval;
		;
		goto R999;

	case 4: // STATE 2
		;
		now.A[1] = trpt->bup.oval;
		;
		goto R999;

	case 5: // STATE 3
		;
		now.A[2] = trpt->bup.oval;
		;
		goto R999;

	case 6: // STATE 4
		;
		now.A[3] = trpt->bup.oval;
		;
		goto R999;

	case 7: // STATE 5
		;
		now.A[4] = trpt->bup.oval;
		;
		goto R999;

	case 8: // STATE 6
		;
		now.max = trpt->bup.oval;
		;
		goto R999;
;
		;
		;
		;
		
	case 11: // STATE 9
		;
		p_restor(II);
		;
		;
		goto R999;

		 /* PROC P */
;
		;
		;
		;
		
	case 14: // STATE 3
		;
		now.max = trpt->bup.oval;
		;
		goto R999;

	case 15: // STATE 5
		;
		now.max = trpt->bup.oval;
		;
		goto R999;

	case 16: // STATE 8
		;
		now.index = trpt->bup.oval;
		;
		goto R999;

	case 17: // STATE 9
		;
		p_restor(II);
		;
		;
		goto R999;
	}

