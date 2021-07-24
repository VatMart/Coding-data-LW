package lR02;
import java.util.ArrayList;
import java.util.Scanner;
public class ExtoFact {
	 public static void main(String[] args) {
		 Scanner in = new Scanner(System.in);
		 	//(ExtToFact(59) == 2121)
//	        boolean test52 = (ExtToFact(4) == 20);
//	        boolean	test53 = (ExtToFact(62) == 2210);
//	        boolean test54 = (ExtToFact(127) == 10101);
//	        boolean test55 = (ExtToFact(40327) == 10000101);
		 	TestExtoFact();
		 	//System.out.println(Integer.parseInt(ExtToFact(Integer.toString(2))) == (Integer.parseInt(FactToEx.FacttoEx(ExtToFact(Integer.toString(2))))));
	        System.out.println("Input a number: ");
	        String num1 = in.next();
	        System.out.println(ExToFact(num1));
	        in.close();
	 }
	 static String ExToFact(String input) { 
		 int inputint = Integer.parseInt(input); // перевод введенной строки в int
		 String ost = "";
		 int sum = 0, i = 2;
		 while (inputint != 0) {
			 sum = inputint / i;
			 ost = (inputint - sum*i) + ost;
			 inputint = (inputint) / i;
			 i++;
		 }
		 return ost;
	 }
	 static void TestExtoFact() {
		 	int count = 0;
		    ArrayList<Boolean> tests = new ArrayList<>();
	 		for (int i = 1; i <= 1000; i++) { 
	 		//System.out.println(i);
	 		tests.add(0, null);
	 		tests.add(i,(Integer.toString(i).equals(FactToEx.FacttoEx(ExToFact(Integer.toString(i))))));
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
