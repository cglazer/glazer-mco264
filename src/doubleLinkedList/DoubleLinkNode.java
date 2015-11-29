package doubleLinkedList;

	import java.io.Serializable;

	public class DoubleLinkNode<T extends Serializable & Comparable<T> >
	   implements Serializable
	{
	    
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		protected T data;
	    protected DoubleLinkNode<T> prev;
	    protected DoubleLinkNode<T> next;
	    
	   public DoubleLinkNode(){
	       data = null; prev = null; next = null;
	       // 	super();
	    //	prev = null;
	    //
	       }
	  
	    public DoubleLinkNode(T value){
	    	data = value;
	    	prev = null;
	    	next = null;
	    }
	    
	    public void setPrev(DoubleLinkNode<T> aNode){
	    	prev = aNode;
	    }
	    
	    public DoubleLinkNode<T> getPrev(){
	    	return prev;
	    }
	    
	    public void setNext(DoubleLinkNode<T> aNode){
	    	next = aNode;
	    }
	    
	    public DoubleLinkNode<T> getNext(){
	    	return next;
	    }
	    
	    public T getData(){
	    	return data;
	    }
	
}
