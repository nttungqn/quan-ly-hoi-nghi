package Models;

import Utils.HashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Base64;
import java.util.Set;

@Entity
@Table(name = "ACCOUNT", schema = "qlhoinghi")
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNTID", nullable = false)
    private int accountId;

    @Basic
    @Column(name = "NAME", nullable = false, length = 255)
    private String name;

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
    @Column(name = "STATUS", nullable = true)
    private int status;

    @Basic
    @Column(name = "SALT", nullable = true)
    private byte[] salt;

    @OneToMany(mappedBy = "accountByAccountId")
    private Set<JoinTheConference> joinTheConferenceByAccountId;

    public enum Role {
        Admin, User
    }
    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", nullable = false)
    private Role role;

    public Account() {
    }

    public Account(String name, String username, String password, String email, int status, Role role) {
        this.name = name;
        this.username = username;
        try {
            this.salt = HashCode.getSalt();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        this.password = HashCode.getSecurePassword(password, getSalt());
        this.email = email;
        this.status = status;
        this.role = role;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        try {
            this.salt = HashCode.getSalt();
            System.out.println(Base64.getEncoder().encodeToString(this.salt));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        this.password = HashCode.getSecurePassword(password, this.salt);
        System.out.println(this.password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Set<JoinTheConference> getJoinTheConferenceByAccountId() {
        return joinTheConferenceByAccountId;
    }

    public void setJoinTheConferenceByAccountId(Set<JoinTheConference> joinTheConferenceByAccountId) {
        this.joinTheConferenceByAccountId = joinTheConferenceByAccountId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = Role.valueOf(role);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
//                ", joinTheConferenceByAccountId=" + joinTheConferenceByAccountId +
                ", role=" + role +
                '}';
    }

}
