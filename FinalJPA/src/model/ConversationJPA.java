package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the conversation database table.
 * 
 */
@Entity
@Table(name="conversation")
public class ConversationJPA implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idconversation;

	private String reciever;

	private String sender;

	//bi-directional many-to-one association to Group
    @ManyToOne
	private GroupJPA group;

	//bi-directional many-to-one association to StaffJPA
    @ManyToOne
	private StaffJPA staff;

	//bi-directional many-to-one association to Message
	@OneToMany(mappedBy="conversation",cascade = CascadeType.ALL)
	private Set<MessageJPA> messages;

    public ConversationJPA() {
    }

	public int getIdconversation() {
		return this.idconversation;
	}

	public void setIdconversation(int idconversation) {
		this.idconversation = idconversation;
	}

	public String getReciever() {
		return this.reciever;
	}

	public void setReciever(String reciever) {
		this.reciever = reciever;
	}

	public String getSender() {
		return this.sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public GroupJPA getGroup() {
		return this.group;
	}

	public void setGroup(GroupJPA group) {
		this.group = group;
	}
	
	public StaffJPA getStaffJPA() {
		return this.staff;
	}

	public void setStaffJPA(StaffJPA staff) {
		this.staff = staff;
	}
	
	public Set<MessageJPA> getMessages() {
		return this.messages;
	}

	public void setMessages(Set<MessageJPA> messages) {
		this.messages = messages;
	}
	
}