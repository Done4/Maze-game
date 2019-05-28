package kcsj;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;


@SuppressWarnings("serial")
public class Test extends JFrame implements ActionListener ,KeyListener{
	boolean map[][]=new PMap().prim(2, 0, 20, 19, true);
	PaintMap p=new PaintMap(map,new EMap(map).exitmap());
	
	public Test() {
		this.setTitle("Prim迷宫");
		this.add(p);
		this.setSize(500,500);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		addKeyListener(this);//监听键盘
	}
	public static void main(String[] args) {
		//Swing 不是线程安全的。
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Test().setVisible(true);
			}
		});
	}
	@Override
	public void keyPressed(KeyEvent key) {
		// TODO Auto-generated method stub
		switch(key.getKeyCode()) {
		case KeyEvent.VK_UP:p.moveUp();break;
		case KeyEvent.VK_DOWN:p.moveDown();break;
		case KeyEvent.VK_LEFT:p.moveLeft();break;
		case KeyEvent.VK_RIGHT:p.moveRight();break;
		case KeyEvent.VK_SPACE:p.PressSp();break;
		}
		this.repaint();
	}
	
	public void keyReleased(KeyEvent arg0) {}
	public void keyTyped(KeyEvent arg0) {}
	public void actionPerformed(ActionEvent arg0) {}
}