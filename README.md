# ![minecraft_title (7)](https://github.com/user-attachments/assets/ff38267b-d390-4dcc-abbf-3b1808156bfd)

A remake of my first project, Minecraft Old-School, now on Eaglercraft Beta 1.7.3!

### Warning! This project is not Chromebook-friendly!

Unless you put your video settings on low and fast, then it would chrombook-friendly. What I mean by that is that Peyton's Eaglercraft Beta 1.7.3 is really laggy on chromebooks. This is because its single-threaded, and not like newer versions which use web workers to load chunks and more. I recommend using medium to high end computers to play.

### Compiling

Ok, finally figured out how to compile it. Here are the steps:

For app.js:
- Create Codespace or import project as a gradle project
- Do 'gradle generatejavascript'
- Wait for it to load the new app.js file
- Download it and change it out in the /js folder

For resources.mc:

- In Windows, double click the file called CompileEPK.bat, which will generate the new file.
 ### OR
- In Linux or Mac, open up terminal and type chmod +x CompileEPK.sh, and then ./CompileEPK.sh to run it. This will generate the new file.

Once compiled, move the new resources.mc to the /web folder.

### Play a Demo!

You heard right! You can actually PLAY the project now unlike the original Old-School. Play here: https://saiyangonzalez01.github.io/Minecraft-Old-School-ReDefault/web

### Contributers

- SaiyanGonzalez01 ~ Owner
- chicken-nugget1104 ~ Pull Request Helper (https://github.com/chicken-nugget1104)
- PeytonPlayz595 ~ Original Beta 1.7.3 Creator (https://github.com/PeytonPlayz595)
