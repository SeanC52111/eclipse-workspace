package utility.Compare;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import utility.security.DataIO;
import utility.security.Gfunction;
import utility.security.Point;
import utility.security.RSA;
import utility.security.SecurityUtility;

public class DataOfPoint{
	public int pid;
	public Point p;
	public Gfunction gf_x, gf_y;
	public Long[] delaunayIds = null;
	public String signature;
	
	
	public DataOfPoint(){
		p = new Point();
		delaunayIds = new Long[0];
		gf_x = new Gfunction();
		gf_y = new Gfunction();
	}
	
	public DataOfPoint(int id, Point _p, Long[] _ids){
		p = _p;
		delaunayIds = _ids;
		gf_x = new Gfunction(p.x, 16);
		gf_y = new Gfunction(p.y, 16); 
	}
	
	public DataOfPoint(Point _p, Gfunction _gf_x, Gfunction _gf_y){
		p = _p;
		gf_x = _gf_x;
		gf_y = _gf_y;
	}
	
	public DataOfPoint(byte[] buf){
		this();
		try {
			loadFromBytes(buf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public byte[] writeToBytes() throws IOException{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		dos.writeInt(pid);
		p.writeToFile(dos);
		dos.writeInt(delaunayIds.length);
		for(int i = 0 ; i < delaunayIds.length; i++){
			dos.writeLong(delaunayIds[i]);
		}		
		DataIO.writeString(dos, signature);
		gf_x.writeToFile(dos);
		gf_y.writeToFile(dos);
		return baos.toByteArray();
	}
	
	public void load(DataInputStream dis){
		try {
			pid = dis.readInt();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		p.readFromFile(dis);
		int len;
		try {
			len = dis.readInt();
			delaunayIds = new Long[len];
			for(int i = 0 ; i < len; i++){
				delaunayIds[i] = dis.readLong();
			}
			signature = DataIO.readString(dis);
			gf_x.readFromFile(dis);
			gf_y.readFromFile(dis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void loadFromBytes(byte[] buffer) throws IOException{
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(buffer));
		pid = dis.readInt();
		p.readFromFile(dis);
		int len = dis.readInt();
		delaunayIds = new Long[len];
		for(int i = 0 ; i < len; i++){
			delaunayIds[i] = dis.readLong();
		}
		signature = DataIO.readString(dis);
		gf_x.readFromFile(dis);
		gf_y.readFromFile(dis);
		dis.close();
	}
	
	public String getDigest(){
		String hashValueOfNei = SecurityUtility.computeGeneralHashValue(delaunayIds);
		return SecurityUtility.computeGeneralHashValue(new String[]{
				new Integer(pid).toString(),
				p.getDigest(),
				hashValueOfNei
		});
	}
	
	public String getSignature(){
		return signature;
	}
	
	public void signWithRSA(RSA rsa){
		signature = rsa.encrypt(getDigest());
	}
	
}