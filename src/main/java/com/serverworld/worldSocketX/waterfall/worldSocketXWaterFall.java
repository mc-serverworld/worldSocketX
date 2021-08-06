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

package com.serverworld.worldSocketX.waterfall;

import com.serverworld.worldSocketX.config.worldSocketXConfig;
import com.serverworld.worldSocketX.socket.SSLSocketServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.UUID;

public class worldSocketXWaterFall extends Plugin {
    public SSLSocketServer sslSocketServer;
    private static worldSocketXWaterFall waterFallworldSocketX;
    private static Configuration configuration;

    @Override
    public void onEnable() {
        waterFallworldSocketX = this;
        LoadConfig();
        startSocketServer();
    }

    public void LoadConfig() {
        if (!getDataFolder().exists())
            getDataFolder().mkdir();

        File file = new File(getDataFolder(), "config.yml");
        if (!file.exists()) {
            try (InputStream in = getResourceAsStream("config.yml")) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));
            if (configuration.getString("client.uuid").isEmpty()) {
                configuration.set("client.uuid", (UUID.randomUUID().toString()));
                ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, new File(getDataFolder(), "config.yml"));
            }//RandomUUID when its null
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));

            worldSocketXConfig.setApiVersion(configuration.getInt("configinfo.api-version"));
            worldSocketXConfig.setDebug(configuration.getBoolean("configinfo.debug"));
            worldSocketXConfig.setPort(configuration.getInt("general.port"));

            worldSocketXConfig.setThreads(configuration.getInt("socketserver.threads"));

            worldSocketXConfig.setUUID(UUID.fromString(configuration.getString("client.uuid")));
            worldSocketXConfig.setHost(configuration.getString("client.host"));
            worldSocketXConfig.setCheckRate(configuration.getInt("client.check-rate"));

            worldSocketXConfig.setKeyStoreFile(configuration.getString("SSL.keyStore_file"));
            worldSocketXConfig.setTrustStoreFile(configuration.getString("SSL.trustStore_file"));
            worldSocketXConfig.setKeyStorePassword(configuration.getString("SSL.keyStorePassword"));
            worldSocketXConfig.setTrustStorePassword(configuration.getString("SSL.trustStorePassword"));

/*
            worldSocketXConfig.setSERVER_KEY_STORE_FILE(configuration.getString("SSL.server.keyStore_file"));
            worldSocketXConfig.setSERVER_TRUST_KEY_STORE_FILE(configuration.getString("SSL.server.trustStore_file"));
            worldSocketXConfig.setSERVER_KEY_STORE_PASSWORD(configuration.getString("SSL.server.keyStorePassword"));
            worldSocketXConfig.setSERVER_TRUST_KEY_STORE_PASSWORD(configuration.getString("SSL.server.trustStorePassword"));

            worldSocketXConfig.setCLIENT_KEY_STORE_FILE(configuration.getString("SSL.client.keyStore_file"));
            worldSocketXConfig.setCLIENT_TRUST_KEY_STORE_FILE(configuration.getString("SSL.client.trustStore_file"));
            worldSocketXConfig.setCLIENT_KEY_STORE_PASSWORD(configuration.getString("SSL.client.keyStorePassword"));
            worldSocketXConfig.setCLIENT_TRUST_KEY_STORE_PASSWORD(configuration.getString("SSL.client.trustStorePassword"));
 */
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startSocketServer() {
        sslSocketServer = new SSLSocketServer();
        sslSocketServer.start();
    }

    /**
     * Return WaterFallSocketX Plugin Instance.
     */
    public static worldSocketXWaterFall getInstance() {
        return waterFallworldSocketX;
    }
}
