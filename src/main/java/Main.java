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

    public String prefix = "!";
    public DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    public Date date = new Date();

    public static void main(String[] args) throws LoginException, IOException {
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        String token = getToken();
        builder.setToken(token);
        builder.addEventListener(new Main());
        builder.buildAsync();
    }

    public static String getToken() throws IOException {
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
        //Server message
        System.out.println("Received a message from " +
                event.getAuthor().getName() + ": " +
                event.getMessage().getContentDisplay()
        );

        //Change .equals("String") to trigger word
        if (event.getMessage().getContentRaw().equals(prefix + "vote")) {
        //Message being shown after keyword in sendMessage("String").queue()
            event.getChannel().sendMessage("Kah Kah! It is " + dateFormat.format(date) +
                    ". Seems like a perfect time to vote! " +
                    "https://clanlist.io/vote/CNTO" + " and upvote on Reddit! " +
                    "https://www.reddit.com/r/FindAUnit/").queue();
        }

        if (event.getMessage().getContentRaw().equals(prefix + "twitter")) {
            event.getChannel().sendMessage("Twitter is being managed by Shails!" + System.lineSeparator() +
                    "https://twitter.com/carpenoctemarma"  +
                    " check it out! Kah Kah!").queue();
        }

        if (event.getMessage().getContentRaw().equals(prefix + "help")) {
            event.getChannel().sendMessage
                    (
                    prefix + "vote " + System.lineSeparator() + "*Shows the voting links*" + System.lineSeparator() + "" + System.lineSeparator() +
                         prefix + "twitter " + System.lineSeparator() + "*Shows the CNTO twitter channel*" + System.lineSeparator() + "" + System.lineSeparator()
                    ).queue();
        }


        //Ignore if message comes from another bot.
        if (event.getAuthor().isBot()) {
            return;
        }
    }
}
