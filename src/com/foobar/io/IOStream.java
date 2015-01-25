package com.foobar.io;

import org.junit.Test;

// 文件流
// FileInputStream FileOutputStream FileReader FileWriter

// 缓冲流
// BufferedInputStream BufferedOuputStream BufferedReader BufferedWriter

// 转换流
// InputStreamReader OutputStreamWriter

// 打印流
// PrintStream PrintWriter

// 数据流
// DataInputStream DataOutputStream

// 对象流
// ObjectInputStream ObjectOutputStream

// 随机存取文件流
// RandomAccessFile

public class IOStream {
    private static final String FILE_SEPARATOR = System.getProperty("file.separator");

    public IOStream() {
    }

    public static void main(String[] args) {
        System.out.println(FILE_SEPARATOR);
        System.out.println(System.getProperties());
    }

    @Test
    public void testFileStream() {

    }

    @Test
    public void testBufferedStream() {

    }

    @Test
    public void testIOPutStream() {

    }

    @Test
    public void testPrintStream() {

    }

    @Test
    public void testDataStream() {

    }

    @Test
    public void testObjectStream() {

    }

    @Test
    public void testRandomAccessFileStream() {

    }
}
