import java.util.ArrayList;
import java.util.Scanner;
public class Fibonacci {
	 public static void main(String[] args) {
		 Scanner in = new Scanner(System.in);
		 System.out.print("Введите число n: ");
		 int n = in.nextInt();
		 System.out.println("Код: "+FibonacciCode(n));
		 System.out.println("Декодирование: "+FibonacciDecode(FibonacciCode(n)));
		 TestsFibonacciCode(); //
		 in.close();
	 }
	 
	 static String FibonacciCode(int n) {
		 if (n == 0)
			 return "0";
		 StringBuffer code = new StringBuffer(ExToFib(n));
		 //System.out.println(code);
		 code = code.reverse().append("1");
		 //System.out.println(code);
		 return code.toString();
	 }
	 
	 static int FibonacciDecode(String code) {
		 if (code.equals("0"))
			 return 0;
		 StringBuffer codebuf = new StringBuffer(code.substring(0, code.length()-1 ));
		 codebuf = codebuf.reverse();
		 int result = FibToEx(codebuf.toString());
		 //System.out.println(code);
		 return result;
	 }
	 
	 static int Fib(int n) { // Фибоначчи
		  if (n == 0 || n == 1 ) return 1;
		 return Fib(n-2)+Fib(n-1);
	 }
	 
	 static String ExToFib(int input) { 
		 int inputint = input;
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
	 
	 static int FibToEx(String input) { 
		 //long inputint = Integer.parseInt(input);
		 int sum = 0;
		 int i = 1;
		 while (input.length() > 0) {
			 sum = sum + (Integer.parseInt(input.substring(input.length()-1)))*Fib(i);
			 input = input.substring(0, input.length()-1);
			 i++;
		 }
		 return  sum;
	 }
	 
	 //test
	 static void TestsFibonacciCode() {
//		    tests.add(0, null);
	 			ArrayList<Boolean> tests = new ArrayList<Boolean>();
	 			for (int n = 0; n <= 150; n++) {
	 			//System.out.println(i);
	 			tests.add(n,(n == (FibonacciDecode(FibonacciCode(n)))));
	 			//System.out.println("n ="+n + "; k =" +k+ "; Код: " + RiceCode(n,k));
	 			if (tests.get(n) == false)
	 			{
	 				System.out.println("Ошибка при значении "+(n));
	 				break;
	 			}
	 			if (n == 150)
	 				System.out.println("Все тесты вернули значение "+ true);
	 			}
	 }
}