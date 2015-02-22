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
package de.mash1t.networking.methods;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import de.mash1t.networking.packets.Packet;
import de.mash1t.networking.packets.InvalidPacket;

/**
 * Class for the network protocol TCP
 *
 * @author Manuel Schmid
 */
public class TCP implements NetworkProtocol {

    public ObjectInputStream inStream = null;
    public ObjectOutputStream outStream = null;
    private Socket clientSocket = null;
    private final InetAddress ip;
    private final String ipString;

    /**
     * Creates input and output streams
     *
     * @param clientSocket Socket for client
     * @throws IOException
     */
    public TCP(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        inStream = new ObjectInputStream(clientSocket.getInputStream());
        outStream = new ObjectOutputStream(clientSocket.getOutputStream());
        ip = clientSocket.getInetAddress();
        ipString = ip.toString();
    }

    /**
     * Writes a Packet to the ObjectOutputStream
     *
     * @param packet stands for itself
     * @return result of sending
     */
    @Override
    public boolean send(Packet packet) {
        try {
            outStream.writeObject(packet);
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    @Override
    public Packet read() {
        try {
            Object obj = this.inStream.readObject();
            if (obj instanceof Packet) {
                Packet readPacket = (Packet) obj;
                return readPacket;
            }
        } catch (IOException | ClassNotFoundException ex) {

        }
        return new InvalidPacket();
    }

    @Override
    public boolean close() {
        try {
            // Close streams and socket
            inStream.close();
            outStream.close();
            clientSocket.close();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(TCP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String getIP() {
        return ipString;
    }
}
