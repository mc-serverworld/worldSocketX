/*
 *
 *  * WorldMISF-lib: library and basic component of mc-serverworld
 *  * Copyright (C) 2020-2021 mc-serverworld
 *  *
 *  * This program is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU General Public License as published by
 *  * the Free Software Foundation, either version 3 of the License, or
 *  * (at your option) any later version.
 *  *
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  * GNU General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU General Public License
 *  * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.serverworld.worldSocketX.socket;

import lombok.AccessLevel;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

/**
 * Class will be create when client want to join no man channel.
 */
public class ChannelObject {


    /**
     * Create new channel.
     *
     * @param ChannelName Name of channel.
     */
    public ChannelObject(String ChannelName) {
        this.ChannelName = ChannelName;
    }

    /**
     * Return ChannelName.
     */
    @Getter(AccessLevel.PUBLIC) private String ChannelName;

    /**
     * Return clients in channel.
     */
    @Getter(AccessLevel.PUBLIC) private Set<ClientObject> Clients = new HashSet<>();

    /**
     * Add clinet to channel.
     */
    public void addClient(ClientObject clientObject) {
        if (!Clients.contains(clientObject))
            this.Clients.add(clientObject);
    }

    /**
     * Remove client in channel.
     */
    public void removeClient(ClientObject clientObject) {
        this.Clients.removeIf(stuff -> stuff.equals(clientObject));
    }

}
