package LZ;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.io.IOException;
import java.util.Scanner;

public class LZSS {
	int vocabularySize;
	int bufferSize;
	StringBuffer input;

	public LZSS(int vocabularySize, int bufferSize, String input) {
		this.vocabularySize = vocabularySize;
		this.bufferSize = bufferSize;
		this.input = new StringBuffer(input);
	}

	public static void main(String[] args) throws FileNotFoundException {
		//Тест: 1)Иванов_Иван_Иванович; 2) красная_краска; 3) абракадабра;
		long startTime = System.currentTimeMillis();
		
//		try {
//		Runtime.getRuntime().exec("wscript D:\\Projects\\eclipseprojects\\LR08TINF\\HiddenOpenBatFile.vbs");
//		} catch(IOException e) { System.out.println(e);}
		
		Scanner in = new Scanner(System.in);
		System.out.println("Введите строку символов: ");
		String s = in.next();
		LZSS lzss = new LZSS(8, 5, s);
		printWriter = new PrintWriter(new File("OUT.txt"));
		printWriter.println("LZSS ТЕСТЫ:");
		printWriter.println(s);
		System.out.println("Код: ");
     	printWriter.println("Код: ");
		ArrayList<LZSSCodeStructure> tr = lzss.LZSSCode();
		for (LZSSCodeStructure entry : tr) {
			entry.print();
			entry.printFile();
		}
		System.out.println("Декодирование: " + lzss.LZSSDecode(tr));
		printWriter.println("Декодирование: "+lzss.LZSSDecode(tr));
		lzss.TestsLZSSCode();
		in.close();
		printWriter.close();
		long finishTime = System.currentTimeMillis();
		System.out.println(finishTime - startTime + "ms");
	}
	
	public static PrintWriter printWriter; // Вывод в файл
	
	public ArrayList<LZSSCodeStructure> LZSSCode() {
		// String savedinput = input.toString();
		ArrayList<LZSSCodeStructure> code = new ArrayList<LZSSCodeStructure>();
		StringBuffer vocabulary = new StringBuffer(initialVoc());
		StringBuffer buffer = new StringBuffer("");
		while (input.length() >= 0) {
			buffer = FillBuffer(buffer);
			//System.out.println(buffer+ ":Буффер");
			//System.out.println(vocabulary+ ":vocabulary");
			char seq = buffer.charAt(0);
			//System.out.println(seq+ ":первый символ");
			if (seq == 126) // ~ - конец строки
				break;
			if (vocabulary.toString().contains(Character.toString(seq))) {
				StringBuffer charseq = new StringBuffer(Character.toString(seq));
				int i = 1;
				while (vocabulary.toString().contains(charseq.toString()) && (i < bufferSize)) {
					charseq.append(buffer.charAt(i));
					i++;
				}
				charseq.deleteCharAt(charseq.length() - 1);

				int position = vocabulary.length() - vocabulary.lastIndexOf(charseq.toString());
				int quanSymbs = charseq.length();
//				System.out.println("<" + position + "," + quanSymbs + "," + symb + ">");
				code.add(new LZSSCodeStructure(position, quanSymbs));

				vocabulary.append(charseq);
				vocabulary = TrimtoSizeVoc(vocabulary);
				buffer.delete(0, charseq.length());
			} else {
//				System.out.println("<" + 0 + "," + 0 + "," + seq + ">");
				code.add(new LZSSCodeStructure(seq));

				vocabulary.append(seq);
				vocabulary = TrimtoSizeVoc(vocabulary);
				buffer.deleteCharAt(0);
				// input.deleteCharAt(0);
			}
		}
		return code;
	}
	
	public String LZSSDecode(ArrayList<LZSSCodeStructure> code) {
		StringBuffer vocabulary = new StringBuffer(initialVoc());
		StringBuffer result = new StringBuffer("");
		for (int i = 0; i < code.size(); i++) {
			if (code.get(i).prefixBit == 0) {
				vocabulary.append(code.get(i).symb);
				result.append(code.get(i).symb);
				vocabulary = TrimtoSizeVoc(vocabulary);
			} else if (code.get(i).prefixBit == 1){
				StringBuffer charseq = new StringBuffer(vocabulary.substring((vocabulary.length()) - (code.get(i).pos),
						(vocabulary.length() - (code.get(i).pos)) + code.get(i).quan));
				vocabulary.append(charseq);
				result.append(charseq);
				vocabulary = TrimtoSizeVoc(vocabulary);
			}
//			System.out.println("vocabulary" + vocabulary);
		}
		return result.toString();
	}
	
	// tests
	public Boolean TestsLZSSCode() throws FileNotFoundException {

		Random randomstr = new Random();
		boolean istrue = false;
		for (int j = 0; j < 20; j++) {
			StringBuffer str = new StringBuffer("");
			for (int i = 0; i < 14; i++) {
				str.append((char) ('a' + randomstr.nextInt(13)));
			}
			printWriter.println("Генерируемая строка " + str);
			printWriter.println("Код: ");
			LZSS LZss = new LZSS(8, 5, str.toString());
			ArrayList<LZSSCodeStructure> tr = LZss.LZSSCode();
			for (LZSSCodeStructure entry : tr) {
				entry.printFile();
			}
			istrue = (LZSSDecode(tr).substring(0,
					(LZSSDecode(tr).charAt(LZSSDecode(tr).length() - 1) == '\0' ? LZSSDecode(tr).length() - 1
							: LZSSDecode(tr).length()))).equals(str.toString());
			printWriter.println("Декод: " + LZSSDecode(tr));
			printWriter.println();
			if (istrue == false) {
				System.out.println("Ошибка при тестировании! Строка: " + str);
				System.out.println("Результаты в файле OUT.txt");
				printWriter.println("Ошибка при строке: " + str);
				printWriter.close();
				return false;
			}
		}
		System.out.println("Количество тестов 20. Результаты тестов: " + istrue);
		System.out.println("Результаты в файле OUT.txt");
		printWriter.println("Количество тестов 20. Результаты тестов: " + istrue);
		printWriter.close();
		return istrue;
	}
	
	private StringBuffer TrimtoSizeVoc(StringBuffer stroke) {
		while (stroke.length() > vocabularySize) {
			stroke = new StringBuffer(stroke.substring(1));
		}
		return stroke;
	}

	private String initialVoc() {
		StringBuffer voc = new StringBuffer("");
		while (voc.length() < vocabularySize) {
			voc.append('~');
		}
		return voc.toString();
	}

	private StringBuffer FillBuffer(StringBuffer buffer) {
		while (buffer.length() < bufferSize) {
			if (input.length() == 0) {
				buffer.append('~'); // ascii code = 126
			} else {
				buffer.append(input.charAt(0));
				input.deleteCharAt(0);
			}
		}
		return buffer;
	}
}

class LZSSCodeStructure {
	byte prefixBit;
	char symb;
	int pos;
	int quan;
	
	public LZSSCodeStructure(int pos, int quan) {
		this.prefixBit = 1;
		this.pos = pos;
		this.quan = quan;
	}
	
	public LZSSCodeStructure(char symb) {
		this.prefixBit = 0;
		this.symb = symb;
	}

	public void print() {
		if (prefixBit == 0)
			System.out.println(prefixBit + " '" + symb + "'");
		else if (prefixBit == 1)
			System.out.println(prefixBit + " (" + pos + ", " + quan + ")");
	}

	public void printFile() throws FileNotFoundException {
		// PrintWriter printWriter = new PrintWriter(new
		// FileOutputStream("OUT.txt",true));
		// printWriter.println();
		LZSS.printWriter.println("<" + pos + "," + quan + "," + symb + ">");
		// LZSS.printWriter.close();
	}

}