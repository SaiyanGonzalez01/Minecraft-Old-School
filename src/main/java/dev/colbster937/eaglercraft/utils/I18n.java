package dev.colbster937.eaglercraft.utils;

import dev.colbster937.eaglercraft.EaglercraftVersion;

import net.minecraft.src.StringTranslate;

public class I18n {
  private static final StringTranslate translate;

  public static boolean isFormattable(String key) {
    return translate.hasTranslateKey(key);
  }

  public static String format(String key) {
    String eaglerKey = EaglercraftVersion.LANG_CONTEXT + "." + key;
    return translate.hasTranslateKey(eaglerKey) ? translate.translateKey(eaglerKey) : translate.translateKey(key);
  }

  public static String format(String key, Object... args) {
    String eaglerKey = EaglercraftVersion.LANG_CONTEXT + "." + key;
    return translate.hasTranslateKey(eaglerKey) ? translate.translateKeyFormat(eaglerKey, args)
        : translate.translateKeyFormat(key, args);
  }

  public static String formatNamed(String key) {
    key += ".name";
    String eaglerKey = EaglercraftVersion.LANG_CONTEXT + "." + key;
    return translate.hasTranslateKey(eaglerKey) ? translate.translateKey(eaglerKey) : translate.translateKey(key);
  }

  static {
    translate = StringTranslate.getInstance();
  }
}
