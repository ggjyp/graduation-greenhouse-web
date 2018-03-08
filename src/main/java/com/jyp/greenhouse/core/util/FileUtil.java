package com.jyp.greenhouse.core.util;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by wjh on 2016/3/19.
 */
public class FileUtil {


    //     重写写入
    public static void rewrite(File file, String data) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file));
            bw.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //    列出文件中的内容
    public static java.util.List<String> readList(File file) {
        BufferedReader br = null;
        java.util.List<String> data = new ArrayList<String>();
        try {
            br = new BufferedReader(new FileReader(file));
            for (String str = null; (str = br.readLine()) != null; ) {
                data.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
    }

    //    上传文件
    public static String uploadfile(String logoPathDir, MultipartFile multipartFile, HttpServletRequest request) {
        /** 得到文件保存目录的真实路径* */
        String uploadFileName=null;
        try {
            String logoRealPathDir = request.getSession().getServletContext()
                    .getRealPath(logoPathDir);
            /** 根据真实路径创建目录* */
            File logoSaveFile = new File(logoRealPathDir);
            if (!logoSaveFile.exists())
                logoSaveFile.mkdirs();
            /** 获取文件的后缀* */
            String suffix = multipartFile.getOriginalFilename().substring(
                    multipartFile.getOriginalFilename().lastIndexOf("."));
            // 构建文件名称
            String filename = FileUtil.getRandFileName() + suffix;
            /** 拼成完整的文件保存路径加文件* */
            String fileName = logoRealPathDir + File.separator + filename;
            uploadFileName = logoPathDir + File.separator + filename;
            uploadFileName = uploadFileName.replaceAll("\\\\", "/");
            multipartFile.transferTo(new File(fileName));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return uploadFileName;
    }

    /**
     * 上传base64编码图像
     * @param logoPathDir
     * @param base64 图像的base64编码
     * @param request
     * @return 图像在服务器的路径
     */
    public static String uploadBase64Img(String logoPathDir, String base64, HttpServletRequest request) {
        /** 得到文件保存目录的真实路径* */
        String uploadFileName=null;
        try {
            String logoRealPathDir = request.getSession().getServletContext()
                    .getRealPath(logoPathDir);
            /** 根据真实路径创建目录* */
            File logoSaveFile = new File(logoRealPathDir);
            if (!logoSaveFile.exists())
                logoSaveFile.mkdirs();
            // 构建文件名称
            String filename = FileUtil.getRandFileName()+".jpg";
            /** 拼成完整的文件保存路径加文件* */
            String filePath = logoRealPathDir + File.separator + filename;
            uploadFileName = logoPathDir + File.separator + filename;
            uploadFileName = uploadFileName.replaceAll("\\\\", "/");
            //保存至
            Base64Util.base64ToImage(base64, filePath);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return uploadFileName;
    }

    /**
     * 判断指定格式是否为图片
     *
     * @param ext
     * @return
     */
    public static boolean isImageExt(String ext) {
        return ext != null && Arrays.asList("jpg", "jpeg", "png", "bmp", "gif").contains(ext.toLowerCase());
    }

    /**
     * 获取文件名后缀(不含".")
     *
     * @param filename
     * @return
     */
    public static String getFileExt(String filename) {
        int point = filename.lastIndexOf(".");
        return filename.substring(point + 1);
    }

    /**
     * 获取文件名(不包含后缀)
     *
     * @param filename
     * @return
     */
    public static String getFileName(String filename) {
        filename = getFileNameWithExt(filename);
        int point = filename.lastIndexOf(".");
        return filename.substring(0, point);
    }

    /**
     * 获取文件名(包含后缀)
     *
     * @param filename
     * @return
     */
    public static String getFileNameWithExt(String filename) {
        int slash = filename.lastIndexOf("/");
        return filename.substring(slash + 1);
    }

    //      构建上传目录
    public static String getuploaddir(String path) {
        /** 构建文件保存的目录* */
        String logoPathDir = File.separator + "upload" + File.separator + path;
        return logoPathDir;

    }

    //得到随机上传文件名（不含后缀）
    public static String getRandFileName() {
        String now = TimeUtil.getNowTimestamp() + "";
        String r = StringUtil.getRandChar(6);
        return r + now;
    }

    //    获取目录下所有文件
    public static String[] getFileNames(String path) {
        File file = new File(path);
        String[] fileName = file.list();
        return fileName;
    }

    //    获取目录下所有文件---递归获取
    public static ArrayList<String> getAllFileName(String path, ArrayList<String> fileName) {
        File file = new File(path);
        File[] files = file.listFiles();
        String[] names = file.list();
        if (names != null)
            fileName.addAll(Arrays.asList(names));
        for (File a : files) {
            if (a.isDirectory()) {
                getAllFileName(a.getAbsolutePath(), fileName);
            }
        }
        return fileName;
    }

    //    压缩图片
    public static void reduceImg(String imgsrc, String imgdist, String filename) throws Exception {
        File srcfile = new File(imgsrc);
        // 检查文件是否存在
        if (!srcfile.exists()) {
            return;
        }
        File file = new File(imgdist);
        if (!file.exists())
            file.mkdir();
        int widthdist, heightdist;

        // 获取文件高度和宽度
        int[] results = getImgWidth(srcfile);
        if (results == null || results[0] == 0 || results[1] == 0) {
            return;
        } else {
            double rate = 0;
            if (results[0] > 300) {
                rate = 300.f / results[0];
            } else {
                rate = 1;
            }
            widthdist = (int) (results[0] * rate);
            heightdist = (int) (results[1] * rate);
        }

        // 开始读取文件并进行压缩
        Image src = javax.imageio.ImageIO.read(srcfile);
        BufferedImage tag = new BufferedImage((int) widthdist,
                (int) heightdist, BufferedImage.TYPE_INT_RGB);
        tag.getGraphics().drawImage(
                src.getScaledInstance(widthdist, heightdist,
                        Image.SCALE_SMOOTH), 0, 0, null);
        File targetfile = new File(imgdist + "\\" + filename);
        FileOutputStream out = new FileOutputStream(targetfile);


        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(tag);
        param.setQuality(100f, true);
        encoder.encode(tag, param);
        out.close();
    }

    //    获取文件大小
    public static int[] getImgWidth(File file) throws Exception {
        InputStream is = null;
        BufferedImage src = null;
        int result[] = {0, 0};

        is = new FileInputStream(file);
        src = javax.imageio.ImageIO.read(is);
        result[0] = src.getWidth(null); // 得到源图宽
        result[1] = src.getHeight(null); // 得到源图高
        is.close();
        return result;
    }

    public static void main(String[] args) throws Exception {
        String str="/upload\\material/sound/doc\\MRcXHZ1491982872.txt";
        System.out.println(str.replaceAll("\\\\","/"));
    }


}
