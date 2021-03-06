package com.payudon.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil {
	public static final Logger logger = LoggerFactory.getLogger(FileUtil.class);
	
	public static String writeString(String msg,String path) {
		PrintWriter out = null;
		try {
			File file = new File(path);
			if(file.isDirectory()) {
				return "文件写出路径有误!";
			}
			if(!file.exists()) {
				file.createNewFile();
			}
			out = new PrintWriter(path);
			out.write(msg);
			out.flush();
		} catch (FileNotFoundException e) {
			logger.error("写出文件失败",e);
		} catch (IOException e) {
			logger.error("写出文件失败",e);
		}finally{
			if(out!=null) {
				out.close();
			}
		}
		return null;
	}
    public static byte[] readFile(File file) throws IOException {
        FileInputStream input = new FileInputStream(file);
        byte[] inData = new byte[(int) file.length()];
        while (input.read(inData) != -1) {
        }
        input.close();
        return inData;
    }
	public static String copyFile(InputStream in,String outPaht,String fileName) {
		File file = new File(outPaht);
		if(!file.exists()) {
			file.mkdirs();
		}
		File outFile = new File(outPaht+File.separatorChar+fileName);
		try {
			wirteFile(in, new FileOutputStream(outFile));
	        return outFile.getPath();
		} catch (IOException e) {
			logger.error("复制文件错误:",e);
		}
		return "";
	}
	public static void copyFile(String inPath,String outPaht,String fileName) {
		 File file = new File(inPath);
		 File outFile = new File(outPaht+File.separatorChar+fileName);
		 try {
			InputStream in = new FileInputStream(file);
			wirteFile(in, new FileOutputStream(outFile));
		} catch (IOException e) {
			logger.error("复制文件错误:",e);
		}
	}
	public static void wirteFile(InputStream in,OutputStream out) {
        wirteFile (in,out,true);
	}
    public static void wirteFile(InputStream in, OutputStream out, boolean isClosed) {
        byte[] b = new byte[1024];
        int len;
        try {
            while ((len = in.read(b)) != -1) {
                out.write(b, 0, len);
            }
            out.flush();
        } catch (IOException e) {
            logger.error("复制文件出错：" + e);
        } finally {
            if (isClosed) {
                try {
                    if (out != null) {
                        out.close();
                    }
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }

    }
	public static boolean deleteFile(File file) {
		if (file.exists() && file.isFile()) {
			if(file.delete()) {
				return true;
			}else {
				return false;
			}
		}
		return false;
	}
	
	public static boolean deleteDirectory(File dir) {
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dir.exists()) || (!dir.isDirectory())) {
        	logger.debug("删除目录失败：" + dir + "不存在！");
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
        	  // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i]);
                if (!flag)
                    break;
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = deleteDirectory(files[i]);
                if (!flag)
                    break;
            }
        }
        if (!flag) {
        	logger.debug("删除目录失败！");
            return false;
        }
        // 删除当前目录
        if (dir.delete()) {
        	logger.debug("删除目录" + dir + "成功！");
            return true;
        } else {
        	logger.debug("删除目录失败！");
            return false;
        }
	}
	public static boolean delete(String filePath) {
		File file = new File(filePath);
		if(!file.exists()) {
			return false;
		}
		if(file.isFile()) {
			return deleteFile(file);
		}else{
			return deleteDirectory(file);
		}
	}
}
