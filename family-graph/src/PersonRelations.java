import java.util.ArrayList;

public class PersonRelations {
    private PersonRelationsModel relationModel;

    private final String constantRelationType = "FAMILY";
    private ArrayList<PersonDetailsModel> personDetailsList;
    private ArrayList<RelationshipModel> relationshipsDetailsList;
    private ArrayList<PersonRelationsModel> personRelationshipsList = new ArrayList<>();

//   Arraylist for extended family
    private ArrayList<FamilyMembersModel> familyMembersList =new ArrayList<>();

    public PersonRelations(ArrayList<PersonDetailsModel> personDetailsList, ArrayList<RelationshipModel> relationshipsDetailsList) {
        this.personDetailsList = personDetailsList;
        this.relationshipsDetailsList = relationshipsDetailsList;

        mapRelationships();
    }//getting the data from both files for finding relation.


     ArrayList<PersonRelationsModel> getRelationships(){
        return personRelationshipsList;
     }

     private void mapRelationships(){
        for (RelationshipModel detailsCheck : relationshipsDetailsList) {
            mapRelationship(detailsCheck.getEmailIdPerson1(), detailsCheck.getEmailIdPerson2(), detailsCheck.getTypeRelationship());
        }
    }

    private void mapRelationship(String emailIdPerson1 , String emailIdPerson2, String typeRelationship){

        PersonDetailsModel personDetails;
        String person1Name = " ",person2Name = " ";

        for (PersonDetailsModel personDetailsModel : personDetailsList) {
            personDetails = personDetailsModel;
            // find first person's name from given emailId:
            if (personDetails.getPersonEmailId().equals(emailIdPerson1)) {
                person1Name = personDetails.getPersonName();
            }
            //find second person's name from given emailId:
            else if (emailIdPerson2.equals(personDetails.getPersonEmailId())) {
                person2Name = personDetails.getPersonName();
            }
        }
        relationModel =new PersonRelationsModel(person1Name,person2Name,typeRelationship);
        personRelationshipsList.add(relationModel);
    }

    int getTotalRelations(String name){
        int totalRelations = 0;
        for (PersonRelationsModel personRelationsModel : personRelationshipsList) {
            relationModel = personRelationsModel;
            if (name.equals(relationModel.getPerson1Name()) || name.equals(relationModel.getPerson2Name())) {
                totalRelations++;
            }
        }
        return totalRelations;
    }

    int getAllFamilyMembers(String name){
        int totalMembers = 1;
        familyMembersList.add(new FamilyMembersModel(name));
        ArrayList<FamilyMembersModel> tempMembersList = new ArrayList<>();

        // find the total family members from given name and store in list
        for (PersonRelationsModel personRelationsModel : personRelationshipsList) {
            relationModel = personRelationsModel;
            if ((name.equals(relationModel.getPerson1Name()) || name.equals(relationModel.getPerson2Name())) && relationModel.getTypeRelationship().equals(constantRelationType)) {
                FamilyMembersModel tempFamilyMembers;
                if ((name.equals(relationModel.getPerson1Name()))) {
                    tempFamilyMembers = new FamilyMembersModel(relationModel.getPerson2Name());
                } else {
                    tempFamilyMembers = new FamilyMembersModel(relationModel.getPerson1Name());
                }
                tempMembersList.add(tempFamilyMembers);
                familyMembersList.add(tempFamilyMembers);
                totalMembers++;
            }
        }
        //extended Family member's : relation = "FAMILY"
        for (FamilyMembersModel familyMembersModel : tempMembersList) {
            //extendedRelations from familyMembers.
            totalMembers = extendedMemberRelation(familyMembersModel.getName(), totalMembers);
        }
        return totalMembers;
    }

    private int extendedMemberRelation(String nameExtendedMember , int totalMembers ){

        ArrayList<FamilyMembersModel> extendedRelativeMembersList =new ArrayList<>();
        //find total family members from extendedMember
        for (PersonRelationsModel personRelations : personRelationshipsList) {

            if ((nameExtendedMember.equals(personRelations.getPerson1Name()) || nameExtendedMember.equals(personRelations.getPerson2Name())) &&
                    personRelations.getTypeRelationship().equals(constantRelationType)) {
                FamilyMembersModel tempFamilyMembers;
                if ((personRelations.getPerson1Name().equals(nameExtendedMember))) {
                    tempFamilyMembers = new FamilyMembersModel(personRelations.getPerson2Name());
                } else {
                    tempFamilyMembers = new FamilyMembersModel(personRelations.getPerson1Name());
                }
                extendedRelativeMembersList.add(tempFamilyMembers);
            }
        }
//       compare Member that exist in Family Members list:
        for (FamilyMembersModel familyMembersModel : extendedRelativeMembersList) {
            int flag = 0;
            for (FamilyMembersModel membersModel : familyMembersList) {
                if (familyMembersModel.getName().equals(membersModel.getName())) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                totalMembers++;
            }
        }
        return totalMembers;
    }
}

