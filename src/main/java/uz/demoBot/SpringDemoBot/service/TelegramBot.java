package uz.demoBot.SpringDemoBot.service;

import uz.demoBot.SpringDemoBot.config.BotConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {

    final BotConfig config;


    public TelegramBot(BotConfig config) {
        this.config = config;
//        List<BotCommand> listofCommands = new ArrayList<>();
//        listofCommands.add(new BotCommand("/start", "get a welcome message"));
//        listofCommands.add(new BotCommand("/mydata", "get your data stored"));
//        listofCommands.add(new BotCommand("/deletedata", "delete my data"));
//        listofCommands.add(new BotCommand("/help", "info how to use this bot"));
//        listofCommands.add(new BotCommand("/settings", "set your preferences"));
//        try {
//            this.execute(new SetMyCommands(listofCommands, new BotCommandScopeDefault(), null));
//        } catch (TelegramApiException e) {
//            log.error("Error setting bot's command list: " + e.getMessage());
//        }
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();


            switch (messageText) {
                case "/start":
                    try {
                        start(chatId);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                    break;
            }
        }
    }

    public void start(Long chat) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("hello");
        sendMessage.setChatId(String.valueOf(chat));
        execute(sendMessage);
    }
}
