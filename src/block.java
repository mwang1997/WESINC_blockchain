import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

public class block implements node{
	
	/*
	 * Tree Structure
	 */
	public LinkedList<String> dataList;
	private branch head = null;
	private block previous;
	private block next = null;
	
	/*
	 * HashCode information
	 */
	public byte[] hashCode;
	public long timeStamp = System.currentTimeMillis();
	private int nonce;
	
	/*
	 * Constructor
	 */
	public block(block previous, LinkedList<String> dataList) throws NoSuchAlgorithmException{
		this.previous = previous;
		if(this.next != null){
			this.previous.setNext(this);
		}
		this.dataList = dataList;
		this.head = buildTree();
		calculateHashCode();
	}
	
	public block(){
		//Made for the genesis block
	}
	
	@Override
	public byte[] calculateHashCode() throws NoSuchAlgorithmException {
		//Making the new hash code 
		byte[] tempHash;
		byte[] byteNonce;
		byte[] byteTimeStamp;
		
		//Tries new nonces
		for(nonce = 0; hashCode == null || hashCode[0] != 0 || hashCode[1] != 0 || hashCode[2] != 0 || hashCode[3] == 0; nonce++){
			//Buffers to turn values into byte arrays
			ByteBuffer bb = ByteBuffer.allocate(Integer.BYTES);
			
			//Calculating nonce to byte to hash
			bb.putInt(nonce);
			byteNonce = bb.array();
			
			//Calculating time stamp to byte to hash
			bb = ByteBuffer.allocate(Long.BYTES);
			bb.putLong(timeStamp);
			byteTimeStamp = bb.array();
			
			//New hash
			tempHash = new byte[previous.getHashCode().length + head.getHashCode().length + byteTimeStamp.length + byteNonce.length];
			
			//Assigning byte array to hash
			System.arraycopy(previous.getHashCode(), 0, tempHash, 0, previous.getHashCode().length);
			System.arraycopy(head.getHashCode(), 0, tempHash, previous.getHashCode().length, head.getHashCode().length);
			System.arraycopy(byteTimeStamp, 0, tempHash, previous.getHashCode().length + head.getHashCode().length, byteTimeStamp.length);
			System.arraycopy(byteNonce, 0, tempHash, previous.getHashCode().length + head.getHashCode().length + byteTimeStamp.length, byteNonce.length);
			hashCode = MessageDigest.getInstance("SHA-256").digest(tempHash);
		}
		return hashCode;
	}
	

	@Override
	public boolean isLeaf() {
		return false;
	}
	
	private branch buildTree() throws NoSuchAlgorithmException{
		//Temporary branch
		branch tempBranch = new branch();
		
		//Temporary LinkedLists used to store branches
		LinkedList<branch> branchList1 = new LinkedList<branch>();
		LinkedList<branch> branchList2 = new LinkedList<branch>();
		
		//Assigning data to leaves, and then leaves to branches
		for(String data: dataList){
			if(tempBranch.isFull()){
				tempBranch = new branch();
			}
			if(!branchList1.contains(tempBranch)){
				branchList1.add(tempBranch);
			}
			new leaf(tempBranch, new item(data));
			tempBranch.calculateHashCode();
		}
		
		//Iterates building a tree until there is only the head
		while(true){
			//Exit conditions
			if(branchList1.size() == 1){
				return branchList1.getFirst();
			}
			
			//Resets variables
			tempBranch = new branch();
			branchList2 = new LinkedList<branch>();
			
			//Loops through branchList1 assigning it's parents to branchList2
			for(branch branch: branchList1){
				if(tempBranch.isFull()){
					tempBranch = new branch();
				}
				if(!branchList2.contains(tempBranch)){
					branchList2.add(tempBranch);
				}
				branch.setParent(tempBranch);
				tempBranch.calculateHashCode();
			}
			
			//Exit conditions
			if(branchList2.size() == 1){
				return branchList2.getFirst();
			}
			
			//Resets variables
			tempBranch = new branch();
			branchList1 = new LinkedList<branch>();
			
			//Loops through branchList2 assigning it's parents to branchList1
			for(branch branch: branchList2){
				if(tempBranch.isFull()){
					tempBranch = new branch();
				}
				if(!branchList1.contains(tempBranch)){
					branchList1.add(tempBranch);
				}
				branch.setParent(tempBranch);
				tempBranch.calculateHashCode();
			}
		}
	}
	
	public boolean hasNext(){
		return next != null;
	}
	
	/*
	 * Getters
	 */
	@Override
	public byte[] getHashCode() {
		return hashCode;
	}
	
	public block getNext(){
		return next;
	}
	
	/*
	 * Setters
	 */
	public void setNext(block next){
		this.next = next;
	}
}
