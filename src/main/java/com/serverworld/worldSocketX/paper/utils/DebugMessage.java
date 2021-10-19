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

package com.serverworld.worldSocketX.paper.utils;


import com.serverworld.worldSocketX.config.worldSocketXConfig;
import com.serverworld.worldSocketX.paper.worldSocketXPaper;

public class DebugMessage {

    public static void sendInfo(String msg){ worldSocketXPaper.getInstance().getLogger().info(msg);
    }
    public static void sendWarring(String msg){
        worldSocketXPaper.getInstance().getLogger().warning(msg);
    }
    public static void sendInfoIfDebug(String msg){
        if(worldSocketXConfig.isDebug())
            worldSocketXPaper.getInstance().getLogger().info(msg);

    }
    public static void sendWarringIfDebug(String msg){
        if(worldSocketXConfig.isDebug())
            worldSocketXPaper.getInstance().getLogger().warning(msg);
    }
}
