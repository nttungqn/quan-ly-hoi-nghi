package Models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "DIADIEM", schema = "qlhoinghi")
public class DiaDiem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MADD", nullable = false)
    private int maDD;

    @Basic
    @Column(name = "TENDD", nullable = true, length = 255)
    private String tenDD;

    @Basic
    @Column(name = "DIACHI", nullable = true, length = 255)
    private String diaChi;

    @Basic
    @Column(name = "SOLUONG", nullable = true)
    private Integer soLuong;

    @OneToMany(mappedBy = "diaDiem", cascade = CascadeType.ALL)
    private Set<HoiNghi> dsHoiNghi;

    public DiaDiem() {
    }

    public DiaDiem(String tenDD, String diaChi, Integer soLuong) {
        this.tenDD = tenDD;
        this.diaChi = diaChi;
        this.soLuong = soLuong;
    }

    public int getMaDD() {
        return maDD;
    }

    public void setMaDD(int maDD) {
        this.maDD = maDD;
    }

    public String getTenDD() {
        return tenDD;
    }

    public void setTenDD(String tenDD) {
        this.tenDD = tenDD;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Set<HoiNghi> getDsHoiNghi() {
        return dsHoiNghi;
    }

    public void setDsHoiNghi(Set<HoiNghi> dsHoiNghi) {
        this.dsHoiNghi = dsHoiNghi;
    }

    @Override
    public String toString() {
        return "DiaDiem = {" +
                "maDD=" + maDD +
                ", tenDD='" + tenDD + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", soLuong=" + soLuong +
                '}';
    }
}
