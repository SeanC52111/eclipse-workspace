//package utility.Compare;
//
//import java.io.IOException;
//import java.math.BigInteger;
//
//import jdbm.PrimaryTreeMap;
//import jdbm.RecordManager;
//import jdbm.RecordManagerFactory;
//import utility.StatisticForAuth;
//import utility.Statistics;
//import utility.security.IVo;
//import utility.security.Point;
//import utility.security.RSA;
//
//public class VO implements IVo{
//	public Statistics stat = new Statistics();
//	public int[] p_id, p_x, p_y;
//	public DistanceCompare[] distanceCompare;
//	public StatisticForAuth statForAuth;
//	
//	public VO(){}
//	public VO(int[] id, int[] x, int[] y, int q_x, int q_y) throws IOException{
//		p_id = new int[id.length];
//		p_x = new int[id.length];
//		p_y = new int[id.length];
//		DataOfPoint[] data = new DataOfPoint[id.length];
//		distanceCompare = new DistanceCompare[id.length - 1];
//		RecordManager recman = RecordManagerFactory.createRecordManager(buildBtreeOfPoints.filename);
//		PrimaryTreeMap<Long, byte[]> bt = recman.treeMap("treemap");
//		for(int i = 0; i < id.length; i++){
//			p_id[i] = id[i];
//			p_x[i] = x[i];
//			p_y[i] = y[i];
//			byte[] da = null;
//			try {
//				da = bt.find(new Long(id[i]));
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			data[i] = new DataOfPoint();
//			try {
//				data[i].loadFromBytes(da);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			if(i > 0){
//				distanceCompare[i - 1] = new DistanceCompare(data[i - 1].p, data[i].p, true);
//				distanceCompare[i - 1].GenerateVeryfyPart(new Point(q_x, q_y));
//			}
//		}
//		recman.close();
//	}
//	
//	public VO(Integer[] id, Integer[] x, Integer[] y, int q_x, int q_y) throws IOException{
//		p_id = new int[id.length];
//		p_x = new int[id.length];
//		p_y = new int[id.length];
//		DataOfPoint[] data = new DataOfPoint[id.length];
//		distanceCompare = new DistanceCompare[id.length - 1];
//		RecordManager recman = RecordManagerFactory.createRecordManager(buildBtreeOfPoints.filename);
//		PrimaryTreeMap<Long, byte[]> bt = recman.treeMap("treemap");
//		for(int i = 0; i < id.length; i++){
//			p_id[i] = id[i].intValue();
//			p_x[i] = x[i].intValue();
//			p_y[i] = y[i].intValue();
//			byte[] da = bt.find(new Long(id[i].longValue()));
//			data[i] = new DataOfPoint();
//			data[i].loadFromBytes(da);
//			if(i > 0){
//				distanceCompare[i - 1] = new DistanceCompare(data[i - 1].p, data[i].p, true);
//				distanceCompare[i - 1].GenerateVeryfyPart(new Point(q_x, q_y));
//			}
//		}
//		recman.close();
//	}
//	
//	public static void main(String args[]) throws IOException{
//		VO vo = new VO(new int[]{1, 2}, new int[]{6650, 14866}, new int[]{14009, 14581}, 10000, 10000);
//		if(vo.ClientVerify(10000, 10000))System.out.println("Pass !");
//		else System.err.println("Fail !");
//		//System.out.println(vo.g_a2);
//	}
//	
//	@Override
//	public boolean ClientVerify(int q_x, int q_y) {
//		// TODO Auto-generated method stub
//		for(int i = 1; i < p_id.length; i++){
//			if(distanceCompare[i - 1].ClientVerify(q_x, q_y) == false){
//				return false;
//			}
//		}
//		return true;
//	}
//	/* (non-Javadoc)
//	 * @see utility.security.IVo#getHashcode()
//	 */
//	@Override
//	public String getDigest() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//}
