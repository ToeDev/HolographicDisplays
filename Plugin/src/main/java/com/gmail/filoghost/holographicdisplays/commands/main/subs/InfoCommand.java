/*
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package com.gmail.filoghost.holographicdisplays.commands.main.subs;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.CommandSender;
import com.gmail.filoghost.holographicdisplays.commands.Colors;
import com.gmail.filoghost.holographicdisplays.commands.CommandValidator;
import com.gmail.filoghost.holographicdisplays.commands.Strings;
import com.gmail.filoghost.holographicdisplays.commands.main.HologramSubCommand;
import com.gmail.filoghost.holographicdisplays.disk.HologramDatabase;
import com.gmail.filoghost.holographicdisplays.exception.CommandException;
import com.gmail.filoghost.holographicdisplays.object.NamedHologram;
import com.gmail.filoghost.holographicdisplays.object.line.CraftHologramLine;

public class InfoCommand extends HologramSubCommand {

	public InfoCommand() {
		super("info", "details");
		setPermission(Strings.BASE_PERM + "info");
	}

	@Override
	public String getPossibleArguments() {
		return "<hologramName>";
	}

	@Override
	public int getMinimumArguments() {
		return 1;
	}


	@Override
	public void execute(CommandSender sender, String label, String[] args) throws CommandException {
		NamedHologram hologram = CommandValidator.getNamedHologram(args[0]);
		
		sender.sendMessage("");
		sender.sendMessage(Strings.formatTitle("Lines of the hologram '" + hologram.getName() + "'"));

		sender.sendMessage(Colors.SECONDARY + "Viewing Region(s): " + "Any Region");

		int index = 0;
		
		for (CraftHologramLine line : hologram.getLinesUnsafe()) {
			sender.sendMessage(Colors.SECONDARY + Colors.BOLD + (++index) + Colors.SECONDARY_SHADOW + ". " + Colors.SECONDARY + HologramDatabase.serializeHologramLine(line));
		}
		EditCommand.sendQuickEditCommands(sender, label, hologram.getName());
	}

	@Override
	public List<String> getTutorial() {
		return Arrays.asList("Shows the lines of a hologram.");
	}
	
	@Override
	public SubCommandType getType() {
		return SubCommandType.EDIT_LINES;
	}

}
