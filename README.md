Coverage: 34%
# Inventory Management System<a name="top"></a>

A Basic Command-line Interface(CLI) for a retail Inventory Management System.

The CLI has full CRUD functionality of Customers, Orders and Items in Inventory.

Allowing a User to Create, Read, Update and Delete - Customers, Orders and Items in the system.

The CLI System Front-End is implemented in Java with a MySQL database backend.

## Contents
* #### [Getting Started](#Getting_Started)
    * [Prerequisites](#Prerequisites)
* ##### [Installing](#Installing)
    * [Git Bash](#Git_Bash)
        * [Git Clone](#Git_Clone)
    * [IntelliJ](#IntelliJ)
        * [Open Project](#I_Open)
        * [Run Config](#I_Run)
        * [Environment Variables](#I_Env)
    * [Eclipse](#Eclipse)
        * [Open Project](#E_Open)
        * [Run Config](#E_Run)
        * [Environment Variables](#E_Env)
* ##### [Running the Tests](#Run_Tests)
    * [Unit Tests](#Unit_Tests)
    * [Integration Tests](#Integration_Tests)
    * [Coding Style Tests](#Coding_Tests)
* ##### [Deployment](#Deployment)
* ##### [Appendix](#Appendix)
    * [Built With](#Built_With)
    * [Versioning](#Versioning)
    * [Authors](#Authors)
    * [License](#License)
    * [Acknowledgments](#Acknowledgments)

# Getting Started<a name="Getting_Started"></a>

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

See deployment for notes on how to deploy the project on a live system.

### Prerequisites<a name="Prerequisites"></a>

What things you need to install the software and how to install them

```
Git
Maven
MySQL (Local or Cloud instance)
JDK
```

# Installing<a name="Installing"></a>

A step by step of how to set up a development environment.

### Git Bash<a name="Git_Bash"></a>
Navigate to a desired storage location for the project.

```
Example - cd f:/JavaProjects
```
![Git Bash CD](Assets/gitcd.png?raw=true)

#### Git Clone<a name="Git_Clone"></a>
Clone the repository.

```
 git clone https://github.com/JasonFyfe/IMS-Project.git
```
![Git Clone](Assets/gitclone.png?raw=true)

## IntelliJ<a name="IntelliJ"></a>
Instructions for opening the project with IntelliJ IDEA.

### Open Project<a name="I_Open"></a>

```
File > Open... > Navigate to the directory you cloned the repository to.

```
![Git Clone](Assets/ifile.png?raw=true)
```
Select the IMS-Starter folder > Click OK
```
![Git Clone](Assets/iopen.png?raw=true)

### Run the Project<a name="I_Run"></a>
```
From the Project Panel:
    Right-Click IMS-Starter/src/main/java/com.qa.ims/Runner
```
![Git Clone](Assets/iproject.png?raw=true)

```
Select Run 'Runner.main()' or Press Ctrl+Shift+F10
```
![Git Clone](Assets/irun.png?raw=true)

### Edit Environment Variables<a name="I_Env"></a>
On first Run you may encounter an error with the Database URL.
This section will resolve this issue.
#### Edit Configuration
```
From the top menu Select Run > Edit Configurations...
```
![Run Menu](Assets/EditConfig.png?raw=true)

#### Add Environment Variable
```
Enter DB_URL= followed by the IP address of your MySQL instance.
```
![Configurations Menu](Assets/EditConfig2.png?raw=true)

#### Alternative Method
```
Selecting the green highlighted button above opens an alternate way to add the variable.
```
![Environment Variable Menu](Assets/EditConfig3.png?raw=true)


## Eclipse<a name="Eclipse"></a>
Instructions for opening the project with IntelliJ IDEA.

### Open Project<a name="E_Open"></a>

```
TODO
```

### Run the Project<a name="E_Run"></a>
```
TODO
```

### Edit Environment Variables<a name="E_Env"></a>
On first Run you may encounter an error with the Database URL.
This section will resolve this issue.
#### Edit Configuration
```
TODO
```


End with an example of getting some data out of the system or using it for a little demo

## Running the tests<a name="Run_Tests"></a>

Explain how to run the automated tests for this system. Break down into which tests and what they do.

### Unit Tests<a name="Unit_Tests"></a>

Explain what these tests test, why and how to run them

```
Give an example
```

### Integration Tests<a name="Integration_Tests"></a>
Explain what these tests test, why and how to run them

```
Give an example
```

### Coding Style Tests<a name="Coding_Tests"></a>

Explain what these tests test and why

```
Give an example
```

## Deployment<a name="Deployment"></a>

Add additional notes about how to deploy this on a live system

## Built With<a name="Built_With"></a>

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning<a name="Versioning"></a>

We use [SemVer](http://semver.org/) for versioning.

## Authors<a name="Authors"></a>

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)

## License<a name="License"></a>

This project licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments<a name="Acknowledgments"></a>

* Hat tip to anyone whose code used
* Inspiration
* etc

[Go to the Top](#top)
