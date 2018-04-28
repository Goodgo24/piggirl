package com.common.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageHelper {
	private static ImageHelper imageHelper = new ImageHelper();

	public static ImageHelper getInstance() {
		return imageHelper;
	}

	/**
	 * 根据尺寸图片居中裁剪
	 */
	public void cutCenterImage(String src, String dest, int w, int h) throws IOException {
		String fileType = src.substring(src.lastIndexOf(".") + 1, src.length());
		Iterator iterator = ImageIO.getImageReadersByFormatName(fileType);
		ImageReader reader = (ImageReader) iterator.next();
		InputStream in = new FileInputStream(src);
		ImageInputStream iis = ImageIO.createImageInputStream(in);
		reader.setInput(iis, true);
		ImageReadParam param = reader.getDefaultReadParam();
		int imageIndex = 0;
		Rectangle rect = new Rectangle((reader.getWidth(imageIndex) - w) / 2, (reader.getHeight(imageIndex) - h) / 2, w,
				h);
		param.setSourceRegion(rect);
		BufferedImage bi = reader.read(0, param);
		ImageIO.write(bi, fileType, new File(dest));
	}

	/**
	 * 图片裁剪二分之一
	 */
	public void cutHalfImage(String src, String dest) throws IOException {
		String fileType = src.substring(src.lastIndexOf(".") + 1, src.length());
		Iterator iterator = ImageIO.getImageReadersByFormatName(fileType);
		ImageReader reader = (ImageReader) iterator.next();
		InputStream in = new FileInputStream(src);
		ImageInputStream iis = ImageIO.createImageInputStream(in);
		reader.setInput(iis, true);
		ImageReadParam param = reader.getDefaultReadParam();
		int imageIndex = 0;
		int width = reader.getWidth(imageIndex) / 2;
		int height = reader.getHeight(imageIndex) / 2;
		Rectangle rect = new Rectangle(width / 2, height / 2, width, height);
		param.setSourceRegion(rect);
		BufferedImage bi = reader.read(0, param);
		ImageIO.write(bi, fileType, new File(dest));
	}

	/**
	 * 图片裁剪通用接口
	 */

	public void cutImage(String src, String dest, int x, int y, int w, int h) throws IOException {
		String fileType = src.substring(src.lastIndexOf(".") + 1, src.length());
		Iterator iterator = ImageIO.getImageReadersByFormatName(fileType);
		ImageReader reader = (ImageReader) iterator.next();
		InputStream in = new FileInputStream(src);
		ImageInputStream iis = ImageIO.createImageInputStream(in);
		reader.setInput(iis, true);
		ImageReadParam param = reader.getDefaultReadParam();
		Rectangle rect = new Rectangle(x, y, w, h);
		param.setSourceRegion(rect);
		BufferedImage bi = reader.read(0, param);
		ImageIO.write(bi, fileType, new File(dest));
	}

	/**
	 * 图片缩放
	 */
	public void zoomImage(String src, String dest, int w, int h) throws Exception {
		double wr = 0, hr = 0;
		File srcFile = new File(src);
		File destFile = new File(dest);
		BufferedImage bufImg = ImageIO.read(srcFile);
		Image Itemp = bufImg.getScaledInstance(w, h, bufImg.SCALE_SMOOTH);
		wr = w * 1.0 / bufImg.getWidth();
		hr = h * 1.0 / bufImg.getHeight();
		AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(wr, hr), null);
		Itemp = ato.filter(bufImg, null);
		try {
			ImageIO.write((BufferedImage) Itemp, dest.substring(dest.lastIndexOf(".") + 1), destFile);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 等比图片缩放
	 * 
	 * @param src
	 * @param dest
	 * @param w
	 * @param h
	 * @throws Exception
	 */
	public void zoomImageDb(String src, String dest, int w, int h) throws Exception {
		double wr = 0, hr = 0, width = 0, height = 0;
		File srcFile = new File(src);
		File destFile = new File(dest);
		BufferedImage bufImg = ImageIO.read(srcFile);
		// Image Itemp = bufImg.getScaledInstance(w, h, bufImg.SCALE_SMOOTH);//
		// SCALE_SMOOTH
		Image Itemp = bufImg.getScaledInstance(w, h, BufferedImage.TYPE_INT_RGB);// TYPE_INT_RGB
		width = w;
		height = bufImg.getHeight(null) * w / bufImg.getWidth(null);// 按比例，将高度缩减
		if (height > h) {
			width = bufImg.getWidth(null) * h / bufImg.getHeight(null);// 按比例，将宽度缩减
			height = h;
		}
		wr = width * 1.0 / bufImg.getWidth();
		hr = height * 1.0 / bufImg.getHeight();
		AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(wr, hr), null);
		Itemp = ato.filter(bufImg, null);
		try {
			ImageIO.write((BufferedImage) Itemp, dest.substring(dest.lastIndexOf(".") + 1), destFile);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	/**
	 * 等比缩小图片到1000px以内 
	 * @param src
	 * @throws Exception
	 */
	public void zoomImageDbTo1300(String src) throws Exception {
		File srcFile = new File(src);
		BufferedImage bufImg = ImageIO.read(srcFile);
		if(bufImg.getWidth()>1300 || bufImg.getHeight()>1300){
			double wr = 0, hr = 0, width = 0, height = 0;
			String dest=src;
			File destFile = new File(dest);
			int w=1300, h=1300;
			// Image Itemp = bufImg.getScaledInstance(w, h, bufImg.SCALE_SMOOTH);//
			// SCALE_SMOOTH
			Image Itemp = bufImg.getScaledInstance(w, h, BufferedImage.TYPE_INT_RGB);// TYPE_INT_RGB
			width = w;
			height = bufImg.getHeight(null) * w / bufImg.getWidth(null);// 按比例，将高度缩减
			if (height > h) {
				width = bufImg.getWidth(null) * h / bufImg.getHeight(null);// 按比例，将宽度缩减
				height = h;
			}
			wr = width * 1.0 / bufImg.getWidth();
			hr = height * 1.0 / bufImg.getHeight();
			AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(wr, hr), null);
			Itemp = ato.filter(bufImg, null);
			try {
				ImageIO.write((BufferedImage) Itemp, dest.substring(dest.lastIndexOf(".") + 1), destFile);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	public void pngToJpg(String src, String dest, boolean isDeleteOld) {
		BufferedImage bufferedImage;
		try {
			File file = new File(src);
			// read image file
			bufferedImage = ImageIO.read(file);
			// create a blank, RGB, same width and height, and a white
			// background
			BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(),
					BufferedImage.TYPE_INT_RGB);
			newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);
			// write to jpeg file
			ImageIO.write(newBufferedImage, "jpg", new File(dest));
			if (isDeleteOld) {
				file.delete();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void markImageByWordToBottom(String word, String srcImgPath) {
		FileOutputStream out = null;
		try {
			Integer degree=20;
			File _file = new File(srcImgPath);
			Image src = ImageIO.read(_file);
			int wideth = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(wideth, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.drawImage(src, 0, 0, wideth, height, null);
			g.setColor(Color.WHITE);
			g.setFont(new Font("serif", Font.PLAIN, 50));
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.4f)); // 1.0f为透明度 ，值从0-1.0，依次变得不透明 
			int widethN=wideth/2-20,heightN= height/2-20;
			if (null != degree){
                // 设置水印旋转     
                g.rotate(Math.toRadians(degree),widethN,heightN);//设置水印旋转
            }
			g.drawString(word,widethN,heightN);
			g.dispose();
			out = new FileOutputStream(srcImgPath);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(image);
			out.close();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 给图片添加水印、可设置水印图片旋转角度
	 * 
	 * @param iconPath
	 *            水印图片路径
	 * @param srcImgPath
	 *            源图片路径
	 * @param targerPath
	 *            目标图片路径
	 * @param degree
	 *            水印图片旋转角度
	 */
	public static void markImageByIcon(String iconPath, String srcImgPath, String targerPath, Integer degree) {
		OutputStream os = null;
		try {
			Image srcImg = ImageIO.read(new File(srcImgPath));
			BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null), srcImg.getHeight(null),BufferedImage.TYPE_INT_RGB);
			// 得到画笔对象
			// Graphics g= buffImg.getGraphics();
			Graphics2D g = buffImg.createGraphics();
			// 设置对线段的锯齿状边缘处理
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg.getHeight(null), Image.SCALE_SMOOTH), 0,0, null);
			if (null != degree) {
				// 设置水印旋转
				g.rotate(Math.toRadians(degree), (double) buffImg.getWidth() / 2, (double) buffImg.getHeight() / 2);
			}
			// 水印图象的路径 水印一般为gif或者png的，这样可设置透明度
			ImageIcon imgIcon = new ImageIcon(iconPath);
			// 得到Image对象。
			Image img = imgIcon.getImage();
			float alpha = 0.2f; // 透明度
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
			// 表示水印图片的位置
			g.drawImage(img, 150, 300, null);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
			g.dispose();
			os = new FileOutputStream(targerPath);
			// 生成图片
			ImageIO.write(buffImg, "JPG", os);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != os) os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	//加不透明水印 居中
	public static void markImageByIconDpCt(String iconPath, String srcImgPath, String targerPath, Integer degree) {
		OutputStream os = null;
		try {
			Image srcImg = ImageIO.read(new File(srcImgPath));
			BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null), srcImg.getHeight(null),BufferedImage.TYPE_INT_RGB);
			// 得到画笔对象
			// Graphics g= buffImg.getGraphics();
			Graphics2D g = buffImg.createGraphics();
			// 设置对线段的锯齿状边缘处理
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg.getHeight(null), Image.SCALE_SMOOTH), 0,0, null);
			if (null != degree) {
				// 设置水印旋转
				g.rotate(Math.toRadians(degree), (double) buffImg.getWidth() / 2, (double) buffImg.getHeight() / 2);
			}
			// 水印图象的路径 水印一般为gif或者png的，这样可设置透明度
			ImageIcon imgIcon = new ImageIcon(iconPath);
			// 得到Image对象。
			Image img = imgIcon.getImage();
			int tw=buffImg.getWidth()/2 - imgIcon.getIconWidth()/2, th=buffImg.getHeight()/2 - imgIcon.getIconHeight()/2;
			float alpha = 1f; // 透明度
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
			// 表示水印图片的位置
			g.drawImage(img, tw, th, null);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
			g.dispose();
			os = new FileOutputStream(targerPath);
			// 生成图片
			ImageIO.write(buffImg, "JPG", os);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != os) os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		// System.out.println("writer suffer:");
		// String[] writerSuff = ImageIO.getWriterFileSuffixes();
		// for (int i = 0; i < writerSuff.length; i++)
		// System.out.print(writerSuff[i] + " ");
		// System.out.println("\nreader suffer:");
		// String[] readerSuff = ImageIO.getReaderFormatNames();
		// for (int i = 0; i < readerSuff.length; i++)
		// System.out.print(readerSuff[i] + " ");
		ImageHelper ih = ImageHelper.getInstance();
		try {
			// ih.zoomImageDb("E:/e65/1.png", "E:/e65/2.png", 800, 100);
			// ih.cutHalfImage("E:/e65/1.png", "E:/e65/2.png");
			// ih.markImageByWordToBottom("E:/e65/1.png", "E:/e65/1.png");
			// ih.zoomImageDb("E:/e65/1.png", "E:/e65/1_m.png", 500, 500);
			// ih.pngToJpg("E:/e65/1_m.png", "E:/e65/1_s.jpg",true);
			//ih.markImageByWordToBottom("AskDeer", "E:/e65/1_s.jpg");
			ih.markImageByIconDpCt("F:/video/bofang_tp.png","F:/video/test1.jpg", "F:/video/test55.jpg",null);
		} catch (Exception e) {
			e.printStackTrace();
		}
//	    File   file=new   File("D:/gai.jpg");   //指定文件名及路径  
//	    String name="123";     
//	    String   filename=file.getAbsolutePath();     
//	    if(filename.indexOf(".")>=0){     
//	           filename   =   filename.substring(0,filename.lastIndexOf("."));     
//	    }     
//	    file.renameTo(new   File(name+".jpg"));   //改名     
//		getFile("D:/apache-tomcat-front/webapps/e65_front/upload/20160628");
	}
	 private static void getFile(String path){   
	        // get file list where the path has   
	        File file = new File(path);   
	        // get the folder list   
	        File[] array = file.listFiles();   
	          
	        for(int i=0;i<array.length;i++){   
	            if(array[i].isFile()){   
	                // only take file name   
	            	String filename= array[i].getName();
	                System.out.println("^^^^^" +filename);   
	                // take file path and name   
	                //System.out.println("#####" + array[i]);   
	                // take file path and name   
	               // System.out.println("*****" + array[i].getPath());   
	            	if(filename.contains("jpgcropper")){
	            		filename = filename.replace("jpgcropper","jpg");
	            		array[i].renameTo(new File(filename));   //改名     
	            	}
	            }else if(array[i].isDirectory()){
	                getFile(array[i].getPath());   
	         }  
	    }   
	 }   
}
