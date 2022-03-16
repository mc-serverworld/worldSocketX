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

package com.serverworld.worldSocketX.waterfall.utils;


import com.serverworld.worldSocketX.config.worldSocketXConfig;
import com.serverworld.worldSocketX.waterfall.worldSocketXWaterFall;

/**
 * Console message sender.
 * ChatColor is supported.
 */
public class DebugMessage {
    /**
     * Send info level message to console.
     */
    public static void sendInfo(String msg) {
        worldSocketXWaterFall.getInstance().getLogger().info(msg);
    }

    /**
     * Send warring level message to console.
     */
    public static void sendWarring(String msg) {
        worldSocketXWaterFall.getInstance().getLogger().warning(msg);
    }

    /**
     * Send info level message to console if debug mode is true.
     */
    public static void sendInfoIfDebug(String msg) {
        if (worldSocketXConfig.isDebug())
            worldSocketXWaterFall.getInstance().getLogger().info(msg);
    }

    /**
     * Send warring level message to console if debug mode is true.
     */
    public static void sendWarringIfDebug(String msg) {
        if (worldSocketXConfig.isDebug())
            worldSocketXWaterFall.getInstance().getLogger().warning(msg);
    }
}
