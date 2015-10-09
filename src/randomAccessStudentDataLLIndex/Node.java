package randomAccessStudentDataLLIndex;

import java.io.Serializable;

import randomAccessStudentData.StudentIndexRec;

//keeps a reference to a specific student index record
//keeps a reference to the next Node in the linked list
public class Node<T> implements Serializable {
   private StudentIndexRec data;
   private Node<T> nextNode;
   
   public Node(StudentIndexRec data){
	   this.data = data;
	   this.nextNode = null;
   }
   
   public Node(StudentIndexRec data, Node<T> nextNode){
	   this.data = data;
	   this.nextNode = nextNode;
   }
   
   public void setNext(Node<T> nextNode){
	   this.nextNode  = nextNode;
   }
   
   public Node<T> getNext(){
	   return this.nextNode;
   }
   
   public void setData(StudentIndexRec data){
	   this.data = data;
   }
   
   public StudentIndexRec getData(){
	   return this.data;
   }
   
   public int compareTo(Node<T> otherNode){
	   return this.getData().compareTo(otherNode.getData());
   }
}
