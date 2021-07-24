import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
public class SSS {
	 public static void main(String[] args) throws FileNotFoundException {
		 Scanner in = new Scanner(System.in);
		 System.out.print("Введите числа n, i, j, k соответственно через пробел ");
		 int n = in.nextInt();
		 int i = in.nextInt();
		 int j = in.nextInt();
		 int k = in.nextInt();
		 PrintWriter printWriter = new PrintWriter(new File("OUT.txt")); // Вывод в файл
		 printWriter.print("КОДЫ SSS:");
		 printWriter.println("Код: "+SSSCode(n, i, j, k));
		 printWriter.println("Декодирование: "+SSSDecode(SSSCode(n, i, j, k),i, j, k));
		 
		 System.out.println("Код: "+SSSCode(n, i, j, k));
		 System.out.println("Декодирование: "+SSSDecode(SSSCode(n, i, j, k),i, j, k));
		 TestsSSSCode(); //
		 in.close();
		 printWriter.close();
	 }
	 
	 static String SSSCode(int n,int i,int j,int k) {
		 if (j == 0) {
			 System.out.println("Ошибка! деление на ноль!");
			 return "";
		 }
		 int k1 = (k - i) / j + 1;
		 Range range = new Range(i,j,k);
		 int down[] = range.BuildRangeDown();
		 int up[] = range.BuildRangeUp();
		 if (n > up[k1-1]) {
			 System.out.println("Ошибка! n out of bound!");
			 return "";
		 }
//		 for(int myInt : down) System.out.println("В "+myInt);
//		 for(int myInt : up) System.out.println("Н "+myInt);
		 int layn = 0;
		 for (int count = 0; count < k1; count++) {
			 if (n >= down[count] && n <= up[count]) {
				 layn = count+1;
//				 System.out.println(layn);
				 break;
			 }
		 }
		 String unarycode = UnaryCode(layn-1);
//		 System.out.println("Унарный код: " + unarycode);
		 String mantissa = IntToBin(n-down[layn-1]);
//		 System.out.println("Мантисса в 10-ной " + (n-down[layn-1]));
//		 System.out.println("Мантисса в 2-ной " + mantissa);
		 while (mantissa.length() != (i + (layn - 1)*j)) {
			 if (mantissa.length() < (i + (layn - 1)*j))
				 mantissa = "0"+ mantissa;
//			 if (mantissa.length() > (i + (layn - 1)*j))
//				 mantissa = mantissa.substring(1);
		 }
		 String result = unarycode + mantissa;
		 return result;
	 }
	 
	 static int SSSDecode(String code,int i, int j, int k) {
		 int layn = UnaryDecode(code)+1;
		 code = code.substring(layn);
		 Range range = new Range(i,j,k);
		 int result = BinToInt(code) + range.BuildRangeDown()[layn-1];
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
	 static void TestsSSSCode() throws FileNotFoundException {
		    ArrayList<Boolean> tests = new ArrayList<Boolean>();
		    tests.add(0, null);
		    int koltests = 200;
		    int i = 3, j = 2, k = 11;
		    //int i = 1, j = 1, k = 6;
		    //int i = 5, j = 5, k = 1000; //Моделирование гамма-кода Элайеса по основанию 2^5
		    //int i = 5, j = 1, k = 1000; // Моделирования простого бинарного кода для натуральных чисел
		    PrintWriter printtests = new PrintWriter("OUT.txt");
	 			for (int n = 1; n <= koltests; n++) {
	 				//System.out.println(i);
	 				tests.add(n,(n == (SSSDecode(SSSCode(n,i,j,k),i,j,k))));
	 				printtests.println("n ="+ n + "; Код: " + SSSCode(n,i,j,k) + " Декод: " + (SSSDecode(SSSCode(n,i,j,k),i,j,k)));
	 				if (tests.get(n) == false)
	 				{
	 					printtests.println("Ошибка при значении n ="+n);
	 					System.out.println("Ошибка при значении n ="+n);
	 					break;
	 				}
	 				if (n == koltests) {
	 					printtests.println("Все тесты вернули значение "+ true);
	 					System.out.println("Все тесты вернули значение "+ true + ". См. Файл OUT.txt");
	 				}
	 			}
	 		printtests.close();
	 }
	 
}

class Range {
	int i;
	int j;
	int k;
	int k1;
	int down[] ;
	int up[] ;
	public Range(int i, int j, int k) {
		this.i = i;
		this.j = j;
		this.k = k;
		this.k1 = (k - i) / j + 1;
		this.down = new int[k1];
		this.up = new int[k1];
	}
	public int [] BuildRangeDown() {
		down[0] = 1;
		for (int count = 1; count < k1; count++) {
			down[count] = down[count-1] + (int) Math.pow(2, i + j*(count-1));
		}
		return down;
	}
	
	public int [] BuildRangeUp() {
		up[0] = (int) Math.pow(2, i);
		for (int count = 1; count < k1; count++) {
			up[count] = up[count-1] + (int) Math.pow(2, i + j*(count));
		}
		return up;
	}
}