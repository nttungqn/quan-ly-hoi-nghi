package Models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "DIADIEM", schema = "qlhoinghi")
public class Place implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MADD", nullable = false)
    private int placeId;

    @Basic
    @Column(name = "TENDD", nullable = true, length = 255)
    private String name;

    @Basic
    @Column(name = "DIACHI", nullable = true, length = 255)
    private String address;

    @Basic
    @Column(name = "SOLUONG", nullable = true)
    private Integer amount;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private Set<Conference> conferenceList;

    public Place() {
    }

    public Place(String name, String address, Integer amount) {
        this.name = name;
        this.address = address;
        this.amount = amount;
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int idPlace) {
        this.placeId = idPlace;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Set<Conference> getConferenceList() {
        return conferenceList;
    }

    public void setConferenceList(Set<Conference> conferenceList) {
        this.conferenceList = conferenceList;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.address;
    }
}
