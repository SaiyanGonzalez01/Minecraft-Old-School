package dev.colbster937.eaglercraft.command;

import java.util.ArrayList;

import dev.colbster937.eaglercraft.SingleplayerCommands;
import dev.colbster937.eaglercraft.utils.I18n;

public class HelpCommand extends Command {
  public HelpCommand() {
    super("/help", new String[] { "/?" }, "[command]");
  }

  @Override
  public void run(String[] args) {
    ArrayList<Command> commands = SingleplayerCommands.getCommandList();
    if (args.length == 1) {
      SingleplayerCommands.showChat(I18n.format("command.help"));
      for (Command command : commands)
        SingleplayerCommands.showChat(I18n.format("command.helpLine", command.getCommand(),
            command.getCommandUsage()));
    } else if (args.length == 2) {
      if (!args[1].startsWith("/"))
        args[1] = "/" + args[1];
      for (Command command : commands) {
        if (command.isCommand(args[1])) {
          command.showUsage(args[1]);
          return;
        }
      }
      SingleplayerCommands.showChat(I18n.format("command.unknown", args[1]));
    } else {
      this.showUsage(args[0]);
    }
  }
}