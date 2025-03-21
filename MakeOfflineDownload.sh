#!/bin/sh
java -cp "jars/MakeOfflineDownload.jar:jars/CompilePackage.jar" net.lax1dude.eaglercraft.v1_8.buildtools.workspace.MakeOfflineDownload "web/OfflineDownloadTemplate.txt" "web/js/app.js" "web/js/app.js.map" "web/resources.mc" "web/Minecraft_Old-School_US.html" "web/Minecraft_Old-School_Inter.html" "web/lang"
