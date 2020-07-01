package testingBot;
import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.util.*;

public class bot extends ListenerAdapter{
	public static void main(String[] args) throws Exception{
		new JDABuilder("NzA4ODYyODQ0MjQxODM4MDky.XrdimA.C8uFywHMzeF3kCBLmQHBTQsKLzQ")
			.addEventListeners(new bot())
			.setActivity(Activity.playing("with myself so you don't have to."))
			.build();
	}
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		//String crypticraft="687312985286508569";
		String testing="707304216552669225";
		Guild server=event.getGuild();
		User user=event.getAuthor(); 
		MessageChannel channel=event.getChannel();
		Member member=event.getMember();
		//MessageChannel botSpam=server.getTextChannelById("709222717588766811");	
		if (event.getAuthor().isBot()) {return;}
			String msg=normalizeText(event.getMessage());		
		if (server.getId().equals(testing)) {
			//if (event.getChannel() != botSpam) {return;}
			if (event.getMessage().getContentRaw().indexOf("!")==0) {channel.sendMessage("```command method called```").queue();;
				commands(event.getMessage().getContentRaw(),user,member,channel,server);
			} else {MessageReactions(msg,user,channel);}
		} else {
			MessageReactions(msg,user,channel);
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
	private void commands(String msg,User user,Member member,MessageChannel channel,Guild server) {
		List<Role> roles=member.getRoles();
		if (msg.equals("!hiatus") && (roles.get(0).getName().equals("member") || roles.get(1).getName().equals("member"))) {
			server.removeRoleFromMember(member, server.getRolesByName("member", true).get(0)); roles(roles,channel);
			server.addRoleToMember(member,server.getRolesByName("hiatus", true).get(0)); roles(roles,channel);
			channel.sendMessage("```role applied```").queue();
		}
		if (msg.equals("!return") && (roles.get(0).getName().equals("hiatus") || roles.get(1).getName().equals("hiatus"))) {
			server.removeRoleFromMember(member, server.getRolesByName("hiatus", true).get(0)); roles(roles,channel);
			server.addRoleToMember(member, server.getRolesByName("member", true).get(0)); roles(roles,channel);
			channel.sendMessage("```role applied```").queue();
		}
		if(msg.equals("!roles")) {
			for (int i=0;i<roles.size();i++) {
				channel.sendMessage(i+1+":`"+roles.get(i).getName()+"`").queue();
			}
		}
	}
	private void roles(List<Role> roles, MessageChannel channel) {
		for (int i=0;i<roles.size();i++) {
			channel.sendMessage(i+1+":`"+roles.get(i).getName()+"`").queue();
		}
	}
}