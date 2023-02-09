import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        File peopleCsvFile = new File("/Users/harshgajjar/Downloads/familygraph_exercise/src/test/resources/people.csv");
        File relationshipCsvFile = new File("/Users/harshgajjar/Downloads/familygraph_exercise/src/test/resources/relationships.csv");

        ArrayList<PersonDetailsModel> peopleDetailList = new ArrayList<>();
        ArrayList<RelationshipModel> relationshipsDetailList = new ArrayList<>();
        ArrayList<PersonRelationsModel> personRelationsModels;

        String[] tempPeopleDetail;
        String[] tempRelationshipDetail;

        PersonDetailsModel personDetailsModel;//object Data from people csv file
        RelationshipModel relationshipModel;//Data from relationship csv file
        PersonRelations personRelations;//find the personRelations :
        PersonRelationsModel personRelationsModel;//show person's personRelations with each other.

        Scanner peopleCsv = new Scanner(peopleCsvFile);
        Scanner relationshipCsv = new Scanner(relationshipCsvFile);

        //transfer data from peopleCsv file into ArrayList named :peopleDetailList
        while (peopleCsv.hasNext()) {

            String personName, personEmail;

            tempPeopleDetail = peopleCsv.next().split(",");

            personName = tempPeopleDetail[0];
            personEmail = tempPeopleDetail[1];

            personDetailsModel = new PersonDetailsModel(personName, personEmail);
            peopleDetailList.add(personDetailsModel);

        }
        peopleCsv.close();


        //transfer data from relationshipsCsv file into ArrayList named :relationshipDetailList
        while (relationshipCsv.hasNext()) {

            String emailIdPerson1, relationType, emailIdPerson2;

            tempRelationshipDetail = relationshipCsv.next().split(",");

            emailIdPerson1 = tempRelationshipDetail[0];
            relationType = tempRelationshipDetail[1];
            emailIdPerson2 = tempRelationshipDetail[2];

            relationshipModel = new RelationshipModel(emailIdPerson1, relationType, emailIdPerson2);
            relationshipsDetailList.add(relationshipModel);

        }
        relationshipCsv.close();

        personRelations = new PersonRelations(peopleDetailList, relationshipsDetailList);//pass the Lists {peopleCsv,relationsCsv} into constructor.
        personRelationsModels = personRelations.getRelationships();//get mapped list .

        //represents the people in the file and their relationships with each other.
        System.out.println("\nExercise 1:represents the people in the file and their relationships with each other.");
        for (PersonRelationsModel relationsModel : personRelationsModels) {
            personRelationsModel = relationsModel;
            System.out.println(personRelationsModel.getPerson1Name() + " and " + personRelationsModel.getPerson2Name() + " are " + personRelationsModel.getTypeRelationship());
        }

        // loaded the expected number of people.
        System.out.println("\nExercise 2:loaded the expected number of people.");
        for (int i = 0; i < peopleDetailList.size(); i++) {
            personDetailsModel = peopleDetailList.get(i);
            System.out.println(personDetailsModel.getPersonName() + " = " + (i + 1));
        }

        //following people have the correct expected number of connections to other people
        System.out.println("\nExercise 3:people have the correct expected number of connections to other people");
        String[] followingPersons = {"Bob", "Jenny", "Nigel", "Alan"};
        int totalRelations;
        for (String followingPerson : followingPersons) {
            totalRelations = personRelations.getTotalRelations(followingPerson);
            System.out.println(followingPerson + " (" + totalRelations + " relationships)");
        }

        //Exercise 4:
        System.out.println("\nExercise 4: ");
        Scanner findName = new Scanner(System.in);
        System.out.print("Enter Name : ");
        String name = findName.nextLine();
        int totalMembers = personRelations.getAllFamilyMembers(name);
        System.out.println(name + " (" + totalMembers + " family members)");
    }

}

