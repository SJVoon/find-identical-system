# find-identical-system

## Problem Statement:
A list of legacy database record is generated in .csv file,  
We are required to read through the file, and print any 2 of the object that has at least 2 identical columns based on the following properties:  
- Name
- ID Number, ID Type
- DOB in string

## Analysis & Development Plan
1. Define model class to store each record from .csv
2. Define a service interface to work on compare and display
3. Define a controller level interface to integrate all the logic
4. Implement all the interfaces
5. Create the service object and run the function at main()

## Refinement on Development Plan
1. Controller level interface is named as Program, target to be executed by main() method
2. Pair data structure is introduced under util package, to store the paired model