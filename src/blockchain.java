import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

public class blockchain {
	//The very first block
	public final genesis genesis = new genesis();
	public block newestBlock;
	public int size = 1;
	
	public blockchain() throws NoSuchAlgorithmException {
		newestBlock = genesis;
	}
	
	public void add(LinkedList<String> dataList) throws NoSuchAlgorithmException{
		newestBlock.setNext(new block(newestBlock, dataList));
		newestBlock = newestBlock.getNext();
	}
	
	/*
	 * Getters
	 */
	
	//Index starts at 0, the genesis block
	public block getBlock(int index){
		block block = genesis;
		for(int i = 0; i < index && block.hasNext(); i++){
			block = block.getNext();
		}
		return block;
	}
}
