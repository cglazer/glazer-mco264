package simulation;
import java.util.Scanner;
import java.util.Random;
import java.util.LinkedList;

public class SimulationValues {
	//
	/* SimulationValues maintains important simulation values.
	 *                  manages the simulation
	=============================================================*/

	         

	static final int NUM_LIMITS = 5;
	//supplied by user
    private double arrivalRate;
	private int[] servicePercent;
	private int   lengthOfSimulation;
	private int busyTimeRemaining;
	// Calculated Outputs - result of simulation
	private   int callsReceived;
	private   double totalWaitingTime;
	private Timer myTimer;
	private Random numGenerator;
	private LinkedList<Call> myIncomingCalls;  //will be a queue of calls

	/***** Function Members *****/
	/*** Constructor
	  Postcondition: Output data members are initialized to 0
	                 and random number generator is seeded
	---------------------------------------------------------*/
	SimulationValues()
	{
	  callsReceived = 0;
	  totalWaitingTime = 0;
	  busyTimeRemaining =0;
	  servicePercent = new int[NUM_LIMITS];
	  myTimer = new Timer();
	  numGenerator = new Random();  //will be used to generate random numbers
	  myIncomingCalls = new LinkedList<Call>();
	}

	
	/*** Input
	  Input: Arrival rate, percentages for service times,
	         and time limit for simulation
	  Postcondition: Input data members are set.
	---------------------------------------------------------*/
	void setValues(Scanner in){{
		  System.out.println( "Enter arrival rate: calls per hour ");
		  int callsPerHour = in.nextInt();
		  arrivalRate = callsPerHour / 60.0;
		  System.out.println( "Enter percent of calls serviced in");
		  int perc, 
		      sum = 0;
		  int j=0;
		  for (int i = 0; i < NUM_LIMITS -1; i++)
		  { j++;
		    System.out.print( "  < "  + j  + " min. ");
		    perc = in.nextInt();
		    sum += perc;
		    servicePercent[i] = sum;
		    System.out.println();
		  }
		  servicePercent[NUM_LIMITS -1] = 100;
		  System.out.println("Enter # of minutes to run simulation: ");
		   lengthOfSimulation = in.nextInt();
		   myTimer.setTimer(lengthOfSimulation);
		}
	
}
	
	 public void run()
	{
	  // Begin the simulation
	   busyTimeRemaining = 0;
	   while (myTimer.timeRemaining() > 0)
	  {
	    service();
	    checkForNewCall();
	    myTimer.tick();
	  }
	   System.out.println ( "Not accepting more calls -- service those waiting");
	  
	  // Service any remaining calls in incomingCalls queue
	  while (!myIncomingCalls.isEmpty())
	  {
	    service();
	    myTimer.tick();
	  }

	  // Output the results
	   display();
	}

    void checkForNewCall(){
    	int x;
    	x = numGenerator.nextInt(100);
    	if (x < 100 * arrivalRate){
    		// a new call has arrived. generate a random service time for it
    		int r = numGenerator.nextInt(100);
    		int serviceTime =0;
    		while (r > servicePercent[serviceTime]){
    			serviceTime++;  //add another minute
    		}
    		//construct a new Call and add it to the queue of incoming calls
    		Call newCall = new Call(myTimer,serviceTime +1);
    		myIncomingCalls.addLast(newCall); //add to back of list
    		callsReceived++;
    	   	}
       }
    
  //--- Definition of service()
    public void service()
    {
       if (busyTimeRemaining > 0)        // servicing a call
          busyTimeRemaining--;           // service it for another minute
       else
          if (!myIncomingCalls.isEmpty())  // calls are waiting -- get one
          {
             Call nextCall = myIncomingCalls.getFirst();
             myIncomingCalls.removeFirst();         
             busyTimeRemaining = nextCall.getServiceTime();
      
             // Update total waiting time
             totalWaitingTime += 
                    nextCall.getArrivalTime() - myTimer.timeRemaining();
          }
    }
	/*** Output
	  Output: total number of call and the average
	          waiting time for calls
	---------------------------------------------------------*/
	void display()
	{
	  System.out.format("Number of calls processed: %d \nAverage waiting time per call: %4.2f \n ", 
			  callsReceived, totalWaitingTime / callsReceived);
	}
}  // end of class declaration

	


