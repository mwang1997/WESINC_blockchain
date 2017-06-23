import java.security.NoSuchAlgorithmException;

public interface node {

	/*
	 * Calculate Hash Codes up a Merkle tree
	 */ 
	byte[] calculateHashCode() throws NoSuchAlgorithmException;
	
	/*
	 * Checks if the type is a leaf or not
	 */
	boolean isLeaf();
	
	/*
	 * Returns the hashCode
	 */
	byte[] getHashCode();
	
}
