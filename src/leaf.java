import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class leaf implements node{

	/*
	 * Tree Structure
	 */
	private item item;
	private branch parent = null;
	
	/*
	 * HashCode Information
	 */
	private byte[] hashCode;
	
	/*
	 * Constructor
	 */
	public leaf(branch parent, item item) throws NoSuchAlgorithmException{
		if(!parent.isFull()){
			this.parent = parent;
			if(!this.parent.hasLeft()){
				this.parent.setLeft(this);
			}
			else if(!this.parent.hasRight()){
				this.parent.setRight(this);
			}
		}
		this.item = item;
		calculateHashCode();
	}
	
	@Override
	public byte[] calculateHashCode() throws NoSuchAlgorithmException {
		return hashCode = MessageDigest.getInstance("SHA-256").digest(item.getData().getBytes(StandardCharsets.UTF_8));
	}

	@Override
	public boolean isLeaf() {
		return true;
	}

	/*
	 * Getters
	 */
	
	@Override
	public byte[] getHashCode() {
		return hashCode;
	}

}
