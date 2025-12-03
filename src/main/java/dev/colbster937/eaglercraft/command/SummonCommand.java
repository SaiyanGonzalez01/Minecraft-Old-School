package dev.colbster937.eaglercraft.command;

import java.util.ArrayList;

import dev.colbster937.eaglercraft.FormattingCodes;
import dev.colbster937.eaglercraft.SingleplayerCommands;
import dev.colbster937.eaglercraft.utils.I18n;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityList;

public class SummonCommand extends Command {
  private static final ArrayList<Integer> blacklist;

  public SummonCommand() {
    super("/summon", new String[] { "" }, "<id> [x] [y] [z]");
  }

  @Override
  public void run(String[] args) {
    if (args.length >= 2 && args.length <= 5) {
      try {
        Entity e;
        try {
          int id = Integer.parseInt(args[1]);
          e = EntityList.createEntity(id, this.mc.theWorld);
        } catch (NumberFormatException ex) {
          e = EntityList.createEntityInWorld(args[1], this.mc.theWorld);
        }
        if (e != null && !isBlacklisted(args[1], e)) {
          double x = this.mc.thePlayer.posX;
          double y = this.mc.thePlayer.posY;
          double z = this.mc.thePlayer.posZ;
          if (args.length >= 3)
            x = this.getRelativeCoord(args[2], x);
          if (args.length >= 4)
            y = this.getRelativeCoord(args[3], y);
          if (args.length >= 5)
            z = this.getRelativeCoord(args[4], z);
          e.setLocationAndAngles(x, y, z, 0.0f, 0.0f);
          this.mc.theWorld.entityJoinedWorld(e);
          SingleplayerCommands.showChat(
              I18n.format("command.summoned", EntityList.getEntityString(e).trim(), x,
                  y, z));
        } else {
          SingleplayerCommands
              .showChat(
                  FormattingCodes.RED + I18n.format("command.doesntExist", args[1]));
        }
      } catch (Throwable t) {
        this.showCommandError(t);
      }
    } else {
      this.showUsage(args[0]);
    }
  }

  private static boolean isBlacklisted(String arg, Entity e) {
    int eid = EntityList.getEntityID(e);
    int aid = -1;
    try {
      aid = Integer.parseInt(arg);
    } catch (NumberFormatException t) {
      aid = EntityList.getEntityID(arg);
    }
    return blacklist.contains(eid) || blacklist.contains(aid);
  }

  static {
    blacklist = new ArrayList<>();
    blacklist.add(1);
    blacklist.add(9);
  }
}