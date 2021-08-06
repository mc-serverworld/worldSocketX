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

package com.serverworld.worldSocketX.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public class worldSocketXConfig {
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private static int ApiVersion;
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private static boolean Debug;
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private static int Port;
    //@Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static String Password;
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private static int Threads;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private static java.util.UUID UUID;
    //@Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static String Name;
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private static String Host;
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private static int CheckRate;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private static String KeyStoreFile;
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private static String TrustStoreFile;
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private static String KeyStorePassword;
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private static String TrustStorePassword;

/*
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static String SERVER_KEY_STORE_FILE;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static String SERVER_TRUST_KEY_STORE_FILE;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static String SERVER_KEY_STORE_PASSWORD;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static String SERVER_TRUST_KEY_STORE_PASSWORD;

    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static String CLIENT_KEY_STORE_FILE;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static String CLIENT_TRUST_KEY_STORE_FILE;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static String CLIENT_KEY_STORE_PASSWORD;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static String CLIENT_TRUST_KEY_STORE_PASSWORD;
 */

}
