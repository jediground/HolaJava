package com.foobar.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

public class FileInputOutputStreamClass {

    public FileInputOutputStreamClass() {
    }

    @Test
    public void testFileInputStream() throws IOException {
        FileInputStream fis = new FileInputStream(new File("/Users/Moch/.vimrc"));

        int b;
        for (; -1 != (b = fis.read());) {
            System.out.print((char) b);
        }

        fis.close();
    }

    @Test
    public void testFileInputStream2() throws IOException {
        FileInputStream fis = new FileInputStream(
                new File("/Users/Moch/.vimrc"));

        byte[] by = new byte[5];
        int len;
        for (; -1 != (len = fis.read(by));) {
            for (int i = 0; i < len; i++) {
                System.out.print((char) by[i]);
            }
        }

        fis.close();
    }

    @Test
    public void testFileOutputStream() {
        File file = new File("HolaJava.txt");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(new String("Hola Java ...").getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != fos) {
                try {
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testFileOutputStream2() {
        File inputFile = new File("/Users/Moch/.vimrc");
        File outFile = new File("HolaVimrc.txt");

        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream(inputFile);
            fos = new FileOutputStream(outFile);

            byte[] by = new byte[20];
            int len;
            for (; -1 != (len = fis.read(by));) {
                fos.write(by, 0, len);
                ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != fos) {
                try {
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (null != fis) {
                try {
                    fis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
