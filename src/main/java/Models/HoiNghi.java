package Models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "HOINGHI", schema = "qlthamgiahoinghi")
public class HoiNghi implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAHN", nullable = false)
    private int maHN;

    @Basic
    @Column(name = "TENHN", nullable = true, length = 255)
    private String tenHN;

    @Basic
    @Column(name = "MOTANGANGON", nullable = true, length = 255)
    private String mtNganGon;

    @Basic
    @Column(name = "MOTACHITIET", nullable = true, length = 255)
    private String mtChiTiet;

    @Basic
    @Column(name = "ANH", nullable = true, length = 255)
    private String anh;

    @Basic
    @Column(name = "NGAYBD", nullable = true)
    private Date ngayBD;

    @Basic
    @Column(name = "NGAYKT", nullable = true)
    private Date ngayKT;

    @Basic
    @Column(name = "NGUOITHAMDU", nullable = false)
    private int nguoiThamDu;

    @ManyToOne(targetEntity = Models.DiaDiem.class)
    @JoinColumn(name = "DIADIEM", referencedColumnName = "MADD", nullable = false)
    private DiaDiem diaDiem;

    @OneToMany(mappedBy = "hoiNghiByMaHN", cascade = CascadeType.ALL)
    private Set<ThamGiaHoiNghi> thamGiaHoiNghi;

    public HoiNghi(String tenHN, String mtNganGon, String mtChiTiet, String anh, Date ngayBD, Date ngayKT, int nguoiThamDu, DiaDiem diaDiem) {
        this.tenHN = tenHN;
        this.mtNganGon = mtNganGon;
        this.mtChiTiet = mtChiTiet;
        this.anh = anh;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
        this.nguoiThamDu = nguoiThamDu;
        this.diaDiem = diaDiem;
    }

    public int getMaHN() {
        return maHN;
    }

    public void setMaHN(int maHN) {
        this.maHN = maHN;
    }

    public String getTenHN() {
        return tenHN;
    }

    public void setTenHN(String tenHN) {
        this.tenHN = tenHN;
    }

    public String getMtNganGon() {
        return mtNganGon;
    }

    public void setMtNganGon(String mtNganGon) {
        this.mtNganGon = mtNganGon;
    }

    public String getMtChiTiet() {
        return mtChiTiet;
    }

    public void setMtChiTiet(String mtChiTiet) {
        this.mtChiTiet = mtChiTiet;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public Date getNgayBD() {
        return ngayBD;
    }

    public void setNgayBD(Date ngayBD) {
        this.ngayBD = ngayBD;
    }

    public Date getNgayKT() {
        return ngayKT;
    }

    public void setNgayKT(Date ngayKT) {
        this.ngayKT = ngayKT;
    }

    public int getNguoiThamDu() {
        return nguoiThamDu;
    }

    public void setNguoiThamDu(int nguoiThamDu) {
        this.nguoiThamDu = nguoiThamDu;
    }

    public DiaDiem getDiaDiem() {
        return diaDiem;
    }

    public void setDiaDiem(DiaDiem diaDiem) {
        this.diaDiem = diaDiem;
    }

    public Set<ThamGiaHoiNghi> getThamGiaHoiNghi() {
        return thamGiaHoiNghi;
    }

    public void setThamGiaHoiNghi(Set<ThamGiaHoiNghi> thamGiaHoiNghi) {
        this.thamGiaHoiNghi = thamGiaHoiNghi;
    }

    @Override
    public String toString() {
        return "HoiNghi = {" +
                "maHN=" + maHN +
                ", tenHN='" + tenHN + '\'' +
                ", mtNganGon='" + mtNganGon + '\'' +
                ", mtChiTiet='" + mtChiTiet + '\'' +
                ", anh='" + anh + '\'' +
                ", ngayBD=" + ngayBD +
                ", ngayKT=" + ngayKT +
                ", nguoiThamDu=" + nguoiThamDu +
                ", diaDiem=" + diaDiem +
                '}';
    }
}
