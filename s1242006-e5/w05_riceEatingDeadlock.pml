bool stick1 = true; // rice-eating example
bool stick2 = true;

active [2] proctype P() {
  atomic { 
	stick1 == true;    // wait for a stick
           	stick1 = false; 
           }	   // take a stick
  atomic { 
	stick2 == true;   // wait for a stick
           	stick2 = false; 
           }	   // take a stick
  atomic {
  	stick1 = true;   // eat and put the sticks back
  	stick2 = true;
  	assert (stick1 == true && stick2 == true);
  }
}
