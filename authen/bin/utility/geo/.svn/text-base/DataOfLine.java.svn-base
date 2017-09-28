package utility.geo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import utility.security.DataIO;
import utility.security.RSA;
import utility.security.SecurityUtility;

public class DataOfLine {

	public Long lid;
	public Line line;
	public static Long M = (long) 100000000;
	public String signature;
	
	public DataOfLine() {
		line = new Line();
		// TODO Auto-generated constructor stub
	}

	public DataOfLine(long _lid, Line _line){
		lid = _lid;
		line = _line;
		signature = signWithRSA(new RSA());
	}
	
	public DataOfLine(byte[] buffer){
		this();
		this.loadFromBytes(buffer);
	}
	
	public byte[] writeToBytes(){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		try {
			dos.writeLong(lid);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		line.writeToFile(dos);
		DataIO.writeString(dos, signature);
		try {
			dos.flush();
			dos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return baos.toByteArray();
	}
	
	public void loadFromBytes(byte[] buffer){
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(buffer));
		try {
			lid = dis.readLong();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		line.readFromFile(dis);
		signature = DataIO.readString(dis);
		try {
			dis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadFromFile(DataInputStream dis){
		try {
			lid = dis.readLong();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		line.readFromFile(dis);
	}

	public static long calcLineId(int id1, int id2){
		if(id1 > id2){
			return ((long)id2) * M + id1;
		}
		return ((long)id1) * M + id2;
	}
	
	public static long calcLineId(long id1, long id2){
		if(id1 > id2){
			return ((long)id2) * M + id1;
		}
		return id1 * M + id2;
	}
	
	public String getDigest(){
		return SecurityUtility.computeGeneralHashValue(new String[]{
			new Integer((int)(lid / M)).toString(),
			new Integer((int)(lid % M)).toString(),
			line.getDigest()
		});
	}
	
	public String signWithRSA(RSA rsa){
		return rsa.encrypt(getDigest());
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
