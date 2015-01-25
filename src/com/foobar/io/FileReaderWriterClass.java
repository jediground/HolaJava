package com.foobar.io;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

public class FileReaderWriterClass {

    public FileReaderWriterClass() {
    }

    @Test
    public void testFileReader() {
        File file = new File("/Users/Moch/.vimrc");
        FileReader fr = null;
        try {
            fr = new FileReader(file);
            char[] cbuf = new char[24];
            int len;
            for (; -1 != (len = fr.read(cbuf));) {
                String str = new String(cbuf, 0, len);
                System.out.println(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != fr) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testFileReaderAndWriter() {
        File inFile = new File("/Users/Moch/.vimrc");
        File outFile = new File("vimrc_file.txt");

        FileReader fr = null;
        FileWriter fw = null;

        try {
            fr = new FileReader(inFile);
            fw = new FileWriter(outFile);

            char[] cbuf = new char[24];
            int len;
            for (; -1 != (len = fr.read(cbuf));) {
                fw.write(cbuf, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != fw) {
                try {
                    fw.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (null != fr) {
                try {
                    fr.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
