package src;

import java.util.Random;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class bot extends ListenerAdapter{
	public static String crypticraft="687312985286508569";
	public static String testing="707304216552669225";
	public static void main(String[] args) throws Exception{
		new JDABuilder("<censored>")
			.addEventListeners(new bot())
			.setActivity(Activity.playing("with myself so you don't have to."))
			.build();
	}
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		Guild server=event.getGuild();
		User user=event.getAuthor(); 
		MessageChannel channel=event.getChannel();
		Member member=event.getMember();
		//MessageChannel botSpam=server.getTextChannelById("709222717588766811");	
		if (event.getAuthor().isBot()) {return;}
		String msg=normalizeText(event.getMessage());
		if(event.getMessage().getContentRaw().indexOf("!")==0){Commands.commandManager(msg, user, member, channel, server);} else {
			MessageReactions(msg, user, channel);
		}
		
	}
	private String normalizeText(Message message) {
		String text=message.getContentRaw();
		return text.replaceAll("[^a-zA-Z0-9]*", "").toUpperCase();
	}
	private void MessageReactions(String msg,User user,MessageChannel channel) {
		if (msg.contains("HISENSHI") || msg.contains("HEYSENSHI")) {
			channel.sendMessage("Which one?").queue();
		}
		if (msg.equals("HEH")) {
			if (user.getName().equals("bbbb7")) {
				channel.sendMessage("smh ben").queue();
			} else {
			channel.sendMessage("***heh***").queue();}
		}
		if (user.getName().equals("Susu")) {
			Random ran=new Random();
			if (ran.nextInt(15)==1) {
				channel.sendMessage("Hi creator!").queue();
			}
		}
		if (msg.contains("LMAO")) {
			channel.sendMessage("lmao yeah").queue();
		}
		if (msg.contains("HMM")) {
			channel.sendMessage("hmmm indeed").queue();
		}
		if (msg.contains("ILMANGO")) {
			channel.sendMessage("Did you mean: *daddy mango*?").queue();
		}
		if (msg.equals("NICE")) {
			channel.sendMessage("ye").queue();
		}
		if (msg.equals("THANK") || msg.equals("THANKS")) {
			channel.sendMessage("welc").queue();
		}
		if (msg.contains("WHATDOESITDO")) {
			if (msg.equals("WHATDOESITDO")) {
				channel.sendMessage("It turns Senshi into a robot ofc").queue();
			} else {
			channel.sendMessage("it turns them into a robot ofc").queue();}
		}
		if(msg.equals("ONLINE") && user.getName().equals("Susu")) {
			channel.sendMessage("Online!").queue();
		}
	}
	
}
