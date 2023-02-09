public class RelationshipModel {
    private final String emailIdPerson1;
    private final String typeRelationship;
    private final String emailIdPerson2;

    public RelationshipModel(String emailIdPerson1, String typeRelationship, String emailIdPerson2) {
        this.emailIdPerson1 = emailIdPerson1;
        this.typeRelationship = typeRelationship;
        this.emailIdPerson2 = emailIdPerson2;
    }

    public String getEmailIdPerson1() {
        return emailIdPerson1;
    }

    public String getTypeRelationship() {
        return typeRelationship;
    }

    public String getEmailIdPerson2() {
        return emailIdPerson2;
    }


}
