package com.aks.code.systemdesign.mailbox;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SimpleMailBoxSystem implements MailBoxSystem {
	Map<String, MailBox> mailBox;

	public SimpleMailBoxSystem() {
		mailBox = new HashMap<>();
	}

	@Override
	public List<Mail> retrieveMessages(String mailbox) {
		return mailBox.getOrDefault(mailbox, new MailBox()).mails();
	}

	@Override
	public boolean sendMessage(String mailbox, Mail mail) {
		mailBox.putIfAbsent(mailbox, new MailBox());
		mailBox.get(mailbox).addMail(mail);
		return true;
	}

	@Override
	public void purgeMessage(String mailbox, int days) {
		LocalDate now = LocalDate.now();
		Iterator<Mail> itr = retrieveMessages(mailbox).iterator();
		while (itr.hasNext()) {
			Mail mail = itr.next();
			int diff = (int) ChronoUnit.DAYS.between(mail.time.toLocalDate(), now);
			if (diff >= days) {
				itr.remove();
			} else {
				break;
			}
		}
	}
}
