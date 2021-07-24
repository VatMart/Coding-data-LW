import java.util.ArrayList;
import java.util.Scanner;

public class G_Loewenstein {
	 public static void main(String[] args) {
		 Scanner in = new Scanner(System.in);
		 System.out.print("Введите число ");
		 int num = in.nextInt();
		 System.out.println("Код: " + G_LoewensteinCode(num));
		 System.out.println("Декодирование: " + G_LoewensteinDecode(G_LoewensteinCode(num)));
		 TestsG_LoewensteinCode();
		 in.close();
	 }
	 static String G_LoewensteinCode(int innum) {
		 String result = new StringBuffer(IntToBin(innum)).reverse().toString();// обращение двоичного представления
		 char lastbit = result.charAt(result.length()-1); //последний ,bn
		 result = result.substring(0, result.length()-1); // удаление последнего бита
		 result = result.replace("0", "00"); // замена 0 на 00
		 result = result.replace("1", "01") + lastbit;
		 //System.out.println(result);
		 return result;
	 }
	 
	 static int G_LoewensteinDecode(String strcode) {
		 char lastbit = strcode.charAt(strcode.length()-1);
		 
		 strcode = strcode.substring(0, strcode.length()-1);
		 strcode = strcode.replace("01", "1");
		 strcode = strcode.replace("00", "0") + lastbit;
		 strcode = new StringBuffer(strcode).reverse().toString();	
		 
		 int result = BinToInt(strcode);
		 return result;
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
		 for (int i = value.length()-1; i >= 0; i--) {
			char c = value.charAt(i);
			if (c == '1')
				result += Math.pow(2, value.length()-(i+1));
			//System.out.println(result);
		}
		 return result;
	 }
	 //tests
	 static void TestsG_LoewensteinCode() {
		    ArrayList<Boolean> tests = new ArrayList<>();
	 		for (int i = 0; i <= 50; i++) { 
	 			//System.out.println(i);
//	 			tests.add(0, null);
	 			tests.add(i,(i == (G_LoewensteinDecode(G_LoewensteinCode(i)))));
	 			//System.out.println(i + " = " + (G_LoewensteinDecode(G_LoewensteinCode(i))));
	 			if (tests.get(i) == false)
	 			{
	 				System.out.println("Ошибка при значении "+i);
	 				break;
	 			}
	 		}
	 		System.out.println("Все тесты вернули значение "+ true);
	 }
	 
}