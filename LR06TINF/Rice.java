import java.util.ArrayList;
import java.util.Scanner;
public class Rice {
	 public static void main(String[] args) {
		 Scanner in = new Scanner(System.in);
		 System.out.print("Введите число n и k через пробел ");
		 int n = in.nextInt();
		 int k = in.nextInt();
		 System.out.println("Код: "+RiceCode(n, k));
		 System.out.println("Декодирование: "+RiceDecode(RiceCode(n, k),k));
		 TestsRiceCode(); //
		 in.close();
	 }
	 
	 static String RiceCode(int n, int k) {
		 String code = "";
		 String unarycode;
		 String nol = "";
		 int m = (int) Math.pow(2, k);
		 unarycode = UnaryCode(n / m);
		 String betta = IntToBin(n % m);
		 code = unarycode + betta;
		 while (code.length() < ((n/m == 0) ? (n/m) : (n/m)) + k + 1) {
			 nol += "0";
			 code = unarycode + nol +betta; 
		 }
//		 while (code.length() > ((n/m == 0) ? (n/m+1) : (n/m)) + k + 1) {
//			 int temp = code.length() - ((n/m == 0) ? (n/m+1) : (n/m)) + k + 1;
//			 code = code.substring(temp);
//		 }
		 return code;
	 }
	 
	 static int RiceDecode(String code, int k) {
		 int gamma = UnaryDecode(code);
		 code = code.substring(gamma+1);
//		 System.out.println(code);
		 int delta = BinToInt(code);
		 int m = (int) Math.pow(2, k);
		 int n = gamma*m + delta;
		 //System.out.println(m);
		 return n;
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
	 
	 static String UnaryCode (int innum) {
		 String result = "";
		 if (innum == 0)
			 return "0";
		 for (int i = 0; i < innum; i++) {
			 result += "1";
		 }
		 result += "0";
		 return result;
	 }
	 
	 static int UnaryDecode (String strcode) {
		 int i = 0;
		 while (strcode.charAt(i) != '0') {
			 i++;
		 }
		 return i;
	 }
	 
	 static void TestsRiceCode() {
	
//		    tests.add(0, null);
	 		for (int k = 0; k <= 6; k++) { 
	 			ArrayList<Boolean> tests = new ArrayList<Boolean>();
	 			for (int n = 0; n <= 17; n++) {
	 			//System.out.println(i);
	 			tests.add(n,(n == (RiceDecode(RiceCode(n,k),k))));
	 			//System.out.println("n ="+n + "; k =" +k+ "; Код: " + RiceCode(n,k));
	 			if (tests.get(n) == false)
	 			{
	 				System.out.println("Ошибка при значении "+n);
	 				break;
	 			}
	 			if (n == 17 && k ==6)
	 				System.out.println("Все тесты вернули значение "+ true);
	 			}
	 		}
	 }
}