package Models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "HOINGHI", schema = "qlhoinghi")
public class Conference implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAHN", nullable = false)
    private int confId;

    @Basic
    @Column(name = "TENHN", nullable = true, length = 255)
    private String name;

    @Basic
    @Column(name = "MOTANGANGON", nullable = true, length = 255)
    private String shortDesc;

    @Basic
    @Column(name = "MOTACHITIET", nullable = true, length = 255)
    private String detailDesc;

    @Basic
    @Column(name = "ANH", nullable = true, length = 255)
    private String image;

    @Basic
    @Column(name = "NGAYBD", nullable = true)
    private LocalDate startDate;

    @Basic
    @Column(name = "NGAYKT", nullable = true)
    private LocalDate endDate;

    @Basic
    @Column(name = "SLNGTHAMDU", nullable = false)
    private int participants;

    @ManyToOne(targetEntity = Place.class)
    @JoinColumn(name = "DIADIEM", referencedColumnName = "MADD", nullable = false)
    private Place place;

    @OneToMany(mappedBy = "conferenceByIdConfId", cascade = CascadeType.ALL)
    private Set<JoinTheConference> joinTheConference;

    public Conference() {
    }

    public Conference(String name, String shortDesc, String detailDesc, String image, LocalDate startDate, LocalDate endDate, int participants) {
        this.name = name;
        this.shortDesc = shortDesc;
        this.detailDesc = detailDesc;
        this.image = image;
        this.startDate = startDate;
        this.endDate = endDate;
        this.participants = participants;
    }

    public int getConfId() {
        return confId;
    }

    public void setConfId(int maHN) {
        this.confId = maHN;
    }

    public String getName() {
        return name;
    }

    public void setName(String tenHN) {
        this.name = tenHN;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String mtNganGon) {
        this.shortDesc = mtNganGon;
    }

    public String getDetailDesc() {
        return detailDesc;
    }

    public void setDetailDesc(String mtChiTiet) {
        this.detailDesc = mtChiTiet;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String anh) {
        this.image = anh;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate ngayBD) {
        this.startDate = ngayBD;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate ngayKT) {
        this.endDate = ngayKT;
    }

    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int slngThamDu) {
        this.participants = slngThamDu;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Set<JoinTheConference> getJoinTheConference() {
        return joinTheConference;
    }

    public void setJoinTheConference(Set<JoinTheConference> joinTheConference) {
        this.joinTheConference = joinTheConference;
    }

    @Override
    public String toString() {
        return "Conference{" +
                "confId=" + confId +
                ", name='" + name + '\'' +
                ", shortDesc='" + shortDesc + '\'' +
                ", detailDesc='" + detailDesc + '\'' +
                ", image='" + image + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", participants=" + participants +
                ", place=" + place +
                ", joinTheConference=" + joinTheConference +
                '}';
    }
}
