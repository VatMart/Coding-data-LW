import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
public class Golomb {
	 public static void main(String[] args) throws FileNotFoundException {
		 Scanner in = new Scanner(System.in);
		 System.out.print("Введите число n и m через пробел ");
//		 String str = in.next();
//		 System.out.println("перевод: "+BinToInt(str));
		 int n = in.nextInt();
		 int m = in.nextInt();
		 PrintWriter printWriter = new PrintWriter(new File("OUT.txt")); // Вывод в файл
		 printWriter.print("КОД ГОЛОМБА:");
		 printWriter.println("Код: "+GolombCode(n, m));
		 printWriter.println("Декодирование: "+GolombDecode(GolombCode(n, m), m));
		 
		 System.out.println("Код: "+GolombCode(n, m));
		 System.out.println("Декодирование: "+GolombDecode(GolombCode(n, m), m));
		 TestsGolombCode(); //
		 in.close();
		 printWriter.close();
	 }
	 
	 static String GolombCode(int n, int m) {
		 String result;
		 if (isPowOfTwo(m))
		 { 
			 result = UnaryCode(n / m);
//			 System.out.println("n / m = " + n / m);
//			 System.out.println("n % m = " + n % m);
			 String betta = IntToBin(n % m);
			 if (betta.length() == Math.log(m)/Math.log(2)) {
				 result += betta;
				 return result;
			 }
			 else while (betta.length() < Math.log(m)/Math.log(2)) {
				 	betta = "0" + betta;
			 	  }
			 result += betta;
			 return result;
		 }
		 else {
			 int r = n % m;
			 int b = (((int)((Math.log(m) / Math.log(2))) - ((Math.log(m) / Math.log(2))) == 0) ? (int)((Math.log(m) / Math.log(2))) : (int)((Math.log(m) / Math.log(2)))+1 );
			 result = UnaryCode(n / m);
			 if (r < Math.pow(2, b) - m) {
				 String betta = IntToBin(r);
				 if (betta.length() == b-1) {
					 return result + betta;
				 }
				 else if (betta.length() < b-1) {
					 while (betta.length() < b-1) {
						 	betta = "0" + betta;
					 	  }
					 return result + betta;
				 }
				 else if (betta.length() > b-1) {
					 int temp = betta.length() - b-1;
					 betta = betta.substring(temp+1);
					 return result + betta;
				 }
			 }
			 else {
				 String betta = IntToBin(r + (int) Math.pow(2, b) - m);
				 if (betta.length() == b) {
					 return result + betta;
				 }
				 else if (betta.length() < b) {
					 while (betta.length() < b) {
						 	betta = "0" + betta;
					 	  }
					 return result + betta;
				 }
				 else if (betta.length() > b) {
					 int temp = betta.length() - b;
					 betta = betta.substring(temp+1);
					 return result + betta;
				 }
				 
			 }
			 return "Ошибка";
			 //КОДИРОВАНИЕ С ПОМОЩЬЮ ХАФФМАНА
			 
//			 StringBuffer hafStroke = new StringBuffer("");
//			 for (int i = m-1; i >= 0; i--) {
//				 hafStroke.append(i);
//			 }
//			 Map<Integer, Node> charNodes = new HashMap<Integer, Node>();
//			 Map<Integer, Node> tempNodes = new HashMap<Integer, Node>();
//			 Map<Integer, Integer> chint = new LinkedHashMap<Integer, Integer>();
//			
//			 for (int i = 0; i < hafStroke.length(); i++) {
//				 int c = Integer.parseInt(Character.toString(hafStroke.charAt(i)));
//				 if (chint.containsKey(c))
//				 chint.put(c, chint.get(c) + hafStroke.length());
//				 else chint.put(c,  hafStroke.length());
//			 }
//			 //chint = sortByValue(chint);
//
//			 ArrayDeque<Node> states = new ArrayDeque<Node>();
//			 for (Map.Entry<Integer, Integer> entry : chint.entrySet()) {
//				 //Добавление в очередь всех листовых узлов
//				 LeafNode leafnode = new LeafNode(entry.getKey(), entry.getValue());
//				 charNodes.put(entry.getKey(), leafnode);
//				 tempNodes.put(entry.getKey(), leafnode);
//				 states.add(leafnode);
//			 }
//			 //System.out.println("stroke " + charNodes);
//			 while (states.size() > 1) {
//				 Node first = states.poll();
//				 Node second = states.poll();
//				 InternalNode node = new InternalNode(first,second);
//				 states.add(node);
//			 }
//			 Node root = states.poll();
//			 if (chint.size() == 1) {
//				 root.code = "0";
//			 } else root.buildCode("");
//			 int c = (n % m);
//			 System.out.println("n % m = " + n % m);
//			 result += charNodes.get(c).code;
//			 return result;
		 }
		 
	 }
	 
	 static int GolombDecode(String code, int m) {
		 String strunary = code.substring(0,code.indexOf('0')+1);
		 int gamma = UnaryDecode(strunary);
		 code = code.substring(gamma+1);
		 int delta;
		 if (isPowOfTwo(m)) {
			 delta = BinToInt(code.substring(0, (int) (Math.log(m)/Math.log(2))));
			 return gamma*m + delta;
		 }
		 int b = (int) (Math.log(m)/Math.log(2))+1; // Высчитанное b
		 int b2 = code.length(); //фактическое b
		 int r = BinToInt(code); // r или r + 2^b - m
		 if (b == b2) { // если кодируется r + 2^b - m
			 r =  r - ((int) Math.pow(2, b) - m);
			 return (gamma*m + r ); // n = gamma*m + r
		 }
		 // r закодирована b - 1 битами
		 // Следовательно к числу r добавлен 0 слева либо у r обрезана 1 слева
		 
		 return (gamma*m + r); //gamma * m + (r2 - ((int) Math.pow(2, b) - m)/*-1*/ );
	 }
	 
	 static boolean isPowOfTwo(int n) {
		 while (n != 1 && n % 2 == 0) {
	            n /= 2;
	        }
		 return n == 1 ? true : false;
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
	 static void TestsGolombCode() throws FileNotFoundException {
		    ArrayList<Boolean> tests = new ArrayList<Boolean>();
//		    tests.add(0, null);
		 	PrintWriter printtests = new PrintWriter("OUT.txt");
	 		for (int m = 1; m <= 8; m++) { 
	 			for (int n = 0; n <= 17; n++) {
	 				//System.out.println(i);
	 				tests.add(n,(n == (GolombDecode(GolombCode(n,m),m))));
	 				printtests.println("n ="+n + "; m =" +m+ "; Код: " + GolombCode(n,m) + " Декод: " + GolombDecode(GolombCode(n,m),m));
	 				if (tests.get(n) == false)
	 				{
	 					printtests.println("Ошибка при значении n ="+n + " m ="+ m);
	 					System.out.println("Ошибка при значении n ="+n + " m ="+ m);
	 					break;
	 				}
	 				if (n == 17 && m == 8) {
	 					printtests.println("Все тесты вернули значение "+ true);
	 					System.out.println("Все тесты вернули значение "+ true + ". См. Файл OUT.txt");
	 				}
	 			}
	 		}
	 		printtests.close();
	 }
	 
}