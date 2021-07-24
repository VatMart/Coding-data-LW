import java.util.ArrayList;
import java.util.Scanner;

public class Loewenstein {
	 public static void main(String[] args) {
		 Scanner in = new Scanner(System.in);
		 System.out.print("Введите число ");
		 int num = in.nextInt();
		 System.out.println("Код: "+LoewensteinCode(num));
		 System.out.println("Декодирование: "+LoewensteinDecode(LoewensteinCode(num)));
		 TestsLoewensteinCode();
		 in.close();
	 }
	 static String LoewensteinCode(int innum) {
		String binnum;
		int count = 1;
		int M = innum;
		String result = "";
		if (innum == 0) {
			//System.out.println("0");
			return "0";
		}
		do {
			binnum = IntToBin(M).substring(1);
			result = binnum + result;
			M = binnum.length();
			count++;
		} while (M != 0);
		result = "0" + result;
		for (int i = 1; i < count; i++) {
			result = "1" + result;
		}
		//System.out.println(result);
		 return result;
	 }
	 
	 static int LoewensteinDecode(String strcode) {
//		 System.out.println("Изначальный код: "+strcode);
		char ch;
		int i = 0;
		int count = 0;
		do {
			ch = strcode.charAt(i);
			i++;
			if (ch == '1')
				count++;
		} while (ch != '0');
		if (count == 0)
			return 0;
		strcode = strcode.substring(count+1);
//		System.out.println("С = "+count);
//		System.out.println("удалены единицы и нуль: "+strcode);
		int N = 1, p = count - 1;
		String Z = strcode;
		while (p != 0) {
			Z = strcode.substring(0,N);
			strcode = strcode.substring(N);
//			System.out.println("Строка при str "+strcode);
//			System.out.println("Строка z "+Z);
			Z = "1" + Z;
			N = BinToInt(Z);
			p--;
		}
//		System.out.println("Ответ " + N);
		 return N; 
	 }
	 
	 static String IntToBin(int value) {
		 int intvaluetemp = value;
		 String res = "";
		 if (value == 0) {
			 res = "0";
			 return res;
		 }
		 while (value != 0) {
			 intvaluetemp = value % 2;
			 res = intvaluetemp + res;
			 value = value / 2;
		 }
		 //System.out.println(res);
		 return res;
	 }
	 static int BinToInt(String value) {
		 int result = 0;
		 for (int i = 0; i < value.length(); i++) {
			char c = value.charAt(i);
			if (c == '1')
				result += Math.pow(2, value.length()-(i+1));
			//System.out.println(result);
		}
		 return result;
	 }
	 //tests
	 static void TestsLoewensteinCode() {
		    ArrayList<Boolean> tests = new ArrayList<>();
	 		for (int i = 0; i <= 50; i++) { 
	 			//System.out.println(i);
//	 			tests.add(0, null);
	 			tests.add(i,(i == (LoewensteinDecode(LoewensteinCode(i)))));
	 			//System.out.println(i + " = " + (LoewensteinDecode(LoewensteinCode(i))));
	 			if (tests.get(i) == false)
	 			{
	 				System.out.println("Ошибка при значении "+i);
	 				break;
	 			}
	 		}
	 		System.out.println("Все тесты вернули значение "+ true);
	 }
}
	 