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

package com.serverworld.worldSocketX.paper;

import com.serverworld.worldSocketX.config.worldSocketXConfig;
import com.serverworld.worldSocketX.socket.SSLSocketClient;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;


public class worldSocketXPaper extends JavaPlugin {

    //public worldSocketCommands commands;
    //public eventsender eventsender;
    public SSLSocketClient sslSocketClient;
    //public messager messager;
    //public checker checker;
    private static worldSocketXPaper worldSocketXPaper;

    @Override
    public void onEnable() {
        worldSocketXPaper = this;
        loadConfig();

        //eventsender = new eventsender(this); TODO paper eventsender
        sslSocketClient = new SSLSocketClient();
        sslSocketClient.startConnect();
    }

    public void reconnect(){
        getLogger().warning(ChatColor.RED + "Can't connect to socket server!");
        getLogger().info(ChatColor.YELLOW + "Reconnecting now");
    }

    public void loadConfig(){
        saveDefaultConfig();
        reloadConfig();
        if(getConfig().getString("client.uuid").isEmpty())
            getConfig().set("client.uuid",UUID.randomUUID().toString());
        saveConfig();
        reloadConfig();
        worldSocketXConfig.setApiVersion(getConfig().getInt("configinfo.api-version"));
        worldSocketXConfig.setDebug(getConfig().getBoolean("configinfo.debug"));

        worldSocketXConfig.setPort(getConfig().getInt("general.port"));
        worldSocketXConfig.setThreads(getConfig().getInt("socketserver.threads"));

        worldSocketXConfig.setUUID(UUID.fromString(getConfig().getString("client.uuid")));
        worldSocketXConfig.setHost(getConfig().getString("client.host"));
        worldSocketXConfig.setCheckRate(getConfig().getInt("client.check-rate"));

        worldSocketXConfig.setKeyStoreFile(getConfig().getString("SSL.keyStore_file"));
        worldSocketXConfig.setTrustStoreFile(getConfig().getString("SSL.trustStore_file"));
        worldSocketXConfig.setKeyStorePassword(getConfig().getString("SSL.keyStorePassword"));
        worldSocketXConfig.setTrustStorePassword(getConfig().getString("SSL.trustStorePassword"));
    }

    public static worldSocketXPaper getInstance(){
        return worldSocketXPaper;
    }
}
