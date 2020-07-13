package Models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "THAMGIAHOINGHI", schema = "qlhoinghi")
//@IdClass(ThamgiahoinghiEntityPK.class)
public class JoinTheConference implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAHN", nullable = false)
    private int confId;

    @Id
    @Column(name = "MATK", nullable = false)
    private int accountId;

    @Basic
    @Column(name = "TTXETDUYET", nullable = true)
    private int status;

    @ManyToOne(targetEntity = Conference.class)
    @JoinColumn(name = "MAHN", referencedColumnName = "MAHN", nullable = false)
    private Conference conferenceByIdConfId;

    @ManyToOne(targetEntity = Account.class)
    @JoinColumn(name = "MATK", referencedColumnName = "MATK", nullable = false)
    private Account accountByAccountId;

    public int getConfId() {
        return confId;
    }

    public void setConfId(int maHN) {
        this.confId = maHN;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int maTK) {
        this.accountId = maTK;
    }

    public JoinTheConference() {
    }

    public JoinTheConference(int confId, int accountId, int status) {
        this.confId = confId;
        this.accountId = accountId;
        this.status = status;
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
        return "ThamGiaHoiNghi = {" +
                "maHN=" + confId +
                ", maTK=" + accountId +
                ", ttXetDuyet=" + status +
                '}';
    }
}

