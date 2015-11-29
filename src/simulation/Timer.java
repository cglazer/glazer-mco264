package simulation;
public class Timer {
	private int minutes;
	/***** methods *****/
	

	/* Constructor
	 *  Receive:  An initial value to start the timer.
	 *  Postcondition: Timer.minutes initialized to initTime.
	 **************************************************************/
	Timer(int initTime)
	{  if (initTime > 0)
	     minutes = initTime;
	   else
		 minutes =0;
	}
    
	Timer(){
		minutes = 0;
	}
	
	void setTimer(int min)throws RuntimeException{
		if (min > 0)
			minutes = min;
		else 
			throw new RuntimeException("incorrect time");
		
		
	}
	/* Time remaining
	 *  Return:  Time left before timer runs out
	 **************************************************************/
	int timeRemaining() 
	{ return minutes; }


	/* Decrement by one minute
	 *  Return:  Timer with time minute decremeted
	 **************************************************************/
	void tick()
	{ minutes--; }

	/* Check if timer has run out
	 *  Return:  True if time has expired, false otherwise
	 **************************************************************/
	boolean hasTimeLeft() 
	{ return (minutes > 0); }

	
	}  // end of class declaration


