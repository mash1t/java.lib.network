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
package de.mash1t.networklib.packets;

import org.bouncycastle.openpgp.PGPPublicKey;

/**
 * Used for establishing a connection
 *
 * @author Manuel Schmid
 */
public class ConnectPacket extends Packet {

    protected final String name;
    protected final PGPPublicKey pubKey;

    /**
     * Set up packet type and name
     *
     * @param name nickname for the chat
     */
    public ConnectPacket(String name) {
        this.name = name;
        this.pubKey = null;
        this.packetType = PacketType.Connect;
    }
    
        /**
     * Set up packet type, name and public key for RSA
     *
     * @param name nickname for the chat
     * @param pubKey public key for encryption
     */
    public ConnectPacket(String name, PGPPublicKey pubKey) {
        this.name = name;
        this.pubKey = pubKey;
        this.packetType = PacketType.Connect;
    }

    /**
     * Returns the name of the client who wants to connect
     *
     * @return name
     */
    public String getName() {
        return this.name;
    }
    
        /**
     * Returns the name of the client who wants to connect
     *
     * @return name
     */
    public PGPPublicKey getKey() {
        return this.pubKey;
    }
}
