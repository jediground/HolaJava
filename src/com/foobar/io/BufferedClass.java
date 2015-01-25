package com.foobar.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;

import org.junit.Test;

public class BufferedClass {

    public BufferedClass() {
    }

    @Test
    public void testBufferedInputAndOutputStream() {
        File inFile = new File("Objc.md");
        File outFile = new File("ObjcCp.md");

        FileInputStream fis = null;
        FileOutputStream fos = null;

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            fis = new FileInputStream(inFile);
            fos = new FileOutputStream(outFile);

            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

            byte[] by = new byte[24];
            int len;
            for (; -1 != (len = bis.read(by));) {
                bos.write(by, 0, len);
                bos.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testBufferedInputAndOutputStream2() {
        BufferedReader br = null;
        BufferedWriter bw = null;

        try {
            br = new BufferedReader(new FileReader(new File("Objc.md")));
            bw = new BufferedWriter(
                    new FileWriter(new File("Objc_readLine.md")));

            String line;
            for (; null != (line = br.readLine());) {
                bw.write(line);
                bw.newLine();
                bw.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
