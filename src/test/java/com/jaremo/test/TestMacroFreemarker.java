package com.jaremo.test;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class TestMacroFreemarker {

    @Test
    public void case1(){
        /*
          第一步,创建configuration实例
         */
        //Configuration 实例是存储 FreeMarker 应用级设置的核心部分
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23); // freemarker的配置,设置freemarker的版本

        try{
            cfg.setDirectoryForTemplateLoading(new File("E:\\ideacode\\test_freemarker\\src\\main\\webapp\\template")); // 模板存放地址
        }catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }
        cfg.setDefaultEncoding("UTF-8"); // 设置编码
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        /*
           第二步,创建数据模型
         */
        Map<String, Object> root = new HashMap<>();

        /*
            第三步,获取模板
         */
        Writer out = null;
        try{
            Template temp = cfg.getTemplate("macro.ftl");
            out = new OutputStreamWriter(System.out);
            temp.process(root, out);
            out.flush();
        }catch (TemplateException e){
            throw new RuntimeException(e.getMessage());
        }catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }finally {
            try{
                out.close();
            }catch (IOException e){
                throw new RuntimeException(e.getMessage());
            }
        }
    }
}
