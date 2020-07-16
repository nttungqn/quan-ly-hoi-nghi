package Models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "CONFERENCE", schema = "qlhoinghi")
public class Conference implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONFID", nullable = false)
    private int confId;

    @Basic
    @Column(name = "NAME", nullable = true, length = 255)
    private String name;

    @Basic
    @Column(name = "SHORTDESC", nullable = true, length = 255)
    private String shortDesc;

    @Basic
    @Column(name = "DETAILDESC", nullable = true, length = 255)
    private String detailDesc;

    @Basic
    @Column(name = "IMAGE", nullable = true, length = 255)
    private String image;

    @Basic
    @Column(name = "STARTDATE", nullable = true)
    private LocalDate startDate;

    @Basic
    @Column(name = "ENDDATE", nullable = true)
    private LocalDate endDate;

    @Basic
    @Column(name = "PARTICIPANTS", nullable = true)
    private int participants;

    @ManyToOne(targetEntity = Place.class)
    @JoinColumn(name = "PLACE", referencedColumnName = "PLACEID", nullable = true)
    private Place place;

    @OneToMany(mappedBy = "conferenceByIdConfId", cascade = CascadeType.ALL)
    private Set<JoinTheConference> joinTheConference;

    public Conference() {
    }

    public int getConfId() {
        return confId;
    }

    public void setConfId(int confId) {
        this.confId = confId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getDetailDesc() {
        return detailDesc;
    }

    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
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
//                ", place=" + place +
//                ", joinTheConference=" + joinTheConference +
                '}';
    }
}
