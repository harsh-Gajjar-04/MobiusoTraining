public class PersonDetailsModel {
    private String personName;
    private String personEmailId;

    public PersonDetailsModel(String personName, String person_emailId) {
        this.personName = personName;
        this.personEmailId = person_emailId;
    }

    public String getPersonName() {
        return personName;
    }

    public String getPersonEmailId() {
        return personEmailId;
    }
}
