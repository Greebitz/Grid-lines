import javax.swing.*;
import java.awt.*;


class Grid extends JFrame{

    int width, height;
    int rezise = 100;
    boolean grid = false;
    int x0;
    int y0;
    int x1;
    int y1;

    Grid(int x, int y) {
        width = rezise * x;
        height = rezise * y;
        Frame();
    }

    public void rasterline(int xx0, int yy0, int xx1, int yy1) {
        System.out.println("trueeee");
        x0 = xx0 * rezise;
        y0 = yy0 * rezise;
        x1 = xx1 * rezise;
        y1 = yy1 * rezise;
    }

    public void drawGrid (){
        }

    public void paint(Graphics g) { //Paint component to draw the grid
        g.setColor(Color.black);
        for (int i = 0; i < width / rezise; i++) {
            g.drawLine(i * rezise, 0, i * rezise, width);
            g.drawLine(0, i * rezise, width, i * rezise);
            grid = true;
            }
        line(g, x0, y0, x1, y1);
        }

        public void line(Graphics g, int x0, int y0, int x1, int y1){
            //Used fields in algorithm
            int x = x0;
            int y = y0;
            int w = x1 - x;
            int h = y1 - y;
            int dx1 = 0; //The d stands for direction
            int dy1 = 0;
            int dx2 = 0;
            int dy2 = 0;
            int xCoord;
            int yCoord;

            if (w < 0) { //All the if else statement are implemented to decide what octant the line is supposed to go
                dx1 = -1;
            } else if (w > 0) {
                dx1 = 1;
            }

            if (h < 0) {
                dy1 = -1;
            } else if (h > 0) {
                dy1 = 1;
            }

            if (w < 0) {
                dx2 = -1;
            } else if (w > 0) {
                dx2 = 1;
            }

            int w2 = Math.abs(w); //Math.abs gives the absolute value of a number
            int h2 = Math.abs(h);//Example: -123 turns into 123

            if (w2 < h2) {
                w2 = Math.abs(h);
                h2 = Math.abs(w);
                if (h < 0) {
                    dy2 = -1;
                } else if (h > 0) {
                    dy2 = 1;
                }
                dx2 = 0;
            }

            int increment = (w2 / 10) * 10;

            for (int i = 0; i <= w2; i++) {
                xCoord = (x / 10) * 10; //This turns 123 into 120. This is to ensure the numbers are rounded up, this is because the grid is 10 times the size.
                yCoord = (y / 10) * 10;
                g.fillRect(xCoord, yCoord, rezise, rezise);

                increment += h2;

                if (increment > w2) { //If else to decide the direction of the line
                    increment -= w2;
                    x += dx1;
                    y += dy1;
                } else {
                    x += dx2;
                    y += dy2;
                }
            }
        }


    public void Frame(){
            this.setTitle("Raster Graphics");
            this.setSize(width, height);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setResizable(false);
        }
    }

    public class Rastering {
        public static void main(String[] args) {
            Grid g = new Grid(20, 20);

            g.rasterline(0, 10, 19, 0);//left to right
            g.rasterline(0, 0, 19, 19);

            g.setVisible(true);
            g.drawGrid();
        }
    }



