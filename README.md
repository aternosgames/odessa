# Odessa
**Customizable** and **easy-to-use** game framework built on top of the Bukkit API and implemented through Sponge

## Currently, when building artifacts it is recommended to assemble these jars:

**odessa.jar:**

--> odessa-engine compile output
--> odessa-game compile output
--> extracted 'reflections-<version>.jar' (Dependency not found in bukkit by default)
--> extracted 'javaassist-<version>-GA.jar' (Dependency for the needed parts of reflections, not found in bukkit by default.

**hungergames.jar**

--> The example hungergames compile output.

..and whatever other games you build can be compiled like hungergames.jar as long as odessa.jar is on the server.

## Configuration
Arenas/Maps/LobbySpawns need to be set up when the plugin first installs with the /odessa <> command. It will configure/add arenas for whatever game is active/set to (if multiple registered).

## Note
Currently in development. Not at all suitable for production use, or additional game development besides those in example due to the ever-evolving nature of the framework. Contributions are always welcome.
