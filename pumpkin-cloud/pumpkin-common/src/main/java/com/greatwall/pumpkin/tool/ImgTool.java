package com.greatwall.pumpkin.tool;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import lombok.extern.slf4j.Slf4j;

/**
 * @author NieQing
 * 
 */
@Slf4j
public class ImgTool {

    // 对图片裁剪，并把裁剪新图片保存

    /**
     * 裁剪图片
     * 
     * @param srcPath
     * @param destPath
     * @param x
     * @param y
     * @param width
     * @param height
     * @param readImageFormat
     * @param writeImageFormat
     * @return
     * @throws IOException
     */
    public static boolean cropImage(String srcPath, String destPath, int x, int y, int width, int height,
        String readImageFormat, String writeImageFormat) throws IOException {
        boolean flag = true;
        FileInputStream fis = null;
        ImageInputStream iis = null;
        try {
            fis = new FileInputStream(srcPath);
            Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(readImageFormat);
            ImageReader reader = (ImageReader)it.next();
            iis = ImageIO.createImageInputStream(fis);
            reader.setInput(iis, true);
            ImageReadParam param = reader.getDefaultReadParam();
            Rectangle rect = new Rectangle(x, y, width, height);
            param.setSourceRegion(rect);
            BufferedImage bi = reader.read(0, param);
            ImageIO.write(bi, writeImageFormat, new File(destPath));
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (iis != null) {
                iis.close();

            }
        }
        if (!(new File(destPath).exists())) {
            log.info("crop fail!!");
            flag = false;
        } else {
            log.info("crop success!!");
        }
        return flag;
    }

    /**
     * 重置图形的边长大小
     * 
     * @param srcImagePath
     *            源图像路径
     * @param destImagePath
     *            目标图像路径
     * @param width
     *            重置的宽
     * @param height
     *            重置的高
     * @throws IOException
     */
    public static boolean resizeImage(String srcImagePath, String destImagePath, int width, int height)
        throws IOException {
        boolean flag = true;
        FileOutputStream out = null;
        try {
            File file = new File(srcImagePath);
            BufferedImage src = ImageIO.read(file);
            Image from = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D g2d = tag.createGraphics();
            tag = g2d.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
            g2d.dispose();
            g2d = tag.createGraphics();
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
            g2d.drawImage(from, 0, 0, null);

            g2d.dispose();
            out = new FileOutputStream(destImagePath);
            ImageIO.write(tag, "png", new File(destImagePath));
        } catch (Exception e) {
            log.error("ResizeImage Exception", e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
        if (!(new File(destImagePath).exists())) {
            log.info("convert fail!!");
            flag = false;
        } else {
            log.info("convert success!!");
        }
        return flag;
    }

    /**
     * 图片灰化操作
     * 
     * @param srcImage
     *            读取图片路径
     * @param destPath
     *            写入灰化后的图片路径
     * @param imageFormat
     *            图片写入格式
     */
    public static boolean grayImage(String srcImage, String destPath, String imageFormat) {
        boolean flag = true;
        try {
            BufferedImage src = ImageIO.read(new File(srcImage));
            ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
            ColorConvertOp op = new ColorConvertOp(cs, null);
            src = op.filter(src, null);
            ImageIO.write(src, imageFormat, new File(destPath));
        } catch (Exception e) {
            log.error("GrayImage Exception", e);
        }
        if (!(new File(destPath).exists())) {
            log.info("gray fail!!");
            flag = false;
        } else {
            log.info("gray success!!");
        }
        return flag;
    }

    /**
     * 保存网络图片到文件
     * 
     * @param urlAddress
     * @param fileAddress
     * @throws Exception
     */
    public static void saveUrlImageToFile(String urlAddress, String fileAddress) throws Exception {
        // new一个URL对象
        URL url = new URL(urlAddress);
        // 打开链接
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        // 设置请求方式为"GET"
        conn.setRequestMethod("GET");
        // 超时响应时间为5秒
        conn.setConnectTimeout(5 * 1000);
        // 通过输入流获取图片数据
        InputStream inStream = conn.getInputStream();
        // 得到图片的二进制数据，以二进制封装得到数据，具有通用性
        byte[] data = readInputStream(inStream);
        // new一个文件对象用来保存图片，默认保存当前工程根目录
        File imageFile = new File(fileAddress);
        // 创建输出流
        FileOutputStream outStream = new FileOutputStream(imageFile);
        // 写入数据
        outStream.write(data);
        // 关闭输出流
        outStream.close();
    }

    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        // 创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        // 每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        // 使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            // 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        // 关闭输入流
        inStream.close();
        // 把outStream里的数据写入内存
        return outStream.toByteArray();
    }
}
