package phonedirectory;

public class Contact {
    private Integer id;
    private String middleName;
    private String firstName;
    private String lastName;
    private String phone;
    private String birth;

    public Contact(Integer id, String middleName, String firstName, String lastName, String phone, String birth) {
        this.id = id;
        this.middleName = middleName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.birth = birth;
    }

    public int getId() {
        return id;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;

    }

    public String getPhone() {
        return phone;
    }

    public String getBirth() {
        return birth;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return id + " " + lastName + " " + firstName + " " + middleName + " " + phone + " " + birth;
    }

}
