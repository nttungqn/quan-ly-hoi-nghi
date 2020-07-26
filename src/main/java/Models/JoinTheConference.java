package Models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "JOINTHECONFERENCE", schema = "qlhoinghi")
public class JoinTheConference implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "JOINID", nullable = false)
    private int joinId;

    @Basic
    @Column(name = "APPROVAL")
    private int status;

    @ManyToOne(targetEntity = Conference.class)
    @JoinColumn(name = "CONFID", referencedColumnName = "CONFID", nullable = true)
    private Conference conferenceByIdConfId;

    @ManyToOne(targetEntity = Account.class, optional = false)
    @JoinColumn(name = "ACCOUNTID", referencedColumnName = "ACCOUNTID", nullable = true)
    private Account accountByAccountId;

    public JoinTheConference() {
    }

    public JoinTheConference(Conference conference, Account account, int status){
        this.conferenceByIdConfId = conference;
        this.accountByAccountId = account;
        this.status = status;
    }

    public int getJoinId() {
        return joinId;
    }

    public void setJoinId(int joinId) {
        this.joinId = joinId;
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
                "confId=" + conferenceByIdConfId.getConfId() +
                ", accountId=" + accountByAccountId.getAccountId() +
                ", status=" + status +
//                ", conferenceByIdConfId=" + conferenceByIdConfId +
//                ", accountByAccountId=" + accountByAccountId +
                '}';
    }
}

