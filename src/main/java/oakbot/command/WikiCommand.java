package oakbot.command;

import static oakbot.command.Command.reply;

import com.google.common.net.UrlEscapers;

import oakbot.bot.BotContext;
import oakbot.bot.ChatCommand;
import oakbot.bot.ChatResponse;

/**
 * Displays on-boxed Wikipedia pages.
 * @author Michael Angstadt
 */
public class WikiCommand implements Command {
	@Override
	public String name() {
		return "wiki";
	}

	@Override
	public String description() {
		return "Displays a one-box for a Wikipedia page.";
	}

	@Override
	public String helpText(String trigger) {
		//@formatter:off
		return new HelpBuilder(trigger, this)
			.example("James Gosling", "Displays the one-box for the wiki page of James Gosling.")
		.toString();
		//@formatter:on
	}

	@Override
	public ChatResponse onMessage(ChatCommand chatCommand, BotContext context) {
		String content = chatCommand.getContent().trim();
		if (content.isEmpty()) {
			return reply("Please specify the term you'd like to display.", chatCommand);
		}

		String url = "http://en.wikipedia.org/wiki/" + UrlEscapers.urlPathSegmentEscaper().escape(content);
		return new ChatResponse(url);
	}
}
