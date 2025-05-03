
## ![Minecraft Old-School Official Title](https://github.com/user-attachments/assets/b3616c30-e6d8-444f-95f3-bc31b37a4645)

A remake of my first project, Minecraft Old-School, now on Eaglercraft Beta 1.7.3!

### Warning! This project is not Chromebook-friendly!

Unless you put your video settings on low and fast and disable particles printing and clouds, then it would be chrombook-friendly. What I mean by that is that Peyton's Eaglercraft Beta 1.7.3 is really laggy on chromebooks. This is because its single-threaded, and not like newer versions which use web workers to load chunks and more. I recommend using medium to high end computers to play.
However day by day it is slowly becoming more Chromebook-friendly!

### Compiling

Ok, finally figured out how to compile it. And now as of commit [1c96970](https://github.com/SaiyanGonzalez01/Minecraft-Old-School-ReDefault/commit/1c9697069fce6a5390d4a87685912464bd98d7e7) compileEPK.bat now also compiles the javascript! Here are the steps:

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

You heard right! You can actually PLAY the project now unlike the original Old-School. Play by clicking/tapping [Here](https://saiyangonzalez01.github.io/Minecraft-Old-School-ReDefault/web)

### Contributers

- SaiyanGonzalez01 ~ Owner & Creator Of Old-School
- chicken-nugget1104 ~ Project Contributor
- PeytonPlayz595 ~ Original Beta 1.7.3 Creator (https://github.com/PeytonPlayz595)
