package com.bwzy.company.service.impl;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.util.List;

import net.sf.json.JSONArray;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.bwzy.company.service.ParserAttachmentService;
import com.bwzy.company.util.PDF2Image;

/**
 * Created with IntelliJ IDEA.
 * User: lvyangjun
 * Date: 13-8-6
 * Time: 下午2:21
 * To change this template use File | Settings | File Templates.
 */
public class ParserAttachmentServiceImpl implements ParserAttachmentService{
    /**
     * 将附件解析为图片
     *
     * @param inputPath    附件路径
     * @param inputFileType       附件文件类型
     * @param fileName             附件名
     * @return   图片范文地址
     */
    @Override
    public String parserAttacchment(String inputPath,String inputFileType,String fileName) {
        String responseStr="";
        String outputPath="";
        if(!inputFileType.equals("pdf")){
            //下载的文件
            File inputFile=new File(inputPath);
            //要转换的PDF格式的文件名
            outputPath=inputPath.substring(0,inputPath.lastIndexOf("\\")+1)+fileName+".pdf";

            //保存在本地的PDF文件
            File outputFile=new File(outputPath);
            OpenOfficeConnection connection = null;
            connection = new SocketOpenOfficeConnection(8100);
            try {
                connection.connect();
            } catch (ConnectException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
            converter.convert(inputFile,outputFile);
            connection.disconnect();
        }else{
            inputPath=outputPath;
        }
        if(!outputPath.equals("")){
            //在将pdf转成图片
            PDF2Image pdf2Image= new PDF2Image();
            List<Object> list= null;
            String imagesPath=inputPath.substring(0,inputPath.lastIndexOf("\\")+1);

            try {
                list = pdf2Image.pdf2Image(outputPath,imagesPath,fileName);
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            responseStr= JSONArray.fromObject(list).toString();
        }
        return responseStr;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
