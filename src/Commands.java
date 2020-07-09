package src;

import java.util.List;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;

public class Commands {
    static String CCHiatus="698944556351357018";
    static String CCMember="692033739873583197";
    static String testingHiatus="724904304699375669";
    static String testingMember="724904279072047134";

    public static void commandManager(String msg,User user,Member member,MessageChannel channel,Guild server) {
        if (msg.equals("HIATUS")) {hiatus(user,member,channel,server);}
        if (msg.equals("RETURN")) {returnMember(user, member, channel, server);}
		if(msg.equals("ROLES")) {roles(member, channel);}
    }

    private static void hiatus(User user, Member member, MessageChannel channel, Guild server) {
        if (server.getId().equals(bot.testing) && member.getRoles().contains(server.getRoleById(testingMember))) { // if in testing and has member
            server.removeRoleFromMember(member, server.getRoleById(testingMember));
            server.addRoleToMember(member, server.getRoleById(testingHiatus));
            channel.sendMessage("```role applied```").queue();
        }
        if (server.getId().equals(bot.crypticraft)) {
            if (member.getRoles().contains(server.getRoleById(CCMember))) { // if they are a member
                server.removeRoleFromMember(member, server.getRoleById(CCMember));
                server.addRoleToMember(member, server.getRoleById(CCHiatus));
                channel.sendMessage("```role applied```").queue();
            } else {
                channel.sendMessage("@" + user.getId() + " you must be a member to go on hiatus!").queue(); // error message
            }
        }
    }

    private static void returnMember(User user, Member member, MessageChannel channel, Guild server) {
        if (server.getId().equals(bot.testing) && member.getRoles().contains(server.getRoleById(testingHiatus))) {
            server.removeRoleFromMember(member, server.getRoleById(testingHiatus));
            server.addRoleToMember(member, server.getRoleById(testingMember));
            channel.sendMessage("```role applied```").queue();
        }
        if (server.getId().equals(bot.crypticraft)) {
            if (member.getRoles().contains(server.getRoleById(CCHiatus))) {
                server.removeRoleFromMember(member, server.getRoleById(CCHiatus));
                server.addRoleToMember(member, server.getRoleById(CCMember));
                channel.sendMessage("```role applied```");
            } else {
                channel.sendMessage("@" + user.getId() + " you must be on hiatus to rejoin!").queue();
            }
        }
    }

    private static void roles(Member member, MessageChannel channel) {
        List<Role> roles=member.getRoles();
		for (int i=0;i<roles.size();i++) {
			channel.sendMessage(i+1+":`"+roles.get(i).getName()+"`").queue();
		}
	}
}