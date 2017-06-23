import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class genesis extends block{

	/*
	 * Data Structure
	 */
	@SuppressWarnings("unused")
	private block next = null;
	
	/*
	 * HashCode information
	 */
	private long timeStamp = System.currentTimeMillis();
	private int nonce = 0;
	final String string = "Genesis Block";
	private byte[] hashCode = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	
	public genesis() throws NoSuchAlgorithmException {
		byte[] byteNonce;
		byte[] byteTimeStamp;
		byte[] tempHash;
		byte[] stringHash = MessageDigest.getInstance("SHA-256").digest(string.getBytes(StandardCharsets.UTF_8));
		
		for(nonce = 0; hashCode == null || hashCode[0] != 0 || hashCode[1] != 0 || hashCode[2] != 0; nonce++){
			
			/*
			//Buffers to turn values into byte arrays
			ByteBuffer bb = ByteBuffer.allocate(Integer.BYTES);
			
			//Calculating nonce to byte to hash
			bb.putInt(nonce);
			byteNonce = bb.array();
			
			//Calculating time stamp to byte to hash
			bb = ByteBuffer.allocate(Long.BYTES);
			bb.putLong(timeStamp);
			byteTimeStamp = bb.array();
			
			tempHash = new byte[stringHash.length + byteTimeStamp.length + byteNonce.length];
			System.arraycopy(stringHash, 0, tempHash, 0, stringHash.length);
			System.arraycopy(byteTimeStamp, 0, tempHash, stringHash.length, byteTimeStamp.length);
			System.arraycopy(byteNonce, 0, tempHash, stringHash.length + byteTimeStamp.length, byteNonce.length);
			
			hashCode = MessageDigest.getInstance("SHA-256").digest(tempHash);	
			*/
			
		}
	}
	
	@Override
	public byte[] getHashCode(){
		return hashCode;
	}

}
