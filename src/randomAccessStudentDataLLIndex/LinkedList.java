package randomAccessStudentDataLLIndex;

import java.io.Serializable;

import randomAccessExceptions.NotFoundException;
import randomAccessStudentData.StudentIndexRec;

public class LinkedList<T> implements Serializable{
	private Node<T> head;
	
	public StudentIndexInternalIterator iter;
	
	public LinkedList(){
		head = null;
		iter = new StudentIndexInternalIterator();
	}
	
	public void add(StudentIndexRec indexRec){
		Node<T> currentNode;
		Node<T> prevNode;
		if (head == null){   //the first Node in the list points to the student index record
			head = new Node<T>(indexRec);
		}
		else{
			Node<T> newNode = new Node<T>(indexRec);  //allocate a new Node
			//find the right spot for the Node inside the list
			currentNode = prevNode = head;
			
			while (currentNode != null  && indexRec.compareTo(currentNode.getData())>0){
				prevNode = currentNode;
				currentNode = currentNode.getNext(); //move along to next Node
			}
			//found the right place , attach the links
			if (currentNode == head){
				//the new node will become the new head and point to the current head
				newNode.setNext(head);
				head = newNode;
			}
			else{  //goes in between other node or at the end of the list
			prevNode.setNext(newNode);
			newNode.setNext(currentNode);
			}
		    
			
			
		}
	}
	
	public void remove (Integer studentID)throws NotFoundException{
		Node<T> currentNode = head;
		Node<T> prevNode = head;
		
		while (currentNode != null){
			if (currentNode.getData().getStudentID().equals(studentID)){
				//this is the Node that must be removed
				if (currentNode == head){
					head = head.getNext();  //must reset the head
					return;
				}
				else{
				  prevNode.setNext(currentNode.getNext());
				  return;
				}
			}
			else{
				//move further along in the list
				prevNode = currentNode;
				currentNode = currentNode.getNext();
			}
		}
		throw new NotFoundException();
	}
	
	public StudentIndexRec find(Integer studentID)throws NotFoundException{
		Node<T> currentNode = head;  //start to iterate through the list
		while (currentNode  != null){
			if (currentNode.getData().getStudentID().equals(studentID)){
				return currentNode.getData();
			}
			currentNode = currentNode.getNext(); //move further down the list
		}
		throw new NotFoundException();
	}

	class StudentIndexInternalIterator implements Serializable{
		
		private Node<T> currentNode;
		
		public StudentIndexInternalIterator(){
			currentNode = head;
		}
		
		public void reset(){
			currentNode = head;
		}
		
		public boolean hasNext(){
			if (currentNode != null){
				return true;
			}
			else{
				return false;
			}
		}
		
		public StudentIndexRec next(){
			StudentIndexRec currIndexRec = currentNode.getData();
			currentNode = currentNode.getNext();//move to next Node
			return currIndexRec;
		}
	}
}
