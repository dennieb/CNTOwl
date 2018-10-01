import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;

public class Main extends ListenerAdapter {

    private String prefix = "!";
    private DateFormat dateFormat = new SimpleDateFormat("/dd/MM/yyyy HH:mm");
    private Date date = new Date();

    private String vote =       "Kah Kah! It is " + dateFormat.format(date) +
                                ". Seems like a perfect time to vote! " +
                                "https://clanlist.io/vote/CNTO" + " and upvote on Reddit! " +
                                "https://www.reddit.com/r/FindAUnit/";

    private String kah =        "Kah!";

    private String twitter =    "Twitter is being managed by Shails!" +
                                System.lineSeparator() +
                                "https://twitter.com/carpenoctemarma"  +
                                " check it out! Kah Kah!";

    private String steam =    "Steam is being managed by Hadassa!" +
                                System.lineSeparator() +
                                "https://steamcommunity.com/groups/CNTO"  +
                                " check it out! Kah Kah!";

    private String units =    "BI units is being managed by Churizo!" +
                                System.lineSeparator() +
                                "https://steamcommunity.com/groups/CNTO"  +
                                " check it out! Kah Kah!";

    private String reddit =    "Reddit is being managed by Racoon!" +
                                System.lineSeparator() +
                                "https://www.reddit.com/r/FindAUnit/"  +
                                " check it out! Kah Kah!";

    public static void main(String[] args) throws LoginException, IOException {
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        String token = getToken();
        builder.setToken(token);
        builder.addEventListener(new Main());
        builder.buildAsync();
    }

    private static String getToken() throws IOException {
        try {
            File file = new File("config.properties");
            FileInputStream fileInput = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fileInput);
            fileInput.close();

            Enumeration enuKeys = properties.keys();
            while (enuKeys.hasMoreElements()) {
                String key = (String) enuKeys.nextElement();
                String value = properties.getProperty(key);
                return value;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Its not working :(";
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        //Server message here
        System.out.println("Received a message from " +
                event.getAuthor().getName() + ": " +
                event.getMessage().getContentDisplay()
        );

        //Change .equals("String") to trigger word
        if (event.getMessage().getContentRaw().equals(prefix + "vote")) {
        //Message being shown after keyword in sendMessage("String").queue()
            event.getChannel().sendMessage(vote).queue();
        }

        if (event.getMessage().getContentRaw().equals(prefix + "twitter")) {
            event.getChannel().sendMessage(twitter).queue();
        }

        if (event.getMessage().getContentRaw().equals(prefix + "steam")) {
            event.getChannel().sendMessage(steam).queue();
        }

        if (event.getMessage().getContentRaw().equals(prefix + "units")) {
            event.getChannel().sendMessage(units).queue();
        }

        if (event.getMessage().getContentRaw().equals(prefix + "reddit")) {
            event.getChannel().sendMessage(reddit).queue();
        }

        if (event.getMessage().getContentRaw().equals(prefix + "kah")) {
            event.getChannel().sendMessage(kah).queue();
        }

        if (event.getMessage().getContentRaw().equals(prefix + "help")) {
            event.getChannel().sendMessage
                    (
                    prefix + "vote " + System.lineSeparator() + "*Shows the voting links*" + System.lineSeparator() + "" + System.lineSeparator() +
                         prefix + "twitter " + System.lineSeparator() + "*Shows the CNTO twitter channel*" + System.lineSeparator() + "" + System.lineSeparator() +
                         prefix + "steam " + System.lineSeparator() + "*Shows the CNTO steam channel*" + System.lineSeparator() + "" + System.lineSeparator() +
                         prefix + "units " + System.lineSeparator() + "*Shows the CNTO Bohemia Interactive Units channel*" + System.lineSeparator() + "" + System.lineSeparator() +
                         prefix + "reddit " + System.lineSeparator() + "*Shows the FindAUnit reddit channel*" + System.lineSeparator() + "" + System.lineSeparator() +
                         prefix + "kah " + System.lineSeparator() + "*Kah!*" + System.lineSeparator() + "" + System.lineSeparator()
                    ).queue();
        }

        //Ignore if message request comes from another/this bot.
        if (event.getAuthor().isBot()) {
            return;
        }
    }
}
