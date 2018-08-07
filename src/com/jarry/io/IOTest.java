package com.jarry.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * Created by jarry on 2018/7/25.
 */
public class IOTest {
    public static void main(String[] args) throws IOException {

        File file = new File("");
        System.out.println(file.getAbsoluteFile());
        System.out.println(file.getCanonicalFile());
        System.out.println(file.getParentFile());
        System.out.println(file.getPath());

        File file2 = new File(".");
        System.out.println(file2.getAbsoluteFile());
        System.out.println(file2.getCanonicalFile());
        System.out.println(file2.getParentFile());
        System.out.println(file2.getPath());

        File file3 = new File("..");
        System.out.println(file3.getAbsoluteFile());
        System.out.println(file3.getCanonicalFile());
        System.out.println(file3.getParentFile());
        System.out.println(file3.getPath());

        File file4 = new File(".\\");
        System.out.println(file4.getAbsoluteFile());
        System.out.println(file4.getCanonicalFile());
        System.out.println(file4.getParentFile());
        System.out.println(file4.getPath());

        //文件输入输出流
       /* File file5 = new File("src/com/jarry/io/a.txt");
        if(!file5.exists()){
            file5.createNewFile();
            System.out.println("创建");
        }

        try (
            FileInputStream fis = new FileInputStream(file5);
            FileOutputStream fos = new FileOutputStream(new File("src/com/jarry/io/a1.txt"));

        ){

            int a = 0;
            while( (a = fis.available()) != 0){
                int read = fis.read();
                fos.write(read);
            }

        }*/


        //字节数据输入输出流
        String str = "嘻嘻哈哈a";
        try (
                ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes("utf-8"));
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ) {
            //utf-8每个汉字占3个字节
            System.out.println(bais.available());

            /*
            //输入流
            byte[] bb = new byte[6];
            bais.read(bb);
            //baos.write(bb);
            ByteBuffer buffer = ByteBuffer.allocate(bb.length);
            buffer.put(bb);
            buffer.flip();

            //定义编码
            Charset cs = Charset.forName ("UTF-8");
            CharBuffer cb = cs.decode(buffer);
            //CharBuffer charBuffer = buffer.asCharBuffer();
            char[] array = cb.array();
            for(int i=0; i<cb.limit(); i++){
                System.out.println(array[i]);
            }*/
            int a = 0;
            while( (a = bais.read()) != -1){
                baos.write(a);
            }

            System.out.println(baos.toString());
        }


    }
}
