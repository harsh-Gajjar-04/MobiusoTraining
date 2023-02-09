public class PersonRelationsModel {

    private  String person1Name;
    private String getPerson2Name;
    private String typeRelationship;

    public PersonRelationsModel(String person1Name, String person2Name, String typeRelationship) {
        this.person1Name = person1Name;
        this.getPerson2Name = person2Name;
        this.typeRelationship = typeRelationship;
    }

    public String getPerson1Name() {
        return person1Name;
    }

    public String getPerson2Name() {
        return getPerson2Name;
    }

    public String getTypeRelationship() {
        return typeRelationship;
    }


}
