package randomtest;
import java.util.Random;
public class randomtest {
	public static void main(String[] args) { 
		Random r=new Random(); 
		for(int i=0;i<10;i++){ 
		    System.out.println(r.nextInt(10000000)); 
		}
	}
}
