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

package com.serverworld.worldSocketX.waterfall.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WorldSocketXCommands extends Command implements TabExecutor {
    public WorldSocketXCommands(Plugin plugin) {
        super("wsx", "worldsocketx.command");
    }

    public void execute(CommandSender commandSender, String[] strings) {
        for (String stuff : strings) {
            commandSender.sendMessage(stuff);
        }
    }

    public static boolean startsWithIgnoreCase(String s, String start) {
        return s.regionMatches(true, 0, start, 0, start.length());
    }


    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
        List<String> completions = new ArrayList<>();
        List<String> commands = new ArrayList<>();

        if (args.length == 1) {
            if (sender.hasPermission("worldsocketx.command.list.channel"))
                commands.add("list_channel");
            if (sender.hasPermission("worldsocketx.command.list.client"))
                commands.add("list_client");

            for (String subCmd : commands)
                if (startsWithIgnoreCase(subCmd, args[0])) completions.add(subCmd);
        }
        Collections.sort(completions);
        return completions;
    }
}
