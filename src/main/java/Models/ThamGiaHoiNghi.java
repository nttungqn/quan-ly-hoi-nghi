package Models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "THAMGIAHOINGHI", schema = "qlthamgiahoinghi")
//@IdClass(ThamgiahoinghiEntityPK.class)
public class ThamGiaHoiNghi implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAHN", nullable = false)
    private int maHN;

    @Id
    @Column(name = "MATK", nullable = false)
    private int maTK;

    @Basic
    @Column(name = "TTXETDUYET", nullable = true)
    private int ttXetDuyet;

    @ManyToOne(targetEntity = Models.HoiNghi.class)
    @JoinColumn(name = "MAHN", referencedColumnName = "MAHN", nullable = false)
    private HoiNghi hoiNghiByMaHN;

    @ManyToOne(targetEntity = Models.TaiKhoan.class)
    @JoinColumn(name = "MATK", referencedColumnName = "MATK", nullable = false)
    private TaiKhoan taiKhoanByMaTK;

    public int getMaHN() {
        return maHN;
    }

    public void setMaHN(int maHN) {
        this.maHN = maHN;
    }

    public int getMaTK() {
        return maTK;
    }

    public void setMaTK(int maTK) {
        this.maTK = maTK;
    }

    public ThamGiaHoiNghi(int maHN, int maTK, int ttXetDuyet) {
        this.maHN = maHN;
        this.maTK = maTK;
        this.ttXetDuyet = ttXetDuyet;
    }

    public int getTtXetDuyet() {
        return ttXetDuyet;
    }

    public void setTtXetDuyet(int ttXetDuyet) {
        this.ttXetDuyet = ttXetDuyet;
    }

    public HoiNghi getHoiNghiByMaHN() {
        return hoiNghiByMaHN;
    }

    public void setHoiNghiByMaHN(HoiNghi hoiNghiByMaHN) {
        this.hoiNghiByMaHN = hoiNghiByMaHN;
    }

    public TaiKhoan getTaiKhoanByMaTK() {
        return taiKhoanByMaTK;
    }

    public void setTaiKhoanByMaTK(TaiKhoan taiKhoanByMaTK) {
        this.taiKhoanByMaTK = taiKhoanByMaTK;
    }

    @Override
    public String toString() {
        return "ThamGiaHoiNghi = {" +
                "maHN=" + maHN +
                ", maTK=" + maTK +
                ", ttXetDuyet=" + ttXetDuyet +
                '}';
    }
}

