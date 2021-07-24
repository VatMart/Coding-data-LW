package lR02;
import java.util.ArrayList;
import java.util.Scanner;

public class FibbToEx {
	 public static void main(String[] args) {
		 Scanner in = new Scanner(System.in);
	        //задание II № 6
		 //tests
//	        boolean test61 = (FibToEx(10100000) == 47);
//	        boolean test62 = (FibToEx(100001010) == 62);
//	        boolean	test63 = (FibToEx(101001010) == 83);
//	        boolean test64 = (FibToEx(1000010100) == 100);
//	        if (test61 && test62 && test63 && test64) 
//	        	System.out.println("true");
//	        else System.out.println("false");
	        TestFibToEx();
	        System.out.println("Input a number: ");
	        String num2 = in.next();
	        System.out.println(FibToEx(num2));
	        in.close();

	 }
	 static int Fib(int n) { // Фибоначчи
		  if (n == 0 || n == 1 ) return 1;
		 return Fib(n-2)+Fib(n-1);
	 }
	 
	 static String FibToEx(String input) { //задание II № 6
		 int inputint = Integer.parseInt(input);
		 int sum = 0, i = 1;
		 while (inputint > 0) {
			 sum = sum + (inputint % 10)*Fib(i);
			 inputint = inputint / 10;
			 i++;
		 }
		 return Integer.toString(sum);
	 }
	 static void TestFibToEx() {
		 	int count = 0;
		    ArrayList<Boolean> tests = new ArrayList<>();
	 		for (int i = 1; i <= 100; i++) { 
	 		//System.out.println(i);
	 		tests.add(0, null);
	 		tests.add(i,(Integer.toString(i).equals(FibToEx(ExtoFibb.ExToFib(Integer.toString(i))))));
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
