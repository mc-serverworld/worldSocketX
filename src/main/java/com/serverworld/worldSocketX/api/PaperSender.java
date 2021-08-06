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

package com.serverworld.worldSocketX.api;

import com.serverworld.worldSocketX.config.worldSocketXConfig;
import com.serverworld.worldSocketX.socket.MessageObject;

/**
 * Message Sender on PaperSpigot side
 */
public class PaperSender {

    /**
     * Send message via proved param.
     *
     * @param message      message want to send.
     * @param receiver     receiver information.
     * @param receiverType type of receiver information.
     */
    public static void sendMessage(String message, String receiver, ReceiverType receiverType) {
        MessageObject object = null;
        object = new MessageObject(message, worldSocketXConfig.getUUID().toString(), receiver, receiverType, null);
        /*
        if(receiverType.equals(ReceiverType.CLIENT))
            object = new MessageObject(message,worldSocketXConfig.getUUID().toString(), receiver,receiverType);
        if(receiverType.equals(ReceiverType.CHANNEL))
            object = new MessageObject(message,worldSocketXConfig.getUUID().toString(), receiver,receiverType);
        if(receiverType.equals(ReceiverType.MASTER))
            object = new MessageObject(message,worldSocketXConfig.getUUID().toString(), receiver,receiverType);
        if(receiverType.equals(ReceiverType.SOCKETSYSTEM))
            object = new MessageObject(message,worldSocketXConfig.getUUID().toString(), receiver,receiverType);
        */
        //SSLSocketClient.sendMessage(object); TODO
    }

}
