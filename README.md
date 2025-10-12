
## ![Minecraft Old-School Official Title Rev2](https://github.com/user-attachments/assets/abcf7c81-c3f3-4120-9855-b0a66834703c)


A remake of my first project, Minecraft Old-School, now on Eaglercraft Beta 1.7.3!

Minecraft Old-School is an Extension Mod of Eaglercraft 1.7.3 that adds in some new features, bringing back removed ones, and porting some from later versions, all while keeping the Beta-like feel you know & love. It's inspired by mods like Better Than Adventure, Not-So-Seecret Saturday, and more!

### Warning! This project is not Chromebook-friendly!

Unless you put your video settings on low and fast and disable particles printing and clouds, then it would be chrombook-friendly. What I mean by that is that Peyton's Eaglercraft Beta 1.7.3 is really laggy on chromebooks. This is because its single-threaded, and not like newer versions which use web workers to load chunks and more. I recommend using medium to high end computers to play.
However day by day it is slowly becoming more Chromebook-friendly!

### Compiling

As of commit [1c96970](https://github.com/SaiyanGonzalez01/Minecraft-Old-School-ReDefault/commit/1c9697069fce6a5390d4a87685912464bd98d7e7) compileEPK.bat now also compiles the javascript! Here are the steps:

(Either Launch Codespaces or do it on Windows first)

#### For app.js (Codespaces & Similar):
- Open up terminal and type 'gradle generatejavascript', this will generate the new file in the /web folder.

#### (Windows):

- In Windows, double click the file called CompileEPK.bat, which will generate the new file.
- It will also build the resources.

#### For resources.mc (Codespaces & Similar):

- Open up terminal and type 'chmod +x CompileEPK.sh', and then ./CompileEPK.sh to run it. This will generate the new file in the /web/js folder. But will NOT build the javascript.

#### (Windows):

- In Windows, double click the file called CompileEPK.bat, which will generate the new file.
- It will also build the javascript.

#### Notes For Powershell Users:

- If you opened it in command prompt then wait for it to close, and see if the file date changed, if not or if you saw any errors, fix them and try again.
- On powershell, you will be able to see error's better. Powershell recommended.

### Known Big Bugs
- Ghasts crashes game if they fire a fireball and it contacts the floor
- World Importing
- Renaming Worlds

### Play The Project!

You heard right! You can actually PLAY the project now unlike the original Old-School.

Play [Recent Update](https://saiyangonzalez01.github.io/Minecraft-Old-School/web)

### Contributers

- SaiyanGonzalez01 ~ Owner & Creator Of Old-School
- [chicken-nugget1104](https://github.com/chicken-nugget1104) ~ Project Contributor
- [PeytonPlayz595](https://github.com/PeytonPlayz595) ~ Original Beta 1.7.3 Creator 
### Special Thanks

- [Kristoffer Zetterstrand](https://zetterstrand.com/works/) ~ Painting Producer 
