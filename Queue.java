import java.util.NoSuchElementException;
/**
 * @author Toumanidou Andromachi 3040185
 * 
 */
public class Queue<T>
{
	private List<T> myList;//lista monhs syndeshs parametrikou typou
	private int currentSize;//mege8os ouras


	public Queue()
	{
		myList = new List<T>("Queue");
		currentSize = 0;
	}
	
	
	public Queue(String name)
	{
		myList = new List<T>(name);
		currentSize = 0;
	}
	
	public boolean isEmpty()
	{
		return myList.isEmpty();
	}

	
	public void put(T item)
	{
		myList.insertAtBack(item);//ylopoihsh tou first-in-first-out. To stoixeio prepei na mpei ekei pou deixnei to tail
		currentSize++;//aykshsh tou size me thn eisodo enos epipleon stoixeiou
	}

	public T get() throws NoSuchElementException
	{
		if (isEmpty())
			throw new NoSuchElementException("MyList get Error");
		T returnValue = myList.removeFromFront();//ylopoihsh tou first-in-first-out. To stoixeio(to palaiotero) prepei na bgei apo ekei pou deixnei to head
		currentSize--;//meiwsh tou size me thn eksodo enos epipleon stoixeiou
		return returnValue;
	}


	public int size()
	{
		return this.currentSize;
	}
}
