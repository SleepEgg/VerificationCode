package com.big3.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class codeUtil {
    //    定义图片的width
    private static int width = 90;
    //    定义图片的height
    private static int height = 20;
    //    定义图片的验证码长度
    private static int codeCount = 5;
    private static int xx =15;
    private static int fontHeight = 18;
    private static int codeY = 16;
    private static char[] codeSequence = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G',
            'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z',
            '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9'};

    /**
     *生成集合
     *生成的验证码：code
     * 生成的image对象： codePic
     */
    public static Map<String,Object> generateCodeAndPic(){
//        定义图像buffer 8 位 RGB 颜色分量
        BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
//        绘制图像
        Graphics graphics = bufferedImage.getGraphics();
//        生成随机数
        Random random = new Random();
//        填充图像颜色
        graphics.setColor(Color.WHITE);
//        填充指定的矩形
        graphics.fillRect(0,0,width,height);
//        显示字体
        Font font = new Font("Consolas",Font.BOLD,fontHeight);
//        设置字体
        graphics.setFont(font);
//        验证码边框
        graphics.setColor(Color.BLACK);
        graphics.drawRect(0,0,width-1,height-1);

//        随机生产干扰图像
        graphics.setColor(Color.BLACK);
        for(int i=0;i<20;i++){
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int z = random.nextInt(12);
            int w = random.nextInt(12);
            graphics.drawLine(x,y,x+z,y+w);
        }

//        保存随机产生的验证码
        StringBuffer randomCode = new StringBuffer();
        int red = 0,green=0,blue=0;

//        随机产生验证码
        for(int i=0;i<codeCount;i++){
//            获取随机产生的验证码
            String code = String.valueOf(codeSequence[random.nextInt(36)]);
//            随机产生颜色
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
//            将随机产生的颜色绘制到图中
            graphics.setColor(new Color(red,green,blue));
            graphics.drawString(code,(i+1)*xx,codeY);
//            组合随机数
            randomCode.append(code);
        }

        Map<String,Object> map = new HashMap<String,Object>();
//            存放验证码
            map.put("code",randomCode);
//            存放验证码图像
            map.put("codeImage",bufferedImage);
        return map;
    }

    public static void main(String[] args) {
//        输出
        try {
            OutputStream out = new FileOutputStream("生成验证码图片路径"+"验证码名称");
            Map<String,Object> map = codeUtil.generateCodeAndPic();
            ImageIO.write((RenderedImage)map.get("codeImage"),"jpg",out);
            System.out.println("验证码为："+map.get("code"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
