package am.aca.phonebook.common.bean;

/**
 * Created by Roman on 9/16/2016.
 */
public class TelNumber {
    private Integer id;
    private String number;
    private PhoneType phoneType;
    private Integer user_id;

    public TelNumber(String number, PhoneType phoneType) {
        this.number = number;
        this.phoneType = phoneType;
    }

    public Integer getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public PhoneType getPhoneType() {
        return phoneType;
    }

    public Integer getUser_id() {
        return user_id;
    }

}
