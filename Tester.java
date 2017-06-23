import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.Scanner;

public class Tester {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		// TODO Auto-generated method stub

		
		//The blockchain
		blockchain blockchain = new blockchain();
		
		System.out.println("Genesis is done");
		
		//LinkedLists for different blocks
		LinkedList<String> testList1 = new LinkedList<String>();
		LinkedList<String> testList2 = new LinkedList<String>();
		LinkedList<String> testList3 = new LinkedList<String>();
		
		//Reader
		Scanner sc = new Scanner(System.in);
		
		//Reading in information for LinkedLists
		String s = "";
		while(!s.equals("exit")){
			s = sc.next();
			testList1.add(s);
		}
		
		s = "";
		while(!s.equals("exit")){
			s = sc.next();
			testList2.add(s);
		}
		
		s = "";
		while(!s.equals("exit")){
			s = sc.next();
			testList3.add(s);
		}
		
		sc.close();
		
		System.out.print("finished");
		
		//Adding new blocks
		blockchain.add(testList1);
		blockchain.add(testList2);
		blockchain.add(testList3);
		
		byte[] hashCode1 = blockchain.getBlock(1).getHashCode();
		byte[] hashCode2 = blockchain.getBlock(2).getHashCode();
		byte[] hashCode3 = blockchain.getBlock(3).getHashCode();

		s = "" + '\n';
		for(byte bit: hashCode1){
			s = s + bit;
		}
		System.out.println(s);
		
		s = "";
		for(byte bit: hashCode2){
			s = s + bit;
		}
		System.out.println(s);
		
		s = "";
		for(byte bit: hashCode3){
			s = s + bit;
		}
		System.out.println(s);
	}

}
