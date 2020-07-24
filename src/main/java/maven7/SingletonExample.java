package maven7;
class Example
{
	private static final Example instance = new Example(); 
	  
	  Example() 
	  { 
	    // private constructor 
	  } 
	  public static Example getInstance(){ 
	        return instance; 
	    } 
	  public void sayHello()
	  {
		  System.out.println("HELLO..!!");
	  }
}
public class SingletonExample {
	 public static void main(String []args)
	 {
		Example a=Example.getInstance();
		a.sayHello();
	 }
}

