package simulation;
public class Call {
	
	  /***** instance variables *****/
	  private  int myTimeOfArrival;
	  private  int myServiceTime;
	
	    /***** methods *****/
	   /***** Constructor *****/
	  
	  /*----------------------------------------------------------
	     Construct a Call object (default).

	     Precondition:  None
	     Postcondition: All data members are initialized to 0.
	       
	   -----------------------------------------------------------*/
	   public Call(){ 
	    myTimeOfArrival = myServiceTime = 0; 
	 }
	  
	   /*----------------------------------------------------------
	     Construct a Call object (explicit-value).

	     Precondition:  Countdown timer t is received
	     Postcondition: myTimeOfArrival has been set to time left
	         on Timer t and myServiceTime to serviceTime.
	       
	   -----------------------------------------------------------*/
	   public Call( Timer  t, int serviceTime)
	   { 
		    // record call's time of arrival
		    myTimeOfArrival = t.timeRemaining();
		   
		    // set its service time
		    myServiceTime = serviceTime;
		 }
	  

	   public int getArrivalTime()  {
		    return myTimeOfArrival;
		 }
	   

	   public int getServiceTime() {
		    return myServiceTime;
		 }
	  

	   void display() {
		   System.out.println( "Arrival Time:    " +  myTimeOfArrival +
	 	       "\nService Time:    " + myServiceTime );
	 }

	   
	 
	 } 


	
	 
	
	 

	

	 
	

	
	