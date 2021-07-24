package lR02;
import java.util.ArrayList;
import java.util.Scanner;

public class NegatoEx {
	 public static void main(String[] args) {
		 Scanner in = new Scanner(System.in);
		 //tests
//	        boolean test61 = (NegaToEx(110001) == -15.0);
//	        boolean test62 = (NegaToEx(10011) == 15.0);
//	        boolean	test63 = (NegaToEx(11110) == 10.0);
//	        boolean test64 = (NegaToEx(111101) == -19.0);
//	        if (test61 && test62 && test63 && test64) 
//	        	System.out.println("true");
//	        else System.out.println("false");
//	        
	        //задание III № 2
	        System.out.println("Input a number: ");
	        String num4 = in.next();
	        System.out.println(NegaToEx(num4));
	        in.close();
	 }
	 
	 static String NegaToEx(String input) { //задание III № 2
		int inputint = Integer.parseInt(input);
		int sum = 0;
		int i = 0;
		while (inputint > 0) {
			 sum = (int) (sum + (inputint % 10)*Math.pow((-2), i));
			 inputint = inputint / 10;
			 i++;
		 }
		 return Integer.toString(sum);
	 }
	 
	 static void TestNegaToEx() {
		 	int count = 0;
		    ArrayList<Boolean> tests = new ArrayList<>();
	 		for (int i = 1; i <= 100; i++) { 
	 		//System.out.println(i);
	 		tests.add(0, null);
	 		tests.add(i,(Integer.toString(i).equals(NegaToEx(ExtoNega.ExToNega(Integer.toString(i))))));
	 		//equals - метод для сравнения строк
	 		if (tests.get(i) == false)
	 		{
	 			System.out.println("Ошибка при значении "+i);
	 			break;
	 		}
	 		count++;
	 		}
	 		if (count == 100) System.out.println(true);
	 }
}