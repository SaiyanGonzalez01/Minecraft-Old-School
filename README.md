## Minecraft Old-School (Old & Broken)

Welcome To Minecraft Old-School, a project that aims to add new features to Eaglercraft 1.3_01. (This project is just broken code, it was literally made from my past self who had no clue on how to code, please do not use this and try to mod it.)

![Screenshot 2024-02-12 3 51 12 PM](https://github.com/SaiyanGonzalez01/Minecraft-Old-School/assets/153963453/4b483027-043e-449c-9761-93bd9bdd7e2a)

## Compiling
(From Original Repo)

Note: There will be Updates To `src/java` & `lwjgl-rundir/resources` for new Old-School updates. The `lwjgl-rundir/resources` will have to be changed by you using the `run.bat`. (The HTML file in the /javascript folder has no changes to it, so you need to change it yourself.)

Just import this entire repository as a gradle project and use the **teavmc** gradle task. It will generate a `classes.js` in the `javascript` folder of this repository.

To change any textures and stuff, make your changes in `lwjgl-rundir/resources` and then run `epkcompiler/run.bat` to generate a new `assets.epk` in the `javascript` folder where `classes.js` is. On linux/mac, open terminal and type `chmod +x run_unix.sh` and then `./run_unix.sh` to run the tool. Copy the new `javascript/assets.epk` onto your website over the old one to update it.

**Make sure you install java and add it to your PATH, or these scripts will not work.**

To compile the server, read the [readme.txt](https://github.com/LAX1DUDE/eaglercraft-beta/blob/main/bukkit/readme.txt) in the `bukkit` folder.

**To run the desktop java runtime**, create a new java project in an IDE. Link `src/main/java` and `src/lwjgl/java` as source folders, and add all jars in the `lwjgl-rundir` folder to your build path. Create a run configuration and set the main class to `net.lax1dude.eaglercraft.MinecraftMain`, and then set the working directory to `lwjgl-rundir`. The client should then launch with no errors, if not then re-read the previous two sentances and try again.

## Branches

**Original**

The default branch, with new features and gimmicks added to Eaglercraft!

## What's New?

This is the **Original** Branch.

Features in this branch included:

- New Textures
- New Skins
- New Music
- New Updates

## Texture Packs

In 1.0.5a, the texture pack menu was added, as it was unused. To add a texture pack to the list, download and open the texture pack via the button below. To make a texture pack, you need to download the resources folder and edit it.

![Screenshot 2024-03-10 10 16 15 PM](https://github.com/SaiyanGonzalez01/Minecraft-Old-School/assets/153963453/e3944dc4-c0f2-4d9d-93a0-1c65a762deb4)


## Project Contributors
Saiyan Gonzalez (Creator)

Radmanplays (Original Eagler 1.3 Repo Creator)


