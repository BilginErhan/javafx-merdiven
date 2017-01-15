/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafikfinal4;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author ERHAN
 */
class top
{
    public int x,y,r;
    public int en,boy;
    public int hiz=0;
    public int sayac1=0;
    
    public int sayac=0;
    
    public top()
    {
      x=0;y=0;r=30;
    }
    
    public void move()
    {
        sayac1++;
        if(sayac1<29)
        {
            if(sayac==1)
            {
                x+=en;
                y+=0;
                sayac=0;
            }else
            {
                x+=0;
                y+=boy;
                sayac=1;
            }
        }else
        {
            if(sayac==1)
            {
                x-=en;
                y+=0;
                sayac=0;
            }else
            {
                x+=0;
                y-=boy;
                sayac=1;
            }
            if(sayac1==56)
                sayac1=0;
        }
        
    }
    
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_UP)
        {
            hiz=-100; 
        }
        if(key == KeyEvent.VK_DOWN)
        {
            hiz=100;
        }
    }
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_UP)
        {
            hiz=0;
        }
        if(key == KeyEvent.VK_DOWN)
        {
            hiz=0;
        }
    }
}
class Board extends JPanel implements ActionListener{

    top t1;
    Timer timer;
    public int DELAY=1000;
    public Board()
    {
        t1 = new top();
        timer = new Timer(DELAY, this);
        timer.start();
        addKeyListener(new TAdapter());
        setFocusable(true);
    }
    
    private void doDrawing(Graphics g)
    {
        timer.stop();
        timer = new Timer(DELAY, this);
        timer.start();
        
        Graphics2D g2d = (Graphics2D) g.create();
        
        t1.en=getWidth()/15;
        t1.boy=getHeight()/15;
        
        g2d.setPaint(Color.RED);
        g2d.fillOval(t1.x, t1.y, t1.r, t1.r);
        
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        doDrawing(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        t1.move();
        DELAY+=t1.hiz;
    }
    
    private class TAdapter extends KeyAdapter
    {
        @Override
        public void keyReleased(KeyEvent e)
        {
            t1.keyReleased(e);
        }
        
        @Override
        public void keyPressed(KeyEvent e)
        {
            t1.keyPressed(e);
        }
    }
    
}
public class GrafikFinal4 extends JFrame{


    public GrafikFinal4()
    {
        Board board = new Board();
        add(board);
        setSize(700,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    public static void main(String[] args) {
        // TODO code application logic here
        
        GrafikFinal4 gf4 = new GrafikFinal4();
        gf4.setVisible(true);
    }
    
}
