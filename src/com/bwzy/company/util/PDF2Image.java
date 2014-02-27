package com.bwzy.company.util;


import com.bwzy.mag.proces.config.WirelessConfig;
import com.bwzy.mag.proces.util.FileUtil;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: LVYANGJUN
 * Date: 12-8-10
 * Time: 下午3:46
 * To change this template use File | Settings | File Templates.
 */
public class PDF2Image {
    /**
     *  将PDF直接转为图片，并返回图片访问地址
     * @param pdfPath
     * @param imagepath
     * @param fileName
     * @return
     * @throws IOException
     */
    public List<Object> pdf2Image(String pdfPath,String imagepath,String fileName) throws IOException {
        File file = new File( pdfPath);
        RandomAccessFile raf = new RandomAccessFile(file, "r");
        FileChannel channel = raf.getChannel();
        ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
        PDFFile pdffile = new PDFFile(buf);
        List<Object> list=new ArrayList<Object>();
        for (int i = 1; i <= pdffile.getNumPages(); i++) {
            // draw the first page to an image
            PDFPage page = pdffile.getPage(i);

            // get the width and height for the doc at the default zoom
            Rectangle rect = new Rectangle(0, 0, (int) page.getBBox()
                    .getWidth(), (int) page.getBBox().getHeight());

            // generate the image
            Image img = page.getImage(rect.width, rect.height, // width &
                    // height
                    rect, // clip rect
                    null, // null for the ImageObserver
                    true, // fill background with white
                    true // block until drawing is done
            );

            BufferedImage tag = new BufferedImage(rect.width, rect.height,
                    BufferedImage.TYPE_INT_RGB);
            tag.getGraphics().drawImage(img, 0, 0, rect.width, rect.height,
                    null);

            FileUtil.checkfolder(imagepath);
            FileOutputStream out = new FileOutputStream(
                    imagepath+"images"+i+".jpg"); // 输出到文件流

            Map<String,Object> map =new HashMap<String,Object>();
            String requestImagesURL= FilePathConfigUtil.getValue("request.images.url");
            map.put("imagesURL",requestImagesURL+"images"+i+".jpg");
            list.add(map);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(tag); // JPEG编码
            out.close();
        }

        return list;

    }
}
