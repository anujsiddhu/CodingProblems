package com.aks.code.systemdesign.mailbox;

import java.util.List;

// https://userweb.cs.txstate.edu/~js236/cs3354/oodcasestudy.pdf
public interface MailBoxSystem {
	List<Mail> retrieveMessages(String mailbox);

	boolean sendMessage(String mailbox, Mail mail);

	void purgeMessage(String mailbox, int days);
}

// CRC Card process: Walkthrough Leave a Message
// 1 User dials extension. Telephone sends number to Connection
// (Add collaborator Connection to Telephone)
// 2 Connection asks MailSystem to find matching Mailbox
// 3 Connection asks Mailbox for greeting
// (Add responsibility "manage greeting" to Mailbox,
// add collaborator Mailbox to Connection)
// 4 Connection asks Telephone to play greeting
// 5 User speaks message. Telephone asks Connection to record it.
// (Add responsibility "record voice input" to Connection)
// 6 User hangs up. Telephone notifies Connection.
// 7 Connection constructs Message
// (Add card for Message class (we already did this),
// add collaborator Message to Connection)
// 8 Connection adds Message to Mailbox

/***********************************************************/
// CRC Card process:
// Walkthrough Retrieve Messages
// 1 User types in passcode. Telephone notifies Connection
// 2 Connection asks Mailbox to check passcode.
// (Add responsibility "manage passcode" to Mailbox)
// 3 Connection sets current mailbox and asks Telephone to speak menu
// 4 User selects "retrieve messages". Telephone passes key to Connection
// 5 Connection asks Telephone to speak menu
// 6 User selects "listen to current message". Telephone passes key to
// Connection
// 7 Connection gets first message from current mailbox.
// (Add "retrieve messages" to responsibility of Mailbox).
// 8 Connection asks Telephone to speak message
// 9 Connection asks Telephone to speak menu
// 10 User selects "save current message". Telephone passes key to Connection
// 11 Connection tells Mailbox to save message
// (Modify responsibility of Mailbox to "retrieve,save,delete messages")
// 12 Connection asks Telephone to speak menu