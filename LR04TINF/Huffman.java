import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Huffman {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(System.in);
		System.out.print("Введите строку символов: ");
		String s = in.next();
		HuffmanDecode(HuffmanCode(s), charNodes);
		//System.out.println("Введите длину генерируемой строки: ");
		//System.out.println(HuffmanTests(in.nextInt()));
		in.close();
	}

	static Map<Character, Node> charNodes;
	public static final double EPS = 0.000001;
	static String HuffmanCode(String str) {
		Map<Character, Double> chint = new HashMap<Character, Double>();
		System.out.println("strlen="+str.length());
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (chint.containsKey(c))
				chint.put(c, (chint.get(c) + 1.d / (double)str.length()));
			else
				chint.put(c, (1.d / (double) str.length()));
		}

		 //for (Entry<Character, Double> entry : chint.entrySet())
		 //System.out.println(entry.getKey() + ": " + entry.getValue());

		charNodes = new HashMap<Character, Node>();
		PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>();
		for (Map.Entry<Character, Double> entry : chint.entrySet()) {
			// Добавление в очередь всех листовых узлов
			LeafNode leafnode = new LeafNode(entry.getKey(), entry.getValue());
			charNodes.put(entry.getKey(), leafnode);
			priorityQueue.add(leafnode);
		}
		priorityQueue = priorityQueue.stream().sorted((n1,n2) -> n1.sum.compareTo(n2.sum)).collect(Collectors.toCollection(PriorityQueue::new));
		System.out.println(priorityQueue.toString());
		int sum = 0; // Сумма частот во всех узлах
		while (priorityQueue.size() > 1) {
			Node first = priorityQueue.poll();
			//System.out.println(first.sum);
			Node second = priorityQueue.poll();
			InternalNode node = new InternalNode(first, second);
			sum += node.sum;
			priorityQueue.add(node);
		}
		if (chint.size() == 1) {
			sum = str.length();
		}
		System.out.println("Мощность: " + chint.size() + " Сумма частот: " + sum);
		Node root = priorityQueue.poll();
		if (chint.size() == 1) {
			root.code = "0";
		} else
			root.buildCode("");
		String result = "";
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			result += charNodes.get(c).code;
		}
		System.out.println("Результат кодирования: " + result);
		return result;
	}

	static String HuffmanDecode(String str, Map<Character, Node> mapin) {
		String result = "";
		while (!str.equals("")) {
			// System.out.println(str);
			for (Map.Entry<Character, Node> entry : mapin.entrySet()) {
				int j = mapin.get(entry.getKey()).code.length();
				try {
					if ((mapin.get(entry.getKey()).code).equals(str.substring(0, j))) {
						result += entry.getKey();
						str = str.substring(j);
						break;
					}
				} catch (IndexOutOfBoundsException e) {
				} // str.substring(0, j) может выйти за границы
			}
		}
		System.out.println("Результат декодирования: " + result);
		return result;

	}

//tests	 
	static Boolean HuffmanTests(int n) throws FileNotFoundException {
		PrintWriter printtests = new PrintWriter("OUT.txt");
		String str = "";
		Random randomstr = new Random();
		boolean istrue = false;
		for (int j = 0; j < 20; j++) {
			for (int i = 0; i < n; i++) {
				str += (char) ('a' + randomstr.nextInt(26));
			}
			printtests.println("Генерируемая строка " + str);
			
			System.out.println("Генерируемая строка " + str);
			istrue = HuffmanDecode(HuffmanCode(str), charNodes).equals(str);
			printtests.println("Код: " + HuffmanCode(str));
			printtests.println("Декодирование: " + HuffmanDecode(HuffmanCode(str), charNodes));
			if (istrue == false) {
				System.out.print("Ошибка при строке: " + str);
				printtests.println("Ошибка при строке: " + str);
			}
			str = "";
		}
		System.out.print("Результаты тестов: ");
		printtests.println(" Результаты тестов: ");
		printtests.close();
		return istrue;
	}

}

class Node implements Comparable<Node> {
	final Double sum;
	String code;

	void buildCode(String code) {
		this.code = code;
	}

	public Node(Double sum) {
		this.sum = sum;
	}

	@Override
	public int compareTo(Node o) {
		return Double.compare(sum, o.sum);
	}
	@Override
	public String toString() {
		
		return ""+sum;
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
	char symbol;

	@Override
	void buildCode(String code) {
		super.buildCode(code);
		System.out.println(symbol + ": " + code);
	}

	public LeafNode(char symbol, Double freq) {
		super(freq);
		this.symbol = symbol;
	}
}