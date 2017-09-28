/**
 * 
 */
package utility;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;



/**
 * @author qchen
 *
 */
public class testData extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int DEFAULT_WIDTH = 800;// default windows width
	public static final int DEFAULT_HEIGHT = 600;// default windows height
	public static final int DEFAULT_LOCATION_X = 150; // default windows initial location x
	public static final int DEFAULT_LOCATION_Y = 150;//default windows initial location y
	private String filename = "source/CA.dat";
	private String fileofquery = "query/query.in.1000";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			EventQueue.invokeAndWait(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					testData testdata = new testData();
					testdata.setVisible(true);
					testdata.setResizable(false);
					testdata.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
			});
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public testData(){
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setLocation(DEFAULT_LOCATION_X, DEFAULT_LOCATION_Y);
		add(new data());
		
	}
	
	class data extends JPanel{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		ArrayList<Point> points = new ArrayList<Point>();
		ArrayList<Point> points2 = new ArrayList<Point>();
		data(){
			int sx = 1 << 20, sy = 1 << 20, mx = -1, my = -1;
			try {
				Scanner in = new Scanner(new FileInputStream(new File(filename)));
				while(in.hasNext()){
					in.nextInt(); in.nextInt();
					in.nextInt(); in.nextInt();
					int x1 = in.nextInt(), y1 = in.nextInt();
					if(x1 > mx) mx = x1;
					if(x1 < sx) sx = x1;
					if(y1 > my) my = y1;
					if(y1 < sy) sy = y1;
				}
				in.close();
				in = new Scanner(new FileInputStream(new File(filename)));
				int rangex = mx - sx, rangey = my - sy;
				while(in.hasNext()){
					in.nextInt(); in.nextInt();
					in.nextInt(); in.nextInt();
					int x1 = (int) (1.0 * (in.nextInt() - sx) / rangex * DEFAULT_WIDTH), y1 = (int) (1.0 * (in.nextInt()- sy) / rangey * DEFAULT_HEIGHT);
					points.add(new Point(x1, y1));
				}
				in.close();
				in = new Scanner(new FileInputStream(new File(fileofquery)));
				int count = 0;
				while(in.hasNext()){
					int x1 = (int) (1.0 * (in.nextInt() - sx) / rangex * DEFAULT_WIDTH), y1 = (int) (1.0 * (in.nextInt()- sy) / rangey * DEFAULT_HEIGHT);
					points2.add(new Point(x1, y1));
					if(count ++ >= 50)break;
				}
				in.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.setColor(Color.BLACK);
			System.out.println(points.size());
			for(Point p : points){
				g.fillRect(p.x, p.y, 5, 5);
			}
			g.setColor(Color.RED);
			for(Point p : points2){
				g.fillPolygon(new int[]{p.x, p.x + 8, p.x + 4}, new int[]{p.y, p.y, p.y - 8}, 3);
			}
			Toolkit.getDefaultToolkit().sync();
			g.dispose();
		}
	} 
}
