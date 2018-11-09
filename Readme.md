# Address Registry App

## Assumption
* No Logger Used.
* Used JUnit ,Mokito  for testing
* Seperate source for Integration test.
* Used Gradle for dependency management
* Not tested Classes used as UI component of Application. Example AddressRegistryApp.java and AddressBookRegistryService.java


## Overall Idea
* Used HashMap and Sets to save address books and contacts.
* Used a concept of Index to separate contacts in different list based on First Letter of Name.
* For large Contact list search will be faster as it will search only relevant list for contacts.

# Instructions
* Gradle Wrapper present .So no need to install gradle
* To build project run 
```
gradlew.bat build test integrationTest
```
or
```
./gradlew build test integrationTest
```
* To build jar
./graldew.bat jar

* To run the jar.
```
In project Directory
java -jar build\lib\AddressBookRegistryApp.jar
```

