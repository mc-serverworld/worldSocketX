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

import com.serverworld.worldSocketX.api.ReceiverType;
import com.serverworld.worldSocketX.config.worldSocketXConfig;
import lombok.AccessLevel;
import lombok.Getter;

import javax.annotation.Nullable;
import java.util.UUID;
import java.util.zip.CRC32C;


public class MessageObject {

    @Getter(AccessLevel.PUBLIC) private String Message;
    @Getter(AccessLevel.PUBLIC) private String MessageType;
    @Getter(AccessLevel.PUBLIC) private String Sender;
    @Getter(AccessLevel.PUBLIC) private String Receiver;
    @Getter(AccessLevel.PUBLIC)
    private ReceiverType ReceiverType;
    @Getter(AccessLevel.PUBLIC)
    private Long Time;

    public UUID getSenderUUID() {
        return UUID.fromString(Sender);
    }

    public UUID getReceiverUUID() {
        return UUID.fromString(Receiver);
    }

    public CRC32C getCRC32C() {
        CRC32C Scm = new CRC32C();
        Scm.update(Byte.parseByte(Message + Time));
        return Scm;
    }

    /**
     * @param Message      Editable:The message need to send.
     * @param MessageType  Editable:Type of message.
     * @param Sender       Sender UUID,use UUID in config if empty.
     * @param Receiver     Receiver UUID,ignore if ReceiverType is MASTER(socket server).
     * @param ReceiverType Can be CLIENT(send to other client with UUID), MASTER(socket server), CHANNEL(send via channel name) or SOCKETSYSTEM(for advance user only);
     */
    public MessageObject(String Message, String Sender, String Receiver, ReceiverType ReceiverType, @Nullable String MessageType) {
        this.Message = Message;
        this.MessageType = MessageType;
        if (Sender.isEmpty())
            this.Sender = worldSocketXConfig.getUUID().toString();
        else
            this.Sender = Sender;
        this.Receiver = Receiver;
        this.ReceiverType = ReceiverType;
        this.Time = System.currentTimeMillis();
    }
}
