/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.mash1t.networklib.methods;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Manuel Schmid
 */
public class NetworkBasics {

    // Preset network protocol type

    public static NetworkProtocolType nwpType = NetworkProtocolType.TCP;

    /**
     * Makes a network protocol object of type nwpType
     *
     * @param socket Socket to connect to
     * @throws java.io.IOException
     * @return NetworkProtocol
     */
    public static NetworkProtocol makeNetworkProtocolObject(Socket socket) throws IOException {
        switch (nwpType) {
            case TCP:
            default:
                return new TCP(socket);
        }
    }
}
