The Robot Problem
==========


# Problem:

A robot manipulates containers in a storage room. The containers can be stacked on top of each other and each container has a name.

The robot can receive commands like:
- move b1 b2 - which means move the container named b1 on top of the container named b2
- fill b 50 - which means fill container b with 50l of fluid.
A container can be moved on top of a destination container only if there isn't already another container on top of the destination. Therefore any container to be moved has to be freed first by moving the containers on top of it.

The containers can be filled through an opening at the top of the container so they cannot be filled if there's already another container set on top of them.

The space on the floor is limited and the containers can be stacked up to a specified limit.

The robot can receive UNDO and REDO commands which refer to previous commands but not the  intermediary movements involved by the previous move and fill commands (i.e. if one move command involves multiple moves they should not be considered individually for each undo).


# Requirements:

1.	**Developers**: write the class structures and the code that implements the items and behaviors in the problem. 
2.	**Architects/Designers**: Use either Visual Paradigm or ArgoUML to provide the following elements that model the problem (limit the diagrams to just the one mentioned for each category): use case modeling (use case diagram), structural modeling (class diagram), behavioral modeling (sequence diagram and, optionally, activity diagram).


# Assumptions

- There is a special container name `floor`. It represents floor. `move b10 floor` means move container with name `b10` to the floor.
- There is a special container name `none`. It represents missing container.
- When issuing command `move source dest`, both containers have to be present in the storage room.
- When issuing command `move source floor`, container `source` is created/added if it does not exist.
- When issuing command `fill dest fluid` the `dest` container has to be present in the storage room.
- Operations `undo` and `redo` will process any number of consecutive `move` commands but only one `fill` command.
- For simplicity checks on the capacity of container and if it can accept fluid content are not performed. Size of the containers is considered to be unlimited.


# Comments

- JUnit 4 was used for tests instead of new JUnit 5 which GA release is available since September, 2017. The decision was based on preexisting familiarity with JUnit 4.
- Although the test coverage is 100% of lines not all representative test cases were added and probably some interesting edge cases are missing.
- Sample of UML diagrams is located in `uml_diagrams` folder.
