package com.common.util;

import java.io.File;
import java.util.List;

public class VideoUtil {
	private static final VideoUtil instance = new VideoUtil();
	private static String ffmpeg_path=null, ffmpeg_video_path=null;
	public static VideoUtil getInstance() {
		if(ffmpeg_path==null)
			ffmpeg_path=PropertiesConfigUtil.getInstance().getValue("ffmpeg.path");//获取绝对路径
		if(ffmpeg_video_path==null)
			ffmpeg_video_path=PropertiesConfigUtil.getInstance().getValue("front.media.dir")+"/default/bofang_tp.png";//获取绝对路径
		return instance;
	}
	//win下获取ffmpeg路径  
	//private static String path = ServletActionContext.getServletContext().getRealPath("/WEB-INF/classes/ffmpeg.exe");
	// linux路径 
	//private String ffmpeg_path="/usr/local/bin/ffmpeg";
	//private static String ffmpeg_path = "F:/video/ffmpeg.exe";
	/**
	 * 对视频截图
	 * @param veido_path
	 * @return
	 */
	public boolean processImg(String veido_path) {
		File file = new File(veido_path);
		if (!file.exists()) {
			System.err.println("路径[" + veido_path + "]对应的视频文件不存在!");
			return false;
		}
		String full_path=null;
		List<String> commands = new java.util.ArrayList<String>();
		commands.add(ffmpeg_path);
		commands.add("-i");
		commands.add(veido_path);
		commands.add("-y");
		commands.add("-f");
		commands.add("image2");
		commands.add("-ss");
		commands.add("1");// 这个参数是设置截取视频多少秒时的画面
		//commands.add("-t");
		//commands.add("0.001");
		commands.add("-s");
		commands.add("250x188");//300x225
		full_path=veido_path.substring(0, veido_path.lastIndexOf(".")) + ".jpg";
		commands.add(full_path);
		try {
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(commands);
			builder.start();
			//System.out.println("截取成功");
			//ImageHelper.markImageByIconDpCt(ffmpeg_video_path,full_path, full_path, null);
			(new Thread(new RunMark(full_path))).start();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	//加水印
	class RunMark implements Runnable{
		String full_path=null;
		public RunMark(String full_path){
			this.full_path=full_path;
		}
		public void run() {
			int i=0;
			while(true){
				try {Thread.sleep(1000*2);} catch (InterruptedException e){e.printStackTrace();}
				File f=new File(full_path);
				if(f.exists()&&f.length()>3000){
					ImageHelper.markImageByIconDpCt(ffmpeg_video_path,full_path, full_path, null);
					break;
				}
				if(i>10) break;
				i++;
			}
		}
	}
	
	public static void main(String[] args) {
		try {
			// randomGrabberFFmpegImage("F:/video/test1.mp4", "F:/video/",
			// "test1_1", 10);
			//videoCv("F:/video/test1.mp4", "F:/video/test1_2.mp4");
			getInstance().processImg("F:/video/test1.mp4");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}