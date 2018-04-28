package com.common.util;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;


public class FileUtilMy {
	private static FileUtilMy instance = new FileUtilMy();
	private static int widthM=0,heightM=0, widthS=0, heightS=0;
	private static String MarkWatermarkByWord=null;
	String png=".png", jpg=".jpg", mJpg="_m.jpg", sJpg="_s.jpg", X="/";
	public static FileUtilMy getInstance(){
		return instance;
	}
	/**
	 * 根据当前时间，自动获取存储路径
	 * @return
	 */
	public File getSaveFile(String realPath,String oldName){
		String filep = DateUtil.getTodayStr();
		realPath = realPath+X+filep;
		File file = new File(realPath);
		if(!file.exists()){
			file.mkdirs();
		}
		oldName = oldName.substring(oldName.lastIndexOf("."));
		realPath += X+DateUtil.getTimeStr()+oldName;
		return new File(realPath);
	}
	public static void main(String[] args){
//		File file = getSaveFile("D:/install/PLSQL","sfasdf.jpg");
//		System.out.println(file.getPath());
	}
	String realPath = null;
	/**
	 * 文件上传
	 * @param req
	 * @param attach
	 * @return
	 */
	public String saveFile(MultipartFile attach){
		if(realPath==null)
			realPath = PropertiesConfigUtil.getInstance().getValue("front.media.dir");//获取绝对路径
		if (!attach.isEmpty()) {// 判断文件是否为空
//			File file = new File(realPath + X + attach.getOriginalFilename());
			String oldName =  attach.getOriginalFilename();
			String todayStr =  DateUtil.getTodayStr();
			String filep = realPath+X+todayStr;
			File file = new File(filep);
			if(!file.exists()){
				file.mkdirs();
			}
			String newName = X+DateUtil.getTimeStr()+oldName.substring(oldName.lastIndexOf("."));
			file = new File(filep+newName);
			try {
				attach.transferTo(file);// 直接调用spring 框架MultipartFile对象实现文件上传
				return Configuration.UploadPath+todayStr +newName;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 文件上传 自动识别，如果是图片：裁剪小图 压缩图片 加水印
	 * @param req
	 * @param attach
	 * @return
	 */
	public String saveFileAtuo(MultipartFile attach){
		if(attach==null) return null;
		if (!attach.isEmpty()) {// 判断文件是否为空
			String n= attach.getOriginalFilename().toLowerCase();
			if(n.endsWith(".jpg")||n.endsWith(".png")||n.endsWith(".jpeg")){
				return saveFileImg(attach);
			}else if(n.endsWith(".mp4")||n.endsWith(".3gp")){
				return saveVideoFile(attach);
			}else{
				return saveFile(attach);
			}
		}
		return null;
	}
	/**
	 * 视频文件上传，包括裁剪图片
	 * @param req
	 * @param attach
	 * @return
	 */
	public String saveVideoFile(MultipartFile attach){
		if(realPath==null)
			realPath = PropertiesConfigUtil.getInstance().getValue("front.media.dir");//获取绝对路径
		if (!attach.isEmpty()) {// 判断文件是否为空
//			File file = new File(realPath + X + attach.getOriginalFilename());
			String oldName =  attach.getOriginalFilename();
			String todayStr =  DateUtil.getTodayStr();
			String filep = realPath+X+todayStr;
			File file = new File(filep);
			if(!file.exists()){
				file.mkdirs();
			}
			String newName = X+DateUtil.getTimeStr()+oldName.substring(oldName.lastIndexOf("."));
			String fileFullPath=filep+newName;//完整路径
			file = new File(fileFullPath);
			try {
				attach.transferTo(file);// 直接调用spring 框架MultipartFile对象实现文件上传
			} catch (Exception e) {
				e.printStackTrace();
			}
			VideoUtil.getInstance().processImg(fileFullPath);
			return Configuration.UploadPath+todayStr +newName;
		}
		return null;
	}
	/**
	 * 文件上传 大 中  小 图 ，没有加加水印
	 * @param req
	 * @param attach
	 * @return
	 */
	public String saveFileImgNoWater(MultipartFile attach){
		return saveFileImg(attach,null,false);
	}
	/**
	 * 文件上传 大 中  小 图 ，并且加水印
	 * @param req
	 * @param attach
	 * @return
	 */
	public String saveFileImg(MultipartFile attach){
		return saveFileImg(attach,null,true);
	}
	public String saveFileImg(MultipartFile attach,String markWatermark,boolean isAddWater){
		if(realPath==null)
			realPath = PropertiesConfigUtil.getInstance().getValue("front.media.dir");//获取绝对路径
		initImgs();
		if(markWatermark==null) markWatermark=MarkWatermarkByWord;
		if (!attach.isEmpty()) {// 判断文件是否为空
//			File file = new File(realPath + X + attach.getOriginalFilename());
			String oldName =  attach.getOriginalFilename();
			String todayStr =  DateUtil.getTodayStr();
			String filep = realPath+X+todayStr;
			File file = new File(filep);
			if(!file.exists()){
				file.mkdirs();
			}
			filep+=X;
			String endName=oldName.substring(oldName.lastIndexOf("."));
			String newName = DateUtil.getTimeStr()+endName;
			String fullPathName=filep+newName;
			file = new File(fullPathName);
			try {
				attach.transferTo(file);// 直接调用spring 框架MultipartFile对象实现文件上传
				ImageHelper ih = ImageHelper.getInstance();
				if(fullPathName.endsWith(png)){
					String newFullPathName=fullPathName.replace(png, jpg);
					ih.pngToJpg(fullPathName, newFullPathName,true);
					fullPathName=newFullPathName;
					newName=newName.replace(png, jpg);
				}
				ih.zoomImageDbTo1300(fullPathName);//压缩图片
				ih.zoomImageDb(fullPathName, fullPathName+mJpg, widthM, heightM);//中图
				ih.zoomImageDb(fullPathName, fullPathName+sJpg, widthS, heightS);//小图
				if(isAddWater)
					ih.markImageByWordToBottom(markWatermark,fullPathName);//加水印
				return Configuration.UploadPath +todayStr+X +newName;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	private void initImgs(){
		if(MarkWatermarkByWord==null){
			SysConfigService scs = (SysConfigService)ContextUtil.getBean("sysConfigService");
			String vale[] = scs.getValue("productImgMidSize").split(",");
			String[] ms=vale[0].split("x"), ss=vale[1].split("x");
			heightM=Integer.parseInt(ms[0]);widthM=Integer.parseInt(ms[1]);
			heightS=Integer.parseInt(ss[0]);widthS=Integer.parseInt(ss[1]);
			MarkWatermarkByWord=scs.getValue("MarkWatermarkByWord");
		}
	}
    /**
     * 删除单个文件 
     * @param   sPath    被删除文件的文件名 
     */  
    public void deleteFile(String sPath){  
    	File file = new File(sPath);  
        // 路径为文件且不为空则进行删除  
        if (file.isFile() && file.exists()) {  
            file.delete();
        }
    }
}
