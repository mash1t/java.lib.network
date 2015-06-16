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

import java.util.List;

/**
 * Used for a userlist
 *
 * @author Manuel Schmid
 */
public class UserListPacket extends Packet {

    protected final List<String> users;
    protected final String user;
    private UserListPacketType ulPacketType = UserListPacketType.Full;

    /**
     * Set up packet type and userlist Used to transmit a full list of all users
     *
     * @param userlist
     */
    public UserListPacket(List userlist) {
        this.user = null;
        this.users = userlist;
        this.ulPacketType = UserListPacketType.Full;
        this.packetType = PacketType.Userlist;
    }

    /**
     * Set up packet type, user and type of the userlist Used to only transmit changes and not a full list of all users
     *
     * @param user
     * @param ulPacketType
     */
    public UserListPacket(String user, UserListPacketType ulPacketType) {
        this.user = user;
        this.users = null;
        this.ulPacketType = ulPacketType;
        this.packetType = PacketType.Userlist;
    }

    /**
     * Returns the complete userlist
     *
     * @return users
     */
    public List<String> getUserList() {
        return this.users;
    }

    /**
     * Returns the user which has changed
     *
     * @return user
     */
    public String getUser() {
        return this.user;
    }

    /**
     * Returns the current UserListPacketType
     *
     * @return userlist packet type
     */
    public UserListPacketType getUserListType() {
        return this.ulPacketType;
    }
}
