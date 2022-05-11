package ddss.catalog.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "catalog_user")
@Getter
@Setter
public class CatalogUser implements UserDetails {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "username", nullable = false)
    @Length(min = 5, max = 64)
    private String username;
    @Column(name = "password", nullable = false)
    @Length(min = 5, max = 64)
    private String password;
    @Column(name = "about")
    @Length(max = 256)
    private String about;
    @Column(name = "ip_address", nullable = false)
    @Length(max = 15)
    private String ipAddress;
    @Column(name = "port", nullable = false)
    private short port;
    @Column(name = "is_storage", nullable = false)
    private boolean isStorage;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "catalogUser")
    @ToString.Exclude
    @JsonIgnore
    private List<CatalogRecord> catalogRecords;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "catalogUser")
    @ToString.Exclude
    @JsonIgnore
    private List<CatalogWithStorageRecord> catalogWithStorageRecords;

    public CatalogUser() {
    }

    public CatalogUser(String username, String passwordEncoded, String about,
                       String ipAddress, short port, boolean isStorage) {
        this.username = username;
        this.password = passwordEncoded;
        this.about = about;
        this.ipAddress = ipAddress;
        this.port = port;
        this.isStorage = isStorage;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public String getUsername() {
        return username;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_CATALOG_USER"));
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
