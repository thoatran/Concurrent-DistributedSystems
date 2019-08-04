	switch (t->back) {
	default: Uerror("bad return move");
	case  0: goto R999; /* nothing to undo */

		 /* PROC main */
;
		;
		
	case 4: // STATE 2
		;
		now.i = trpt->bup.oval;
		;
		goto R999;
;
		;
		
	case 6: // STATE 7
		;
		now.i = trpt->bup.oval;
		;
		goto R999;

	case 7: // STATE 12
		;
		p_restor(II);
		;
		;
		goto R999;

		 /* PROC Assign */
;
		;
		
	case 9: // STATE 2
		;
		A[ Index(now.i, 5) ] = trpt->bup.oval;
		;
		goto R999;

	case 10: // STATE 3
		;
		now.i = trpt->bup.oval;
		;
		goto R999;

	case 11: // STATE 4
		;
		p_restor(II);
		;
		;
		goto R999;
	}

