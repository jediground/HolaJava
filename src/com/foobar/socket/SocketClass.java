package com.foobar.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;

public class SocketClass {
    // 客户端
    @Test

    public void testclient() {
        Socket socket = null;
        OutputStream os = null;
        try {
            // 1.创建一个Socket的对象，通过构造器指明服务端的IP地址，以及其接收程序的端口号
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 9091);
            // 2.getOutputStream()：发送数据，方法返回OutputStream的对象
            os = socket.getOutputStream();
            // 3.具体的输出过程
            os.write("我是客户端，请多关照".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4.关闭相应的流和Socket对象
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }

    }

    // 服务端
    @Test
    public void testserver() {
        ServerSocket ss = null;
        Socket s = null;
        InputStream is = null;
        try {
            // 1.创建一个ServerSocket的对象，通过构造器指明自身的端口号
            ss = new ServerSocket(9091);
            // 2.调用其accept()方法，返回一个Socket的对象
            s = ss.accept();
            // 3.调用Socket对象的getInputStream()获取一个从客户端发送过来的输入流
            is = s.getInputStream();
            // 4.对获取的输入流进行的操作
            byte[] b = new byte[20];
            int len;
            while ((len = is.read(b)) != -1) {
                String str = new String(b, 0, len);
                System.out.print(str);
            }
            System.out.println("收到来自于" + s.getInetAddress().getHostAddress()
                    + "的连接");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            // 5.关闭相应的流以及Socket、ServerSocket的对象
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
            if (s != null) {
                try {
                    s.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
            if (ss != null) {
                try {
                    ss.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }
    }

    public SocketClass() {

    }

    @Test
    public void client() {
        Socket socket = null;
        OutputStream os = null;
        try {
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 9090);
            os = socket.getOutputStream();
            os.write("This is some messages send from clien!".getBytes());
            System.out.println("Send finished");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != os) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void server() {
        System.out.println("Server runing...");

        ServerSocket ss = null;
        Socket socket = null;
        InputStream is = null;

        try {
            ss = new ServerSocket(9090);
            socket = ss.accept();
            is = socket.getInputStream();

            byte[] by = new byte[20];
            int len;
            while (-1 != (len = is.read(by))) {
                System.out.println(new String(by, 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (ss != null) {
                try {
                    ss.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
