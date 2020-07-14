package Models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ACCOUNT", schema = "qlhoinghi")
public class Account {
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

    @OneToMany(mappedBy = "accountByAccountId", cascade = CascadeType.ALL)
    private Set<JoinTheConference> joinTheConferenceByAccountId;

    enum Role {
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
        this.password = password;
        this.email = email;
        this.status = status;
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
        this.password = password;
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

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", trangThai=" + status +
                ", joinTheConferenceByAccountId=" + joinTheConferenceByAccountId +
                ", role=" + role +
                '}';
    }
}
