package com.gmail.filoghost.holographicdisplays.commands.main.subs;

import com.gmail.filoghost.holographicdisplays.commands.Colors;
import com.gmail.filoghost.holographicdisplays.commands.CommandValidator;
import com.gmail.filoghost.holographicdisplays.commands.Strings;
import com.gmail.filoghost.holographicdisplays.commands.main.HologramSubCommand;
import com.gmail.filoghost.holographicdisplays.disk.HologramDatabase;
import com.gmail.filoghost.holographicdisplays.event.NamedHologramEditedEvent;
import com.gmail.filoghost.holographicdisplays.exception.CommandException;
import com.gmail.filoghost.holographicdisplays.object.NamedHologram;
import com.gmail.filoghost.holographicdisplays.object.line.CraftHologramLine;
import com.gmail.filoghost.holographicdisplays.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class AddregionCommand extends HologramSubCommand {

    public AddregionCommand() {
        super("addregion");
        setPermission(Strings.BASE_PERM + "addregion");
    }

    @Override
    public String getPossibleArguments() {
        return "<hologramName> <regionName>";
    }

    @Override
    public int getMinimumArguments() {
        return 2;
    }

    @Override
    public void execute(CommandSender sender, String label, String[] args) throws CommandException {
        NamedHologram hologram = CommandValidator.getNamedHologram(args[0]);
        if(hologram.getVisibilityManager().isVisibleByDefault()) {
            hologram.getVisibilityManager().setVisibleByDefault(false);
        }
        if(hologram.getRegions().contains(args[1].toLowerCase())) {
            sender.sendMessage(Colors.ERROR + args[1] + " is already added to the viewing regions!");
            return;
        }
        hologram.addRegion(args[1].toLowerCase());

        HologramDatabase.saveHologram(hologram);
        HologramDatabase.trySaveToDisk();

        sender.sendMessage(Colors.PRIMARY + args[1] + " added to viewing regions!");
    }

    @Override
    public List<String> getTutorial() {
        return Arrays.asList("Adds a viewing region to an existing hologram.");
    }

    @Override
    public SubCommandType getType() {
        return SubCommandType.GENERIC;
    }
}
