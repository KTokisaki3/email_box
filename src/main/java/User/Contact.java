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

    public Contact() {}
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
        this.group = "";
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

    //装换为CSV格式的字符串
    public String toCSV() {
        StringBuilder csvString = new StringBuilder();
        csvString.append(escapeField(name)).append(",");
        csvString.append(escapeField(telephone)).append(",");
        csvString.append(escapeField(mobile)).append(",");
        csvString.append(escapeField(instantMessaging)).append(",");
        csvString.append(escapeField(email)).append(",");
        csvString.append(escapeField(homepage)).append(",");
        csvString.append(escapeField(birthday)).append(",");
        csvString.append(escapeField(photo)).append(",");
        csvString.append(escapeField(workplace)).append(",");
        csvString.append(escapeField(homeAddress)).append(",");
        csvString.append(escapeField(zipCode)).append(",");
        csvString.append(escapeField(group)).append(",");
        csvString.append(escapeField(note));
        return csvString.toString();
    }
    private String escapeField(String field) {
        // If the field contains a comma, double quotes, or new line characters, enclose it in double quotes and escape any existing double quotes
        if (field.contains(",") || field.contains("\"") || field.contains("\n")) {
            return "\"" + field.replace("\"", "\"\"") + "\"";
        }
        return field;
    }

    //转换为vCard格式的字符串
    public String toVCard() {
        StringBuilder vCard = new StringBuilder();
        vCard.append("BEGIN:VCARD\n");
        vCard.append("VERSION:3.0\n");

        if (name != null) vCard.append("FN:").append(name).append("\n");
        if (name != null) vCard.append("N:").append(name).append(";;;;\n");
        if (telephone != null) vCard.append("TEL;TYPE=HOME:").append(telephone).append("\n");
        if (mobile != null) vCard.append("TEL;TYPE=CELL:").append(mobile).append("\n");
        if (email != null) vCard.append("EMAIL:").append(email).append("\n");
        if (homepage != null) vCard.append("URL:").append(homepage).append("\n");
        if (birthday != null) vCard.append("BDAY:").append(birthday).append("\n");
        if (photo != null) vCard.append("PHOTO;TYPE=JPEG:").append(photo).append("\n");
        if (workplace != null) vCard.append("ORG:").append(workplace).append("\n");
        if (homeAddress != null) vCard.append("ADR;TYPE=HOME:").append(homeAddress.replace("\n", ";")).append("\n");
        if (zipCode != null) vCard.append("ADR;TYPE=ZIP:").append(zipCode).append("\n");
        if (group != null) vCard.append("CATEGORIES:").append(group).append("\n");
        if (note != null) vCard.append("NOTE:").append(note).append("\n");

        vCard.append("END:VCARD\n");
        return vCard.toString();
    }
}
