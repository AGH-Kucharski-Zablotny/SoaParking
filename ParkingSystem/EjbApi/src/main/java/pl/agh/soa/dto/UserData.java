package pl.agh.soa.dto;

import javax.persistence.*;

@Entity
@Table(name = "Users")
@Access(AccessType.FIELD)
public class UserData extends AbstractDTO
{
    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private int id;

    @Column(name = "Login", unique = true, nullable = false)
    private String login;

    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Surname", nullable = false)
    private String surname;

    @Column(name = "Role", nullable = false)
    private String role;

    @Column(name = "Region")
    private int region;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public int getRegion()
    {
        return region;
    }

    public void setRegion(int region)
    {
        this.region = region;
    }
}
