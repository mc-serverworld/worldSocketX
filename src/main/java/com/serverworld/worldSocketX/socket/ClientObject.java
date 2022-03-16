/*
 *     Copyright (C) 2022 mc-serverworld
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.serverworld.worldSocketX.socket;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.net.ssl.SSLSocket;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.UUID;

/**
 * SSLServer socket will create new instance when new client connect.
 * Each client will have unique id.
 */
public class ClientObject {
    public ClientObject(UUID uuid, SSLSocket socket, PrintWriter printWriter, int ProtocolVersion) {
        this.UUID = uuid;
        this.Printer = printWriter;
        this.Socket = socket;
    }

    /**
     * Return Unique ID of client.
     */
    @Getter(AccessLevel.PUBLIC) private UUID UUID;

    //@Getter(AccessLevel.PUBLIC) private UUID ProxyUUID;

    /**
     * Return Socket(SSLSocket) of client.
     */
    @Getter(AccessLevel.PUBLIC) private SSLSocket Socket;

    /**
     * Return Printer of client.
     */
    @Getter(AccessLevel.PUBLIC) private PrintWriter Printer;

    /**
     * Set/Get Channels of client.
     */
    @Setter(AccessLevel.PUBLIC) @Getter(AccessLevel.PUBLIC) private ArrayList<String> Channels;

    /**
     * Return ProtocolVersion of client.
     */
    @Getter(AccessLevel.PUBLIC) private int ProtocolVersion;
}
