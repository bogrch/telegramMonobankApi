package com.bogrych.bot;

import com.bogrych.service.MonobankService;
import com.bogrych.utils.Utils;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class BogrychBot {

	@Autowired
	MonobankService monobankService;

	@Value("${telegram.bot.token}")
	private String token;

	@PostConstruct
	public void init() {


		var bot = new TelegramBot(token);
		bot.setUpdatesListener(updates -> {
			updates.forEach(update -> {
				if(update.message() == null) return;
				var message = update.message().text();
				if(message.contains("/rate")) {
					long chatId = update.message().chat().id();
					bot.execute(new SendMessage(chatId, monobankService.getSpecificRate(message.substring(6))));
				}
				if(message.contains("october")) {
					long chatId = update.message().chat().id();
					bot.execute(new SendMessage(chatId, Utils.convertListToString(monobankService.getOctoberStatement())));
				}
				if(message.contains("december")) {
					long chatId = update.message().chat().id();
					bot.execute(new SendMessage(chatId, Utils.convertListToString(monobankService.getDecemberStatement())));
				}
				if(message.contains("november")) {
					long chatId = update.message().chat().id();
					bot.execute(new SendMessage(chatId, Utils.convertListToString(monobankService.getNovemberStatement())));
				}
			});
			return UpdatesListener.CONFIRMED_UPDATES_ALL;
		});
	}
}
