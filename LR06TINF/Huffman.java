import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Huffman {
	 public static void main(String[] args) {
		 Scanner in = new Scanner(System.in);
		 System.out.print("Введите строку символов: ");
		 String s = in.next();
		 HuffmanDecode(HuffmanCode(s),charNodes);
		 System.out.println("Введите длину генерируемой строки: ");
		 System.out.println(HuffmanTests(in.nextInt()));
		 in.close();
	 }
	 static Map<Integer, Node> charNodes;
	 static String HuffmanCode(String str) {
		 Map<Integer, Integer> chint = new HashMap<Integer, Integer>();
		 for (int i = 0; i < str.length(); i++) {
			 int c = Integer.parseInt(Character.toString(str.charAt(i)));;
			 if (chint.containsKey(c))
			 chint.put(c, chint.get(c) + 1/str.length());
			 else chint.put(c,  1/str.length());
		 }
		 
		 
//		 for (Map.Entry<Character, Integer> entry : chint.entrySet())
//		 System.out.println(entry.getKey() + ": " + entry.getValue());
		 
		 charNodes = new HashMap<Integer, Node>();
		 ArrayDeque<Node> states = new ArrayDeque<Node>();
		 for (Map.Entry<Integer, Integer> entry : chint.entrySet()) {
			 //Добавление в очередь всех листовых узлов
			 LeafNode leafnode = new LeafNode(entry.getKey(), entry.getValue());
			 charNodes.put(entry.getKey(), leafnode);
			 states.add(leafnode);
		 }
		 int sum = 0; // Сумма частот во всех узлах
		 while (states.size() > 1) {
			 Node first = states.poll();
			 Node second = states.poll();
			 InternalNode node = new InternalNode(first,second);
			 sum += node.sum;
			 states.add(node);
		 }
		 if (chint.size() == 1) {
			 sum = str.length();
		 }
		 System.out.println("Мощность: " + chint.size() + " Сумма частот: " + sum);
		 Node root = states.poll();
		 if (chint.size() == 1) {
			 root.code = "0";
		 } else root.buildCode("");
		 String result = "";
		 for (int i = 0; i < str.length(); i++) {
			 int c = Integer.parseInt(Character.toString(str.charAt(i)));
			 result += charNodes.get(c).code;
		 }
		 System.out.println("Результат кодирования: " + result);
		 return result;
	 }
	 static String HuffmanDecode (String str, Map<Integer, Node> mapin) {
		 String result = "";
		 while (!str.equals("")) {
			 //System.out.println(str);
			 for (Map.Entry<Integer, Node> entry : mapin.entrySet()) {
				 int j = mapin.get(entry.getKey()).code.length();
				 try {
				 if ((mapin.get(entry.getKey()).code).equals(str.substring(0, j))) {
					 result += entry.getKey();
					 str = str.substring(j);
					 break;
				 }
				 } catch (IndexOutOfBoundsException e) {}//str.substring(0, j) может выйти за границы
			 }
		 }
		System.out.println("Результат декодирования: " + result);
		return result;
		 
	 }
//tests	 
	 static Boolean HuffmanTests(int n) {
		String str = "";
		Random randomstr = new Random();
		boolean istrue = false;
		for (int j = 0; j < 20; j++) {
		for (int i = 0; i < n; i++) {
			str += (char)('a' + randomstr.nextInt(26));
		}
		System.out.println("Генерируемая строка " + str);
		istrue = HuffmanDecode(HuffmanCode(str),charNodes).equals(str);
		if (istrue == false) {
			System.out.print("Ошибка при строке: " + str);
		}
		str = "";
		}
		System.out.print("Количество тестов 20. Результаты тестов: ");
		 return istrue; 
	 }
	 
	 
}

class Node {
	final Integer sum;
	String code;
	
	void buildCode(String code) {
		this.code = code;
	}
	
	public Node(Integer sum) {
		this.sum = sum;
	}
	
}

class InternalNode extends Node {
	Node left;
	Node right;
	
	@Override
	void buildCode(String code) {
		super.buildCode(code);
		left.buildCode(code + "0");
		right.buildCode(code + "1");
	}
	
	public InternalNode(Node left, Node right) {
		super(left.sum + right.sum);
		this.left = left;
		this.right = right;
	}
}

class LeafNode extends Node {
	Integer symbol;
	
	@Override
	void buildCode(String code) {
		super.buildCode(code);
		System.out.println(symbol + ": " + code);
	}
	
	public LeafNode(Integer symbol, Integer freq) {
		super(freq);
		this.symbol = symbol;
	}
}