package kcsj;

import java.util.ArrayList;

public class EMap {
	private ArrayList<Integer> blockPos = new ArrayList<Integer>(); 
    private int d[][]= {{0,-1},{1,0},{0,1},{-1,0}};
    private boolean a[][];
    private int width;
    private int height;
    private boolean fl=false;
    public EMap(boolean b[][]) {
    	 width=(b.length-1)/2;
    	 height=(b[0].length-1)/2;
         a=new boolean [b.length][b[0].length];
         for(int i=0;i<b.length;i++)
         	for(int j=0;j<b[0].length;j++)
                 a[i][j]=b[i][j];
    }
    private void dfs(int x,int y,int c) {
    	
    	if(x==(width*2)&&y==(height*2-1)) {
    		fl=true;
    		return ;
    	}
    		
    	for(int i=0;i<4;i++) {
    		if(c==i)continue;
    		int dx=x+d[i][0];
    		int dy=y+d[i][1];
    		if(ise(dx,dy)&&a[dx][dy]) {
    			if(fl)break;
    			blockPos.add(dx);blockPos.add(dy);    		
    			a[dx][dy]=false;
    			dfs(dx,dy,(i+2)%4);
    		}
    	}
    	
    	if(!fl) {
    		blockPos.remove(blockPos.size()-1);
    		blockPos.remove(blockPos.size()-1);
    	}
    }
    @SuppressWarnings("unused")
	public ArrayList<Integer> exitmap() {    
    	blockPos.add(0);blockPos.add(1);//≥ı ºŒª÷√
//    	for(int i=0;i<a.length;i++)
//    	{
//    		for(int j=0;j<a[0].length;j++) {
//    			System.out.print(a[i][j]+" ");
//    		}
//    		System.out.print("\n");
//    	}
    	dfs(0,1,3);
//    	for(int i=0;i<blockPos.size();i+=2) {
//    		System.out.println(blockPos.get(i)+","+blockPos.get(i+1));
//    	}
    	return blockPos;
    }
    private boolean ise(int dx,int dy) {
    	return (0 <= dx && dx <= width*2 && 0 <= dy && dy <= height*2);
    }
}
