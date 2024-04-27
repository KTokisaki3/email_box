package User;

/**
 * @ClassName : Contact
 * @Description :联系人
 * @Author : WL
 * @Date : 2024-04-27 15:46
 */
public class Contact {
    private String name;//姓名
    private String telephone;//电话号码
    private String mobile;//手机
    private String instantMessaging;//即时通讯工具及号码
    private String email;//电子邮箱
    private String homepage;//个人主页
    private String birthday;//生日
    private String photo;//相片
    private String workplace;//工作单位
    private String homeAddress;//家庭地址
    private String zipCode;//邮编
    private String group;//所属组
    private String note;//备注

    // 构造函数
    public Contact(String name, String telephone, String mobile, String instantMessaging, String email, String homepage,
                   String birthday, String photo, String workplace, String homeAddress, String zipCode, String note) {
        this.name = name;
        this.telephone = telephone;
        this.mobile = mobile;
        this.instantMessaging = instantMessaging;
        this.email = email;
        this.homepage = homepage;
        this.birthday = birthday;
        this.photo = photo;
        this.workplace = workplace;
        this.homeAddress = homeAddress;
        this.zipCode = zipCode;
        this.group = null;
        this.note = note;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getInstantMessaging() {
        return instantMessaging;
    }

    public void setInstantMessaging(String instantMessaging) {
        this.instantMessaging = instantMessaging;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    // toString 方法
    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                ", mobile='" + mobile + '\'' +
                ", instantMessaging='" + instantMessaging + '\'' +
                ", email='" + email + '\'' +
                ", homepage='" + homepage + '\'' +
                ", birthday='" + birthday + '\'' +
                ", photo='" + photo + '\'' +
                ", workplace='" + workplace + '\'' +
                ", homeAddress='" + homeAddress + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", group='" + group + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
