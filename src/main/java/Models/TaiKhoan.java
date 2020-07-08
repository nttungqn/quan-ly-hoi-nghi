package Models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "TAIKHOAN", schema = "qlhoinghi")
public class TaiKhoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MATK", nullable = false)
    private int maTK;

    @Basic
    @Column(name = "TEN", nullable = false, length = 255)
    private String Ten;

    @Basic
    @Column(name = "USERNAME", nullable = false, length = 255)
    private String username;

    @Basic
    @Column(name = "PASSWORD", nullable = false, length = 255)
    private String password;

    @Basic
    @Column(name = "EMAIL", nullable = true, length = 255)
    private String email;

    @Basic
    @Column(name = "TRANGTHAI", nullable = true)
    private int trangThai;

    @OneToMany(mappedBy = "taiKhoanByMaTK", cascade = CascadeType.ALL)
    private Set<ThamGiaHoiNghi> thamGiaHoiNghiByMaTK;

    enum LoaiND {
        Admin, User
    }
    @Enumerated(EnumType.STRING)
    @Column(name = "LOAIND", nullable = false)
    private LoaiND loaiND;

    public TaiKhoan() {
    }

    public TaiKhoan(String ten, String username, String password, String email, int trangThai, LoaiND loaiND) {
        Ten = ten;
        this.username = username;
        this.password = password;
        this.email = email;
        this.trangThai = trangThai;
        this.loaiND = loaiND;
    }

    public int getMaTK() {
        return maTK;
    }

    public void setMaTK(int maTK) {
        this.maTK = maTK;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public Set<ThamGiaHoiNghi> getThamGiaHoiNghiByMaTK() {
        return thamGiaHoiNghiByMaTK;
    }

    public void setThamGiaHoiNghiByMaTK(Set<ThamGiaHoiNghi> thamGiaHoiNghiByMaTK) {
        this.thamGiaHoiNghiByMaTK = thamGiaHoiNghiByMaTK;
    }

    public LoaiND getLoaiND() {
        return loaiND;
    }

    public void setLoaiND(LoaiND loaiND) {
        this.loaiND = loaiND;
    }

    @Override
    public String toString() {
        return "TaiKhoan = {" +
                "maTK=" + maTK +
                ", Ten='" + Ten + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", trangThai=" + trangThai +
                ", loaiND=" + loaiND +
                '}';
    }
}
