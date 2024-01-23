## Minecraft Old-School (1.0.16_05 Branch)

Welcome To Minecraft Old-School, a project that aims to add new features to Eaglercraft 1.3_01.

![Screenshot 2023-11-18 7 33 26 PM](https://github.com/SaiyanGonzalez01/Minecraft-Old-School/assets/153963453/eadddfb4-08bb-48d1-92eb-703672c6ad32)


## Compiling
(From Original Repo)

Note: New Updates To `assets.epk` & `lwjgl-rundir/resources` for new Old-School updates will have to be changed by you using the `run.bat`.

Just import this entire repository as a gradle project and use the **teavmc** gradle task. It will generate a `classes.js` in the `javascript` folder of this repository.

To change any textures and stuff, make your changes in `lwjgl-rundir/resources` and then run `epkcompiler/run.bat` to generate a new `assets.epk` in the `javascript` folder where `classes.js` is. On linux/mac, open terminal and type `chmod +x run_unix.sh` and then `./run_unix.sh` to run the tool. Copy the new `javascript/assets.epk` onto your website over the old one to update it.

**Make sure you install java and add it to your PATH, or these scripts will not work.**

To compile the server, read the [readme.txt](https://github.com/LAX1DUDE/eaglercraft-beta/blob/main/bukkit/readme.txt) in the `bukkit` folder.

**To run the desktop java runtime**, create a new java project in an IDE. Link `src/main/java` and `src/lwjgl/java` as source folders, and add all jars in the `lwjgl-rundir` folder to your build path. Create a run configuration and set the main class to `net.lax1dude.eaglercraft.MinecraftMain`, and then set the working directory to `lwjgl-rundir`. The client should then launch with no errors, if not then re-read the previous two sentances and try again.

## Whats New?

A Recreation of Alphaver in Eaglercraft Beta

Features in this branch include:

- New textures
- Alphaver Music

## Project Contributors
Saiyan Gonzalez (Creator)

Radmanplays (Original Repo Creator)


