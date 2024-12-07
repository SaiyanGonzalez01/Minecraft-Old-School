# ![minecraft_title (7)](https://github.com/user-attachments/assets/ff38267b-d390-4dcc-abbf-3b1808156bfd)

A remake of my first project, Minecraft Old-School, now on Eaglercraft Beta 1.7.3!

### Warning! This project is not Chromebook-friendly!

Unless you put your video settings on low and fast and disable particles printing and clouds, then it would be chrombook-friendly. What I mean by that is that Peyton's Eaglercraft Beta 1.7.3 is really laggy on chromebooks. This is because its single-threaded, and not like newer versions which use web workers to load chunks and more. I recommend using medium to high end computers to play.
However day by day it is slowly becoming more Chromebook-friendly!

### Compiling

Ok, finally figured out how to compile it. And now as of commit [1c96970](https://github.com/SaiyanGonzalez01/Minecraft-Old-School-ReDefault/commit/1c9697069fce6a5390d4a87685912464bd98d7e7) compileEPK.bat now also compiles the javascript! Here are the steps:

For app.js (only do this if you are on any device that can not run .bat's):
- Create Codespace or import project as a gradle project
- Do 'gradle generatejavascript'
- If you opened it in command prompt then wait for it to close, and see if the file date changed, if not or if you saw any errors, fix them and try again.
- On powershell, you will be able to see error's better. Powershell recommended.

For resources.mc:

- In Windows, double click the file called CompileEPK.bat, which will generate the new file.
- It will also build the javascript.
 ### OR
- In Linux or Mac, open up terminal and type chmod +x CompileEPK.sh, and then ./CompileEPK.sh to run it. This will generate the new file. But will NOT build the javascript.

Once compiled, move the new resources.mc to the /web folder.

### Play a Demo!

You heard right! You can actually PLAY the project now unlike the original Old-School. Play by clicking/tapping [HERE](https://saiyangonzalez01.github.io/Minecraft-Old-School-ReDefault/web) ...or just press this big giant link: https://saiyangonzalez01.github.io/Minecraft-Old-School-ReDefault/web

### Contributers

- SaiyanGonzalez01 ~ Owner & Creator Of Old-School
- chicken-nugget1104 ~ Project Contributor
- PeytonPlayz595 ~ Original Beta 1.7.3 Creator (https://github.com/PeytonPlayz595)
