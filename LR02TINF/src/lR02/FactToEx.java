package lR02;
import java.util.ArrayList;
import java.util.Scanner;

public class FactToEx {
	 public static void main(String[] args) {
		 Scanner in = new Scanner(System.in);
//	        задание I № 5
	      //tests
//	        boolean test51 = (FacttoEx(2121) == 59);
//	        boolean test52 = (FacttoEx(12) == 4);
//	        boolean	test53 = (FacttoEx(2210) == 62);
//	        boolean test54 = (FacttoEx(10101) == 127);
//	        boolean test55 = (FacttoEx(10000101) == 40327);

		 	TestFacttoEx();
	        System.out.println("Input a number: ");
	        String num1 = in.next();
	        System.out.println(FacttoEx(num1));
	        in.close();
	 }
	 static int Fact(int n) { // ФАКТОРИАЛ
		  if (n == 0 ) return 1;
		 return n*Fact(n-1);
	}
	 static String FacttoEx(String input) { // задание I № 5
		 int inputint = Integer.parseInt(input);
		 int sum = 0, i = 1;
		 while (inputint > 0) {
			 sum = sum + (inputint % 10)*Fact(i);
			 inputint = inputint / 10;
			 i++;
		 }
		 return	Integer.toString(sum);
	 }
	 static void TestFacttoEx() {
		 	int count = 0;
		    ArrayList<Boolean> tests = new ArrayList<>();
	 		for (int i = 1; i <= 1000; i++) { 
	 		//System.out.println(i);
	 		tests.add(0, null);
	 		tests.add(i,(Integer.toString(i).equals(FacttoEx(ExtoFact.ExToFact(Integer.toString(i))))));
	 		//equals - метод для сравнения строк
	 		if (tests.get(i) == false)
	 		{
	 			System.out.println("Ошибка при значении "+i);
	 			break;
	 		}
	 		count++;
	 		}
	 		if (count == 1000) System.out.println(true);
	 }
}
