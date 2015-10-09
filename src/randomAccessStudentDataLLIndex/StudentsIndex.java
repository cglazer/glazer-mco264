package randomAccessStudentDataLLIndex;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import randomAccessExceptions.*;
import randomAccessStudentData.StudentIndexRec;

public class StudentsIndex implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LinkedList<StudentIndexRec> index;
	
	public StudentIndexInternalIterator iter;
	
	
	/**
	 * set up an index  for the first time
	 * 
	 */
	public StudentsIndex (){
		//initialize the index array
		index = new LinkedList<StudentIndexRec>();   //no need to specify a quantity
		iter = new StudentIndexInternalIterator();
		
	}
	
	/**restore an index from a file
	 * @exception FileNotFoundException - file cant be found
	 * @exception IOException problem closing file
	 * @exception ClassNotFoundException - inconsistency between way data was stored and current class definition
	 * @param fileName
	 */
	public StudentsIndex (String fileName)throws Exception{
		ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName));
		//read in the array as a series of bits, will reconstruct the array and all the references
		//and the StudentIndexRec instances it is referencing
		index =(LinkedList<StudentIndexRec>) inputStream.readObject();
		inputStream.close();
		
		iter= new StudentIndexInternalIterator();
		
		
	}
	
	/**
	 * 
	 * @param studentID
	 * @param fileLocation
	 * @throws Exception if duplicate or no more room in the index array
	 */
	public void addStudentToIndex(Integer studentID, Long fileLocation)throws Exception{
		//make sure not duplicate
		try{
			index.find(studentID);
			//if found student , this is an error
			throw new DuplicateDataException();
		}
		catch(NotFoundException notfound){
			
			index.add( new StudentIndexRec(studentID,fileLocation));
			
		}
	}
	
	/**
	 * 
	 * @param studentID
	 * @return  Long  - location of record in the data file
	 * @throws NotFoundException
	 */
	
	public Long findStudentLocation (Integer studentID)throws NotFoundException{
		StudentIndexRec indexRecord = index.find(studentID);
		return indexRecord.getFileLocation();
		
	}
	
	
	
	/**
	 * 
	 * @param studentID
	 * @return true if studentid appears in the index array
	 */
	
	public boolean hasStudent(Integer studentID){
		try {
			index.find(studentID);
			return true;
		}
		catch(NotFoundException e){
			return false;
		}
	}
	
	public void removeStudent(Integer studentID)throws NotFoundException{
		index.remove(studentID);
	}
	

	
	class StudentIndexInternalIterator implements Serializable{
		
		public StudentIndexInternalIterator(){
			index.iter.reset();
		}
		
		public boolean hasNext(){
			//delegate the job
			return index.iter.hasNext();
		}
		
		public StudentIndexRec next(){
			return index.iter.next();
		}
		
		public  void reset(){
			index.iter.reset();
		}
	}

}
