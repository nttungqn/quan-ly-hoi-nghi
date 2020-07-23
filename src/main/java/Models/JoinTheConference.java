package Models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "JOINTHECONFERENCE", schema = "qlhoinghi")
public class JoinTheConference implements Serializable {
    @Id
    @Column(name = "CONFID")
    private int confId;

    @Id
    @Column(name = "ACCOUNTID")
    private int accountId;

    @Basic
    @Column(name = "APPROVAL")
    private int status;

    @ManyToOne(targetEntity = Conference.class)
    @JoinColumn(name = "CONFID", referencedColumnName = "CONFID", nullable = true)
    private Conference conferenceByIdConfId;

    @ManyToOne(targetEntity = Account.class)
    @JoinColumn(name = "ACCOUNTID", referencedColumnName = "ACCOUNTID", nullable = true)
    private Account accountByAccountId;

    public JoinTheConference() {
    }

    public JoinTheConference(Conference conference, Account account){
        this.conferenceByIdConfId = conference;
        this.accountByAccountId = account;
    }

    public JoinTheConference(int confId, int accountId, int status) {
        this.confId = confId;
        this.accountId = accountId;
        this.status = status;
    }

    public int getConfId() {
        return confId;
    }

    public void setConfId(int confId) {
        this.confId = confId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Conference getConferenceByIdConfId() {
        return conferenceByIdConfId;
    }

    public void setConferenceByIdConfId(Conference conferenceByIdConfId) {
        this.conferenceByIdConfId = conferenceByIdConfId;
    }

    public Account getAccountByAccountId() {
        return accountByAccountId;
    }

    public void setAccountByAccountId(Account accountByAccountId) {
        this.accountByAccountId = accountByAccountId;
    }

    @Override
    public String toString() {
        return "JoinTheConference{" +
                "confId=" + confId +
                ", accountId=" + accountId +
                ", status=" + status +
//                ", conferenceByIdConfId=" + conferenceByIdConfId +
//                ", accountByAccountId=" + accountByAccountId +
                '}';
    }
}

