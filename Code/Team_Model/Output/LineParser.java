import java.util.HashMap;
import java.util.Map;

public class LineParser extends Thread 	{
	String lines; 
	int number;
	Map<String, Integer> map = new HashMap<>();

	LineParser(String lines,int number) 
	{
		this.lines = lines;
		this.number = number;
	}

	public void run() 
	{
		String[] words = lines.split("[ \\.]");

		for (String i : words) {
			if(!i.equals("")){
				Integer n = map.get(i);
				n = (n == null) ? 1 : ++n;
				map.put(i, n);
			}
		}
		SynchronizedOutput.displayMap(number,map);

	}
	public Map<String, Integer> getmap() { return map; }
}
class SynchronizedOutput
{
	public static synchronized void displayMap(int number,Map<String, Integer> map){
		System.out.println("Printing frequency of words in thread:"+number);
		for (Map.Entry<String,Integer> entry : map.entrySet()) {
			String key = entry.getKey();
			int value = entry.getValue();
			System.out.println("Frequency of '" +key +"':"+value);
		}
		System.out.println("\n\n");
	}
}
