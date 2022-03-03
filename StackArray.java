import java.util.Stack;

public class StackArray <E>{
	int Size ,top;
	E Array [];
// define the size of Array Stack in Constructor 
public StackArray(int n) {
	this.Size=n;
	this.Array=(E[]) new Object[n];
	top=0;
}
// TO check if it full
public boolean isFull() {
	return(Size==top);
}
// check it if empty
public boolean isEmpty() {
	return (top==0);
}

public E peek() {
	return Array[top];
}
// for insert 
public void  push(E entry) {	
	if(!isFull())
		Array[++top]=entry;	
}
// to exit 
public E pop() {
	if(! isEmpty()) {
		top--;				
	}
	return Array[top];		
}
public void Clear() {
	top=0;
}
public static void main (String [] args) {
	
}
}
