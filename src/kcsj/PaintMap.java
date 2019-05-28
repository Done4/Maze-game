package kcsj;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PaintMap extends JPanel{
	final int unitSize =10;
	private int width;
	private int height;
	private int startX;
	private int startY;
	private boolean block;
	private boolean b[][];
	private boolean IsDisplay;
	private ArrayList<Integer> ToExit = new ArrayList<Integer>(); 
	public PaintMap(boolean b[][],ArrayList<Integer>a) {
		ToExit=a;
		this.b=b;
		width=b.length;
        height=b[0].length;
		startX=0; //初始位置
		startY=1;
		block=true;
		IsDisplay=false;
	}
	public void paint(Graphics g) {
		//墙的颜色
		g.setColor(Color.black);
		for(int i=0;i<width;i++)
			for(int j=0;j<height;j++)
				if(!b[i][j])
					g.fill3DRect(30+i*unitSize, 30+j*unitSize, unitSize, unitSize, true);
		//出口路线颜色
		if(IsDisplay) {
			g.setColor(Color.red);
			for(int i=0;i<ToExit.size();i+=2) {
				g.fill3DRect(30+ToExit.get(i)*unitSize, 30+ToExit.get(i+1)*unitSize, unitSize, unitSize, true);
	    	}
		}
		//控制格子颜色
		g.setColor(Color.blue);
		if(IsEdge(startX, startY)) {
			g.fill3DRect(30+startX*unitSize, 30+startY*unitSize, unitSize, unitSize, true);
			
		}
		else
			g.fill3DRect(30+unitSize,30, unitSize, unitSize, true);
	}
	
	public void moveUp() {
		startY-=1;
		if(IsEdge(startX, startY)) {
			if(!b[startX][startY]) {
				block=false;
				startY+=1;
			}
			if(block) 
				repaint();
			else
				block=true;
			Win(startX,startY);
		}
		else
			startY+=1;
	}
	
	public void moveDown() {
		startY+=1;
		if(IsEdge(startX, startY)) {
			if(!b[startX][startY]) {
				block=false;
				startY-=1;
			}
			if(block) 
				repaint();
			else
				block=true;
			Win(startX,startY);
		}
		else
			startY-=1;
	}
	
	public void moveLeft() {
		startX-=1;
		if(IsEdge(startX, startY)) {
			if(!b[startX][startY]) {
				block=false;
				startX+=1;
			}
			if(block) 
				repaint();
			else
				block=true;
			Win(startX,startY);
		}
		else
			startX+=1;
	}
	
	public void moveRight() {
		startX+=1;
		if(IsEdge(startX, startY)) {
			if(!b[startX][startY]) {
				block=false;
				startX-=1;
			}
			if(block) 
				repaint();
			else
				block=true;
			Win(startX,startY);
		}
		else
			startX-=1;
	}
	
	public void PressSp() {
		if(IsDisplay)
			IsDisplay=false;
		else
			IsDisplay=true;
		repaint();
	}
	
	private boolean IsEdge(int x,int y) {
		return (x<width&&y<height&&x>=0&&y>=0) ;
	}
	
	private void Win(int x,int y) {
		if(x==width-1&&y==height-2) {
		Object[] options = {"再来一局","退出"};
		 int response=JOptionPane.showOptionDialog ( this, "出来了","Game Over",JOptionPane.YES_OPTION ,JOptionPane.PLAIN_MESSAGE, null,
		options, options[0] ) ;
		 if (response == 0){
			 b=new PMap().prim(0, 0, (width-1)/2,(height-1)/2, true);
			 ToExit=new EMap(b).exitmap();
			 startX=0;
			 startY=1;
			 block=true;
			 IsDisplay=false;
			 repaint();
		 }
		 else 
			 System.exit(0);

		}
	}
}