/*
 * The MIT License
 *
 * Copyright 2015 Manuel Schmid.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package de.mash1t.networklib;

import de.mash1t.networklib.methods.TCP;
import de.mash1t.networklib.packets.ConnectPacket;
import de.mash1t.networklib.packets.Packet;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Manuel Schmid
 */
public class TCPTest {

    private final int port = 8000;
    private TCP tcpTestObj;
    private boolean readResult, sendResult, closeResult1, closeResult2 = false;

    /**
     * Initializes sockets and ProtocolObjects needed for the test
     *
     * @throws IOException
     */
    private void init() throws IOException {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println("Server started");
                            ServerSocket serverSocket = new ServerSocket(port);
                            TCP tcp;
                            try (Socket clientSocket = serverSocket.accept()) {
                                tcp = new TCP(clientSocket);
                                Packet readPacket = tcp.read();
                                readResult = readPacket instanceof ConnectPacket;
                            }
                            closeResult2 = tcp.close();
                        } catch (IOException ex) {
                            fail("IOException: " + ex.getMessage());
                        }
                    }
                }
        ).start();
        tcpTestObj = new TCP(new Socket("localhost", port));
        sendResult = tcpTestObj.send(new ConnectPacket("test"));
        closeResult1 = tcpTestObj.close();
    }

    /**
     * Test of all methods. 
     * Unfortunately all methods had to be put together into one big test,
     * otherwise the tests would have failed due to not possible simultaneous connections
     *
     * @throws java.io.IOException
     */
    @Test
    public void testAll() throws IOException {
        init();
        System.out.println("Result of sending packet: " + sendResult);
        System.out.println("Result of reading packet: " + readResult);
        System.out.println("Result of closing streams:");
        System.out.println("- Client: " + closeResult1);
        System.out.println("- Server: " + closeResult2);

        assertTrue(sendResult);
        assertTrue(readResult);
        assertEquals(closeResult1, closeResult2);
    }
}
