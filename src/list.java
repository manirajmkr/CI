import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class list {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		List<String> l=new ArrayList();
		for(int i=0;i<50;i++)
		{
			
		l.add("test"+i);
		
		}
		
		List<Object> set= new ArrayList();
		set.add(l);
		System.out.println(l.get(0));
		System.out.println(l.get(1));
		System.out.println("====================================");
		System.out.println(set.get(0));
		
	}

}
