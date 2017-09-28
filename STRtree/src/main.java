import java.util.*;
import Tool.Hasher;
public class main {
	public static void main(String [] args) {
		ArrayList<STRNode> nodelist = new ArrayList<STRNode>();
		Random random = new Random();
		for(int i = 0;i<13;i++) {
			Rect r = new Rect(new Point(random.nextInt(100),random.nextInt(100)));
			STRNode n = new STRNode(r,true,null,"");
			nodelist.add(n);
			System.out.println(n.MBR);
		}
		ArrayList<STRNode> rootnodes = createTree(nodelist,3);
		System.out.println(rootnodes.size());
		traverse(rootnodes);
	}
	
	public static void traverse(ArrayList<STRNode> rootnodes) {
		int layer = 0;
		Queue<STRNode> st = new LinkedList<STRNode>();
		for(int i = 0;i<rootnodes.size();i++) {
			st.offer(rootnodes.get(i));
		}
		while(!st.isEmpty()) {
			STRNode n = st.poll();
			if(n.isleaf) {
				System.out.println("data point: "+n.MBR+" data hash: "+n.hashvalue);
			}
			else {
				System.out.println("internal node:"+n.MBR+" internal hash: "+n.hashvalue);
				for(int i = 0;i<n.child.size();i++) {
					st.offer(n.child.get(i));
				}
			}
		}
	}
	public static ArrayList<STRNode> createTree(ArrayList<STRNode> nodes,int nodec) {
		//x axid comparator for sorting
		Comparator xcomp = new Comparator<STRNode>() {
			public int compare(STRNode r1,STRNode r2) {
				if(r1.MBR.getCenter().x < r2.MBR.getCenter().x)
					return -1;
				else if(r1.MBR.getCenter().x > r2.MBR.getCenter().x)
					return 1;
				else
					return 0;
			}
		};
		//y axid comparator for sorting
		Comparator ycomp = new Comparator<STRNode>() {
			public int compare(STRNode r1,STRNode r2) {
				if(r1.MBR.getCenter().y < r2.MBR.getCenter().y)
					return -1;
				else if(r1.MBR.getCenter().y > r2.MBR.getCenter().y)
					return 1;
				else
					return 0;
			}
		};
		//temporary variable of current layer nodes
		ArrayList<STRNode> current = nodes;
		do {
			ArrayList<STRNode> cur = new ArrayList<STRNode>();
			int xsliceCount = (int) Math.ceil(current.size()/(double)nodec);
			ArrayList[] slices = stripPartition(current,(int) Math.ceil(Math.sqrt(xsliceCount)),xcomp);
			for(int j=0;j<slices.length;j++) {
				ArrayList<STRNode> temp = slices[j];
		    	int ysliceCount = (int) Math.ceil(temp.size()/(double)nodec);
		    	ArrayList[] yslices = stripPartition(temp,ysliceCount,ycomp);
		    	
		    	for(ArrayList<STRNode> arr : yslices) {
		    		if(arr.get(0).isleaf==true) {
		    			String hash = "";
		    			for(STRNode a:arr) {
		    				hash += a.MBR.toString();
		    			}
		    			hash = new Hasher().stringSHA(hash);
		    			STRNode strnode = new STRNode(getMBR(arr),false,arr,hash);
			    		cur.add(strnode);
		    		}
		    		else {
		    			String hash = "";
		    			for(STRNode a:arr) {
		    				hash += a.MBR.toString()+a.hashvalue;
		    			}
		    			hash = new Hasher().stringSHA(hash);
		    			STRNode strnode = new STRNode(getMBR(arr),false,arr,hash);
			    		cur.add(strnode);
		    		}
		    		
		    	}
			}
			current = cur;
		}
		while(current.size()>nodec);
		return current;
	}
	
	public static Rect getMBR(ArrayList<STRNode> array) {
		double xmin = 100000000;
		double xmax = 0;
		double ymin = 100000000;
		double ymax = 0;
		for(STRNode n : array) {
			if(n.MBR.x1<xmin)
				xmin = n.MBR.x1;
			if(n.MBR.x2>xmax)
				xmax = n.MBR.x2;
			if(n.MBR.y1<ymin)
				ymin = n.MBR.y1;
			if(n.MBR.y2>ymax)
				ymax = n.MBR.y2;
		}
		return new Rect(xmin,xmax,ymin,ymax);
	}
	public static ArrayList[] stripPartition(ArrayList<STRNode> plist,int sliceCount,Comparator comp) {
		int datasize = plist.size();
		int sliceCapacity = (int) Math.ceil(datasize / (double) sliceCount);
		plist.sort(comp);
		ArrayList[] slices = new ArrayList[sliceCount];
		Iterator i = plist.iterator();
	    for (int j = 0; j < sliceCount; j++) {
	    	slices[j] = new ArrayList();
	    	int boundablesAddedToSlice = 0;
	    	while (i.hasNext() && boundablesAddedToSlice < sliceCapacity) {
	    		STRNode t = (STRNode)i.next();
	    		slices[j].add(t);
	    		boundablesAddedToSlice++;
	    	}
	    }
	    return slices;
	}
}
