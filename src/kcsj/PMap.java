package kcsj;

import java.util.ArrayList;
import javax.swing.JPanel;

@SuppressWarnings("serial")
/*
			让迷宫全都是墙.。
			选一个格，作为迷宫的通路，然后把它的邻墙放入列表。
			当列表里还有墙时:
					从列表里随机选一个墙，如果它对面的格子不是迷宫的通路:
							把墙打通，让对面的格子成为迷宫的通路。
							把那个格子的邻墙加入列表。
					如果对面的格子已经是通路了，那就从列表里移除这面墙。
 */
public class PMap extends JPanel  {

	public boolean[][] prim(int startX,int startY,int widthLimit,int heightLimit,boolean haveBorder){
		final boolean block=false,unblock=true;	
		if(widthLimit<1)
			widthLimit=1;
		if(heightLimit<1)
			heightLimit=1;
		if(startX<0||startX>=widthLimit)
			startX=(int)Math.round(Math.random()*(widthLimit-1));
		if(startY<0||startY>=heightLimit)
			startY=(int)Math.round(Math.random()*(heightLimit-1));
		if(!haveBorder) {
			--widthLimit;
			--heightLimit;
		}
		//迷宫尺寸换算成带墙尺寸
		widthLimit*=2;
		heightLimit*=2;
		//迷宫起点换算成带墙起点
		startX*=2;
		startY*=2;
		if(haveBorder) {
			++startX;
			++startY;
		}
		//初始化迷宫
		boolean[][]mazeMap=new boolean [widthLimit+1][heightLimit+1];
		for(int i=0;i<=widthLimit;i++)
			for(int j=0;j<=heightLimit;j++)
				mazeMap[i][j]=block;
		mazeMap[0][1]=unblock;//入口
		mazeMap[widthLimit][heightLimit-1]=unblock;//出口
		
		ArrayList<Integer> blockPos = new ArrayList<Integer>(); 
		
		int targetX=startX,targetY=startY;
		
		mazeMap[targetX][targetY]=unblock;
		
		if(targetY>1) {
		   blockPos.add(targetX);blockPos.add(targetY-1);blockPos.add(0);
		}
		if (targetX < widthLimit)
	    {
			blockPos.add(targetX+1);blockPos.add(targetY);blockPos.add(1);
	    }
	    if (targetY < heightLimit)
	    {
	    	blockPos.add(targetX);blockPos.add(targetY+1);blockPos.add(2);
	    }
	    if (targetX > 1)
	    {
	    	blockPos.add(targetX-1);blockPos.add(targetY);blockPos.add(3);
	    }
	  
	    while(!blockPos.isEmpty()) {
	    	int blockIndex=(int)Math.round(Math.random()*(blockPos.size()/3-1))*3;
	    	if(blockIndex+2<blockPos.size()) {
	    	if(blockPos.get(blockIndex+2).equals(0)) {
	    		targetX= blockPos.get(blockIndex);
	    		targetY= blockPos.get(blockIndex+1)-1;
	    	}
	    	else if(blockPos.get(blockIndex+2).equals(1)) {
	    		targetX= blockPos.get(blockIndex)+1;
	    		targetY= blockPos.get(blockIndex+1);
	    	}
	    	else if(blockPos.get(blockIndex+2).equals(2)) {
	    		targetX= blockPos.get(blockIndex);
	    		targetY= blockPos.get(blockIndex+1)+1;
	    	}
	    	else if(blockPos.get(blockIndex+2).equals(3)) {
	    		targetX= blockPos.get(blockIndex)-1;
	    		targetY= blockPos.get(blockIndex+1);
	    	}
	    	
	    	}

	    	if(mazeMap[targetX][targetY]==block) {
	    		//打通墙
	    		if(blockIndex+1<blockPos.size())
	    		mazeMap[blockPos.get(blockIndex)][blockPos.get(blockIndex+1)]=unblock;
	    		else
	    			System.out.println("error");
	    		mazeMap[targetX][targetY]=unblock;
	    		//添加当前目标的邻墙
	    		if (targetY > 1 && mazeMap[targetX][targetY - 1] == block && mazeMap[targetX][targetY - 2] == block)
	            {
	    			 blockPos.add(targetX);blockPos.add(targetY-1);blockPos.add(0);
	            }
	            if (targetX < widthLimit -1&& mazeMap[targetX + 1][targetY] == block && mazeMap[targetX + 2][targetY] == block)
	            {
	            	blockPos.add(targetX+1);blockPos.add(targetY);blockPos.add(1);
	            }
	            if (targetY < heightLimit-1 && mazeMap[targetX][targetY + 1] == block && mazeMap[targetX][targetY + 2] == block)
	            {
	            	blockPos.add(targetX);blockPos.add(targetY+1);blockPos.add(2);
	            }
	            if (targetX > 1 && mazeMap[targetX - 1][targetY] == block && mazeMap[targetX - 1][targetY] == block)
	            {
	            	blockPos.add(targetX-1);blockPos.add(targetY);blockPos.add(3);
	            }
	    	}
	    	for(int l=blockIndex,k=0;k<3;k++) {
	    			blockPos.remove(l);
	    	}
	    }
		return mazeMap;
	 }
}
 