/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shepards.inverter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.MathContext;
import javax.imageio.ImageIO;

/**
 *
 * @author lenovo
 */
public class ImgWriter {
    /*public ImgWriter(String directory, ImgCompiler pointset) throws Exception{
        String name = pointset.getName();
        for(SelPt point : pointset.getSelected()){
            String drawerdir = directory + "\\" + point.getName() + " " + name + ".png";
            String writerdir = directory + "\\" + point.getName() + " " + name + ".txt";
            int imgsize = point.getGridsize();
            int width = imgsize * 16, height = imgsize * 16;
            BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for(int y = 0; y < imgsize; y ++) for(int x = 0; x < imgsize; x ++){
                for(int h = 0; h < 16; h ++) for(int w = 0; w < 16; w ++){
                    img.setRGB(x*16 + w, y*16 + h, this.Img(point.getVals().get(0)[y][x]));
                }
            }
            File f = new File(drawerdir);
            ImageIO.write(img, "png", f);
            
            PrintWriter writer = new PrintWriter(writerdir, "UTF-8");
            writer.println("name: " + point.getName() + " checkdist: " + point.getCheckdist() + " gridsize: " + point.getGridsize() + " compdist: " + point.getCompdist());
            for(int y = 0; y < imgsize; y ++){
                for(int x = 0; x < imgsize-1; x ++){
                    BigDecimal dec = new BigDecimal(point.getVals().get(0)[y][x]);
                    dec = dec.round(new MathContext(6));
                    writer.print(dec.toPlainString() + " ");
                }
                BigDecimal dec = new BigDecimal(point.getVals().get(0)[y][imgsize-1]);
                dec = dec.round(new MathContext(6));
                writer.print(dec.toString());
                writer.println();
            }
            writer.close();
        }
    }*/
    
    public ImgWriter(String directory, Variables vars) throws Exception{
        for(Variables.Var point : vars.getVariables()){
            String drawerdir = directory + "\\" + point.getName() + " FromTemplate.png";
            String writerdir = directory + "\\" + point.getName() + " FromTemplate.txt";
            int imgsize = point.getGridsize();
            int width = imgsize * 16, height = imgsize * 16;
            BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for(int y = 0; y < imgsize; y ++) for(int x = 0; x < imgsize; x ++){
                for(int h = 0; h < 16; h ++) for(int w = 0; w < 16; w ++){
                    img.setRGB(x*16 + w, y*16 + h, this.Img(point.getVals()[y][x]));
                }
            }
            File f = new File(drawerdir);
            ImageIO.write(img, "png", f);
            
            PrintWriter writer = new PrintWriter(writerdir, "UTF-8");
            writer.println("name: " + point.getName() + " checkdist: " + point.getCheckdist() + " gridsize: " + point.getGridsize() + " compdist: " + point.getCompdist());
            for(int y = 0; y < imgsize; y ++){
                for(int x = 0; x < imgsize-1; x ++){
                    BigDecimal dec = new BigDecimal(point.getVals()[y][x]);
                    dec = dec.round(new MathContext(6));
                    writer.print(dec.toPlainString() + " ");
                }
                BigDecimal dec = new BigDecimal(point.getVals()[y][imgsize-1]);
                dec = dec.round(new MathContext(6));
                writer.print(dec.toString());
                writer.println();
            }
            writer.close();
        }
    }
    
    public int Img(double pw){
        int r, g, b, p;
        int weight = 720;
        r = g = b = 255;
        if(pw > 0 ){
            g = (int)(255 - weight * Math.abs(pw));
            r = (int)(255 - weight * Math.abs(pw));
            if(g < 0) g = 0;
            if(r < 0) r = 0;
        } else if(pw < 0){
            g = (int)(255 - weight * Math.abs(pw));
            b = (int)(255 - weight * Math.abs(pw));
            if(g < 0) g = 0;
            if(b < 0) b = 0;
        }
        p = (b<<16) | (g<<8) | r;
        return p;
    }
}
