import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class branch implements node{

	/*
	 * Tree Structure
	 */
	private branch parent;
	private node left = null;
	private node right = null;
	
	/*
	 * HashCode information
	 */
	private byte[] hashCode;
	
	/*
	 * Constructor
	 */
	public branch(){
		
	}
	
	@Override
	public byte[] calculateHashCode() throws NoSuchAlgorithmException {
		//Making the new hash code
		byte[] tempHash = null;
		
		//Both branches are present
		if(left != null && right != null){
			tempHash = new byte[left.getHashCode().length + right.getHashCode().length];
			System.arraycopy(left.getHashCode(), 0, tempHash, 0, left.getHashCode().length);
			System.arraycopy(right.getHashCode(), 0, tempHash, left.getHashCode().length, right.getHashCode().length);
		}
		//Left branch is present
		else if(left != null && right == null){
			tempHash = new byte[left.getHashCode().length + 1];
			System.arraycopy(left.getHashCode(), 0, tempHash, 0, left.getHashCode().length);
			tempHash[tempHash.length - 1] = -1;
		}
		return hashCode = MessageDigest.getInstance("SHA-256").digest(tempHash);
	}
	
	/*
	 * Setters
	 */
	public void setLeft(node left){
		this.left = left;
	}
	
	public void setRight(node right){
		this.right = right;
	}
	
	/*
	 * Getters
	 */
	
	@Override
	public byte[] getHashCode() {
		return hashCode;
	}
	public void setParent(branch parent){
		if(!parent.isFull()){
			this.parent = parent;
			if(!this.parent.hasLeft()){
				this.parent.setLeft(this);
			}
			else if(!this.parent.hasRight()){
				this.parent.setRight(this);
			}
		}
	}
	
	public boolean hasLeft(){
		return left != null;
	}
	
	public boolean hasRight(){
		return right != null;
	}
	
	public boolean isFull(){
		return right != null && left != null;
	}

	@Override
	public boolean isLeaf() {
		return false;
	}
}
