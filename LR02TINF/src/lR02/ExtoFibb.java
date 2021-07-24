package lR02;
import java.util.ArrayList;
import java.util.Scanner;

public class ExtoFibb {
	 public static void main(String[] args) {
		 Scanner in = new Scanner(System.in);
	        //задание II № 6
		 //tests
//	        boolean test61 =  ExToFib(47).equals("10100000") ; // equals - метод для сравнения строк
//	        boolean test62 = ExToFib(62).equals("100001010");
//	        boolean	test63 = ExToFib(83).equals("101001010");
//	        boolean test64 = ExToFib(100).equals("1000010100");
//	        if (test61 && test62 && test63 && test64) 
//	        	System.out.println("true");
//	        else System.out.println("false");
//	        
		 	TestExToFib();
	        System.out.println("Input a number: ");
	        String num2 = in.next();
	        System.out.println(ExToFib(num2));
	        in.close();

	 }
	 static int Fib(int n) { // Фибоначчи
		  if (n == 0 || n == 1 ) return 1;
		 return Fib(n-2)+Fib(n-1);
	 }
	 
	 static String ExToFib(String input) { 
		 int inputint = Integer.parseInt(input);
		 int i = 0, sum = inputint, counter = 0; // i - номер пограничного числа фиббоначи, counter - счетчик для заполнения массива разрядов единиц
		 // sum - тоже самое, что input, необходим для того, чтобы не менять значение input
		 int sumrezult = 0; // конечная сумма разложения; необходима для распознования самого правого разряда с единицей
		 //		 ArrayList<Integer> razr = new ArrayList<Integer>();
		 int [] razr = new int [30]; // Массив разрядов
		 String result = ""; //Строка результат функции
		 do {
			 if (Fib(i) <= sum && Fib(i+1) > sum) { // поиск крайнего значения фиббоначи
				 razr[counter] = i;
				 if (result == "") // последний разряд
					 result = result + 1; 
				 else {
					 for (int j = 1; j < razr[counter-1]-razr[counter]; j++)
						 result = result + 0;
					 result = result + 1;
				 }
				 sum = sum - Fib(i);
				 sumrezult = sumrezult + Fib(i);
				 counter++;
				 i = 0;
			 }
			 if (sumrezult == inputint) { // распознование крайнего правого единичного разряда и заполнение остальных нулями
				 int temp = razr[counter-1];
				 for (int k = 1; k < temp; k++)
					 result = result + 0;
			 }
			 i++;
		 } while (sumrezult != inputint);
		 return result;
	 }
	 static void TestExToFib() {
		 	int count = 0;
		    ArrayList<Boolean> tests = new ArrayList<>();
	 		for (int i = 1; i <= 100; i++) { 
	 		//System.out.println(i);
	 		tests.add(0, null);
	 		tests.add(i,(Integer.toString(i).equals(FibbToEx.FibToEx(ExToFib(Integer.toString(i))))));
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

class eq { // переопределение equals
	   int eqex;

	   public boolean equals(eq eq1) {
	       return this.eqex == eq1.eqex;
	   }
}