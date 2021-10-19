/*
 *     Copyright (C) 2021  mc-serverworld
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.UUID;

/**
 * Login information, contain UUID and Protocol version.
 */
public class LoginMessage {
    @Getter(AccessLevel.PUBLIC) UUID UUID;
    @Getter(AccessLevel.PUBLIC) int ProtocolVersion;

    public LoginMessage(UUID UUID, int ProtocolVersion) {
        this.UUID = UUID;
        this.ProtocolVersion = ProtocolVersion;
    }

    /**
     * Return class in json type.
     */
    public String getLoginJson() {
        Gson gson = new Gson();
        return gson.toJson(this, LoginMessage.class);
    }

    /**
     * Return class convert form string.
     *
     * @param stg gson type string
     */
    public LoginMessage convertToClass(String stg) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        return gson.fromJson(stg, LoginMessage.class);
    }

}