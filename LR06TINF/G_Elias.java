import java.util.ArrayList;
import java.util.Scanner;

public class G_Elias {
	 public static void main(String[] args) {
		 Scanner in = new Scanner(System.in);
		 System.out.print("Введите число ");
		 int num = in.nextInt();
		 System.out.println("Код: "+G_EliasCode(num));
		 System.out.println("Декодирование: "+G_EliasDecode(G_EliasCode(num)));
		 TestsG_EliasCode();
		 in.close();
	 }
	 
	 static String G_EliasCode(int innum) {
		 int lowbound = (int) Math.pow(2, 0), upbound = (int) Math.pow(2, 1);
		 int k, count = 0;
		 if (innum == 0) {
			 System.out.println("Для числа 0 не существует кода");
			 return null;
		 }
		 while (!(innum >= lowbound && innum < upbound)) {
			 count++;
			 lowbound = (int) Math.pow(2, count);
			 upbound = (int) Math.pow(2, count+1);			 
		 }
		 k = count;
		 //System.out.println(k);
		 String result = IntToBin(innum).substring(1);
		 result = UnaryCode(k) + result;
		 return result;
	 }
	 
	 static int G_EliasDecode(String strcode) {
		 int result;
		 if (strcode == null) {
			 System.out.println("Ошибка! Невозможно декодировать null!");
			 return -1;
		 }
		 String strunary = strcode.substring(0,strcode.indexOf('1')+1);
		 strcode = strcode.substring(strcode.indexOf('1')+1);
		 int k = UnaryDecode(strunary);
		 result = BinToInt(strcode) + (int) Math.pow(2, k);
		 return result;
	 }
	 
	 static String UnaryCode (int innum) {
		 String result = "";
		 if (innum == 0)
			 return "1";
		 for (int i = 0; i < innum; i++) {
			 result += "0";
		 }
		 result += "1";
		 return result;
	 }
	 
	 static int UnaryDecode (String strcode) {
		 int result = 0;
		 char c;
		 for (int i = 0; i < strcode.length(); i++) {
			 c = strcode.charAt(i);
			 if (c == '0') {
				 result++;
			 }
			 if (c == '1' && i != strcode.length()-1) {
				 System.out.println("Ошибка! Код не является унарным");
				 return -1;
			 }	 
		 }
		 if (result == strcode.length()-1)
			 return result;
		 else {
			 System.out.println("Ошибка! Код не является унарным");
			 return -1;
		 }
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
	 static void TestsG_EliasCode() {
		    ArrayList<Boolean> tests = new ArrayList<>();
 			tests.add(0, null);
	 		for (int i = 1; i <= 1000; i++) { 
	 			//System.out.println(i);
	 			tests.add(i,(i == (G_EliasDecode(G_EliasCode(i)))));
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