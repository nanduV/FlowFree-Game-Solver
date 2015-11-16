/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mypack;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;
import static mypack.FlowFree.N;
import static mypack.FlowFree.NUM;

/**
 *
 * @author
 */

class Pair{
    int x,y;
}
public class Sample {
    
    
static int[][] solution = new int[N][N];

    
static boolean isValid(int row,int col,int[][] grid, int[][] visited){
	if(row >=0 && row<N && col>=0 && col<N && grid[row][col]==0 && visited[row][col]==0)
		return true ;
	return false;
}


static void solve_util(int[][] grid,int val,int sr,int sc,int er,int ec,int[][] visited,int[][][] ans ,int[] size ){
	 int[][] Grid = new int[grid.length][];
         int[][] Vis = new int[visited.length][];
    for(int k=0 ; k<grid.length ; k++){
        Grid[k] = Arrays.copyOf(grid[k], N);
    Vis[k] = Arrays.copyOf(visited[k], N);
    }
    
    
        int i=sr ;
	int j = sc ;
	Vis[i][j] = 1;
	if(i==er && j==ec){
			Grid[i][j] = val;
			for(int k=0 ; k<N ; k++ ){
    		for(int l=0 ; l<N ; l++)
    			ans[size[0]][k][l] = Grid[k][l] ;
    		
		} 
		size[0]++;
		return ;
	}
	Grid[i][j] =  -val;
	if(isValid(i-1,j,Grid,Vis))
		//cout<<i<<" "<<j<<" - top "<<endl,
		solve_util(Grid,val,i-1,j,er,ec,Vis,ans,size) ;
//	else
//		cout<<i<<" "<<j<<": top is invalid"<<endl;
	if(isValid(i+1,j,Grid,Vis))
		//cout<<i<<" "<<j<<" - bottom "<<endl,
		solve_util(Grid,val,i+1,j,er,ec,Vis,ans,size) ;
//	else
//		cout<<i<<" "<<j<<": bottom is invalid"<<endl;
	if(isValid(i,j-1,Grid,Vis))
		//cout<<i<<" "<<j<<" - left "<<endl,
		solve_util(Grid,val,i,j-1,er,ec,Vis,ans,size) ;
		//else
		//cout<<i<<" "<<j<<": left is invalid"<<endl;
	if(isValid(i,j+1,Grid,Vis))
    //	cout<<i<<" "<<j<<" - right "<<endl,
	solve_util(Grid,val,i,j+1,er,ec,Vis,ans,size) ;
    //	else
	//	cout<<i<<" "<<j<<": right is invalid"<<endl;
	
	
	//right
	//bottom
	//down
	return ;
	
}


static boolean check(int[][] grid){
	int i,j ;
	for(i = 0 ; i<N ; i++){
		for(j = 0 ; j<N ; j++)
			if(grid[i][j]==0)
				return false;
	}
	return true;
//	for(i = 1 ; i<=NUM ; i++)
}
static boolean solve(int[][] grid,int n){
    int[][] Grid = new int[grid.length][];
    for(int k=0 ; k<grid.length ; k++)
        Grid[k] = Arrays.copyOf(grid[k], N);
//		cout<<"Thinking :)\n";
		if(n > NUM){
			if(check(Grid)){
			for(int i=0 ; i<N ; i++){
				for(int j=0 ; j<N ; j++)
					solution[i][j] = Grid[i][j];
		
			}
			return true;
			}
			return false;
		}
		int sr=0, sc=0;
		int er=0,ec=0;
		boolean start = false ;
		for(int i=0 ; i<N ; i++){
			for(int j=0 ; j<N ; j++){
				if(Grid[i][j]==n){
				if(start==false){
					sr = i;
                                        sc= j;
					start = true;
				}
                                else{
					er = i;
                                        ec = j;
                                }
				}
			}
		}
		Grid[sr][sc] = 0;
		Grid[er][ec] = 0;
		int[][] visited = new int[N][N];
		int[] size = new int[1];
          
		int[][][] ans = new int[1000][N][N] ;
//		cout<<"hoo";
		solve_util(Grid,n,sr,sc,er,ec,visited,ans,size); 
		//cout<<"Solutions : "<<size<<endl;
		for(int i=0 ; i<size[0] ; i++){
				                System.out.println("\nTrying for : " + n );//<<endl;
			//	cout<<"hard"<<endl;
				int[][] temp = new int[N][N] ;
				temp = Grid;
				for(int j=0 ; j<N; j++)
				{
					for(int k=0;k<N ; k++)
						Grid[j][k]	= ans[i][j][k] ;
					//cout<<endl;
				}
				
				if(solve(Grid,n+1))
					return true ;
	
                                Grid = temp;
						
		}
		return false;
}
    public static void main(String[] args) {
     int[][] vec = new int[3][3];
     vec[0][0] = 3;
      //fun(vec);
        System.out.println(vec[0][0]);
        int[][] grid = new int[N][N];
	       System.out.println("GRID!!\n");
	for(int i=0 ; i<N ; i++){
		          System.out.println("Row "+(i+1));//endl;
		int[] temp = new int[N];
		for(int j=0 ; j<N ; j++){
			int val;
                        Scanner cin = new Scanner(System.in);
			val = cin.nextInt();
			temp[j]  = val;
		}
		grid[i] =temp;

	}
	
	if(solve(grid,1)){
	//	cout<<"hrllo"<<endl;
			for(int k=0 ; k<N ; k++ ){
    		for(int l=0 ; l<N ; l++)
    			                         System.out.print(solution[k][l] +" ");
    		                      System.out.println("");
    		//cout<<endl;
		}
	}
	else
  	         System.out.println("No solution");
	
	
	
       
    }


}
