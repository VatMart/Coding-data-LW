package lR02;
import java.util.ArrayList;
import java.util.Scanner;

public class ExtoNega {
	 public static void main(String[] args) {
		 Scanner in = new Scanner(System.in);
		//задание III № 1
		 //tests
//	        boolean test61 = (ExToNega(-15) == 110001);
//	        boolean test62 = (ExToNega(15) == 10011);
//	        boolean	test63 = (ExToNega(10) == 11110);
//	        boolean test64 = (ExToNega(-19) == 111101);
//	        if (test61 && test62 && test63 && test64) 
//	        	System.out.println("true");
//	        else System.out.println("false");
//	        
		 	TestExToNega();
	        System.out.println("Input a number: ");
	        String num3 = in.next();
	        System.out.println(ExToNega(num3));
	        in.close();
	 }
	 
	 static String ExToNega(String input) { //задание III № 1
		 	int inputint = Integer.parseInt(input);
			int sum = 0, temp;
			String ost = "";
			while (inputint != 0) {
				 if (inputint > 0) {
					 sum = (inputint /(-2));
				 } 
				 else if (inputint < 0 && inputint % 2 != 0)
					 sum = (inputint /(-2))+1;
				 else if (inputint < 0 && inputint % 2 == 0)
					 sum = (inputint /(-2));
				 temp = inputint-(-2)*sum;
				 ost = temp + ost; 
				 inputint = sum;
			 }
			 return ost;
		 }
	 
	 static void TestExToNega() {
		 	int count = 0;
		    ArrayList<Boolean> tests = new ArrayList<>();
	 		for (int i = 1; i <= 100; i++) { 
	 			//System.out.println(i);
	 			tests.add(0, null);
	 			tests.add(i,(Integer.toString(i).equals(NegatoEx.NegaToEx(ExToNega(Integer.toString(i))))));
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