package dev.colbster937.eaglercraft.utils;

import java.util.ArrayList;
import java.util.List;

import dev.colbster937.eaglercraft.storage.DirStorage;

import net.lax1dude.eaglercraft.internal.vfs2.VFile2;

import net.minecraft.client.Minecraft;
import net.minecraft.src.SaveFormatComparator;
import net.minecraft.src.WorldInfo;

public class SaveUtils {
  public static DirStorage i;

  private static Minecraft mc;
  private static VFile2 dir;

  public static void init(Minecraft _mc) {
    mc = _mc;
    i = new DirStorage(_mc, "saves");
    dir = new VFile2(Minecraft.getMinecraftDir(), "saves");
    findLegacyWorlds();
  }

  public static List<SaveFormatComparator> getWorlds() {
    List<SaveFormatComparator> arr = new ArrayList<>();
    List<String> worlds = i.getFilesInManifest();
    for (String file : worlds) {
      SaveFormatComparator save = getSave(file);
      if (save != null)
        arr.add(save);
    }
    return arr;
  }

  public static List<SaveFormatComparator> scanWorlds() {
    List<SaveFormatComparator> arr = new ArrayList<>();
    List<String> strArr = new ArrayList<>();
    List<VFile2> files = dir.listFiles(true);
    String root = dir.getPath();
    int subpath = root.length();

    for (VFile2 file : files) {
      String path = file.getPath();
      if (!ScuffedUtils.isStringEmpty(path) && path.startsWith(root)) {
        path = path.substring(subpath);
        if (path.startsWith("/"))
          path = path.substring(1);
        if (!ScuffedUtils.isStringEmpty(path)) {
          int i = path.indexOf('/');
          path = i >= 0 ? path.substring(0, i) : path;
          if (!ScuffedUtils.isStringEmpty(path) && !strArr.contains(path))
            strArr.add(path);
        }
      }
    }

    for (String file : strArr) {
      SaveFormatComparator save = getSave(file);
      if (save != null)
        arr.add(save);
    }

    return arr;
  }

  public static void findLegacyWorlds() {
    List<SaveFormatComparator> worlds = scanWorlds();
    List<String> registeredWorlds = i.getFilesInManifest();

    for (SaveFormatComparator world : worlds) {
      String name = world.func_22164_a();
      if (!registeredWorlds.contains(name))
        i.addToManifest(name);
    }
  }

  private static SaveFormatComparator getSave(String file) {
    WorldInfo info = mc.func_22004_c().func_22173_b(file);
    if (info != null) {
      String name = info.getWorldName();
      if (ScuffedUtils.isStringEmpty(name))
        name = file;
      return new SaveFormatComparator(
        file,
        name,
        info.func_22301_l(),
        info.func_22306_g(),
        info.func_22296_k() != 19132
      );
    } else {
      return null;
    }
  }
}