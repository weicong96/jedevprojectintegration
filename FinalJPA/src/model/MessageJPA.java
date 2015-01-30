package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the message database table.
 * 
 */
@Entity
@Table(name="message")
public class MessageJPA implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idmessage;

    @Temporal( TemporalType.DATE)
	private Date messageDate;

	private String messageDetails;

	private String messageTime;

	//bi-directional many-to-one association to Conversation
    @ManyToOne(cascade = CascadeType.ALL)
	private ConversationJPA conversation;

    public MessageJPA() {
    }

	public int getIdmessage() {
		return this.idmessage;
	}

	public void setIdmessage(int idmessage) {
		this.idmessage = idmessage;
	}

	public Date getMessageDate() {
		return this.messageDate;
	}

	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}

	public String getMessageDetails() {
		return this.messageDetails;
	}

	public void setMessageDetails(String messageDetails) {
		this.messageDetails = messageDetails;
	}

	public String getMessageTime() {
		return this.messageTime;
	}

	public void setMessageTime(String messageTime) {
		this.messageTime = messageTime;
	}

	public ConversationJPA getConversation() {
		return this.conversation;
	}

	public void setConversation(ConversationJPA conversation) {
		this.conversation = conversation;
	}
	
}