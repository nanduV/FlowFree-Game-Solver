/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mypack;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static mypack.Sample.solution;
import static mypack.Sample.solve;

/**
 *
 * @author 
 */
public class FlowFree  extends Applet implements MouseListener{
    static int N=5;
    static int NUM = 5;
    int SIZE = 75;
    int board[][] = new int[N][N];
    int grid[][] = new int[N][N];
    int count;
    boolean solved;
    int p = 50;
    Color[] colour = {Color.BLACK,Color.RED , Color.GREEN,Color.BLUE,Color.YELLOW,Color.ORANGE,Color.BLACK};
 //   int sol[][] = new int[N][N];
    public FlowFree()
    {
        
        addMouseListener(this);
    }
    
    public void paint(Graphics g){
        g.setColor(Color.red);
        g.setFont(new Font("Arial",Font.BOLD,40));
        g.drawString("FLOW FREE", 100, 40);
     //   g.clearRect(0, 0, WIDTH, WIDTH);
        for(int i=0 ; i<board.length ; i++){
            for(int j=0 ; j<board[i].length ; j++){
                
                g.setColor(Color.CYAN);
                g.fillRect(i*SIZE+p, j*SIZE+p, SIZE, SIZE);
                if(grid[i][j] != 0){
                g.setColor(colour[grid[i][j]]);
                g.fillOval(i*SIZE+(SIZE/4)+p, j*SIZE+(SIZE/4)+p, SIZE/2, SIZE/2);
                }
                
                g.setColor(Color.BLACK);
                  g.drawRect(i*SIZE+p, j*SIZE+p, SIZE, SIZE);
            }
        }
        if(solved){
            
            for(int c=1 ; c<=NUM ; c++){
               boolean temp=false;
               int cur_row=-1,cur_col=-1;
               for(int i=0 ; i<N && !temp; i++){
                   for(int j=0 ; j<N ; j++)
                   {
                       if(board[i][j]==c){
                           cur_row = i;
                           cur_col = j;
                           temp = true;
                           break;
                       }
                   }
               }
                System.out.println(cur_row + " " + cur_col);
               int flag = 0;
               int x[] = {0,0,1,-1};
               int y[] = {1,-1,0,0};
               int vis[][] = new int[N][N];
               while(flag == 0 || board[cur_row][cur_col] != c ){
                 
                  System.out.println(cur_row + " " + cur_col);
                   flag++;
                   int i=cur_row,j=cur_col;
                     vis[i][j] = 1;
                   int k;
                   for( k=0 ; k<4 ; k++){
                       if(valid(i+x[k],j+y[k],vis) && board[i+x[k]][j+y[k]]==-c)
                         break;  
                   }
                   if(k==4)
                       break;
                  int next_row = i + x[k];
                  int next_col = j + y[k];
                  g.setColor(colour[c]);
                  drawLine(cur_row*SIZE+SIZE/2+p,cur_col*SIZE+SIZE/2+p,next_row*SIZE+SIZE/2+p,next_col*SIZE+SIZE/2+p,g);
                   try {
                       Thread.sleep(300);
                   } catch (InterruptedException ex) {
                       Logger.getLogger(FlowFree.class.getName()).log(Level.SEVERE, null, ex);
                   }
                  cur_row = next_row;
                  cur_col = next_col;
               }
       
            }
       
        }
        //addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        count++;
 
        if(count >2*NUM){
           JOptionPane.showMessageDialog(this,"STOP CLICKING!");
        
           // System.exit(0);
        }
        else{     
        System.out.println("clicked");
        Point pt = e.getPoint();
        int r=0,c=0;
        boolean flag=false;
        for(int i=0 ; i<N && !false; i++){
            for(int j=0 ; j<N ; j++){
                if(pt.x >= i*SIZE+p && pt.x <= i*SIZE+p + SIZE && pt.y >= j*SIZE+p && pt.y<=j*SIZE + SIZE+p)
                {
                    flag = true;
                    r =i;
                    c = j;
                    break;
                }
            }
        }
        int val = count/2;
        if(count%2==1)
            val++;
        System.out.println(r + " " + c + " : " + val );
        grid[r][c] = val;
        if(count==2*NUM){
            int[][] flow = new int[N][N];
           // b1.setVisible(true);
              for(int i=0 ; i<N ; i++)
                for(int j=0;j<N ; j++)
                    flow[i][j] = grid[j][i];
            
            solve(flow,1);
              for(int i=0 ; i<N ; i++)
                for(int j=0;j<N ; j++)
                    board[i][j] = solution[j][i];
            solved = true;
        }
        repaint();
    }
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean valid(int r, int c,int[][] vis) {
        if(r >=0 && c>=0 && r<N && c<N && vis[r][c]==0)
            return true;
        return false;
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
       public static void drawLine(double x1,double y1 ,double x2,double y2,Graphics g){
        double dx,dy,steps,xc,yc,x,y;
        dx = x2-x1;
        dy = y2-y1;
        if(Math.abs(dx) > Math.abs(dy))
               steps = Math.abs(dx) ;
       else
               steps = Math.abs(dy);
        xc = dx/steps;
        yc = dy/steps ;
        x=x1;
        y=y1;
        for(int k=1 ; k<=steps ; k++){
            x = x+xc ;
            y = y+yc ;
            g.fillOval((int)x, (int) y, 15, 15);
        }
    }
    
}