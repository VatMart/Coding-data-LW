import java.util.ArrayList;
import java.util.Scanner;
public class ExpGolomb {
	 public static void main(String[] args) {
		 Scanner in = new Scanner(System.in);
		 System.out.print("Введите число n и k через пробел ");
		 int n = in.nextInt();
		 int k = in.nextInt();
		 //System.out.println(w(n, k));
		 //System.out.println(f(n, k));
		 System.out.println("Код: "+ExpGolombCode(n, k));
		 System.out.println("Декодирование: "+ExpGolombDecode(ExpGolombCode(n, k),k));
		 TestsExpGolombCode(); //
		 in.close();
	 }
	 
	 static String ExpGolombCode(int n, int k) {
		 String unary = UnaryCode(f(n,k));
		 String binW = IntToBin(w(n,k)).substring((IntToBin(w(n,k)).length() - f(n,k)));
		 if (binW.length() > f(n,k)) {
			 binW = binW.substring(binW.length() - f(n,k));
		 }
		 while (binW.length() < f(n,k)) {
			 binW = "0"+binW;
		 }
		 String binN = IntToBin(n);
		 if (binN.length() > k) {
			 binN = binN.substring(binN.length() - k);
		 }
		 while (binN.length() < k) {
			 binN = "0"+binN;
		 }
//		 System.out.println("Унарный Код: "+unary);
//		 System.out.println("w(n,k) Код: "+binW);
//		 System.out.println("N Код: "+binN);
		 return unary + binW + binN;
	 }
	 
	 static int ExpGolombDecode(String code, int k) {
		 int n;
		 int f;
		 if (code.charAt(0) == '0') {
			 n = BinToInt(code.substring(0, k+1));
			 return n;
		 }
		 else f = UnaryDecode(code);
//		 System.out.println("f: "+f);
		 code = code.substring(f+1); 
//		 System.out.println("без Унарный Код: "+code);
		 int w = (BinToInt("1" + code.substring(0,f))-1)*(int)Math.pow(2, k);
		 //System.out.println("w : "+w);
		 code = code.substring(f);
//		 System.out.println("без W Код: "+code);
		 String star;
		 if (isPowOfTwo(w))
			 star = IntToBin(w).substring(0,f);
		 else
		 star = IntToBin(w).substring(0,f);
//		 System.out.println("star : "+star);
		 String mlad = code.substring(0,k);
//		 System.out.println("mlad : "+mlad);
		 int res = BinToInt(star + mlad);
//		 System.out.println("res : "+res);
		 return res;
	 }
	 
	 static boolean isPowOfTwo(int n) {
		 while (n != 1 && n % 2 == 0) {
	            n /= 2;
	        }
		 return n == 1 ? true : false;
	 }
	 
	 static int f(int n, int k) {
		 int result = (int) (Math.log(1+(n / Math.pow(2, k)))/Math.log(2));
		 return result;
	 }
	 
	 static int w(int n, int k) {
		 int result = (int) (1 + n / Math.pow(2, k));
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
	 //test
	 static void TestsExpGolombCode() {
			
//		    tests.add(0, null);
	 		for (int k = 3; k <= 8; k++) { 
	 			ArrayList<Boolean> tests = new ArrayList<Boolean>();
	 			for (int n = 0; n <= 15; n++) {
	 			//System.out.println(i);
	 			tests.add(n,(n == (ExpGolombDecode(ExpGolombCode(n,k),k))));
	 			//System.out.println("n ="+n + "; k =" +k+ "; Код: " + RiceCode(n,k));
	 			if (tests.get(n) == false)
	 			{
	 				System.out.println("Ошибка при значении "+(n) + " k = " + (k));
	 				
	 			}
	 			if (n == 15 && k ==8)
	 				System.out.println("Все тесты вернули значение "+ true);
	 			}
	 		}
	 }
}