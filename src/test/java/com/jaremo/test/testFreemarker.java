package com.jaremo.test;

import freemarker.template.*;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class testFreemarker {

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
        Map<String, Object> latest = new HashMap<>();
        latest.put("url", "products/greenmouse.html");
        latest.put("name", "green mouse");

        Map<String, Object> root = new HashMap<>();
        root.put("user", "Joe");
        root.put("latestProduct", latest);

        // 测试if的用例(由于freemarker属于java的一部分,同样遵循Java的语法规则,即不同类型不能相互比较,不能等于)
        root.put("sex",3);
        // 测试switch的用例
        root.put("class","1705");
        // 测试list的用例
        String[] strs = new String[]{"1","2","3","4","5","6"};
        root.put("strs",strs);
        // 测试实体类和java集合
        List<TestBean> userList = new ArrayList<>();
        TestBean tb = new TestBean();
        tb.setAge(19);
        tb.setName("pyj");
        userList.add(tb);
        TestBean tb1 = new TestBean();
        tb1.setName("wlp");
        tb1.setAge(18);
        userList.add(tb1);
        root.put("tb",tb);
        root.put("userList",userList);

        /*
            第三步,获取模板
         */
        Writer out = null;
        try{
            Template temp = cfg.getTemplate("test.ftl");
            // 数据模型+模板=输出，我们有了一个数据模型 (root) 和一个模板 (temp)， 为了得到输出就需要合并它们。这是由模板的 process 方法完成的
            out = new OutputStreamWriter(new FileOutputStream(new File("E:\\ideacode\\test_freemarker\\src\\main\\webapp\\template\\html\\","index.html")));
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
