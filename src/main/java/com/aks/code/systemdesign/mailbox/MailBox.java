package com.aks.code.systemdesign.mailbox;

import java.util.LinkedList;
import java.util.List;

public class MailBox {
	private final List<Mail> mails;

	public MailBox() {
		mails = new LinkedList<>();
	}

	List<Mail> mails() {
		return mails;
	}

	void addMail(Mail mail) {
		mails.add(mail);
	}
}
