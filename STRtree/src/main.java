import java.util.*;
import Tool.Hasher;

import java.io.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


class DrawFrame extends JFrame{
	public ArrayList<STRNode> l;
    public DrawFrame(ArrayList<STRNode> list){
        super();
        l = list;
        initialize();//调用初始化方法
    }
    //初始化方法
    public void initialize(){
        this.setSize(700, 700);//设置窗体大小
        //设置窗体的关闭方式
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(new CanvasPanel(l));//设置窗体面板为绘图面板对象
        this.setTitle("draw mbrs");//设置窗体标题
    }
    
    //创建内部类
    class CanvasPanel extends JPanel{
    	ArrayList<STRNode> l;
    	public CanvasPanel(ArrayList<STRNode> list) {
    		l = list;
    	}
        public void paint(Graphics g){
            super.paint(g);
            Graphics2D g2=(Graphics2D)g;//强制类型转换位Graphics2D类型
            //Graphics2D是推荐使用的绘图类，但是程序设计中提供的绘图对象大多是
            //Graphics类的实例，这时应该使用强制类型转换将其转换为Graphics
            int length = l.size();
            ArrayList<Shape> shapes = new ArrayList<Shape>();
            //创建矩形对象
            Queue<STRNode> st = new LinkedList<STRNode>();
    		for(int i = 0;i<l.size();i++) {
    			st.offer(l.get(i));
    		}
    		while(!st.isEmpty()) {
    			STRNode n = st.poll();
    			if(n.isleaf) {
    				//System.out.println("data point: "+n.MBR+" data hash: "+n.hashvalue);
    				shapes.add(new Rectangle2D.Double(n.MBR.x1,n.MBR.y1,n.MBR.x2-n.MBR.x1+3,n.MBR.y2-n.MBR.y1+3));
    			}
    			else {
    				//System.out.println("internal node:"+n.MBR+" internal hash: "+n.hashvalue);
    				shapes.add(new Rectangle2D.Double(n.MBR.x1,n.MBR.y1,n.MBR.x2-n.MBR.x1,n.MBR.y2-n.MBR.y1));
    				for(int i = 0;i<n.child.size();i++) {
    					st.offer(n.child.get(i));
    				}
    			}
    		}
            for(Shape shape:shapes){//遍历图型数组
                if(shape.getBounds2D().getWidth()==3 && shape.getBounds2D().getHeight()==3) {
                	g2.setColor(Color.RED);
                	g2.draw(shape);
                }
                else {
                	g2.setColor(Color.BLUE);
                	g2.draw(shape);
                }
            }
        }
    }
}

public class main {
	public static void main(String [] args) {
		ArrayList<STRNode> nodelist = new ArrayList<STRNode>();
		/*
		Random random = new Random();
		for(int i = 0;i<15;i++) {
			Rect r = new Rect(new Point(random.nextInt(100),random.nextInt(100)));
			STRNode n = new STRNode(r,true,null,"");
			nodelist.add(n);
			System.out.println(n.MBR);
		}
		*/
		STRTree strtree = new STRTree("datapoints.txt",3);
		//strtree.DFStraverse();
		DrawFrame df=new DrawFrame(strtree.root.child);
        df.setVisible(true);
        LinkedList<String> VO = new LinkedList<String>();
        strtree.secureRangeQuery(strtree.root, new Rect(3,15,10,70), VO);
        System.out.println("VOs:");
        for(String s:VO) {
        	System.out.println(s);
        }
        VOreturn voreturn = strtree.RootHash(VO);
        System.out.println(voreturn.hash);
        System.out.println(strtree.root.MBR.toString()+strtree.root.hashvalue);
        System.out.println(voreturn.hash.equals(strtree.root.MBR.toString()+strtree.root.hashvalue));
	}
}
